package gov.iti.jets.RestServices;
import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.RestServices.Implementations.DepartmentResource;
import gov.iti.jets.Services.DepartmentService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentResourceTest {

    private static final String BASE_URL = "http://localhost:9090/REST/webapi/departments";

    @Test
    public void testGetAllDepartments() {
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL)
                .queryParam("page", 1)
                .queryParam("size", 10)
                .request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeesInDepartment() {
        int departmentId = 1;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + departmentId + "/employees")
                .request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testChangeDepartmentManager() {
        int departmentId = 1;
        int managerId = 10;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + departmentId + "/manager/" + managerId)
                .request(MediaType.APPLICATION_JSON);

        Response response = request.post(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetDepartmentManager() {
        int departmentId = 1;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + departmentId + "/manager")
                .request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        // Set departmentDTO properties

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/add")
                .request(MediaType.APPLICATION_JSON);

        Entity<DepartmentDTO> departmentEntity = Entity.entity(departmentDTO, MediaType.APPLICATION_JSON);
        Response response = request.post(departmentEntity);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        // Set departmentDTO properties

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/update")
                .request(MediaType.APPLICATION_JSON);

        Entity<DepartmentDTO> departmentEntity = Entity.entity(departmentDTO, MediaType.APPLICATION_JSON);
        Response response = request.put(departmentEntity);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testFindDepartmentByName() {
        String departmentName = "IT";
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/name/" + departmentName)
                .request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testPartialUpdateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        // Set departmentDTO properties for partial update

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/partialUpdate")
                .request(MediaType.APPLICATION_JSON);

        Entity<DepartmentDTO> departmentEntity = Entity.entity(departmentDTO, MediaType.APPLICATION_JSON);
        Response response = request.method("PATCH", departmentEntity);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetDepartmentById() {
        int departmentId = 1;
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + "/" + departmentId)
                .request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
