package com.github.juliakas.persistence;

import com.github.juliakas.entities.Employee;
import org.hibernate.LockMode;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.RollbackException;
import java.util.Collection;

@Default
@ApplicationScoped
@Named
public class EmployeesDAO implements IEmployeesDAO {

    @Inject
    private EntityManager em;

    public Collection<Employee> getAll() {
        return em.createNamedQuery("Employees.getAll", Employee.class).getResultList();
    }

    public Employee get(long empId) {
        return em.find(Employee.class, empId);
    }

    public Employee getForUpdate(long empId) {
        return em.find(Employee.class, LockMode.OPTIMISTIC_FORCE_INCREMENT);
    }

    public Employee insert(Employee emp) {
        em.persist(emp);
        return emp;
    }

    public Employee insertOrUpdate(Employee emp) throws RollbackException {
        return em.merge(emp);
    }

    public void delete(Employee emp) {
        em.remove(emp);
    }
}
