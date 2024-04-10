package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import jakarta.ws.rs.client.Invocation;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobHistoryResourceTest {

    private static final String BASE_URL = "http://localhost:9090/api/rest/jobHistories";

    @Test
    public void testGetAllJobHistories() {
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
    public void testAddJobHistory() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/add")
                .request(MediaType.APPLICATION_JSON);

        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(4);
        jobHistoryDTO.setEmployee(employeeDTO);
        jobHistoryDTO.setStartDate(LocalDate.now());
        jobHistoryDTO.setEndDate(LocalDate.now());
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(9);
        jobHistoryDTO.setJob(jobDTO);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(19);
        jobHistoryDTO.setDepartment(departmentDTO);



        // Set jobHistoryDTO properties here

        Entity<JobHistoryDTO> jobHistoryEntity = Entity.entity(jobHistoryDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(jobHistoryEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateJobHistory() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/update")
                .request(MediaType.APPLICATION_JSON);

        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
        jobHistoryDTO.setJobHistoryId(5);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(4);
        jobHistoryDTO.setEmployee(employeeDTO);
        jobHistoryDTO.setStartDate(LocalDate.now());
        jobHistoryDTO.setEndDate(LocalDate.now().plusDays(5));
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(8);
        jobHistoryDTO.setJob(jobDTO);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(3);
        jobHistoryDTO.setDepartment(departmentDTO);

        Entity<JobHistoryDTO> jobHistoryEntity = Entity.entity(jobHistoryDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(jobHistoryEntity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteJobHistory() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/delete/{id}")
                .resolveTemplate("id", 12)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetJobHistoryById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/{id}")
                .resolveTemplate("id", 5)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
