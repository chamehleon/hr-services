package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Services.EmployeeService;
import gov.iti.jets.Services.LocationService;
import jakarta.ws.rs.core.Context;
import org.mapstruct.AfterMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
//    @Context
//    EmployeeService employeeService = new EmployeeService();
//
//    @Context
//    LocationService locationService = new LocationService();

    Department toEntity(DepartmentDTO departmentDTO);

    DepartmentDTO toDto(Department employee);

//    @AfterMapping
//    default void toEntityAfterMapping(Department department, DepartmentDTO departmentDTO) {
//        if (departmentDTO.getManagerId() != null) {
//            EmployeeDTO employeeDTO = employeeService.findEmployeeById(departmentDTO.getManagerId());
//            department.setManager(EmployeeMapper.INSTANCE.toEntity(employeeDTO));
//        }
//        if (departmentDTO.getLocationId() != null) {
//            LocationDTO locationDTO = locationService.findLocationById(departmentDTO.getLocationId());
//            department.setLocation(LocationMapper.INSTANCE.toEntity(locationDTO));
//        }
//    }
}
