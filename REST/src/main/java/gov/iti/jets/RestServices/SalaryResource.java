package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Services.SalaryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/salaries")
public class SalaryResource {
    SalaryService salaryService = new SalaryService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalaries(){
        return Response.ok().entity(salaryService.getAllSalaries()).build();
    }
    // add new salary
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addSalary(SalaryDTO salaryDTO){
        boolean isAdded = salaryService.createSalary(salaryDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("salary", salaryDTO);
        response.put("message", isAdded ? "Salary added successfully" : "Failed to add salary");
        return Response.ok().entity(response).build();
    }

    // update salary by id
    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalary(@PathParam("id") Integer id, SalaryDTO salaryDTO){
        SalaryDTO updatedSalary = salaryService.updateSalary(id, salaryDTO);
        return Response.ok().entity(updatedSalary).build();
    }

    // partial update
    @PATCH
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdateSalary(@PathParam("id") Integer id, SalaryDTO salaryDTO) {
        SalaryDTO updatedSalary = salaryService.partialUpdateSalary(id, salaryDTO);
        return Response.ok().entity(updatedSalary).build();
    }

    // if i delete the sa
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalary(@PathParam("id") Integer id){
        SalaryDTO deletedSalary = salaryService.deleteSalary(id);
        return Response.ok().entity(deletedSalary).build();

    }
    // get salary by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalaryById(@PathParam("id") Integer id){
        SalaryDTO salary = salaryService.findSalaryById(id).orElse(null);
        return Response.ok().entity(salary).build();
    }





}
