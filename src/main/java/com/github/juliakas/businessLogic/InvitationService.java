package com.github.juliakas.businessLogic;

import com.github.juliakas.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class InvitationService {

    @PersistenceUnit(unitName = "LocalCompanyPU")
    private EntityManagerFactory emf;

    public void sendInvitationAsync(Employee emp) {
        CompletableFuture<String> invitationTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Welcome, " + emp.getFirstName();
        });
        invitationTask.thenAccept(res -> {
            try {
                System.out.println(res);
                emp.setFirstName(emp.getFirstName() + " " + emp.getLastName());
                // At this point, @RequestScoped em is likely disposed
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.merge(emp);
                em.getTransaction().commit();
                em.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        });
    }

}
