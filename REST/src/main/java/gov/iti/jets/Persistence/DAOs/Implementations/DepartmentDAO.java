package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Department;
import gov.iti.jets.Persistence.Entities.Employee;
import jakarta.persistence.EntityManager;

public class DepartmentDAO extends GenericDAO<Department> {


    public DepartmentDAO() {
        super(Department.class);
    }
    // change department manager



}
