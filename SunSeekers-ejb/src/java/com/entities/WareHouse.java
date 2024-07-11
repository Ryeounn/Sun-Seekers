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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "WareHouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WareHouse.findAll", query = "SELECT w FROM WareHouse w"),
    @NamedQuery(name = "WareHouse.findByWareHouseId", query = "SELECT w FROM WareHouse w WHERE w.wareHouseId = :wareHouseId"),
    @NamedQuery(name = "WareHouse.findByTotal", query = "SELECT w FROM WareHouse w WHERE w.total = :total"),
    @NamedQuery(name = "WareHouse.findByInventory", query = "SELECT w FROM WareHouse w WHERE w.inventory = :inventory")})
public class WareHouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WareHouseId")
    private Integer wareHouseId;
    @Column(name = "Total")
    private Integer total;
    @Column(name = "Inventory")
    private Integer inventory;
    @OneToMany(mappedBy = "wareHouseId")
    private Collection<Tour> tourCollection;

    public WareHouse() {
    }

    public WareHouse(Integer wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public Integer getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(Integer wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @XmlTransient
    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wareHouseId != null ? wareHouseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WareHouse)) {
            return false;
        }
        WareHouse other = (WareHouse) object;
        if ((this.wareHouseId == null && other.wareHouseId != null) || (this.wareHouseId != null && !this.wareHouseId.equals(other.wareHouseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.WareHouse[ wareHouseId=" + wareHouseId + " ]";
    }
    
}
