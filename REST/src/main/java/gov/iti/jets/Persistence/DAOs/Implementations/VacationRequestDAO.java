package gov.iti.jets.Persistence.DAOs.Implementations;

import gov.iti.jets.Exceptions.ExceptionMessages.ResourceNotFoundException;
import gov.iti.jets.Persistence.DAOs.GenericDAOs.GenericDAO;
import gov.iti.jets.Persistence.Entities.Job;
import gov.iti.jets.Persistence.Entities.VacationRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Collections;
import java.util.List;

public class VacationRequestDAO extends GenericDAO<VacationRequest> {
    public VacationRequestDAO() {
        super(VacationRequest.class);
    }

    // get last pending request
    public VacationRequest getLastPendingRequest(int employeeId, EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VacationRequest> cq = cb.createQuery(VacationRequest.class);
        Root<VacationRequest> root = cq.from(VacationRequest.class);
        cq.select(root).where(
                cb.equal(root.get("employee").get("id"), employeeId),
                cb.equal(root.get("status"), "Pending"),
                cb.isFalse(root.get("archived"))
        );
        cq.orderBy(cb.desc(root.get("id"))); // Order by id in descending order
        TypedQuery<VacationRequest> query = entityManager.createQuery(cq);
        try {
            return query.setMaxResults(1).getSingleResult(); // Get the first result
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("No pending vacation requests found for employee with id: " + employeeId);
        }
    }







}
