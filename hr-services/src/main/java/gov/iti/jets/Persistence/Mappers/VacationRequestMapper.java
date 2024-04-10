package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DAOs.Implementations.VacationRequestDAO;
import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.DTOs.VacationRequestDTO;
import gov.iti.jets.Persistence.Entities.Location;
import gov.iti.jets.Persistence.Entities.Salary;
import gov.iti.jets.Persistence.Entities.VacationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VacationRequestMapper extends GenericMapper<VacationRequest, VacationRequestDTO>{
    VacationRequestMapper INSTANCE = Mappers.getMapper(VacationRequestMapper.class);

    @Override
    default void toEntityAfterMapping(VacationRequest vacationRequest, VacationRequestDTO VacationRequestDTO) {
    }


}
