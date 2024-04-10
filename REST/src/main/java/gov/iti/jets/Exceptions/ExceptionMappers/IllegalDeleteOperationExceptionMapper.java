package gov.iti.jets.Exceptions.ExceptionMappers;

import gov.iti.jets.Exceptions.ErrorMessage;
import gov.iti.jets.Exceptions.ExceptionMessages.IllegalDeleteOperationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class IllegalDeleteOperationExceptionMapper implements ExceptionMapper<IllegalDeleteOperationException> {
    @Override
    public Response toResponse(IllegalDeleteOperationException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.EXPECTATION_FAILED.getStatusCode(),"delete operation failed");
        return Response.status(Response.Status.EXPECTATION_FAILED)
                .entity(errorMessage)
                .build();
    }
}
