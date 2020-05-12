package com.github.juliakas.persistence;

import com.github.juliakas.entities.Company;
import com.github.juliakas.entities.Employee;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@ApplicationScoped
@Alternative
@Named
public class EmployeesDAOTest implements IEmployeesDAO {

    Collection<Employee> database;

    @Inject
    CompaniesDAO compDao;

    @PostConstruct
    public void init() {
        database = new ArrayList<>();
        Company comp1 = compDao.get(1);
        Company comp2 = compDao.get(3);
        Company comp3 = compDao.get(4);

        Employee manager1 = buildEmployee(1, "Testas1", "Testukas1", comp1, null);
        Employee manager2 = buildEmployee(2, "Testas2", "Testukas2", comp1, manager1);
        Employee manager3 = buildEmployee(3, "Testas3", "Testukas3", comp1, manager1);
        Employee manager4 = buildEmployee(4, "Testas4", "Testukas4", comp1, manager2);

        database = new ArrayList<>(Arrays.asList(
                manager1, manager2, manager3, manager4,
                buildEmployee(5, "Testas5", "Testukas5", comp1, manager2),
                buildEmployee(6, "Testas6", "Testukas6", comp1, manager2),
                buildEmployee(7, "Testas7", "Testukas7", comp1, manager3),
                buildEmployee(8, "Testas8", "Testukas8", comp1, manager4),
                buildEmployee(9, "Testas9", "Testukas9", comp2, null),
                buildEmployee(10, "Testas10", "Testukas10", comp2, null),
                buildEmployee(11, "Testas11", "Testukas11", comp2, null),
                buildEmployee(12, "Testas12", "Testukas12", comp3, null)));

        manager1.setSubordinates(findSubordinates(database, manager1));
        manager2.setSubordinates(findSubordinates(database, manager2));
        manager3.setSubordinates(findSubordinates(database, manager3));
        manager4.setSubordinates(findSubordinates(database, manager4));
    }

    private Collection<Employee> findSubordinates(Collection<Employee> db, Employee manager) {
        return db.stream().filter(sub -> sub.getManager() == manager).collect(Collectors.toList());
    }

    private Employee buildEmployee(int empId, String firstName, String lastName, Company comp, Employee manager) {
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setCompany(comp);
        emp.setManager(manager);
        emp.setSubordinates(new ArrayList<>());
        return emp;
    }

    public Collection<Employee> getAll() {
        return database;
    }

    public Employee get(long empId) {
        return database.stream().filter(emp -> emp.getEmpId() == empId).findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public Employee getForUpdate(long empId) {
        return get(empId);
    }

    public Employee insert(Employee emp) {
        return database.add(emp) ? emp : null;
    }

    public Employee insertOrUpdate(Employee emp) {
        Employee empToUpdate = database.stream()
                .filter(empDb -> empDb.getEmpId() == emp.getEmpId())
                .findFirst().orElse(null);
        if (empToUpdate == null) {
            database.add(emp);
        } else {
            Collections.replaceAll(new ArrayList<>(database), empToUpdate, emp);
        }
        return emp;
    }

    public void delete(Employee emp) {
        Employee manager = emp.getManager();
        database.removeIf(empDb -> empDb.getEmpId() == emp.getEmpId());
        if(manager != null) {
            manager.setSubordinates(findSubordinates(database, manager));
        }
    }
}