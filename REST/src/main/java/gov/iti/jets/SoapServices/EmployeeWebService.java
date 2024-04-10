package gov.iti.jets.SoapServices;


import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Services.EmployeeService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class EmployeeWebService {
    private final EmployeeService employeeService = new EmployeeService();

    @WebMethod
    public List<EmployeeDTO> getAllEmployees(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return employeeService.getAll(page, size);
    }

    @WebMethod
    public EmployeeDTO createEmployee(@WebParam(name = "employeeDTO") EmployeeDTO employeeDTO) {
        return employeeService.create(employeeDTO);
    }

    @WebMethod
    public boolean deleteEmployee(@WebParam(name = "id") Integer id) {
        return employeeService.delete(id);
    }

    @WebMethod
    public EmployeeDTO updateEmployee(@WebParam(name = "employeeDTO") EmployeeDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @WebMethod
    public Set<JobHistoryDTO> getJobHistory(@WebParam(name = "id") int id) {
        return employeeService.getJobHistory(id);
    }

    @WebMethod
    public EmployeeDTO getEmployeeById(@WebParam(name = "id") Integer id) {
        return employeeService.getById(id);
    }

    @WebMethod
    public BigDecimal getNetSalary(@WebParam(name = "id") int id) {
        return employeeService.getNetSalary(id);
    }
}
