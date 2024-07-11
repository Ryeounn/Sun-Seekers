/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Orders;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();
    
    List<Orders> findOrderByUser(int userId, int pageNumber, int pageSize);
    
    long countOrder();

    List<Orders> findOrderByUserID(int userId);
    
    BigDecimal countRevenue();
    
    List<Object[]> showOrderByDate(LocalDate startDate, LocalDate endDate);
    
    List<Orders> showOrderDashboard();
    
    List<Object[]> getOrderFromLast12Months();
    
    long countOrderByYear();
    
    List<Object[]> getRevenueFromLast12Months();
    
    List<Orders> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Orders> findTourSearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
}
