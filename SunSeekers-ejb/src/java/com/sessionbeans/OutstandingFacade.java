/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Outstanding;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ngu Công
 */
@Stateless
public class OutstandingFacade extends AbstractFacade<Outstanding> implements OutstandingFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OutstandingFacade() {
        super(Outstanding.class);
    }
    
    @Override
    public List<Outstanding> findOutstandingByTour(int tourId) {
        Query query = em.createQuery("SELECT o FROM Outstanding o WHERE o.tourId.tourId = :tour", Outstanding.class);
        query.setParameter("tour", tourId);
        System.out.println("Complate");
        return query.getResultList();
    }

    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM Outstanding o WHERE o.tourId.tourId =:tour", Outstanding.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }

    @Override
    public void deleteByAttractionId(int id) {
        Query query = em.createQuery("DELETE FROM Outstanding o WHERE o.attractionId.attractionId =:att", Outstanding.class);
        query.setParameter("att", id);
        query.executeUpdate();
    }

    @Override
    public List<Outstanding> findByAttraction(int id) {
        Query query = em.createQuery("SELECT o FROM Outstanding o WHERE o.tourId.tourId =:out", Outstanding.class);
        query.setParameter("out", id);
        return query.getResultList();
    }

    @Override
    public List<Outstanding> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Outstanding o");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Outstanding> findTourBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Outstanding o WHERE o.tourId.tourName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(o) FROM Outstanding o WHERE o.tourId.tourName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }
}
