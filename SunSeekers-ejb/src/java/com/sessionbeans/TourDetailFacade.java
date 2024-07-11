/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.TourDetail;
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
public class TourDetailFacade extends AbstractFacade<TourDetail> implements TourDetailFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TourDetailFacade() {
        super(TourDetail.class);
    }

    @Override
    public List<TourDetail> findSearch(String depart, String arrived, Date date) {
        String sql = "SELECT t FROM TourDetail t where t.planeId1.planeDepart = :depart and t.planeId1.planeArrive = :arrived and t.planeId1.planeDate >= :date";
        Query query = em.createQuery(sql, TourDetail.class);
        query.setParameter("depart", depart);
        query.setParameter("arrived", arrived);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findRecommend(int tourId) {
        String sql = "SELECT t FROM TourDetail t WHERE t.tourId.tourId = :tour1 or t.tourId.tourId = :tour2 or t.tourId.tourId = :tour3 or t.tourId.tourId = :tour4 or t.tourId.tourId = :tour5 or t.tourId.tourId = :tour6";
        Query query = em.createQuery(sql, TourDetail.class);
        if (tourId == 1 || tourId == 2 || tourId == 3 || tourId == 4 || tourId == 5) {
            query.setParameter("tour1", 8);
            query.setParameter("tour2", 11);
            query.setParameter("tour3", 13);
            query.setParameter("tour4", 15);
            query.setParameter("tour5", 19);
            query.setParameter("tour6", 25);
        } else if (tourId == 6) {
            query.setParameter("tour1", 1);
            query.setParameter("tour2", 14);
            query.setParameter("tour3", 16);
            query.setParameter("tour4", 21);
            query.setParameter("tour5", 27);
            query.setParameter("tour6", 30);
        } else if (tourId == 7 || tourId == 8 || tourId == 9) {
            query.setParameter("tour1", 2);
            query.setParameter("tour2", 13);
            query.setParameter("tour3", 17);
            query.setParameter("tour4", 22);
            query.setParameter("tour5", 31);
            query.setParameter("tour6", 35);
        } else if (tourId == 10) {
            query.setParameter("tour1", 4);
            query.setParameter("tour2", 14);
            query.setParameter("tour3", 18);
            query.setParameter("tour4", 27);
            query.setParameter("tour5", 31);
            query.setParameter("tour6", 36);
        } else if (tourId == 11) {
            query.setParameter("tour1", 4);
            query.setParameter("tour2", 14);
            query.setParameter("tour3", 16);
            query.setParameter("tour4", 20);
            query.setParameter("tour5", 23);
            query.setParameter("tour6", 28);
        } else if (tourId == 12) {
            query.setParameter("tour1", 1);
            query.setParameter("tour2", 14);
            query.setParameter("tour3", 17);
            query.setParameter("tour4", 21);
            query.setParameter("tour5", 26);
            query.setParameter("tour6", 34);
        } else if (tourId == 13 || tourId == 14) {
            query.setParameter("tour1", 3);
            query.setParameter("tour2", 6);
            query.setParameter("tour3", 8);
            query.setParameter("tour4", 27);
            query.setParameter("tour5", 29);
            query.setParameter("tour6", 32);
        } else if (tourId == 15 || tourId == 16 || tourId == 17 || tourId == 18) {
            query.setParameter("tour1", 4);
            query.setParameter("tour2", 6);
            query.setParameter("tour3", 9);
            query.setParameter("tour4", 10);
            query.setParameter("tour5", 12);
            query.setParameter("tour6", 29);
        } else if (tourId == 19 || tourId == 20 || tourId == 21) {
            query.setParameter("tour1", 5);
            query.setParameter("tour2", 9);
            query.setParameter("tour3", 11);
            query.setParameter("tour4", 14);
            query.setParameter("tour5", 15);
            query.setParameter("tour6", 31);
        } else if (tourId == 22 || tourId == 23 || tourId == 24) {
            query.setParameter("tour1", 6);
            query.setParameter("tour2", 7);
            query.setParameter("tour3", 10);
            query.setParameter("tour4", 16);
            query.setParameter("tour5", 27);
            query.setParameter("tour6", 36);
        } else if (tourId == 25 || tourId == 26 || tourId == 27 || tourId == 28) {
            query.setParameter("tour1", 1);
            query.setParameter("tour2", 6);
            query.setParameter("tour3", 16);
            query.setParameter("tour4", 24);
            query.setParameter("tour5", 33);
            query.setParameter("tour6", 36);
        } else if (tourId == 29) {
            query.setParameter("tour1", 2);
            query.setParameter("tour2", 10);
            query.setParameter("tour3", 11);
            query.setParameter("tour4", 18);
            query.setParameter("tour5", 25);
            query.setParameter("tour6", 34);
        } else if (tourId == 30 || tourId == 31 || tourId == 32 || tourId == 33 || tourId == 34) {
            query.setParameter("tour1", 5);
            query.setParameter("tour2", 8);
            query.setParameter("tour3", 24);
            query.setParameter("tour4", 27);
            query.setParameter("tour5", 35);
            query.setParameter("tour6", 36);
        } else if (tourId == 35) {
            query.setParameter("tour1", 2);
            query.setParameter("tour2", 12);
            query.setParameter("tour3", 13);
            query.setParameter("tour4", 23);
            query.setParameter("tour5", 25);
            query.setParameter("tour6", 31);
        } else if (tourId == 36) {
            query.setParameter("tour1", 5);
            query.setParameter("tour2", 10);
            query.setParameter("tour3", 13);
            query.setParameter("tour4", 18);
            query.setParameter("tour5", 19);
            query.setParameter("tour6", 24);
        } else {
            query.setParameter("tour1", 1);
            query.setParameter("tour2", 11d);
            query.setParameter("tour3", 13);
            query.setParameter("tour4", 15);
            query.setParameter("tour5", 19);
            query.setParameter("tour6", 25);
        }
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findExplore() {
        String sql = "SELECT t FROM TourDetail t WHERE t.tourId.tourId =:tour1 or t.tourId.tourId =:tour2 or t.tourId.tourId =:tour3 or t.tourId.tourId =:tour4 or t.tourId.tourId =:tour5 or t.tourId.tourId =:tour6 or t.tourId.tourId =:tour7 or t.tourId.tourId =:tour8";
        Query query = em.createQuery(sql, TourDetail.class);
        query.setParameter("tour1", 1);
        query.setParameter("tour2", 10);
        query.setParameter("tour3", 13);
        query.setParameter("tour4", 18);
        query.setParameter("tour5", 25);
        query.setParameter("tour6", 27);
        query.setParameter("tour7", 31);
        query.setParameter("tour8", 36);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> theTour() {
        String sql = "SELECT t FROM TourDetail t WHERE t.tourId.tourId =:tour1 or t.tourId.tourId =:tour2 or t.tourId.tourId =:tour3 or t.tourId.tourId =:tour4 or t.tourId.tourId =:tour5 or t.tourId.tourId =:tour6 or t.tourId.tourId =:tour7 or t.tourId.tourId =:tour8";
        Query query = em.createQuery(sql, TourDetail.class);
        query.setParameter("tour1", 5);
        query.setParameter("tour2", 8);
        query.setParameter("tour3", 13);
        query.setParameter("tour4", 15);
        query.setParameter("tour5", 20);
        query.setParameter("tour6", 23);
        query.setParameter("tour7", 30);
        query.setParameter("tour8", 35);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findPopular(String code) {
        Query query = em.createQuery("SELECT t FROM TourDetail t WHERE t.tourId.tourCode =:code");
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findBestSeller() {
        Query query = em.createQuery("SELECT t FROM TourDetail t ORDER BY t.tourId.wareHouseId.inventory ASC", TourDetail.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT t FROM TourDetail t");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<TourDetail> findProductBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT t FROM TourDetail t WHERE t.tourId.tourName LIKE :name or t.tourId.regionId.regionName LIKE :area or t.planeId1.planeDepart LIKE :depart or t.planeId1.airCodeDepart LIKE :cde or t.planeId1.planeArrive LIKE :arrive or t.planeId1.airCodeArrive LIKE :cav", TourDetail.class);
        query.setParameter("name", "%" + keyword + "%");
        query.setParameter("area", "%" + keyword + "%");
        query.setParameter("depart", "%" + keyword + "%");
        query.setParameter("cde", "%" + keyword + "%");
        query.setParameter("arrive", "%" + keyword + "%");
        query.setParameter("cav", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery(
                "SELECT COUNT(t) FROM TourDetail t WHERE t.tourId.tourName LIKE :name or t.tourId.regionId.regionName LIKE :area", Long.class);
        query.setParameter("name", "%" + keyword + "%");
        query.setParameter("area", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }

    @Override
    public void deleteTourById(int id) {
        Query query = em.createQuery("DELETE FROM TourDetail t WHERE t.tourId.tourId =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<TourDetail> findTourById(int tourId) {
        Query query = em.createQuery("SELECT t FROM TourDetail t WHERE t.tourId.tourId =:tour", TourDetail.class);
        query.setParameter("tour", tourId);
        System.out.println("TourDetail");
        return query.getResultList();
    }

    @Override
    public TourDetail findByTourId(int tour) {
        try {
            Query query = em.createQuery("SELECT t FROM TourDetail t WHERE t.tourId.tourId =:tour");
            query.setParameter("tour", tour);
            System.out.println("kg");
            return (TourDetail) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("gk");
            return null;
        }
    }
}
