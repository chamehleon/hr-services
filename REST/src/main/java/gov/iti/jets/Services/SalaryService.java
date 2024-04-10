package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.SalaryDAO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Salary;
import gov.iti.jets.Persistence.Mappers.SalaryMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalaryService implements GenericService<SalaryDTO> {
    public SalaryDTO getById(Integer salaryId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.findById(salaryId, entityManager);
            return SalaryMapper.INSTANCE.toDto(salary);
        });
    }
    // create salary
    public SalaryDTO create(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = SalaryMapper.INSTANCE.toEntity(salaryDTO);
            return SalaryMapper.INSTANCE.toDto(salaryDAO.create(salary, entityManager));
        });
    }
    // get all salaries
    public List<SalaryDTO> getAll(int page,int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.findAll(entityManager, page, size);
            return salaries.stream().map(SalaryMapper.INSTANCE::toDto).collect(Collectors.toList());
        });
    }
    // update salary
    public SalaryDTO update(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary newSalary = (Salary) SalaryMapper.INSTANCE.toEntity(salaryDTO);
            salaryDAO.update(newSalary, entityManager);
            return SalaryMapper.INSTANCE.toDto(newSalary);
        });
    }
    // partial update salary
    public SalaryDTO partialUpdate(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary existing = salaryDAO.findById(salaryDTO.getSalaryId(), entityManager);
            Salary newSalary = (Salary) SalaryMapper.INSTANCE.partialUpdate(salaryDTO, existing);
            salaryDAO.update(newSalary, entityManager);
            return SalaryMapper.INSTANCE.toDto(newSalary);
        });
    }
    // delete salary
    public boolean delete(Integer salaryId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.findById(salaryId, entityManager);
            salary.setDeleted(true);
            return salary.isDeleted();
        });
    }
    // add bonus to salary
    public SalaryDTO addBonusToSalary(Integer id, BigDecimal bonus) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary existing = salaryDAO.findById(id, entityManager);
            existing.setBonuses(existing.getBonuses().add(bonus));
            salaryDAO.update(existing, entityManager);
            return SalaryMapper.INSTANCE.toDto(existing);
        });
    }

    // add deduction to salary
    public SalaryDTO deductFromSalary(Integer id, BigDecimal deduction) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary existing = salaryDAO.findById(id, entityManager);
            existing.setDeductions(existing.getDeductions().add(deduction));
            salaryDAO.update(existing, entityManager);
            return SalaryMapper.INSTANCE.toDto(existing);
        });
    }

}
