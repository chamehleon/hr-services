package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Collections;
import java.util.List;

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

    // find all
    public List<Job> findAll(EntityManager entityManager, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> root = cq.from(Job.class);
        cq.select(root);

        TypedQuery<Job> query = entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);

        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }
}
