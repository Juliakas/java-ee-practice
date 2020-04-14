package com.github.juliakas.mybatis;

import com.github.juliakas.mybatis.model.Company;
import com.github.juliakas.mybatis.model.Employee;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntityCaster {
    public Employee castToMyBatis(com.github.juliakas.entities.Employee emp) {
        Employee myBatisEmp = new Employee();
        myBatisEmp.setEmpId(emp.getEmpId());
        myBatisEmp.setManagerId(emp.getManager().getEmpId());
        myBatisEmp.setLastName(emp.getLastName());
        myBatisEmp.setFirstName(emp.getFirstName());
        myBatisEmp.setCompId(emp.getCompany().getCompId());
        return myBatisEmp;
    }

    public Company castToMyBatis(com.github.juliakas.entities.Company comp) {
        Company myBatisComp = new Company();
        myBatisComp.setCompId(comp.getCompId());
        myBatisComp.setCompRegNo(comp.getCompRegNo());
        myBatisComp.setName(comp.getName());
        return myBatisComp;
    }
}
