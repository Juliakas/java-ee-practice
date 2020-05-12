package com.github.juliakas.businessLogic;

import com.github.juliakas.entities.Employee;
import com.github.juliakas.persistence.IEmployeesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;

@ApplicationScoped
public class DeleteEmployee {

    @Inject
    private IEmployeesDAO empDao;

    @Transactional
    public void deleteEmployee(long empId) throws NoDataFound {
        Employee emp = empDao.get(empId);
        if (emp == null) {
            throw new NoDataFound("EmployeeModel with id = " + empId + " is not found");
        }
        restoreDetachedLinks(emp);
        empDao.delete(emp);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void restoreDetachedLinks(final Employee detachedManager) {
        detachedManager.getSubordinates().forEach(sub -> {
            sub.setManager(detachedManager.getManager());
            try {
                empDao.insertOrUpdate(sub);
            } catch (RollbackException e) {
                if (e.getCause() instanceof OptimisticLockException) {
                    System.out.println("Optimistic lock exception, retrying...");
                    restoreDetachedLinks(detachedManager);
                }
                throw new RuntimeException(e);
            }
        });
    }

    public static class NoDataFound extends Exception {
        public NoDataFound(String errMessage) {
            super(errMessage);
        }
    }

}
