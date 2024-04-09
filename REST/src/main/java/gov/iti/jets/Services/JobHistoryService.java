package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.DepartmentDAO;
import gov.iti.jets.Persistence.DAOs.Implementations.JobHistoryDAO;
import gov.iti.jets.Persistence.DTOs.JobHistoryDTO;
import gov.iti.jets.Persistence.Entities.JobHistory;
import gov.iti.jets.Persistence.Mappers.JobHistoryMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;
import java.util.stream.Collectors;

public class JobHistoryService {
    public boolean addJobHistory(JobHistoryDTO jobHistoryDTO){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            return  jobHistoryDAO.create(JobHistoryMapper.INSTANCE.toEntity(jobHistoryDTO),entityManager);
        });
    }

    public JobHistoryDTO getJobHistory(Integer id){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            return JobHistoryMapper.INSTANCE.toDto(jobHistoryDAO.findById(id,entityManager));
        });
    }

    public JobHistoryDTO updateJobHistory(JobHistoryDTO jobHistoryDTO){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            JobHistory existing = jobHistoryDAO.findById(jobHistoryDTO.getJobHistoryId(),entityManager);
            JobHistory newJobHistory = (JobHistory) JobHistoryMapper.INSTANCE.partialUpdate(jobHistoryDTO,existing);
            jobHistoryDAO.update(newJobHistory,entityManager);
            return JobHistoryMapper.INSTANCE.toDto(newJobHistory);
        });
    }

    public boolean deleteJobHistory(Integer id){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            JobHistory jobHistory = jobHistoryDAO.findById(id,entityManager);
            System.out.println("ummm" + jobHistory.getJobHistoryId());
            return jobHistoryDAO.delete(jobHistory,entityManager);
        });
    }

    public List<JobHistoryDTO> getAllJobHistories(){
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobHistoryDAO jobHistoryDAO = new JobHistoryDAO();
            List<JobHistory> jobHistories = jobHistoryDAO.findAll(entityManager);
            return jobHistories.stream().map(JobHistoryMapper.INSTANCE::toDto).collect(Collectors.toList());
        });
    }
}
