package com.github.juliakas.persistence;

import com.github.juliakas.entities.Company;
import com.github.juliakas.interceptors.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class CompaniesDAO {

    @Inject
    //@Named("SecondaryEntityManager")
    private EntityManager em;

    @Transactional
    public Collection<Company> getAll() {
        return em.createNamedQuery("Companies.getAll", Company.class).getResultList();
    }

    @Transactional
    @Logger
    public Collection<Company> getByName(String name) {
        if (name.equals("Bad name")) throw new RuntimeException("Oh no!");
        return em.createNamedQuery("Companies.filterByName", Company.class)
                .setParameter("name", name).getResultList();
    }

    @Transactional
    public Company get(long compId) {
        return em.find(Company.class, compId);
    }

    @Transactional
    public Company get(long compId, LockModeType lockMode) {
        Company comp = em.find(Company.class, compId);
        em.lock(comp, lockMode);
        return comp;
    }

    @Transactional
    public Company insert(Company comp) {
        em.persist(comp);
        return comp;
    }

    public void commit() {
        em.flush();
        em.getTransaction().commit();
    }

    @Transactional
    public Company insertOrUpdate(Company comp) {
        return em.merge(comp);
    }
}
