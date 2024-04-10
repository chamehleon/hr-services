package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.LocationDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Services.EmployeeService;
import gov.iti.jets.Services.LocationService;
import jakarta.ws.rs.core.Context;
import org.mapstruct.AfterMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DepartmentMapper extends GenericMapper<Department, DepartmentDTO>{
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
//    @Context
//    EmployeeService employeeService = new EmployeeService();
//
//    @Context
//    LocationService locationService = new LocationService();

//    Department toEntity(DepartmentDTO departmentDTO);
//
//    DepartmentDTO toDto(Department employee);

    @AfterMapping
    default void toEntityAfterMapping(@MappingTarget Department department, DepartmentDTO departmentDTO) {
        // TODO check mapper annotation instead of el 3ak da
        if (departmentDTO.getManagerId() != null) {
            EmployeeService employeeService = new EmployeeService();
            Employee manager  = employeeService.findEmployeeById1(departmentDTO.getManagerId());
            department.setManager(manager);
        }
        if (departmentDTO.getLocationId() != null) {
            System.out.println("Location ID: " + departmentDTO.getLocationId());
            LocationService locationService = new LocationService();
            LocationDTO locationDTO = locationService.getById(departmentDTO.getLocationId());
            department.setLocation(LocationMapper.INSTANCE.toEntity(locationDTO));
        }
    }
}
