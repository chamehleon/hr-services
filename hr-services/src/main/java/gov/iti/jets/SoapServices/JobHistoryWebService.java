package gov.iti.jets.SoapServices;

import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Services.JobHistoryService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.List;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class JobHistoryWebService {
    JobHistoryService jobHistoryService = new JobHistoryService();

    @WebMethod
    public JobHistoryDTO createJobHistory(@WebParam(name = "jobHistoryDTO") JobHistoryDTO jobHistoryDTO){
        return jobHistoryService.create(jobHistoryDTO);
    }

    @WebMethod
    public JobHistoryDTO betJobHistoryById(@WebParam(name = "id") Integer id){
        return jobHistoryService.getById(id);
    }

    @WebMethod
    public List<JobHistoryDTO> getAllJobHistories(@WebParam(name = "page") int page, @WebParam(name = "size") int size){
        return jobHistoryService.getAll(page, size);
    }

    @WebMethod
    public JobHistoryDTO updateJobHistory(@WebParam(name = "jobHistoryDTO") JobHistoryDTO jobHistoryDTO){
        return jobHistoryService.update(jobHistoryDTO);
    }

    @WebMethod
    public boolean deleteJobHistory(@WebParam(name = "id") Integer id){
        return jobHistoryService.delete(id);
    }
}