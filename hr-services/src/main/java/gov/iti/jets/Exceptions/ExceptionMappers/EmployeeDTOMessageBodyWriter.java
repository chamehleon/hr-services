package gov.iti.jets.Exceptions.ExceptionMappers;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeDTOMessageBodyWriter implements MessageBodyWriter<EmployeeDTO> {

    @Override
    public boolean isWriteable(
            Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType
    ) {
        return EmployeeDTO.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(
            EmployeeDTO employeeDTO, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream
    )
            throws IOException, WebApplicationException {
        String id = String.valueOf(employeeDTO.getId());
        entityStream.write(id.getBytes());
    }
}