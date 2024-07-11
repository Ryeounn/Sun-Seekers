/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.OrderDetails;
import java.time.LocalDate;
import java.util.Date;
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
public class OrderDetailsFacade extends AbstractFacade<OrderDetails> implements OrderDetailsFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailsFacade() {
        super(OrderDetails.class);
    }
    
    @Override
    public List<OrderDetails> findOrderByUser(int userId, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.userId.userId = :userId")
                .setParameter("userId", userId);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
   @Override
    public List<OrderDetails> findOrderByUser(int orderid) {
        Query query = em.createQuery("SELECT o FROM OrderDetails o WHERE o.ordersId.ordersId =:orderid");
        query.setParameter("orderid", orderid);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> showRevenue(LocalDate nower) {
        Date now = DateUtils.asDate(nower);
        Query query = em.createQuery("SELECT o.tourId.tourId, o.tourId.tourName, SUM(o.price * o.quantity) AS Prices FROM OrderDetails o WHERE o.ordersId.ordersDate <=:current GROUP BY o.tourId.tourId, o.tourId.tourName ORDER BY Prices", OrderDetails.class);
        query.setParameter("current", now);
        query.setMaxResults(3);
        return query.getResultList();
    }

}
