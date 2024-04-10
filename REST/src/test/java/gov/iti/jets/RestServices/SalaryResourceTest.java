package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryResourceTest {

    private static final String BASE_URL = "http://localhost:9090/REST/webapi/salaries";

    @Test
    public void testGetAllSalaries() {
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
    public void testAddSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/add")
                .request(MediaType.APPLICATION_JSON);

        SalaryDTO salaryDTO = new SalaryDTO();
        // Set salaryDTO properties here

        Entity<SalaryDTO> salaryEntity = Entity.entity(salaryDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(salaryEntity);

        // Assert
        assertEquals(Response.Status.EXPECTATION_FAILED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/update")
                .request(MediaType.APPLICATION_JSON);

        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setSalaryId(6);
        salaryDTO.setPaymentAmount(new BigDecimal("5000.00"));
        salaryDTO.setDeductions(new BigDecimal("1000.00"));
        salaryDTO.setBonuses(new BigDecimal("500.00"));
        // Set salaryDTO properties to update

        Entity<SalaryDTO> salaryEntity = Entity.entity(salaryDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(salaryEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testPartialUpdateSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/update/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON);

        SalaryDTO salaryDTO = new SalaryDTO();
        // Set salaryDTO properties to partially update

        Entity<SalaryDTO> salaryEntity = Entity.entity(salaryDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.method("PATCH", salaryEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/delete/{id}")
                .resolveTemplate("id", 38)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetSalaryById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/{id}")
                .resolveTemplate("id", 6)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddBonusToSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/addBonus/{id}")
                .resolveTemplate("id", 6)
                .queryParam("bonus", new BigDecimal("500.00"))
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(null); // No request body needed

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeductFromSalary() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/deduct/{id}")
                .resolveTemplate("id", 6)
                .queryParam("deduction", new BigDecimal("100.00"))
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(null); // No request body needed

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}