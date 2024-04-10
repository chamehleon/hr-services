package gov.iti.jets.Exceptions.ExceptionMappers;

import gov.iti.jets.Exceptions.ErrorMessage;
import gov.iti.jets.Exceptions.ExceptionMessages.ResourceNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        System.out.println("ResourceNotFoundExceptionMapper");
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "probably wrong ID ");
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
