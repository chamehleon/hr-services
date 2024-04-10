package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.EmployeeDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.JobDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.VacationRequestDAO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.DTOs.VacationRequestDTO;
import gov.iti.jets.Persistence.Entities.Employee;
import gov.iti.jets.Persistence.Entities.VacationRequest;
import gov.iti.jets.Persistence.Mappers.JobMapper;
import gov.iti.jets.Persistence.Mappers.VacationRequestMapper;
import gov.iti.jets.Utils.JPATransactionManager;
import java.time.temporal.ChronoUnit;

import java.time.Duration;
import java.util.List;

public class VacationRequestService implements GenericService<VacationRequestDTO> {
    VacationRequestDAO vacationRequestDAO;

    public VacationRequestService() {
       vacationRequestDAO = new VacationRequestDAO();
    }


    public List<VacationRequestDTO> getAll(int page, int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {

            return (List<VacationRequestDTO>) VacationRequestMapper.INSTANCE.collectionToDto(vacationRequestDAO.findAll(entityManager, page, size));
        });
    }

    public VacationRequestDTO getById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return VacationRequestMapper.INSTANCE.toDto(vacationRequestDAO.findById(id,entityManager));
        });
    }

    // Implement createVacationRequest method
    public VacationRequestDTO create(VacationRequestDTO vacationRequestDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            VacationRequest vacationRequest = VacationRequestMapper.INSTANCE.toEntity(vacationRequestDTO);
            vacationRequestDAO.create(vacationRequest, entityManager);
            return VacationRequestMapper.INSTANCE.toDto(vacationRequest);
        });
    }

    // Implement updateVacationRequest method
    public VacationRequestDTO update(VacationRequestDTO vacationRequestDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            VacationRequest vacationRequest = VacationRequestMapper.INSTANCE.toEntity(vacationRequestDTO);
            vacationRequestDAO.update(vacationRequest, entityManager);
            return VacationRequestMapper.INSTANCE.toDto(vacationRequest);
        });
    }

    // Implement deleteVacationRequest method
    public boolean delete(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            VacationRequest vacationRequest = vacationRequestDAO.findById(id, entityManager);
            vacationRequest.setArchived(true);
            return vacationRequest.isArchived();
        });
    }

    // Implement handleVacationRequest method to accept or reject the last pending vacation request of an employee accoding to his vacation balance and the vacation request dates
    public VacationRequestDTO handleVacationRequest(int employeeId) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.findById(employeeId, entityManager);
            VacationRequest lastPendingRequest = vacationRequestDAO.getLastPendingRequest(employeeId, entityManager);
            Integer requestedDays = Long.valueOf(ChronoUnit.DAYS.between(lastPendingRequest.getStartDate(), lastPendingRequest.getEndDate())).intValue();
            if (employee.getVacationBalance() >= requestedDays) {
                lastPendingRequest.setStatus("Approved");
                employee.setVacationBalance((employee.getVacationBalance() - requestedDays));

            } else {
                lastPendingRequest.setStatus("Rejected");
            }
            return VacationRequestMapper.INSTANCE.toDto(lastPendingRequest);
        });
    }
}
