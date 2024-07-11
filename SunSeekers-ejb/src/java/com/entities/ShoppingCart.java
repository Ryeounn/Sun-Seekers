/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "ShoppingCart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s"),
    @NamedQuery(name = "ShoppingCart.findByShoppingId", query = "SELECT s FROM ShoppingCart s WHERE s.shoppingId = :shoppingId"),
    @NamedQuery(name = "ShoppingCart.findByQuantity", query = "SELECT s FROM ShoppingCart s WHERE s.quantity = :quantity")})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ShoppingId")
    private Integer shoppingId;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "TourId", referencedColumnName = "TourId")
    @ManyToOne
    private Tour tourId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private UserReg userId;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Integer getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Integer shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Tour getTourId() {
        return tourId;
    }

    public void setTourId(Tour tourId) {
        this.tourId = tourId;
    }

    public UserReg getUserId() {
        return userId;
    }

    public void setUserId(UserReg userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shoppingId != null ? shoppingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.shoppingId == null && other.shoppingId != null) || (this.shoppingId != null && !this.shoppingId.equals(other.shoppingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ShoppingCart[ shoppingId=" + shoppingId + " ]";
    }
    
}
