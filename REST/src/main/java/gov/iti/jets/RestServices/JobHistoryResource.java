package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Services.JobHistoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/jobHistory")
public class JobHistoryResource {
    JobHistoryService jobHistoryService = new JobHistoryService();


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJobHistory(JobHistoryDTO jobHistoryDTO){
        boolean result = jobHistoryService.addJobHistory(jobHistoryDTO);
        return Response.ok().entity(result).build();

    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobHistory(@PathParam("id") Integer id){
        JobHistoryDTO jobHistoryDTO = jobHistoryService.getJobHistory(id);
        return Response.ok().entity(jobHistoryDTO).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobHistories(){
        List<JobHistoryDTO> jobHistories = jobHistoryService.getAllJobHistories();
        return Response.ok().entity(jobHistories).build();
    }

    @PATCH
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobHistory(JobHistoryDTO jobHistoryDTO){
        JobHistoryDTO result = jobHistoryService.updateJobHistory(jobHistoryDTO);
        return Response.ok().entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteJobHistory(@PathParam("id") Integer id){
        boolean result = jobHistoryService.deleteJobHistory(id);
        return Response.ok().entity(result).build();
    }



}
