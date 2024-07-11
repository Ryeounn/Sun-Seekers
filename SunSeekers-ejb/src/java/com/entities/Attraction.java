/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "Attraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attraction.findAll", query = "SELECT a FROM Attraction a"),
    @NamedQuery(name = "Attraction.findByAttractionId", query = "SELECT a FROM Attraction a WHERE a.attractionId = :attractionId"),
    @NamedQuery(name = "Attraction.findByAName", query = "SELECT a FROM Attraction a WHERE a.aName = :aName"),
    @NamedQuery(name = "Attraction.findByADescription", query = "SELECT a FROM Attraction a WHERE a.aDescription = :aDescription")})
public class Attraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AttractionId")
    private Integer attractionId;
    @Size(max = 255)
    @Column(name = "AName")
    private String aName;
    @Size(max = 2147483647)
    @Column(name = "ADescription")
    private String aDescription;
    @JoinColumn(name = "RegionId", referencedColumnName = "RegionId")
    @ManyToOne
    private Region regionId;
    @OneToMany(mappedBy = "attractionId")
    private Collection<Outstanding> outstandingCollection;

    public Attraction() {
    }

    public Attraction(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getADescription() {
        return aDescription;
    }

    public void setADescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public Region getRegionId() {
        return regionId;
    }

    public void setRegionId(Region regionId) {
        this.regionId = regionId;
    }

    @XmlTransient
    public Collection<Outstanding> getOutstandingCollection() {
        return outstandingCollection;
    }

    public void setOutstandingCollection(Collection<Outstanding> outstandingCollection) {
        this.outstandingCollection = outstandingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attractionId != null ? attractionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attraction)) {
            return false;
        }
        Attraction other = (Attraction) object;
        if ((this.attractionId == null && other.attractionId != null) || (this.attractionId != null && !this.attractionId.equals(other.attractionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Attraction[ attractionId=" + attractionId + " ]";
    }
    
}
