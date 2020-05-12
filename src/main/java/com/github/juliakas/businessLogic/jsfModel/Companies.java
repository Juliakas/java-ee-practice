package com.github.juliakas.businessLogic.jsfModel;

import com.github.juliakas.entities.Company;
import com.github.juliakas.persistence.CompaniesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.Collection;

@Named(value = "modelCompanies")
@Model
public class Companies {

    @Getter @Setter
    private String nameFilter;
    @Getter
    private Collection<Company> companies;
    @Getter @Setter
    private String newNameId;
    @Getter @Setter
    private String newName;
    @Inject
    private CompaniesDAO compDao;

    @PostConstruct
    public void init() {
        companies = getAll();
    }

    @Transactional
    public void getFilteredByName() {
        companies = compDao.getByName(nameFilter);
    }

    @Transactional
    public void changeCompanyName() {
        System.out.println(this);
        Company comp = compDao.get(Integer.parseInt(newNameId));
        comp.setName(newName);
        compDao.insertOrUpdate(comp);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private Collection<Company> getAll() {
        System.out.println(this);
        try {
            return compDao.getAll();
        } catch(OptimisticLockException ex) {
            ex.printStackTrace();
            return getAll();
        }
    }
}
