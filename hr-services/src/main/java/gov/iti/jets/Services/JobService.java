package gov.iti.jets.Services;

import gov.iti.jets.Exceptions.ExceptionMessages.BadArgumentException;
import gov.iti.jets.Persistence.DAOs.Implementations.JobDAO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Mappers.JobMapper;
import gov.iti.jets.Utils.JPATransactionManager;

import java.util.List;

public class JobService implements GenericService<JobDTO> {
    JobDAO jobDAO;

    public JobService() {
        jobDAO = new JobDAO();
    }
    // find job by title

    public JobDTO findJobByTitle(String title) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return JobMapper.INSTANCE.toDto(jobDAO.findByTitle(title, entityManager));
        });
    }

    public JobDTO getById(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return JobMapper.INSTANCE.toDto(jobDAO.findById(id, entityManager));
        });
    }
    // get all jobs
    public List<JobDTO> getAll(int page, int size) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            return (List<JobDTO>)JobMapper.INSTANCE.collectionToDto(jobDAO.findAll(entityManager, page, size));
        });
    }

    // add job
    public JobDTO create(JobDTO jobDTO) throws BadArgumentException {
        if (jobDTO.getMaxSalary() == null || jobDTO.getMinSalary() == null || jobDTO.getJobTitle() == null)
            throw new BadArgumentException("Some fields are missing");
        return JPATransactionManager.doInTransaction(entityManager -> {
            Job job = JobMapper.INSTANCE.toEntity(jobDTO);
            return JobMapper.INSTANCE.toDto(jobDAO.create(job, entityManager));
        });
    }

    // update job
    public JobDTO update(JobDTO jobDTO) throws BadArgumentException {
        if (jobDTO.getJobId() == null ||jobDTO.getMaxSalary() == null || jobDTO.getMinSalary() == null || jobDTO.getJobTitle() == null)
            throw new BadArgumentException("Some fields are missing");
        return JPATransactionManager.doInTransaction(entityManager -> {
            Job existing = jobDAO.findById(jobDTO.getJobId(), entityManager);
            Job newJob = (Job) JobMapper.INSTANCE.toEntity(jobDTO);
            return JobMapper.INSTANCE.toDto(jobDAO.update(newJob, entityManager));
        });
    }
    // partial update job
    public JobDTO partialUpdateJob(JobDTO jobDTO) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Job existing = jobDAO.findById(jobDTO.getJobId(), entityManager);
            Job newJob = (Job) JobMapper.INSTANCE.partialUpdate(jobDTO, existing);
            return JobMapper.INSTANCE.toDto(jobDAO.update(newJob, entityManager));
        });
    }
    // delete job
    public boolean delete(Integer id) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            Job job = jobDAO.findById(id, entityManager);
            if(job.isDeleted())
                throw new BadArgumentException("Job is already deleted");
            job.setDeleted(true);
            return jobDAO.update(job, entityManager) != null;
        });
    }
}
