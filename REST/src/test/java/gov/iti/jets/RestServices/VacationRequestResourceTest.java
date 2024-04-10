package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.VacationRequestDTO;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationRequestResourceTest {

    private static final String BASE_URL = "http://localhost:9090/REST/webapi/vacationRequests";

    @Test
    public void testGetAllVacationRequests() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL)
                .queryParam("page", 1)
                .queryParam("size", 10)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacationRequestById() {
        // Arrange
        int vacationRequestId = 3;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + vacationRequestId)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateVacationRequest() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON);

        VacationRequestDTO vacationRequestDTO = new VacationRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(4);
        vacationRequestDTO.setEmployee(employeeDTO);
        vacationRequestDTO.setStartDate(LocalDate.now());
        vacationRequestDTO.setEndDate(LocalDate.now().plusDays(5));
        vacationRequestDTO.setStatus("Pending");

        Entity<VacationRequestDTO> vacationRequestEntity = Entity.entity(vacationRequestDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(vacationRequestEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateVacationRequest() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON);

        VacationRequestDTO vacationRequestDTO = new VacationRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(4);
        vacationRequestDTO.setEmployee(employeeDTO);
        vacationRequestDTO.setStartDate(LocalDate.now());
        vacationRequestDTO.setEndDate(LocalDate.now().plusDays(5));
        vacationRequestDTO.setStatus("Approved");

        Entity<VacationRequestDTO> vacationRequestEntity = Entity.entity(vacationRequestDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(vacationRequestEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteVacationRequest() {
        // Arrange
        int vacationRequestId = 9;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + vacationRequestId)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testHandlePendingRequestByEmployeeId() {
        // Arrange
        int employeeId = 4;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/employee/" + employeeId)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
