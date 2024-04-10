package gov.iti.jets.RestServices.Implementations;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.RestServices.Interfaces.ResourceInterface;
import gov.iti.jets.Services.DepartmentService;
import gov.iti.jets.Utils.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Set;

@Path("/departments")
public class DepartmentResource implements ResourceInterface<DepartmentDTO> {
    DepartmentService departmentService = new DepartmentService();
    @Context
    private UriInfo uriInfo;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("size") @DefaultValue("10") int size){
        List<DepartmentDTO> departments = departmentService.getAll(page,size);
        return ResponseBuilder.SUCCESS(departments);
    }

    // get employees in department
    @GET
    @Path("{id}/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesInDepartment(@PathParam("id") Integer id) {
        Set<EmployeeDTO> departments = departmentService.getEmployeesInDepartment(id);
        return ResponseBuilder.SUCCESS(departments);
    }

    // change manager od department
    @POST
    @Path("{id}/manager/{managerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeDepartmentManager(@PathParam("id") Integer id, @PathParam("managerId") Integer managerId) {
        return ResponseBuilder.SUCCESS(departmentService.changeDepartmentManager(id, managerId));
          //  return Response.ok().entity(departmentService.changeDepartmentManager(managerId, managerId)).build();
    }
    // get department manager
    @GET
    @Path("{id}/manager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentManager(@PathParam("id") Integer id) {
        EmployeeDTO manager = departmentService.getDepartmentManager(id);
        return ResponseBuilder.SUCCESS(manager);
    }

    // add new department
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(DepartmentDTO departmentDTO) {
        UriBuilder builder = uriInfo.getBaseUriBuilder().path("departments");
        DepartmentDTO persistentDepartment = departmentService.create(departmentDTO);
        builder.path(Integer.toString(persistentDepartment.getId()));
        Link self = Link.fromUri(builder.build()).rel("self").build();
        return ResponseBuilder.CREATED(persistentDepartment, self);
    }

    // TODO delete department

    // update department
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(DepartmentDTO departmentDTO) {
        return ResponseBuilder.SUCCESS(departmentService.update(departmentDTO));
    }

    @Override
    public Response delete(Integer id) {
        return null;
    }

    // find department by name
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDepartmentByName(@PathParam("name") String name) {
        return ResponseBuilder.SUCCESS(departmentService.findDepartmentByName(name));
    }

    @PATCH
    @Path("/partialUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response partialUpdateDepartment(DepartmentDTO departmentDTO) {
        return ResponseBuilder.SUCCESS(departmentService.partialUpdateDepartment(departmentDTO));
    }

    // find by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        return ResponseBuilder.SUCCESS(departmentService.getById(id));
    }







}
