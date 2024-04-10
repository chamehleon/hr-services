package gov.iti.jets.Persistence.DAOs.GenericDAOs;

import gov.iti.jets.Exceptions.ExceptionMessages.IllegalCreateOperationException;
import gov.iti.jets.Exceptions.ExceptionMessages.IllegalDeleteOperationException;
import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import gov.iti.jets.Exceptions.ExceptionMessages.ResourceNotFoundException;
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

public abstract class GenericDAO<T> {
    protected Class<T> persistentClass;
    //protected final EntityManager entityManager;

    //    //protected GenericDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
//        this.entityManager = entityManager;
    }

    public List<T> findAll(EntityManager entityManager) {
        return entityManager.createQuery("from " + persistentClass.getName(), persistentClass)
                .getResultList();
    }

    public T findById(int id, EntityManager entityManager) {
        boolean isFound = entityManager.find(persistentClass, id) != null;
        if (!isFound) {
            throw new ResourceNotFoundException("No Entity found for " + persistentClass.getSimpleName() );
        }else{
            return entityManager.find(persistentClass, id);
        }
    }

    public T create(T entity, EntityManager entityManager) {
        try{
            entityManager.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
          // throw new IllegalCreateOperationException("Invalid Save Operation On " + entity.getClass().getSimpleName() );
        }
        return entity;
    }

    public T update(T entity, EntityManager entityManager) {
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            throw new IllegalUpdateOperationException("Invalid Update Operation On " + entity.getClass().getSimpleName());
        }

        return entity;
    }

    public void deleteById(int id, EntityManager entityManager) {
        T entity = findById(id, entityManager);
        System.out.println("Entity found: " + entity);

        delete(entity, entityManager);
    }

    public boolean delete(T entity, EntityManager entityManager) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            throw new IllegalDeleteOperationException("Invalid Delete Operation On " + entity.getClass().getSimpleName());
        }
        entityManager.flush();
        return !entityManager.contains(entity);
    }
    public List<T> findAll(EntityManager entityManager, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        Root<T> root = cq.from(persistentClass);
        cq.select(root);

        TypedQuery<T> query = entityManager.createQuery(cq)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);

        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }
}