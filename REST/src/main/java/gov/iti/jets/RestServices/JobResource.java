package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Services.JobService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/jobs")
public class JobResource {
    JobService jobService = new JobService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobs(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        List<JobDTO> jobs = jobService.findAll(page, size);
        return Response.ok().entity(jobs).build();
    }

    // add job
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJob(JobDTO jobDTO){
        boolean result = jobService.addJob(jobDTO);
        return Response.ok().entity(result).build();
    }

    // find job by title
    @GET
    @Path("/find/title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJobByTitle(@PathParam("title") String title){
        JobDTO jobDTO = jobService.findJobByTitle(title);
        return Response.ok().entity(jobDTO).build();
    }

    // find job by id
    @GET
    @Path("/find/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findJobById(@PathParam("id") Integer id){
        JobDTO jobDTO = jobService.findJobById(id);
        return Response.ok().entity(jobDTO).build();
    }

    // update job
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJob(JobDTO jobDTO){
        JobDTO result = jobService.updateJob(jobDTO);
        return Response.ok().entity(result).build();
    }

    // partial update job
    @PATCH
    @Path("/partialUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partialUpdateJob(JobDTO jobDTO){
        JobDTO result = jobService.partialUpdateJob(jobDTO);
        return Response.ok().entity(result).build();
    }

    // delete job
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJob(@PathParam("id") Integer id){
        boolean result = jobService.deleteJob(id);
        return Response.ok().entity(result).build();
    }
}
