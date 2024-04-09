package gov.iti.jets.Persistence.DAOs.GenericDAOs;

import gov.iti.jets.Exceptions.ExceptionMessages.IllegalCreateOperationException;
import gov.iti.jets.Exceptions.ExceptionMessages.IllegalUpdateOperationException;
import gov.iti.jets.Exceptions.ExceptionMessages.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

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

    public boolean create(T entity, EntityManager entityManager) {
        try{
            entityManager.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new IllegalCreateOperationException("Invalid Save Operation On " + entity.getClass().getSimpleName() );
        }
        return entityManager.contains(entity);
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
        delete(entity, entityManager);
    }

    public boolean delete(T entity, EntityManager entityManager) {
        entityManager.remove(entity);
        entityManager.flush();
        return !entityManager.contains(entity);
    }
}