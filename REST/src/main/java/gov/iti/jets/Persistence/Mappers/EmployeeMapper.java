package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Services.DepartmentService;
import gov.iti.jets.Services.JobService;
import jakarta.ws.rs.core.Context;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper{

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

//    @Context
//    JobService jobService = new JobService();
//
//    @Context
//    DepartmentService departmentService = new DepartmentService();


//    @Mapping(target = "job", source = "jobTitle", qualifiedByName = "mapJob")
//    @Mapping(target = "department", source = "departmentName", qualifiedByName = "mapDepartment")
    Employee toEntity(EmployeeDTO employeeDTO);

    EmployeeDTO toDto(Employee employee);

    List<EmployeeDTO> toDtoList(List<Employee> employees);

//    @AfterMapping
//    default void toEntityAfterMapping(@MappingTarget Employee employee, EmployeeDTO employeeDTO) {
//        if (employeeDTO.getJobTitle() != null) {
//            JobDTO job = jobService.findJobByTitle(employeeDTO.getJobTitle());
//            Job jobEntity = JobMapper.INSTANCE.toEntity(job);
//            employee.setJob(jobEntity);
//        }
//        if (employeeDTO.getDepartmentName() != null) {
//            DepartmentDTO departmentDTO = departmentService.findDepartmentByName(employeeDTO.getDepartmentName());
//            employee.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDTO));
//        }
//
//    }
//    @Named("mapJob")
//    default Job mapJob(String jobTitle) {
//        JobDTO jobDTO = jobService.findJobByTitle(jobTitle);
//        System.out.println("Job: " + jobDTO.getJobTitle());
//        return JobMapper.INSTANCE.toEntity(jobDTO);
//
//    }
//
//    @Named("mapDepartment")
//    default Department mapDepartment(String departmentName) {
//        DepartmentDTO departmentDTO = departmentService.findDepartmentByName(departmentName);
//        return DepartmentMapper.INSTANCE.toEntity(departmentDTO);
//    }

}