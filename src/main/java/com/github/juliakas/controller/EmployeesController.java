package com.github.juliakas.controller;

import com.github.juliakas.businessLogic.DeleteEmployee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/employees")
public class EmployeesController {

    @Inject
    DeleteEmployee deleteEmployee;

    @Path("/{id}")
    @DELETE
    public Response deleteById(@PathParam("id") final long empId) {

        Response.ResponseBuilder responseBuilder = Response.noContent();

        try {
            deleteEmployee.deleteEmployee(empId);
        } catch(DeleteEmployee.NoDataFound ex) {
            ex.printStackTrace();
            responseBuilder.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .type(MediaType.TEXT_PLAIN);
        } catch(Exception ex) {
            ex.printStackTrace();
            responseBuilder.status(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return responseBuilder.build();
    }
}
