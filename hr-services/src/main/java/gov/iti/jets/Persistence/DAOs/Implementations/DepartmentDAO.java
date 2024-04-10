package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import jakarta.persistence.EntityManager;

public class DepartmentDAO extends GenericDAO<Department> {


    public DepartmentDAO() {
        super(Department.class);
    }

    public Department findByDepartmentName(String departmentName, EntityManager entityManager) {
        try {
            return entityManager.createQuery("SELECT d FROM Department d WHERE d.departmentName = :departmentName", Department.class)
                    .setParameter("departmentName", departmentName)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    // change department manager



}
