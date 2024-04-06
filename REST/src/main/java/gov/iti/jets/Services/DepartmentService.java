package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
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
    public boolean changeDepartmentManager(int departmentId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeService employeeService = new EmployeeService();
            Department department = departmentDAO.findById(departmentId, entityManager);
            Employee manager = employeeService.getEmployeeById(4);

            boolean is = employeeService.updateEmployee(manager);
            department.setManager(manager);
            return departmentDAO.update(department, entityManager) != null;
        });
    }
}
