/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.ShoppingCart;
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
public class ShoppingCartFacade extends AbstractFacade<ShoppingCart> implements ShoppingCartFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingCartFacade() {
        super(ShoppingCart.class);
    }
    
    @Override
    public void addcart(ShoppingCart shoppingCart) {
        em.persist(shoppingCart);
    }

    @Override
    public ShoppingCart findCartByTourandUser(int tourId, int userId) {
        String sql = "SELECT s FROM ShoppingCart s WHERE s.tourId.tourId =:tour and s.userId.userId =:user";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("tour", tourId);
        query.setParameter("user", userId);
        List<ShoppingCart> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public void updateQuantity(ShoppingCart sc, int quantity) {
        String sql = "UPDATE ShoppingCart set quantity =:quantity WHERE tourId =:tourId and userId =:userId";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("quantity", quantity);
        query.setParameter("tourId", sc.getTourId());
        query.setParameter("userId", sc.getUserId());
        query.executeUpdate();
    }

    @Override
    public Long sumQuantity(int userId) {
        String sql = "SELECT SUM(s.quantity) FROM ShoppingCart s WHERE s.userId.userId =:user";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("user", userId);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<ShoppingCart> showAllById(int userId) {
        String sql = "SELECT s FROM ShoppingCart s WHERE s.userId.userId =:user";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("user", userId);
        return query.getResultList();
    }

    @Override
    public void deleteItems(ShoppingCart sc) {
        String sql = "DELETE FROM ShoppingCart WHERE tourId =:tour and userId =:user";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("tour", sc.getTourId());
        query.setParameter("user", sc.getUserId());
        query.executeUpdate();
    }

    @Override
    public void deleteAll(int userId) {
        String sql = "DELETE FROM ShoppingCart WHERE userId.userId =:user";
        Query query = em.createQuery(sql, ShoppingCart.class);
        query.setParameter("user", userId);
        query.executeUpdate();
    }

    @Override
    public void dltAfterCheckOut(int userId) {
        Query query = em.createQuery("DELETE FROM ShoppingCart s WHERE s.userId.userId =:user",ShoppingCart.class)
                .setParameter("user", userId);
        query.executeUpdate();
    }

    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM ShoppingCart s WHERE s.tourId.tourId =:tour", ShoppingCart.class);
        query.setParameter("tourId", tourId);
        query.executeUpdate();
    }
}
