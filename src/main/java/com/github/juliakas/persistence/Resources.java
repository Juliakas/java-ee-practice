package com.github.juliakas.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManager;
import javax.persistence.SynchronizationType;

@ApplicationScoped
public class Resources extends ResourcesBase {

    @Override
    @Specializes
    @Produces
    public EntityManager createJTAEntityManager() {
        System.out.println("Resources class");
        return super.createJTAEntityManager();
    }
}
