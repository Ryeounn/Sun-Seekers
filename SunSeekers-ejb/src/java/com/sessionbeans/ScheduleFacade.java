/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Schedule;
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
public class ScheduleFacade extends AbstractFacade<Schedule> implements ScheduleFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScheduleFacade() {
        super(Schedule.class);
    }
    
    @Override
    public List<Schedule> findByTourId(int tourId) {
        String sql = "SELECT s FROM Schedule s WHERE s.tourId.tourId = :tour";
        Query query = em.createQuery(sql,Schedule.class);
        query.setParameter("tour", tourId);
        return query.getResultList();
    }

    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM Schedule s WHERE s.tourId.tourId =:tour", Schedule.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }

    @Override
    public List<Schedule> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT s FROM Schedule s");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findUserBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT s FROM Schedule s WHERE s.sName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(s) FROM Schedule s WHERE s.sName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }
}
