package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeResourceTest {
    private static final String BASE_URL = "http://localhost:9090/api/rest/employees";

    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        int employeeId = 4; // Replace with an existing employee ID
        WebTarget target = client.target(BASE_URL + "/" + employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // If the employee ID does not exist, the response status should be NOT_FOUND
        // assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddEmployee() {
        // Arrange
        WebTarget target = client.target(BASE_URL + "/add");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("ahmed");
        employeeDTO.setLastName("Mohamed");
        employeeDTO.setEmail("ahmed@gmail.com");
        employeeDTO.setPhoneNumber("01000000000");
        employeeDTO.setHireDate(LocalDate.now());
        employeeDTO.setJobTitle("Instructor");
        employeeDTO.setCommissionPct(new BigDecimal(0.1));
        employeeDTO.setManagerId(4);
        employeeDTO.setDepartmentName("IT");
        employeeDTO.setSalaryId(40);


        // Set properties of the employeeDTO

        Entity<EmployeeDTO> employeeEntity = Entity.entity(employeeDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(employeeEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        int employeeId = 20; // Replace with an existing employee ID
        WebTarget target = client.target(BASE_URL + "/delete/" + employeeId);
        Invocation.Builder request = target.request();

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // If the employee ID does not exist, the response status should be NOT_FOUND
        // assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        WebTarget target = client.target(BASE_URL + "/update");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(4);
        employeeDTO.setEmail("nouran@gmail.com");


        Entity<EmployeeDTO> employeeEntity = Entity.entity(employeeDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(employeeEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetJobHistory() {
        // Arrange
        int employeeId = 4; // Replace with an existing employee ID
        WebTarget target = client.target(BASE_URL + "/jobHistory/" + employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }



}
