/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Orders;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
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
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    
    @Override
    public List<Orders> findOrderByUser(int userId, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.userId.userId = :userId")
                .setParameter("userId", userId);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Orders> findOrderByUserID(int userId) {
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.userId.userId = :userId")
                .setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public long countOrder() {
        Query query = em.createQuery("SELECT COUNT(o) FROM Orders o", Orders.class);
        return (long) query.getSingleResult();
    }

    @Override
    public BigDecimal countRevenue() {
        Query query = em.createQuery("SELECT SUM(o.totalPrice) FROM Orders o", Orders.class);
        return (BigDecimal) query.getSingleResult();
    }

    @Override
    public List<Object[]> showOrderByDate(LocalDate startDate, LocalDate endDate) {
        Date start = DateUtils.asDate(startDate);
        Date end = DateUtils.asDate(endDate);
        Query query = em.createQuery("SELECT o.ordersDate, COUNT(o.ordersId) AS Dater FROM Orders o WHERE o.ordersDate <=:startDate AND o.ordersDate >=:endDate GROUP BY o.ordersDate ORDER BY Dater", Orders.class);
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        return query.getResultList();
    }

    @Override
    public List<Orders> showOrderDashboard() {
        Query query = em.createQuery("SELECT o FROM Orders o ORDER BY o.ordersDate Desc");
        query.setMaxResults(7);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getOrderFromLast12Months() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);
        Date date = calendar.getTime();
        Query query = em.createQuery("SELECT FUNCTION('MONTH',o.ordersDate), COUNT(o) FROM Orders o WHERE o.ordersDate >=:date GROUP BY FUNCTION('YEAR', o.ordersDate),FUNCTION('MONTH', o.ordersDate) ORDER BY FUNCTION('YEAR', o.ordersDate),FUNCTION('MONTH', o.ordersDate)", Object[].class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public long countOrderByYear() {
        Query query = em.createQuery("SELECT COUNT(o) FROM Orders o WHERE o.ordersDate <=:start AND o.ordersDate >=:end", Long.class);
        LocalDate localDate = LocalDate.now();
        Date start = DateUtils.asDate(localDate);
        System.out.println("localDate" + start);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date end = calendar.getTime();
        query.setParameter("start", start);
        query.setParameter("end", end);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Object[]> getRevenueFromLast12Months() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);
        Date date = calendar.getTime();
        Query query = em.createQuery("SELECT FUNCTION('MONTH',o.ordersDate),FUNCTION('YEAR',o.ordersDate), SUM(o.totalPrice) FROM Orders o WHERE o.ordersDate >=:date GROUP BY FUNCTION('YEAR', o.ordersDate),FUNCTION('MONTH', o.ordersDate) ORDER BY FUNCTION('YEAR', o.ordersDate),FUNCTION('MONTH', o.ordersDate)", Object[].class);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @Override
    public List<Orders> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Orders o");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Orders> findTourSearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.orderStatus LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(o) FROM Orders o WHERE o.orderStatus LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }
}
