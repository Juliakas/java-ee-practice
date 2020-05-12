package com.github.juliakas.businessLogic.jsfModel;

import com.github.juliakas.businessLogic.DeleteEmployee;
import com.github.juliakas.entities.Company;
import com.github.juliakas.entities.Employee;
import com.github.juliakas.persistence.CompaniesDAO;
import com.github.juliakas.persistence.IEmployeesDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "modelEmployeesInComp")
@Model
public class EmployeesInCompany {

    @Inject
    IEmployeesDAO empDao;
    @Inject
    private CompaniesDAO compDao;
    @Inject
    private DeleteEmployee deleteEmployee;
    @Getter
    private Company comp;

    @PostConstruct
    public void init() {
        String compId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("compId");
        comp = compDao.get(Long.parseLong(compId));
    }

    @Transactional
    public void deleteEmployee() throws DeleteEmployee.NoDataFound {
        String empId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("empId");
        deleteEmployee.deleteEmployee(Long.parseLong(empId));
    }

    @Transactional
    public Collection<Employee> getEmployees() {
        return empDao.getAll().stream().filter(emp -> emp.getCompany()
                .getCompId() == comp.getCompId()).collect(Collectors.toList());
    }

}
