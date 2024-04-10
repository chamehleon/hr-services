package gov.iti.jets.SoapServices;

import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Services.SalaryService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.math.BigDecimal;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class SalaryWebService {
    private final SalaryService salaryService = new SalaryService();

    @WebMethod
    public SalaryDTO getSalaryById(@WebParam(name = "id") Integer id) {
        return salaryService.getById(id);
    }

    @WebMethod
    public SalaryDTO addBonusToSalary(@WebParam(name = "id") Integer id, @WebParam(name = "bonus") BigDecimal bonus) {
        return salaryService.addBonusToSalary(id, bonus);
    }

    @WebMethod
    public SalaryDTO deductFromSalary(@WebParam(name = "id") Integer id, @WebParam(name = "deduction") BigDecimal deduction) {
        return salaryService.deductFromSalary(id, deduction);
    }

    @WebMethod
    public SalaryDTO updateSalary(@WebParam(name = "salaryDTO") SalaryDTO salaryDTO) {
        return salaryService.update(salaryDTO);
    }

    @WebMethod
    public SalaryDTO createSalary(@WebParam(name = "salaryDTO") SalaryDTO salaryDTO) {
        return salaryService.create(salaryDTO);
    }

    @WebMethod
    public void deleteSalary(@WebParam(name = "id") Integer id) {
        salaryService.delete(id);
    }



}
