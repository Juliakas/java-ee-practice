package com.github.juliakas.businessLogic.model;

import com.github.juliakas.entities.Company;
import com.github.juliakas.persistence.CompaniesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Collection;

@Named(value = "modelCompanies")
@Model
public class Companies {

    @Getter @Setter
    private String nameFilter;
    @Getter
    private Collection<Company> companies;

    @Inject
    private CompaniesDAO compDao;

    @PostConstruct
    public void init() {
        companies = getAll();
    }

    @Transactional
    public void getFilteredByName() { companies = compDao.getByName(nameFilter); }

    @Transactional
    private Collection<Company> getAll() {
        return compDao.getAll();
    }
}
