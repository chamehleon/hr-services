package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class JobDAO extends GenericDAO<Job> {
    public JobDAO() {
        super(Job.class);
    }

    public Job findByTitle(String title, EntityManager entityManager) {
        try {
            return entityManager.createQuery("SELECT j FROM Job j WHERE j.jobTitle = :title", Job.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
