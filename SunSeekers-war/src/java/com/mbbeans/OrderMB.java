/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbbeans;

import com.entities.OrderDetails;
import com.entities.Orders;
import com.sessionbeans.OrderDetailsFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ngu Công
 */
@Named(value = "orderMB")
@SessionScoped
public class OrderMB implements Serializable {

    @EJB
    private OrdersFacadeLocal ordersFacade1;

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    private int pageSize = 2;
    private int currentPage = 1;
    private int totalPages;
    private int totalOrderDetail;
    private String keyword;
    private List<Orders> result;
    private int totalOrder;
    private Orders o;
    private Orders oa;
    private int updatingOrder;

    public OrderMB() {
    }

    @PostConstruct
    public void init() {
        o = new Orders();
        orderPagination();
    }

    public List<Orders> orderPagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = ordersFacade.findAll();
            totalOrderDetail = result.size();
            totalPages = (int) Math.ceil((double) totalOrderDetail / pageSize);
            result = ordersFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalOrder = (int) ordersFacade.countWithSearch(keyword);
            totalOrderDetail = result.size();
            totalPages = (int) Math.ceil((double) totalOrder / pageSize);
            result = ordersFacade.findTourSearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public String updatingOrders() {
        oa = ordersFacade.find(o.getOrdersId());
        if (oa != null) {
            oa.setOrderStatus(o.getOrderStatus());
            ordersFacade.edit(oa);
        } else {
            System.out.println("Error Object Orders");
        }
        orderPagination();
        return "/admin/orders";
    }

    public List<OrderDetails> findOrderDetailById(int orderId) {
        return orderDetailsFacade.findOrderByUser(orderId);
    }

    public List<Orders> findAllOrder() {
        return ordersFacade.findAll();
    }

    public Orders getO() {
        return o;
    }

    public void setO(Orders o) {
        this.o = o;
    }

    public void updateStatus() {
        if (o != null && o.getOrdersId() != null) {
            o = ordersFacade.find(o.getOrdersId());
        }
    }

    public void searchOrder() {
        currentPage = 1;
        orderPagination();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            orderPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            orderPagination();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalOrderDetail() {
        return totalOrderDetail;
    }

    public void setTotalOrderDetail(int totalOrderDetail) {
        this.totalOrderDetail = totalOrderDetail;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Orders> getResult() {
        return result;
    }

    public void setResult(List<Orders> result) {
        this.result = result;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Orders getOa() {
        return oa;
    }

    public void setOa(Orders oa) {
        this.oa = oa;
    }

    public int getUpdatingOrder() {
        return updatingOrder;
    }

    public void setUpdatingOrder(int updatingOrder) {
        this.updatingOrder = updatingOrder;
    }

}
