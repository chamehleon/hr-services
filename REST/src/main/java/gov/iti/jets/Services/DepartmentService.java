package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.DTOs.DepartmentDTO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Mappers.DepartmentMapper;
import gov.iti.jets.Persistence.Mappers.EmployeeMapper;
import gov.iti.jets.Utils.JPATransactionManager;

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
    public boolean changeDepartmentManager(Employee manager,int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeService employeeService = new EmployeeService();
            Department department = departmentDAO.findById(departmentId, entityManager);

            // TODO manager can have multiple departments?
            manager.setManagedDepartment(department);
            department.setManager(manager);
            // update manager
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
}
