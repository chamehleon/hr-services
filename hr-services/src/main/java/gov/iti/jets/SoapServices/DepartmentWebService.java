package gov.iti.jets.SoapServices;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Services.DepartmentService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.List;
import java.util.Set;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class DepartmentWebService {
    private final DepartmentService departmentService = new DepartmentService();

    @WebMethod
    public List<DepartmentDTO> getAllDepartments(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return departmentService.getAll(page, size);
    }

    @WebMethod
    public Set<EmployeeDTO> getEmployeesInDepartment(@WebParam(name = "id") Integer id) {
        return departmentService.getEmployeesInDepartment(id);
    }

    @WebMethod
    public EmployeeDTO getDepartmentManager(@WebParam(name = "id") Integer id) {
        return departmentService.getDepartmentManager(id);
    }

    @WebMethod
    public DepartmentDTO createDepartment(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO) {
        return departmentService.create(departmentDTO);
    }

    @WebMethod
    public DepartmentDTO updateDepartment(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO) {
        return departmentService.update(departmentDTO);
    }

    @WebMethod
    public DepartmentDTO findDepartmentByName(@WebParam(name = "name") String name) {
        return departmentService.findDepartmentByName(name);
    }

    @WebMethod
    public DepartmentDTO partialUpdateDepartment(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO) {
        return departmentService.partialUpdateDepartment(departmentDTO);
    }

    @WebMethod
    public DepartmentDTO getDepartmentById(@WebParam(name = "id") Integer id) {
        return departmentService.getById(id);
    }
}
