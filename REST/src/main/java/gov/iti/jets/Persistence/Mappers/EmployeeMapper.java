package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Entities.Salary;
import gov.iti.jets.Services.DepartmentService;
import gov.iti.jets.Services.EmployeeService;
import gov.iti.jets.Services.JobService;
import gov.iti.jets.Services.SalaryService;
import jakarta.ws.rs.core.Context;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper extends GenericMapper<Employee, EmployeeDTO>{

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

//    @Context
//    JobService jobService = new JobService();
//
//    @Context
//    DepartmentService departmentService = new DepartmentService();


//    @Mapping(target = "job", source = "jobTitle", qualifiedByName = "mapJob")
//    @Mapping(target = "department", source = "departmentName", qualifiedByName = "mapDepartment")

    @AfterMapping
    default void toEntityAfterMapping(@MappingTarget Employee employee, EmployeeDTO employeeDTO) {
//        if (employeeDTO.getJobTitle() != null ){
//            JobService jobService = new JobService();
//            JobDTO job = jobService.findJobByTitle(employeeDTO.getJobTitle());
//            Job jobEntity = JobMapper.INSTANCE.toEntity(job);
//            employee.setJob(jobEntity);
//        }
//        if (employeeDTO.getDepartmentName() != null ) {
//            DepartmentService departmentService = new DepartmentService();
//            DepartmentDTO departmentDTO = departmentService.findDepartmentByName(employeeDTO.getDepartmentName());
//            employee.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDTO));
//        }
//        if (employeeDTO.getSalaryId() != null) {
//            SalaryService salaryService = new SalaryService();
//            Optional<SalaryDTO> salaryDTO = salaryDTO = salaryService.findSalaryById(employeeDTO.getSalaryId());
//            employee.setSalary(SalaryMapper.INSTANCE.toEntity(salaryDTO.get()));
//        }
//        if(employeeDTO.getManagedDepartmentName() != null){
//            DepartmentService departmentService = new DepartmentService();
//            DepartmentDTO departmentDTO = departmentService.findDepartmentByName(employeeDTO.getManagedDepartmentName());
//            employee.setManagedDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDTO));
//        }
//        if (employeeDTO.getManagerId() != null) {
//            System.out.println("Manager ID: " + employeeDTO.getManagerId());
//            EmployeeService employeeService = new EmployeeService();
//            EmployeeDTO managerDTO = employeeService.findEmployeeById(employeeDTO.getManagerId());
//            employee.setManager(EmployeeMapper.INSTANCE.toEntity(managerDTO));
//        }
    }
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