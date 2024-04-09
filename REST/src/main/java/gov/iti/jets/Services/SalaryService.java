package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.SalaryDAO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.SalaryDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.Salary;
import gov.iti.jets.Persistence.Mappers.SalaryMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalaryService {
    public Optional<SalaryDTO> findSalaryById(Integer salaryId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Optional<Salary> salary = Optional.ofNullable(salaryDAO.findById(salaryId, entityManager));
            return salary.map(SalaryMapper.INSTANCE::toDto);
        });
    }
    // create salary
    public boolean createSalary(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = SalaryMapper.INSTANCE.toEntity(salaryDTO);
            return salaryDAO.create(salary, entityManager);
        });
    }
    // get all salaries
    public List<SalaryDTO> getAllSalaries() {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.findAll(entityManager);
            return salaries.stream().map(SalaryMapper.INSTANCE::toDto).collect(Collectors.toList());
        });
    }
    // update salary
    public SalaryDTO updateSalary(Integer salaryId, SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary newSalary = (Salary) SalaryMapper.INSTANCE.toEntity(salaryDTO);
            salaryDAO.update(newSalary, entityManager);
            return SalaryMapper.INSTANCE.toDto(newSalary);
        });
    }
    // partial update salary
    public SalaryDTO partialUpdateSalary(Integer salaryId, SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary existing = salaryDAO.findById(salaryId, entityManager);
            Salary newSalary = (Salary) SalaryMapper.INSTANCE.partialUpdate(salaryDTO, existing);
            salaryDAO.update(newSalary, entityManager);
            return SalaryMapper.INSTANCE.toDto(newSalary);
        });
    }
    // delete salary
    public SalaryDTO deleteSalary(Integer salaryId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.findById(salaryId, entityManager);
            salary.setDeleted(true);
            return  SalaryMapper.INSTANCE.toDto(salary);
        });
    }
    // get employees with salary in range

}
