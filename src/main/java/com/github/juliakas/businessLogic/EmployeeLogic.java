package com.github.juliakas.businessLogic;

import com.github.juliakas.api.model.EmployeeModel;
import com.github.juliakas.entities.Employee;
import com.github.juliakas.persistence.CompaniesDAO;
import com.github.juliakas.persistence.IEmployeesDAO;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeLogic {

    @Inject
    IEmployeesDAO employeesDAO;
    @Inject
    CompaniesDAO companiesDAO;
    @Inject
    InvitationService invitationService;

    @Transactional
    public EmployeeModel getById(long empId) {
        Employee entity = employeesDAO.get(empId);
        return EmployeeModel.buildFromEntity(entity);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public EmployeeModel insertOrUpdate(long empId, EmployeeModel empModel) {
        Employee entityToUpdate = employeesDAO.get(empId);
        long version = entityToUpdate == null ? 1 : entityToUpdate.getVersion();
        empModel.setEmpId(empId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Employee entity = empModel.convertToEntity(employeesDAO, companiesDAO);
        entity.setVersion(version);
        try {
            entity = employeesDAO.insertOrUpdate(entity);
            return EmployeeModel.buildFromEntity(entity);
        } catch(RollbackException e) {
            if (e.getCause() instanceof OptimisticLockException) {
                System.out.println("Optimistic Lock Exception, retrying...");
                insertOrUpdate(empId, empModel);
            }
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Transactional
    public EmployeeModel insert(EmployeeModel empModel) {
        Employee entity = empModel.convertToEntity(employeesDAO, companiesDAO);
        entity = employeesDAO.insert(entity);
        sendInvitation(entity);
        return EmployeeModel.buildFromEntity(entity);
    }

    public void sendInvitation(Employee emp) {
        invitationService.sendInvitationAsync(emp);
    }

}
