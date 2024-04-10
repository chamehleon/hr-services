package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Salary;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class SalaryDAO extends GenericDAO<Salary> {
    public SalaryDAO() {
        super(Salary.class);
    }

    // get net salary
    public BigDecimal getNetSalary(Integer salaryId, EntityManager entityManager) {
        return entityManager.createQuery("SELECT s.paymentAmount + s.bonuses - s.deductions FROM Salary s WHERE s.salaryId = :salaryId", BigDecimal.class)
                .setParameter("salaryId", salaryId)
                .getSingleResult();
    }



}
