package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.EmployeeService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("employees")
public class EmployeeResource implements ResourceInterface<EmployeeDTO> {
    EmployeeService employeeService = new EmployeeService();
    @Context
    private UriInfo uriInfo;

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
    public Response getAll(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        List<EmployeeDTO> employees = employeeService.getAll(page, size);
        return ResponseBuilder.SUCCESS(employees);
    }

    // add employee
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EmployeeDTO employeeDTO) {
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("employees");
        EmployeeDTO employee = employeeService.create(employeeDTO);
        builder.path(Integer.toString(employee.getId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();
        return ResponseBuilder.CREATED(employee, self);
    }
    // delete employee
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Integer id) {
        boolean isDeleted = employeeService.delete(id);
        return isDeleted ? ResponseBuilder.DELETED() : ResponseBuilder.ERROR("Employee not deleted");
    }

    // update
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(EmployeeDTO employeeDTO) {
        return ResponseBuilder.SUCCESS(employeeService.update(employeeDTO));
    }

    // get job history
    @GET
    @Path("/jobHistory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobHistory(@PathParam("id") int id) {
        return ResponseBuilder.SUCCESS(employeeService.getJobHistory(id));
    }

    // partial update
    @PATCH
    @Path("/partialUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response partialUpdate(EmployeeDTO employeeDTO) {
        return ResponseBuilder.SUCCESS(employeeService.update(employeeDTO));
    }

    // find by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        return ResponseBuilder.SUCCESS(employeeService.getById(id));
    }

    // get net salary
    @GET
    @Path("/netSalary/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNetSalary(@PathParam("id") int id) {
        return ResponseBuilder.SUCCESS(employeeService.getNetSalary(id));
    }

    // get manager of employee
    // TODO add it to documentation
    @GET
    @Path("/manager/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getManager(@PathParam("id") int id) {
        return ResponseBuilder.SUCCESS(employeeService.getManager(id));
    }
}
