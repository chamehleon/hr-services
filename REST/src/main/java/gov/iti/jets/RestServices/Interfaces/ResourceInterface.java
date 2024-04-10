package gov.iti.jets.RestServices.Interfaces;

import jakarta.ws.rs.core.Response;

public interface ResourceInterface <T>{
    Response getAll(int page,int size);
    Response getById(Integer id);
    Response create(T dto);
    Response update(T dto);
    Response delete(Integer id);
}
