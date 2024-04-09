package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Services.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Set;

@Path("employees")
public class EmployeeResource {
    EmployeeService employeeService = new EmployeeService();

    // get employee by id
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void getEmployeeById(@PathParam("id") int id) {
////
////        EmployeeDTO employee = employeeService.getEmployeeById(id);
////        return Response.ok(employee).build();
//    }

    // get all employees
    @GET
    // TODO add pagination
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        System.out.println(employees.size());
        return Response.ok(employees).build();
    }

    // add employee
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(EmployeeDTO employeeDTO) {
        boolean isAdded = employeeService.addEmployee(employeeDTO);

        return Response.ok(isAdded).build();
    }
    // delete employee
    @DELETE
    @Path("/delete/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        return Response.ok(isDeleted).build();
    }

    // update
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmployeeDTO employeeDTO) {
        boolean isUpdated = employeeService.updateEmployee(employeeDTO);
        return Response.ok(isUpdated).build();
    }

    // get job history
    @GET
    @Path("/jobHistory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobHistory(@PathParam("id") int id) {
        Set<JobHistoryDTO> jobHistory = (Set<JobHistoryDTO>) employeeService.getJobHistory(id);
        return Response.ok(jobHistory).build();
    }

    // partial update
    @PATCH
    @Path("/partialUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response partialUpdateEmployee(EmployeeDTO employeeDTO) {
        boolean isUpdated = employeeService.updateEmployee(employeeDTO);
        return Response.ok(isUpdated).build();
    }

}
