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
@Table(name = "Tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @NamedQuery(name = "Tour.findByTourId", query = "SELECT t FROM Tour t WHERE t.tourId = :tourId"),
    @NamedQuery(name = "Tour.findByTourCode", query = "SELECT t FROM Tour t WHERE t.tourCode = :tourCode"),
    @NamedQuery(name = "Tour.findByTourName", query = "SELECT t FROM Tour t WHERE t.tourName = :tourName"),
    @NamedQuery(name = "Tour.findByTotalDate", query = "SELECT t FROM Tour t WHERE t.totalDate = :totalDate"),
    @NamedQuery(name = "Tour.findByTourDescription", query = "SELECT t FROM Tour t WHERE t.tourDescription = :tourDescription"),
    @NamedQuery(name = "Tour.findByAddDate", query = "SELECT t FROM Tour t WHERE t.addDate = :addDate"),
    @NamedQuery(name = "Tour.findByTourPrice", query = "SELECT t FROM Tour t WHERE t.tourPrice = :tourPrice")})
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TourId")
    private Integer tourId;
    @Size(max = 255)
    @Column(name = "TourCode")
    private String tourCode;
    @Size(max = 255)
    @Column(name = "TourName")
    private String tourName;
    @Column(name = "TotalDate")
    private Integer totalDate;
    @Size(max = 2147483647)
    @Column(name = "TourDescription")
    private String tourDescription;
    @Column(name = "AddDate")
    @Temporal(TemporalType.DATE)
    private Date addDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TourPrice")
    private BigDecimal tourPrice;
    @OneToMany(mappedBy = "tourId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<OrderDetails> orderDetailsCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<WishList> wishListCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<TourDetail> tourDetailCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<ShoppingCart> shoppingCartCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(mappedBy = "tourId")
    private Collection<Outstanding> outstandingCollection;
    @JoinColumn(name = "AdminsId", referencedColumnName = "AdminsId")
    @ManyToOne
    private Admins adminsId;
    @JoinColumn(name = "ImageId", referencedColumnName = "ImageId")
    @ManyToOne
    private Images imageId;
    @JoinColumn(name = "RegionId", referencedColumnName = "RegionId")
    @ManyToOne
    private Region regionId;
    @JoinColumn(name = "WareHouseId", referencedColumnName = "WareHouseId")
    @ManyToOne
    private WareHouse wareHouseId;

    public Tour() {
    }

    public Tour(Integer tourId) {
        this.tourId = tourId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @XmlTransient
    public Collection<WishList> getWishListCollection() {
        return wishListCollection;
    }

    public void setWishListCollection(Collection<WishList> wishListCollection) {
        this.wishListCollection = wishListCollection;
    }

    @XmlTransient
    public Collection<TourDetail> getTourDetailCollection() {
        return tourDetailCollection;
    }

    public void setTourDetailCollection(Collection<TourDetail> tourDetailCollection) {
        this.tourDetailCollection = tourDetailCollection;
    }

    @XmlTransient
    public Collection<ShoppingCart> getShoppingCartCollection() {
        return shoppingCartCollection;
    }

    public void setShoppingCartCollection(Collection<ShoppingCart> shoppingCartCollection) {
        this.shoppingCartCollection = shoppingCartCollection;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @XmlTransient
    public Collection<Outstanding> getOutstandingCollection() {
        return outstandingCollection;
    }

    public void setOutstandingCollection(Collection<Outstanding> outstandingCollection) {
        this.outstandingCollection = outstandingCollection;
    }

    public Admins getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(Admins adminsId) {
        this.adminsId = adminsId;
    }

    public Images getImageId() {
        return imageId;
    }

    public void setImageId(Images imageId) {
        this.imageId = imageId;
    }

    public Region getRegionId() {
        return regionId;
    }

    public void setRegionId(Region regionId) {
        this.regionId = regionId;
    }

    public WareHouse getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(WareHouse wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourId != null ? tourId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.tourId == null && other.tourId != null) || (this.tourId != null && !this.tourId.equals(other.tourId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Tour[ tourId=" + tourId + " ]";
    }
    
}
