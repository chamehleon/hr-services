package gov.iti.jets.Exceptions.ExceptionMappers;


import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import gov.iti.jets.Exceptions.ErrorMessage;

@Provider
public class IllegalUpdateOperationMapper implements ExceptionMapper<IllegalUpdateOperationException> {
    @Override
    public Response toResponse(IllegalUpdateOperationException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.EXPECTATION_FAILED.getStatusCode(),"hazem");
        return Response.status(Response.Status.EXPECTATION_FAILED)
                .entity(errorMessage)
                .build();
    }
}

