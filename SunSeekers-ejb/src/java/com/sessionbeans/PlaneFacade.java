/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Plane;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ngu Công
 */
@Stateless
public class PlaneFacade extends AbstractFacade<Plane> implements PlaneFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaneFacade() {
        super(Plane.class);
    }
    
    @Override
    public List<Plane> findPlaneDepart() {
        String sql = "SELECT DISTINCT p.planeDepart FROM Plane p WHERE p.planeDepart IN ('Ha Noi', 'Ho Chi Minh')";
        Query query = em.createQuery(sql, Plane.class);
        return query.getResultList();
    }

    @Override
    public List<Plane> findPlaneArrive(String code) {
        String sql = "SELECT DISTINCT p.planeArrive FROM Plane p WHERE p.planeArrive IN ('Sydney','Melbourne','Casablanca','Spain','Paris','German','Holland','Taiwan','Seoul','Bangkok','Phuket','Pattaya','Singapore','Japan','Dubai','China','India','USA') AND p.planeDepart = :code";
        Query query = em.createQuery(sql, Plane.class);
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public Plane findDeparture(Date planeDate, Date planeArrive, String codeDepart, String codeArrive) {
        try{
            Query query = em.createQuery("SELECT p FROM Plane p WHERE p.planeDate =:depart and p.planeDateArrive =:arrive and p.airCodeDepart =:codeDepart and p.airCodeArrive =:codeArrive", Plane.class);
            query.setParameter("depart", planeDate);
            query.setParameter("arrive", planeArrive);
            query.setParameter("codeDepart", codeDepart);
            query.setParameter("codeArrive", codeArrive);
            return (Plane) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Plane findArrive(Date planeDate, Date planeArrive, String codeDepart, String codeArrive) {
        try{
            Query query = em.createQuery("SELECT p FROM Plane p WHERE p.planeDate =:depart and p.planeDateArrive =:arrive and p.airCodeDepart =:codeDepart and p.airCodeArrive =:codeArrive", Plane.class);
            query.setParameter("depart", planeDate);
            query.setParameter("arrive", planeArrive);
            query.setParameter("codeDepart", codeDepart);
            query.setParameter("codeArrive", codeArrive);
            return (Plane) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void deletePlae(int planeId) {
        Query query = em.createQuery("DELETE FROM Plane p WHERE p.planeId =:plane", Plane.class);
        query.setParameter("plane", planeId);
        query.executeUpdate();
    }

    @Override
    public List<Plane> findAllPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT p FROM Plane p");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Plane> findPlaneBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT p FROM Plane p WHERE p.planeDepart LIKE :depart or p.planeArrive LIKE :arrive");
        query.setParameter("depart", "%" + keyword + "%");
        query.setParameter("arrive", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
       Query query = em.createQuery("SELECT COUNT(p) FROM Plane p WHERE p.planeDepart LIKE :depart or p.planeArrive LIKE :arrive");
       query.setParameter("depart", "%" + keyword + "%");
       query.setParameter("arrive", "%" + keyword + "%");
       return (long) query.getSingleResult();
    }

    @Override
    public void deleteByPlaneId(int planeId) {
        Query query = em.createQuery("DELETE FROM Plane p WHERE p.planeId =:plane and p.planeId > 72");
        query.setParameter("plane", planeId);
        query.executeUpdate();
    }

    @Override
    public List<Plane> showAllPlaneDeparture() {
        Query query = em.createQuery("SELECT p FROM Plane p WHERE p.planeDepart =:dpart1 or p.planeDepart =:dpart2");
        query.setParameter("dpart1", "Ho Chi Minh");
        query.setParameter("dpart2", "Ha Noi");
        return query.getResultList();
    }

    @Override
    public List<Plane> showAllPlaneArrive() {
        Query query = em.createQuery("SELECT p FROM Plane p WHERE p.planeArrive =:arr1 or p.planeArrive =:arr2");
        query.setParameter("arr1", "Ho Chi Minh");
        query.setParameter("arr2", "Ha Noi");
        return query.getResultList();
    }
    
}
