package gov.iti.jets.Exceptions.ExceptionMappers;

import gov.iti.jets.Exceptions.ErrorMessage;
import gov.iti.jets.Exceptions.ExceptionMessages.BadArgumentException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadArgumentExceptionMapper implements ExceptionMapper<BadArgumentException> {
    @Override
    public Response toResponse(BadArgumentException e) {
        System.out.println("ResourceNotFoundExceptionMapper");
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 400, "Invalid argument provided ");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
