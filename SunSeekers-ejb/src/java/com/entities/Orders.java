/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrdersId", query = "SELECT o FROM Orders o WHERE o.ordersId = :ordersId"),
    @NamedQuery(name = "Orders.findByOrdersDate", query = "SELECT o FROM Orders o WHERE o.ordersDate = :ordersDate"),
    @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Orders.findByTotalQuantity", query = "SELECT o FROM Orders o WHERE o.totalQuantity = :totalQuantity"),
    @NamedQuery(name = "Orders.findByOrderStatus", query = "SELECT o FROM Orders o WHERE o.orderStatus = :orderStatus")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrdersId")
    private Integer ordersId;
    @Column(name = "OrdersDate")
    @Temporal(TemporalType.DATE)
    private Date ordersDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;
    @Column(name = "TotalQuantity")
    private Integer totalQuantity;
    @Size(max = 255)
    @Column(name = "OrderStatus")
    private String orderStatus;
    @JoinColumn(name = "AdminsId", referencedColumnName = "AdminsId")
    @ManyToOne
    private Admins adminsId;
    @JoinColumn(name = "PaymentId", referencedColumnName = "PaymentId")
    @ManyToOne
    private Payment paymentId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private UserReg userId;
    @OneToMany(mappedBy = "ordersId")
    private Collection<OrderDetails> orderDetailsCollection;

    public Orders() {
    }

    public Orders(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Admins getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(Admins adminsId) {
        this.adminsId = adminsId;
    }

    public Payment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Payment paymentId) {
        this.paymentId = paymentId;
    }

    public UserReg getUserId() {
        return userId;
    }

    public void setUserId(UserReg userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordersId != null ? ordersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.ordersId == null && other.ordersId != null) || (this.ordersId != null && !this.ordersId.equals(other.ordersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Orders[ ordersId=" + ordersId + " ]";
    }
    
}
