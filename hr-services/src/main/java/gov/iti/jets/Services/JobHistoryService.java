package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.JobHistoryDAO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Entities.JobHistory;
import gov.iti.jets.Persistence.Mappers.JobHistoryMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;
import java.util.stream.Collectors;

public class JobHistoryService implements GenericService<JobHistoryDTO>{
    public JobHistoryDTO create(JobHistoryDTO jobHistoryDTO){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            return JobHistoryMapper.INSTANCE.toDto(jobHistoryDAO.create(JobHistoryMapper.INSTANCE.toEntity(jobHistoryDTO),entityManager));
        });
    }

    public JobHistoryDTO getById(Integer id){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            return JobHistoryMapper.INSTANCE.toDto(jobHistoryDAO.findById(id,entityManager));
        });
    }

    public JobHistoryDTO update(JobHistoryDTO jobHistoryDTO){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            JobHistory existing = jobHistoryDAO.findById(jobHistoryDTO.getJobHistoryId(),entityManager);
            JobHistory newJobHistory = (JobHistory) JobHistoryMapper.INSTANCE.partialUpdate(jobHistoryDTO,existing);
            jobHistoryDAO.update(newJobHistory,entityManager);
            return JobHistoryMapper.INSTANCE.toDto(newJobHistory);
        });
    }

    public boolean delete(Integer id){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            JobHistory jobHistory = jobHistoryDAO.findById(id,entityManager);
            System.out.println("ummm" + jobHistory.getJobHistoryId());
            return jobHistoryDAO.delete(jobHistory,entityManager);
        });
    }

    public List<JobHistoryDTO> getAll(int page,int size){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            List<JobHistory> jobHistories = jobHistoryDAO.findAll(entityManager,page,size);
            return jobHistories.stream().map(JobHistoryMapper.INSTANCE::toDto).collect(Collectors.toList());
        });
    }
}
