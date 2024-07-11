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
@Table(name = "TourDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TourDetail.findAll", query = "SELECT t FROM TourDetail t"),
    @NamedQuery(name = "TourDetail.findByTourDetailId", query = "SELECT t FROM TourDetail t WHERE t.tourDetailId = :tourDetailId")})
public class TourDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TourDetailId")
    private Integer tourDetailId;
    @JoinColumn(name = "PlaneId1", referencedColumnName = "PlaneId")
    @ManyToOne
    private Plane planeId1;
    @JoinColumn(name = "PlaneId2", referencedColumnName = "PlaneId")
    @ManyToOne
    private Plane planeId2;
    @JoinColumn(name = "TourId", referencedColumnName = "TourId")
    @ManyToOne
    private Tour tourId;

    public TourDetail() {
    }

    public TourDetail(Integer tourDetailId) {
        this.tourDetailId = tourDetailId;
    }

    public Integer getTourDetailId() {
        return tourDetailId;
    }

    public void setTourDetailId(Integer tourDetailId) {
        this.tourDetailId = tourDetailId;
    }

    public Plane getPlaneId1() {
        return planeId1;
    }

    public void setPlaneId1(Plane planeId1) {
        this.planeId1 = planeId1;
    }

    public Plane getPlaneId2() {
        return planeId2;
    }

    public void setPlaneId2(Plane planeId2) {
        this.planeId2 = planeId2;
    }

    public Tour getTourId() {
        return tourId;
    }

    public void setTourId(Tour tourId) {
        this.tourId = tourId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourDetailId != null ? tourDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TourDetail)) {
            return false;
        }
        TourDetail other = (TourDetail) object;
        if ((this.tourDetailId == null && other.tourDetailId != null) || (this.tourDetailId != null && !this.tourDetailId.equals(other.tourDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.TourDetail[ tourDetailId=" + tourDetailId + " ]";
    }
    
}
