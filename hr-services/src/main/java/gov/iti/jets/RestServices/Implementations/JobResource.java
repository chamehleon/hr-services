package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Exceptions.ExceptionMessages.BadArgumentException;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.JobService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/jobs")
public class JobResource implements ResourceInterface<JobDTO> {
    JobService jobService = new JobService();
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        List<JobDTO> jobs = jobService.getAll(page, size);
        return ResponseBuilder.SUCCESS(jobs);
    }

    // add job
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JobDTO jobDTO){
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("jobs");
        JobDTO persistedJob = jobService.create(jobDTO);

        builder.path(Integer.toString(persistedJob.getJobId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();


        return ResponseBuilder.CREATED(persistedJob, self);
    }

    // find job by title
    @GET
    @Path("/find/title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJobByTitle(@PathParam("title") String title){
        JobDTO jobDTO = jobService.findJobByTitle(title);
        return ResponseBuilder.SUCCESS(jobDTO);
    }

    // find job by id
    @GET
    @Path("/find/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        JobDTO jobDTO = jobService.getById(id);
        return ResponseBuilder.SUCCESS(jobDTO);
    }

    // update job
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(JobDTO jobDTO)  {

        JobDTO result = jobService.update(jobDTO);

        return ResponseBuilder.SUCCESS(result);
    }

    // partial update job
    @PATCH
    @Path("/partialUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdate(JobDTO jobDTO){
        JobDTO result = jobService.partialUpdateJob(jobDTO);
        return ResponseBuilder.SUCCESS(result);
    }

    // delete job
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        jobService.delete(id);
        return ResponseBuilder.DELETED();
    }
}
