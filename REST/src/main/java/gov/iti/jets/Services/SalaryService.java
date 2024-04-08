package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.SalaryDAO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.Entities.Salary;
import gov.iti.jets.Persistence.Mappers.SalaryMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.Optional;

public class SalaryService {
    public Optional<SalaryDTO> findSalaryById(Integer salaryId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Optional<Salary> salary = Optional.ofNullable(salaryDAO.findById(salaryId, entityManager));
            return salary.map(SalaryMapper.INSTANCE::toDto);
        });
    }
    // create salary
    public void createSalary(Salary salary) {
        JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            return salaryDAO.create(salary, entityManager);
        });
    }
}
