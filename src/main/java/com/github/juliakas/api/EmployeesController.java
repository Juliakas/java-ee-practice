package com.github.juliakas.api;

import com.github.juliakas.api.model.EmployeeModel;
import com.github.juliakas.businessLogic.EmployeeLogic;
import org.hibernate.StaleObjectStateException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/employees")
public class EmployeesController {

    @Inject
    EmployeeLogic employeeLogic;

    @Path("/{id}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getById(@PathParam("id") final long empId) {
        EmployeeModel emp = employeeLogic.getById(empId);
        return emp != null ? Response.ok(emp).build() : Response.status(404).build();
    }

    @Path("/{id}")
    @PUT
    @Produces({"application/json", "application/xml"})
    public Response put(@PathParam("id") final long empId, @Valid EmployeeModel updatedEmp) {
        try {
            EmployeeModel emp = employeeLogic.insertOrUpdate(empId, updatedEmp);
            return Response.ok(emp).build();
        } catch (RollbackException e) {
            if (e.getCause() instanceof OptimisticLockException) {
                return put(empId, updatedEmp);
            }
            else {
                throw new RuntimeException(e);
            }
        }
    }

    @Path("/")
    @POST
    @Produces({"application/json", "application/xml"})
    public Response insert(EmployeeModel newEmp) {
        EmployeeModel inserted = employeeLogic.insert(newEmp);
        return Response.ok(inserted).build();
    }
}

