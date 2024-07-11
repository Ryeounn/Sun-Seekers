/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Tour;
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
public class TourFacade extends AbstractFacade<Tour> implements TourFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TourFacade() {
        super(Tour.class);
    }

    @Override
    public List<Tour> findCode() {
        String sql = "SELECT t FROM Tour t WHERE t.tourId = 1 or t.tourId = 7 or t.tourId = 13 or t.tourId = 6 or t.tourId = 36";
        Query query = em.createQuery(sql, Tour.class);
        return query.getResultList();
    }

    @Override
    public List<Tour> findTour(String code) {
        String sql = "SELECT t FROM Tour t WHERE t.tourCode = :code";
        Query query = em.createQuery(sql, Tour.class);
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public Long sumTour() {
        Query query = em.createQuery("SELECT COUNT(t.tourId) FROM Tour t");
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteTour(int id) {
        Query query = em.createQuery("DELETE FROM Tour t WHERE t.tourId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Tour findByRegion(int tourId) {
        try {
            Query query = em.createQuery("SELECT t FROM Tour t WHERE t.tourId =:tour");
            query.setParameter("tour", tourId);
            return (Tour) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Tour customCreate(Tour tour) {
        em.persist(tour);
        return tour;
    }

    @Override
    public Tour findByWareHouse(int wId) {
        try {
            Query query = em.createQuery("SELECT t FROM Tour t WHERE t.wareHouseId.wareHouseId =:w");
            query.setParameter("w", wId);
            return (Tour) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
