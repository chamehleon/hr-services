package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Mappers.EmployeeMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;

public class EmployeeService {


    // TODO employee DTO
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);

            return employeeDAO.create(employee, entityManager);
        });
    }

    public Employee getEmployeeById(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.findById(id, entityManager);
            System.out.println(employee.getFirstName());
            System.out.println(employee.getJob().getJobTitle());
            System.out.println(employee.getFirstName());
            System.out.println(employee.getSalary().getPaymentAmount());
            return employee;
        });
    }

    public boolean updateEmployee(Employee employee) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            return employeeDAO.update(employee, entityManager) != null;
        });
    }

    public boolean deleteEmployee(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentService departmentService = new DepartmentService();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.findById(id, entityManager);
            if (employee.getManagedDepartment() != null) {
                // Change the manager of the department
                departmentService.changeDepartmentManager(employee.getManagedDepartment().getId());
            }
            employeeDAO.deleteById(id, entityManager);


            return employeeDAO.findById(id, entityManager) == null;
        });
    }

    public List<EmployeeDTO> getAllEmployees() {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.findAll(entityManager);
            return EmployeeMapper.INSTANCE.toDtoList(employees);

        });
    }




}
