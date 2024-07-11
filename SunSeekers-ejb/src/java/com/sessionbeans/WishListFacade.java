/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.WishList;
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
public class WishListFacade extends AbstractFacade<WishList> implements WishListFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WishListFacade() {
        super(WishList.class);
    }
    
    @Override
    public List<WishList> findWishByUser(int userId) {
        String sql = "SELECT w FROM WishList w WHERE w.userId.userId =:user";
        Query query = em.createQuery(sql, WishList.class);
        query.setParameter("user", userId);
        return query.getResultList();
    }

    @Override
    public WishList findById(int tourId, int userId) {
        try {
            String sql = "SELECT w FROM WishList w WHERE w.tourId.tourId =:tour and w.userId.userId =:user";
            Query query = em.createQuery(sql, WishList.class);
            query.setParameter("tour", tourId);
            query.setParameter("user", userId);
            return (WishList) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteAllById(int userId) {
        String sql = "DELETE FROM WishList WHERE userId.userId =:user";
        Query query = em.createQuery(sql,WishList.class);
        query.setParameter("user", userId);
        query.executeUpdate();
    }

    @Override
    public Long sumQuantity(int userId) {
        String sql = "SELECT COUNT(s.wishListId) FROM WishList s WHERE s.userId.userId =:user";
        Query query = em.createQuery(sql, WishList.class);
        query.setParameter("user", userId);
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteWishById(int tourId) {
        Query query = em.createQuery("DELETE FROM WishList w WHERE w.tourId.tourId =:tour",WishList.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }
}
