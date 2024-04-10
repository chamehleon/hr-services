package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Persistence.DTOs.VacationRequestDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.VacationRequestService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/vacationRequests")
public class VacationRequestResource implements ResourceInterface<VacationRequestDTO> {
    VacationRequestService vacationRequestService = new VacationRequestService();
    @Context
    private UriInfo uriInfo;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                                        @QueryParam("size") @DefaultValue("10") int size) {
        List<VacationRequestDTO> vacationRequestDTOS = vacationRequestService.getAll(page, size);
        return ResponseBuilder.SUCCESS(vacationRequestDTOS);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        VacationRequestDTO vacationRequestDTO = vacationRequestService.getById(id);

        return ResponseBuilder.SUCCESS(vacationRequestDTO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(VacationRequestDTO vacationRequest) {
        VacationRequestDTO createdVacationRequest = vacationRequestService.create(vacationRequest);
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("vacationRequests");
        builder.path(Integer.toString(createdVacationRequest.getId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();
        return ResponseBuilder.CREATED(createdVacationRequest,self);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(VacationRequestDTO vacationRequestDTO) {
        VacationRequestDTO updatedVacationRequest = vacationRequestService.update(vacationRequestDTO);
        return ResponseBuilder.SUCCESS(updatedVacationRequest);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        boolean deleted = vacationRequestService.delete(id);
        return deleted? ResponseBuilder.SUCCESS("Vacation Request Deleted Successfully") : ResponseBuilder.ERROR("Vacation Request deletion failed");
    }

    //handle vacation request of employee by id
    @GET
    @Path("/employee/{id}")
    public Response handlePendingRequestByEmployeeId(@PathParam("id") Integer id) {
        VacationRequestDTO vacationRequestDTOS = vacationRequestService.handleVacationRequest(id);
        return ResponseBuilder.SUCCESS(vacationRequestDTOS);
    }






}
