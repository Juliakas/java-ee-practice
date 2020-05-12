package com.github.juliakas.interceptors;

import com.github.juliakas.entities.Employee;
import com.github.juliakas.persistence.IEmployeesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;

@Decorator
public abstract class EmployeesFilter implements IEmployeesDAO {

    @Inject @Delegate @Any
    private IEmployeesDAO employeesDAO;

    @Override
    public Collection<Employee> getAll() {
        Collection<Employee> employees = employeesDAO.getAll();
        return employees.stream().filter(emp -> emp.getEmpId() > 5).collect(Collectors.toList());
    }
}
