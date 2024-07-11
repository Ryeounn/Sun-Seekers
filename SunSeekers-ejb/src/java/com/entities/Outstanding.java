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
@Table(name = "Outstanding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outstanding.findAll", query = "SELECT o FROM Outstanding o"),
    @NamedQuery(name = "Outstanding.findByOutstandingId", query = "SELECT o FROM Outstanding o WHERE o.outstandingId = :outstandingId")})
public class Outstanding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OutstandingId")
    private Integer outstandingId;
    @JoinColumn(name = "AttractionId", referencedColumnName = "AttractionId")
    @ManyToOne
    private Attraction attractionId;
    @JoinColumn(name = "TourId", referencedColumnName = "TourId")
    @ManyToOne
    private Tour tourId;

    public Outstanding() {
    }

    public Outstanding(Integer outstandingId) {
        this.outstandingId = outstandingId;
    }

    public Integer getOutstandingId() {
        return outstandingId;
    }

    public void setOutstandingId(Integer outstandingId) {
        this.outstandingId = outstandingId;
    }

    public Attraction getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Attraction attractionId) {
        this.attractionId = attractionId;
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
        hash += (outstandingId != null ? outstandingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Outstanding)) {
            return false;
        }
        Outstanding other = (Outstanding) object;
        if ((this.outstandingId == null && other.outstandingId != null) || (this.outstandingId != null && !this.outstandingId.equals(other.outstandingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Outstanding[ outstandingId=" + outstandingId + " ]";
    }
    
}
