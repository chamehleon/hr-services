package gov.iti.jets.RestServices;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Services.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;

@Path("/departments")
public class DepartmentResource {
    DepartmentService departmentService = new DepartmentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return Response.ok().entity(departments).build();
    }

    // get employees in department
    @GET
    @Path("{id}/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesInDepartment(@PathParam("id") Integer id) {
        Set<EmployeeDTO> departments = departmentService.getEmployeesInDepartment(id);
        return Response.ok().entity(departments).build();
    }

    // change manager od department
    @POST
    @Path("{id}/manager/{managerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeDepartmentManager(@PathParam("id") Integer id, @PathParam("managerId") Integer managerId) {
            return Response.ok().entity(departmentService.changeDepartmentManager(managerId, managerId)).build();
    }
    // get department manager
    @GET
    @Path("{id}/manager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentManager(@PathParam("id") Integer id) {
        EmployeeDTO manager = departmentService.getDepartmentManager(id);
        return Response.ok().entity(manager).build();
    }

    // add new department
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDepartment(DepartmentDTO departmentDTO) {
        return Response.ok(departmentService.addDepartment(departmentDTO)).build();
    }

    // TODO delete department






}
