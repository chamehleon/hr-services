package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.JobHistory;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Set;

public class EmployeeDAO extends GenericDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    // get job history of employe by id
    public Set<JobHistory> getJobHistory(int id, EntityManager entityManager) {
        try {
            Employee employee = entityManager.find(Employee.class, id);
            return employee.getJobhistories();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    // getEmployeesManagedBy manager id
    public List<Employee> getEmployeesManagedBy(int id, EntityManager entityManager) {
        try {
            return entityManager.createQuery("select e from Employee e where e.manager.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}









