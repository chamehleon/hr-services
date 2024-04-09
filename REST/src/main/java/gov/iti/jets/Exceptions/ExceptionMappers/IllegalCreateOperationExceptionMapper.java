package gov.iti.jets.Exceptions.ExceptionMappers;

import gov.iti.jets.Exceptions.ExceptionMessages.IllegalCreateOperationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalCreateOperationExceptionMapper implements ExceptionMapper<IllegalCreateOperationException> {
    @Override
    public Response toResponse(IllegalCreateOperationException e) {
        return Response.status(Response.Status.EXPECTATION_FAILED)
                .entity(e.getMessage())
                .status(400)
                .build();
    }
}
