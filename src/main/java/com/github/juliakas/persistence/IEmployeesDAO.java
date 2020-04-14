package com.github.juliakas.persistence;

import com.github.juliakas.entities.Employee;

import javax.transaction.Transactional;
import java.util.Collection;

public interface IEmployeesDAO {
    @Transactional
    Collection<Employee> getAll();

    @Transactional
    Employee get(long empId);

    @Transactional
    Employee insert(Employee emp);

    @Transactional
    Employee insertOrUpdate(Employee emp);

    @Transactional
    void delete(Employee emp);
}
