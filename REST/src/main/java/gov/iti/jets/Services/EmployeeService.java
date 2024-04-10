package gov.iti.jets.Services;


import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.SalaryDAO;
import gov.iti.jets.Persistence.DTOs.*;
import gov.iti.jets.Persistence.Entities.*;
import gov.iti.jets.Persistence.Mappers.*;
import gov.iti.jets.Utils.JPATransactionManager;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class EmployeeService implements GenericService<EmployeeDTO> {
    EmployeeDAO employeeDAO;
    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
    }



    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
            Salary createdSalary = entityManager.find(Salary.class, employeeDTO.getSalaryId());
            employee.setSalary(createdSalary);

            setJob(employeeDTO, employee);
            setDepartment(employeeDTO, employee);
            setManagedDepartment(employeeDTO, employee);
            setManager(employeeDTO, employee);
            return EmployeeMapper.INSTANCE.toDto(employeeDAO.create(employee, entityManager));
        });
    }


//    private void setSalary(EmployeeDTO employeeDTO, Employee employee) {
//        SalaryService salaryService = new SalaryService();
//        SalaryDTO salaryDTO = null;
//
//        if(employeeDTO.getSalaryId() != null)
//            salaryDTO = salaryService.getById(employeeDTO.getSalaryId());
//        if (salaryDTO != null) {
//            employee.setSalary(SalaryMapper.INSTANCE.toEntity(salaryDTO));
//        }else{
//            if(employeeDTO.getSalaryAmount() != null) {
//                Salary salary = new Salary();
//                salary.setPaymentAmount(employeeDTO.getSalaryAmount());
//                SalaryDTO createdSalary = salaryService.create(SalaryMapper.INSTANCE.toDto(salary));
////                SalaryDTO managedSalary = salaryService.update(createdSalary);
//                employee.setSalary(SalaryMapper.INSTANCE.toEntity(createdSalary));
//            }
//        }
//        System.out.println("msh 3arfa" +employee.getSalary().getPaymentAmount());
//
//
//
//    }


    private void setJob(EmployeeDTO employeeDTO, Employee employee) {
        if (employeeDTO.getJobTitle() != null) {
            JobService jobService = new JobService();
            JobDTO job = jobService.findJobByTitle(employeeDTO.getJobTitle());
            Job jobEntity = JobMapper.INSTANCE.toEntity(job);
            employee.setJob(jobEntity);
        }
    }

    private void setDepartment(EmployeeDTO employeeDTO, Employee employee) {
        if (employeeDTO.getDepartmentName() != null) {
            DepartmentService departmentService = new DepartmentService();
            DepartmentDTO departmentDTO = departmentService.findDepartmentByName(employeeDTO.getDepartmentName());
            employee.setDepartment(DepartmentMapper.INSTANCE.toEntity(departmentDTO));
        }
    }

    private void setManagedDepartment(EmployeeDTO employeeDTO, Employee employee) {
        if (employeeDTO.getManagedDepartmentName() != null) {
            DepartmentService departmentService = new DepartmentService();
            DepartmentDTO managedDepartmentDTO = departmentService.findDepartmentByName(employeeDTO.getManagedDepartmentName());
            Department managedDepartment = DepartmentMapper.INSTANCE.toEntity(managedDepartmentDTO);
            employee.setManagedDepartment(managedDepartment);
            managedDepartment.setManager(employee);
        }
    }

    private void setManager(EmployeeDTO employeeDTO, Employee employee) {
        if (employeeDTO.getManagerId() != null) {
            EmployeeService employeeService = new EmployeeService();
            EmployeeDTO managerDTO = employeeService.getById(employeeDTO.getManagerId());
            employee.setManager(EmployeeMapper.INSTANCE.toEntity(managerDTO));
        }
    }

    public EmployeeDTO getById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return EmployeeMapper.INSTANCE.toDto(employeeDAO.findById(id, entityManager));
        });
    }
    public Employee findEmployeeById1(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
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

    public boolean delete(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentService departmentService = new DepartmentService();
            Employee employee = employeeDAO.findById(id, entityManager);


            if (employee.getManagedDepartment() != null) {
                Department department = employee.getManagedDepartment();
                department.setManager(null);
            }
            // loop on all employees who are managed by this employee and set their manager to null
            List<Employee> employees = employeeDAO.getEmployeesManagedBy(id, entityManager);
            for (Employee e : employees) {
                e.setManager(null);
            }

            employeeDAO.deleteById(id, entityManager);


            return !entityManager.contains(employee);
        });
    }

    public List<EmployeeDTO> getAll(int page,int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {

            List<Employee> employees = employeeDAO.findAll(entityManager,page,size);
            return (List<EmployeeDTO>) EmployeeMapper.INSTANCE.collectionToDto(employees);

        });
    }

    public Set<JobHistoryDTO> getJobHistory(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {

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
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Employee existingEmployee = employeeDAO.findById(employeeDTO.getId(), entityManager);
            if (existingEmployee == null) {
                throw new IllegalUpdateOperationException("Employee not found");
            }

            Employee updatedEmployee = (Employee) EmployeeMapper.INSTANCE.partialUpdate(employeeDTO, existingEmployee);
            Salary createdSalary = entityManager.find(Salary.class, employeeDTO.getSalaryId());
            updatedEmployee.setSalary(createdSalary);
            setJob(employeeDTO, updatedEmployee);
            setDepartment(employeeDTO, updatedEmployee);
            setManagedDepartment(employeeDTO, updatedEmployee);
            setManager(employeeDTO, updatedEmployee);
            return EmployeeMapper.INSTANCE.toDto(employeeDAO.update(updatedEmployee, entityManager));
        });
    }

    // get empployee by id

    // get net salary
    public BigDecimal getNetSalary(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Employee employee = employeeDAO.findById(id, entityManager);
            Integer salaryId = employee.getSalary().getSalaryId();
            return salaryDAO.getNetSalary(salaryId, entityManager);
        });
    }

    public EmployeeDTO getManager(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Employee employee = employeeDAO.findById(id, entityManager);
            return EmployeeMapper.INSTANCE.toDto(employee.getManager());
        });
    }



}
