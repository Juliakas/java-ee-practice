package com.github.juliakas.businessLogic;

import com.github.juliakas.entities.Employee;
import com.github.juliakas.mybatis.EntityCaster;
import com.github.juliakas.mybatis.dao.EmployeesMapper;
import com.github.juliakas.persistence.IEmployeesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DeleteEmployee {

    @Inject
    private IEmployeesDAO empDao;

    @Inject
    private EmployeesMapper mapper;

    private final boolean useMyBatis = true;

    @Inject
    private EntityCaster caster;

    @Transactional
    @org.mybatis.cdi.Transactional
    public void deleteEmployee(long empId) throws NoDataFound {

        if(useMyBatis) {
            com.github.juliakas.mybatis.model.Employee emp = mapper.selectByPrimaryKey(empId);
            if (emp == null) {
                throw new NoDataFound("Employee with id = " + empId + " is not found");
            }
            restoreDetachedLinks(emp);
            mapper.deleteByPrimaryKey(emp.getEmpId());
        } else {
            Employee emp = empDao.get(empId);
            if (emp == null) {
                throw new NoDataFound("Employee with id = " + empId + " is not found");
            }
            restoreDetachedLinks(emp);
            empDao.delete(emp);
        }
    }

    @Transactional
    private void restoreDetachedLinks(Employee detachedManager) {
        detachedManager.getSubordinates().forEach(sub -> {
            sub.setManager(detachedManager.getManager());
            empDao.insertOrUpdate(sub);
        });
    }

    @org.mybatis.cdi.Transactional
    private void restoreDetachedLinks(com.github.juliakas.mybatis.model.Employee detachedManager) {
        List<com.github.juliakas.mybatis.model.Employee> subordinates
                = mapper.selectByManager(detachedManager.getEmpId());
        subordinates.forEach(sub -> {
            sub.setManagerId(detachedManager.getManagerId());
            mapper.updateByPrimaryKey(sub);
        });
    }

    public static class NoDataFound extends Exception {
        public NoDataFound(String errMessage) {
            super(errMessage);
        }
    }

}
