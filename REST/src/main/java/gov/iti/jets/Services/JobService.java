package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.JobDAO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Mappers.JobMapper;
import gov.iti.jets.Utils.JPATransactionManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.GET;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JobService {
    // find job by title

    public JobDTO findJobByTitle(String title) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            return JobMapper.INSTANCE.toDto(jobDAO.findByTitle(title, entityManager));
        });
    }

    public JobDTO findJobById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            return JobMapper.INSTANCE.toDto(jobDAO.findById(id, entityManager));
        });
    }
    // get all jobs
    public List<JobDTO> findAll(int page, int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            return (List<JobDTO>)JobMapper.INSTANCE.collectionToDto(jobDAO.findAll(entityManager, page, size));
        });
    }

    // add job
    public boolean addJob(JobDTO jobDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            Job job = JobMapper.INSTANCE.toEntity(jobDTO);
            return jobDAO.create(job, entityManager);
        });
    }

    // update job
    public JobDTO updateJob(JobDTO jobDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            Job existing = jobDAO.findById(jobDTO.getJobHistoryId(), entityManager);
            Job newJob = (Job) JobMapper.INSTANCE.toEntity(jobDTO);
            return JobMapper.INSTANCE.toDto(jobDAO.update(newJob, entityManager));
        });
    }
    // partial update job
    public JobDTO partialUpdateJob(JobDTO jobDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            Job existing = jobDAO.findById(jobDTO.getJobHistoryId(), entityManager);
            Job newJob = (Job) JobMapper.INSTANCE.partialUpdate(jobDTO, existing);
            return JobMapper.INSTANCE.toDto(jobDAO.update(newJob, entityManager));
        });
    }
    // delete job
    public boolean deleteJob(int id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            Job job = jobDAO.findById(id, entityManager);
            job.setDeleted(true);
            return jobDAO.update(job, entityManager) != null;
        });
    }
}
