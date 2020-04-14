package com.github.juliakas.persistence;

import com.github.juliakas.entities.Company;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class CompaniesDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public Collection<Company> getAll() {
        return em.createNamedQuery("Companies.getAll", Company.class).getResultList();
    }

    @Transactional
    public Collection<Company> getByName(String name) {
        return em.createNamedQuery("Companies.filterByName", Company.class)
                .setParameter("name", name).getResultList();
    }

    @Transactional
    public Company get(long compId) {
        return em.find(Company.class, compId);
    }

    @Transactional
    public Company insert(Company comp) {
        em.persist(comp);
        return comp;
    }

    @Transactional
    public Company insertOrUpdate(Company comp) {
        return em.merge(comp);
    }
}
