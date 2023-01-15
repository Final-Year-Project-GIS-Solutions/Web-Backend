package com.gissolution.webdata.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityMangerWrapper {

    private final EntityManagerFactory entityManagerFactory;

    final ThreadLocal<EntityManager> entityMangerThreadLocal = new ThreadLocal<EntityManager>();

    public EntityMangerWrapper(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }


    public EntityManager getEntityManager() {
        EntityManager entityManager = entityMangerThreadLocal.get();
        if(entityManager == null || !entityManager.isOpen()){
            entityManager = entityManagerFactory.createEntityManager();
            entityMangerThreadLocal.set(entityManager);
            entityManager.getTransaction().begin();
        }
        return entityManager;
    }

    public void onSuccess(){
        getEntityManager().getTransaction().commit();
    }

    public void onFailure(){
        getEntityManager().getTransaction().rollback();
    }

    public void finallyDo(){
        getEntityManager().close();
        entityMangerThreadLocal.set(null);
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public int getStart(int page) {
        if(page <= 0) {
            return 0;
        }else {
            return (page-1)*getCountPerPage();
        }
    }

    public int getCountPerPage() {
        return 50;
    }
}
