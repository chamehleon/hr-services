package gov.iti.jets.Persistence.DAOs.GenericDAOs;

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
        return entityManager.find(persistentClass, id);
    }

    public boolean create(T entity, EntityManager entityManager) {
        entityManager.persist(entity);
        return entityManager.contains(entity);
    }

    public T update(T entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    public void deleteById(int id, EntityManager entityManager) {
        T entity = findById(id, entityManager);
        delete(entity, entityManager);
    }

    public void delete(T entity, EntityManager entityManager) {
        entityManager.remove(entity);
    }
}