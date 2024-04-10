package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.JobDTO;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobResourceTest {

    private static final String BASE_URL = "http://localhost:9090/api/rest/jobs";

    @Test
    public void testGetAllJobs() {
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
    public void testAddJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/add")
                .request(MediaType.APPLICATION_JSON);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobTitle("Software Engineer");
        jobDTO.setMaxSalary(new BigDecimal(1000));
        jobDTO.setMinSalary(new BigDecimal(500));
        // Set jobDTO properties here

        Entity<JobDTO> jobEntity = Entity.entity(jobDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(jobEntity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testFindJobByTitle() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/find/title/{title}")
                .resolveTemplate("title", "plumber")
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetJobById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/find/id/{id}")
                .resolveTemplate("id", 8)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/update")
                .request(MediaType.APPLICATION_JSON);

        JobDTO jobDTO = new JobDTO();
        // Set jobDTO properties to update

        Entity<JobDTO> jobEntity = Entity.entity(jobDTO, MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(jobEntity);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteJob() {
        // Arrange
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/delete/{id}")
                .resolveTemplate("id", 8)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
