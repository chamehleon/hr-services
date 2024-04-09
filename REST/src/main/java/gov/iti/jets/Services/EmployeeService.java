package gov.iti.jets.Services;


import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.SalaryDAO;
import gov.iti.jets.Persistence.DTOs.*;
import gov.iti.jets.Persistence.Entities.*;
import gov.iti.jets.Persistence.Mappers.*;
import gov.iti.jets.Utils.JPATransactionManager;

import java.lang.reflect.Field;
import java.util.*;

public class EmployeeService {


    // TODO employee DTO
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
           // setSalary(employeeDTO, employee);
            //setJob(employeeDTO, employee);
           // setDepartment(employeeDTO, employee);
           // setManagedDepartment(employeeDTO, employee);
            return employeeDAO.create(employee, entityManager);
        });
    }


    // set salary
//    private void setSalary(EmployeeDTO employeeDTO, Employee employee) {
//        SalaryService salaryService = new SalaryService();
//        Optional<SalaryDTO> salaryDTO = Optional.empty();

//        if(employeeDTO.getSalaryId() != null)
//            salaryDTO = salaryService.findSalaryById(employeeDTO.getSalaryId());
//        if (salaryDTO.isPresent()) {
//            employee.setSalary(SalaryMapper.INSTANCE.toEntity(salaryDTO.get()));
//        }else{
//            Salary salary = new Salary();
//            salary.setPaymentAmount(employeeDTO.getSalaryAmount());
//            salaryService.createSalary(salary);
//            employee.setSalary(salary);
//        }
//        System.out.println("msh 3arfa" +employee.getSalary().getPaymentAmount());
//
//
//
//    }


//    private void setJob(EmployeeDTO employeeDTO, Employee employee) {
//        if (employeeDTO.getJobTitle() != null) {
//            JobService jobService = new JobService();
//            JobDTO job = jobService.findJobByTitle(employeeDTO.getJobTitle());
//            Job jobEntity = JobMapper.INSTANCE.toEntity(job);
//            employee.setJob(jobEntity);
//        }
//    }

//    private void setDepartment(EmployeeDTO employeeDTO, Employee employee) {
//        if (employeeDTO.getDepartmentName() != null) {
//            DepartmentService departmentService = new DepartmentService();
//            DepartmentDTO departmentDTO = departmentService.findDepartmentByName(employeeDTO.getDepartmentName());
//            employee.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDTO));
//        }
//    }

//    private void setManagedDepartment(EmployeeDTO employeeDTO, Employee employee) {
//        if (employeeDTO.getManagedDepartmentName() != null) {
//            DepartmentService departmentService = new DepartmentService();
//            DepartmentDTO managedDepartmentDTO = departmentService.findDepartmentByName(employeeDTO.getManagedDepartmentName());
//            Department managedDepartment = DepartmentMapper.INSTANCE.toEntity(managedDepartmentDTO);
//            employee.setManagedDepartment(managedDepartment);
//            managedDepartment.setManager(employee);
//        }
//    }

    public EmployeeDTO findEmployeeById(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            return EmployeeMapper.INSTANCE.toDto(employeeDAO.findById(id, entityManager));
        });
    }
    public Employee findEmployeeById1(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            return employeeDAO.findById(id, entityManager);
        });
    }

//    public boolean updateEmployee(EmployeeDTO employeeDTO) {
//        return JPATransactionManager.doInTransaction(entityManager -> {
//            EmployeeDAO employeeDAO = new EmployeeDAO();
//            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
//            SalaryDAO salaryDAO = new SalaryDAO();
//
//            setSalary(employeeDTO, employee);
//
//
//
//
//            System.out.println("eh da ya3am??"+employee.getSalary().getPaymentAmount());
//            System.out.println("ehhhhh");
//            setDepartment(employeeDTO, employee);
//            setJob(employeeDTO, employee);
//            setManagedDepartment(employeeDTO, employee);
//
//
//
//            return employeeDAO.update(employee, entityManager) != null;
//        });
//    }

    public boolean deleteEmployee(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentService departmentService = new DepartmentService();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.findById(id, entityManager);


            if (employee.getManagedDepartment() != null) {
                // TODO Change the manager of the department
                Department department = employee.getManagedDepartment();
                department.setManager(null);
            }
            employeeDAO.deleteById(id, entityManager);


            return employeeDAO.findById(id, entityManager) == null;
        });
    }

    public List<EmployeeDTO> getAllEmployees() {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.findAll(entityManager);
            return (List<EmployeeDTO>) EmployeeMapper.INSTANCE.collectionToDto(employees);

        });
    }

    public Set<JobHistoryDTO> getJobHistory(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Collection<JobHistory> jobHistory = employeeDAO.getJobHistory(id, entityManager);
            return new LinkedHashSet<>(JobHistoryMapper.INSTANCE.collectionToDto(jobHistory));
        });
    }

//    public boolean updateEmployee(int id, Map<String, Object> updates) {
//        return JPATransactionManager.doInTransaction(entityManager -> {
//            EmployeeDAO employeeDAO = new EmployeeDAO();
//            Employee employee = employeeDAO.findById(id, entityManager);
//            if (employee == null) {
//                return false;
//            }
//
//            for (Map.Entry<String, Object> entry : updates.entrySet()) {
//                try {
//                    Field field = Employee.class.getDeclaredField(entry.getKey());
//                    field.setAccessible(true);
//                    field.set(employee, entry.getValue());
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//            }
//
//            return employeeDAO.update(employee, entityManager) != null;
//        });
//    }
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee existingEmployee = employeeDAO.findById(employeeDTO.getId(), entityManager);
            if (existingEmployee == null) {
                return false;
            }


            Employee updatedEmployee = (Employee) EmployeeMapper.INSTANCE.partialUpdate(employeeDTO, existingEmployee);
            //setSalary(employeeDTO, updatedEmployee);
            //setJob(employeeDTO, updatedEmployee);
            //setDepartment(employeeDTO, updatedEmployee);
            //setManagedDepartment(employeeDTO, updatedEmployee);
            return employeeDAO.update(updatedEmployee, entityManager) != null;
        });
    }




}
