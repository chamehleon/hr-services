package gov.iti.jets.SoapServices;

import gov.iti.jets.Persistence.DTOs.VacationRequestDTO;
import gov.iti.jets.Services.VacationRequestService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.List;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class VacationRequestWebService {
    private final VacationRequestService vacationRequestService = new VacationRequestService();

    @WebMethod
    public List<VacationRequestDTO> getAllVacationRequests(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return vacationRequestService.getAll(page, size);
    }

    @WebMethod
    public VacationRequestDTO getVacationRequestById(@WebParam(name = "id") Integer id) {
        return vacationRequestService.getById(id);
    }

    @WebMethod
    public VacationRequestDTO createVacationRequest(@WebParam(name = "vacationRequest") VacationRequestDTO vacationRequest) {
        return vacationRequestService.create(vacationRequest);
    }

    @WebMethod
    public VacationRequestDTO updateVacationRequest(@WebParam(name = "vacationRequest") VacationRequestDTO vacationRequestDTO) {
        return vacationRequestService.update(vacationRequestDTO);
    }

    @WebMethod
    public boolean deleteVacationRequest(@WebParam(name = "id") Integer id) {
        return vacationRequestService.delete(id);
    }

    @WebMethod
    public VacationRequestDTO handlePendingRequestByEmployeeId(@WebParam(name = "id") Integer id) {
        return vacationRequestService.handleVacationRequest(id);
    }
}
