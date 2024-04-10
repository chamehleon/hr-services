package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.JobHistoryService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/jobHistories")
public class JobHistoryResource implements ResourceInterface<JobHistoryDTO> {
    JobHistoryService jobHistoryService = new JobHistoryService();
    @Context
    private UriInfo uriInfo;


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JobHistoryDTO jobHistoryDTO){
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("jobHistories");
        JobHistoryDTO jobHistory = jobHistoryService.create(jobHistoryDTO);
        builder.path(Integer.toString(jobHistory.getJobHistoryId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();

        return ResponseBuilder.CREATED(jobHistory, self);

    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        JobHistoryDTO jobHistoryDTO = jobHistoryService.getById(id);
        return ResponseBuilder.SUCCESS(jobHistoryDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        List<JobHistoryDTO> jobHistories = jobHistoryService.getAll(page, size);
        return ResponseBuilder.SUCCESS(jobHistories);
    }

    @PATCH
    @Path("/partialUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdate(JobHistoryDTO jobHistoryDTO){
        JobHistoryDTO result = jobHistoryService.update(jobHistoryDTO);
        return ResponseBuilder.SUCCESS(result);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(JobHistoryDTO jobHistoryDTO){
        JobHistoryDTO result = jobHistoryService.update(jobHistoryDTO);
        return ResponseBuilder.SUCCESS(result);
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Integer id){
        boolean result = jobHistoryService.delete(id);
        return result ? ResponseBuilder.DELETED() : ResponseBuilder.ERROR("Job History not deleted");
    }



}
