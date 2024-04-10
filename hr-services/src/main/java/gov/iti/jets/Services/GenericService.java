package gov.iti.jets.Services;


import gov.iti.jets.Exceptions.ExceptionMessages.BadArgumentException;

import java.util.List;

public interface GenericService <T>{

        T getById(Integer id);
        List<T> getAll(int page, int size);
        T create(T dto) throws BadArgumentException;
        T update(T dto) throws BadArgumentException;
        boolean delete(Integer id) throws BadArgumentException;

}
