package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.Entities.Location;
import gov.iti.jets.Persistence.Entities.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SalaryMapper extends GenericMapper<Salary, SalaryDTO>{
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    @Override
    default void toEntityAfterMapping(Salary salary, SalaryDTO salaryDTO) {
    }

}
