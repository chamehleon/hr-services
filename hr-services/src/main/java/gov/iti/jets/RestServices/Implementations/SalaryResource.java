package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.SalaryService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.math.BigDecimal;

@Path("/salaries")
public class SalaryResource implements ResourceInterface<SalaryDTO> {
    SalaryService salaryService = new SalaryService();
    @Context
    private UriInfo uriInfo;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        return Response.ok().entity(salaryService.getAll(page,size)).build();
    }
    // add new salary
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response create(SalaryDTO salaryDTO){
        SalaryDTO persistedSalary = salaryService.create(salaryDTO);
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("salaries");
        builder.path(Integer.toString(persistedSalary.getSalaryId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();


        return ResponseBuilder.CREATED(persistedSalary,self);
    }

    // update salary by id
    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(SalaryDTO salaryDTO){
        SalaryDTO updatedSalary = salaryService.update(salaryDTO);
        return ResponseBuilder.SUCCESS(updatedSalary);
    }

    // partial update
    @PATCH
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdate(SalaryDTO salaryDTO) {
        SalaryDTO updatedSalary = salaryService.partialUpdate(salaryDTO);
        return ResponseBuilder.SUCCESS(updatedSalary);
    }

    // if i delete the sa
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        boolean isDeleted = salaryService.delete(id);
        return isDeleted ? ResponseBuilder.DELETED() : ResponseBuilder.ERROR("Salary not deleted");

    }
    // get salary by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        SalaryDTO salary = salaryService.getById(id);
        return ResponseBuilder.SUCCESS(salary);
    }

    // add bonus to salary
    @POST
    @Path("/addBonus/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBonusToSalary(@PathParam("id") Integer id, @QueryParam("bonus") BigDecimal bonus){
        SalaryDTO salary = salaryService.addBonusToSalary(id, bonus);
        return ResponseBuilder.SUCCESS(salary);
    }

    // add dedeuction to salary
    @POST
    @Path("/deduct/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deductFromSalary(@PathParam("id") Integer id, @QueryParam("deduction") BigDecimal deduction){
        SalaryDTO salary = salaryService.deductFromSalary(id, deduction);
        return ResponseBuilder.SUCCESS(salary);
    }







}
