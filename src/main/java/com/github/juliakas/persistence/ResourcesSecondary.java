package com.github.juliakas.persistence;

import javax.enterprise.context.*;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;

@ApplicationScoped
@Alternative
public class ResourcesSecondary extends ResourcesBase {

    @Override
    @Produces
    @RequestScoped
    public EntityManager createJTAEntityManager() {
        System.out.println("ResourcesSecondary class");
        return super.createJTAEntityManager();
    }
}
