package com.github.juliakas.api.model;

import com.github.juliakas.businessLogic.jsfModel.Companies;
import com.github.juliakas.entities.Company;
import com.github.juliakas.entities.Employee;
import com.github.juliakas.persistence.CompaniesDAO;
import com.github.juliakas.persistence.IEmployeesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class EmployeeModel {

    private Long empId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Long companyId;
    private Long managerId;

    @Transactional
    public Employee convertToEntity(IEmployeesDAO empDao, CompaniesDAO compDao) {
        Employee entity = new com.github.juliakas.entities.Employee();
        entity.setEmpId(empId == null ? 0 : empId);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setCompany(companyId == null ? null : compDao.get(companyId));
        entity.setManager(managerId == null ? null : empDao.get(managerId));
        return entity;
    }

    public static EmployeeModel buildFromEntity(Employee entity) {
        if (entity == null) return null;

        EmployeeModel model = new EmployeeModel();
        model.setEmpId(entity.getEmpId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());

        Employee manager = entity.getManager();
        model.setManagerId(manager == null ? null : manager.getEmpId());

        Company company = entity.getCompany();
        model.setCompanyId(company == null ? null : company.getCompId());

        return model;
    }
}
