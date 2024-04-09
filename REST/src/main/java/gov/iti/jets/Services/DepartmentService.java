package gov.iti.jets.Services;

import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Mappers.DepartmentMapper;
import gov.iti.jets.Persistence.Mappers.EmployeeMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DepartmentService {

    // delete department
    public boolean deleteDepartment(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            departmentDAO.deleteById(id, entityManager);
            return departmentDAO.findById(id, entityManager) == null;
        });
    }

    // change department manager
    public boolean changeDepartmentManager(Integer managerId, Integer departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            EmployeeService employeeService = new EmployeeService();
            Department department = departmentDAO.findById(departmentId, entityManager);
            Employee manager = employeeDAO.findById(managerId, entityManager);
            // TODO manager can have multiple departments?
            if (manager.getManagedDepartment() != null && !Objects.equals(manager.getManagedDepartment().getId(), departmentId)){
                throw new IllegalUpdateOperationException("Manager already manages a department");
            }
            manager.setManagedDepartment(department);
            department.setManager(manager);
            employeeService.updateEmployee(EmployeeMapper.INSTANCE.toDto(manager));
            return departmentDAO.update(department, entityManager) != null;
        });
    }
    // find department by name
    public DepartmentDTO findDepartmentByName(String name) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            return DepartmentMapper.INSTANCE.toDto(departmentDAO.findByDepartmentName(name, entityManager));
        });
    }


    public List<DepartmentDTO> getAllDepartments() {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            return (List<DepartmentDTO>) DepartmentMapper.INSTANCE.collectionToDto(departmentDAO.findAll(entityManager));
        });
    }
    // get employees in department
    public Set<EmployeeDTO> getEmployeesInDepartment(int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.findById(departmentId, entityManager);
            return new HashSet<>(EmployeeMapper.INSTANCE.collectionToDto(department.getEmployees()));
        });
    }
    // get department manager
    public EmployeeDTO getDepartmentManager(int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.findById(departmentId, entityManager);
            return EmployeeMapper.INSTANCE.toDto(department.getManager());
        });
    }

    // add new department
    public boolean addDepartment(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = DepartmentMapper.INSTANCE.toEntity(departmentDTO);
            return departmentDAO.create(department, entityManager);
        });
    }
}
