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
@Table(name = "Continent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Continent.findAll", query = "SELECT c FROM Continent c"),
    @NamedQuery(name = "Continent.findByContinentId", query = "SELECT c FROM Continent c WHERE c.continentId = :continentId"),
    @NamedQuery(name = "Continent.findByContinentName", query = "SELECT c FROM Continent c WHERE c.continentName = :continentName")})
public class Continent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ContinentId")
    private Integer continentId;
    @Size(max = 255)
    @Column(name = "ContinentName")
    private String continentName;
    @OneToMany(mappedBy = "continentId")
    private Collection<Region> regionCollection;

    public Continent() {
    }

    public Continent(Integer continentId) {
        this.continentId = continentId;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    @XmlTransient
    public Collection<Region> getRegionCollection() {
        return regionCollection;
    }

    public void setRegionCollection(Collection<Region> regionCollection) {
        this.regionCollection = regionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (continentId != null ? continentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Continent)) {
            return false;
        }
        Continent other = (Continent) object;
        if ((this.continentId == null && other.continentId != null) || (this.continentId != null && !this.continentId.equals(other.continentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Continent[ continentId=" + continentId + " ]";
    }
    
}
