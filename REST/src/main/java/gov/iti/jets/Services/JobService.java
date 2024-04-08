package gov.iti.jets.Services;

import gov.iti.jets.Persistence.DAOs.Implementations.JobDAO;
import gov.iti.jets.Persistence.DTOs.JobDTO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Mappers.JobMapper;
import gov.iti.jets.Utils.JPATransactionManager;

public class JobService {
    // find job by title
    public JobDTO findJobByTitle(String title) {
        return JPATransactionManager.doInTransaction(entityManager -> {
            JobDAO jobDAO = new JobDAO();
            return JobMapper.INSTANCE.toDto(jobDAO.findByTitle(title, entityManager));
        });
    }
}
