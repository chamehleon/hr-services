package gov.iti.jets.SoapServices;

import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Services.JobService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.List;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class JobWebService {

    private JobService jobService = new JobService();

    @WebMethod
    public List<JobDTO> getAll(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return jobService.getAll(page, size);
    }

    @WebMethod
    public JobDTO create(@WebParam(name = "jobDTO") JobDTO jobDTO) {
        return jobService.create(jobDTO);
    }

    @WebMethod
    public JobDTO findJobByTitle(@WebParam(name = "title") String title) {
        return jobService.findJobByTitle(title);
    }

    @WebMethod
    public JobDTO getById(@WebParam(name = "id") int id) {
        return jobService.getById(id);
    }

    @WebMethod
    public JobDTO update(@WebParam(name = "jobDTO") JobDTO jobDTO) {
        return jobService.update(jobDTO);
    }

    @WebMethod
    public JobDTO partialUpdate(@WebParam(name = "jobDTO") JobDTO jobDTO) {
        return jobService.partialUpdateJob(jobDTO);
    }

    @WebMethod
    public void delete(@WebParam(name = "id") int id) {
        jobService.delete(id);
    }


}
