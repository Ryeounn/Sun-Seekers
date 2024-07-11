/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "Admins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admins.findAll", query = "SELECT a FROM Admins a"),
    @NamedQuery(name = "Admins.findByAdminsId", query = "SELECT a FROM Admins a WHERE a.adminsId = :adminsId"),
    @NamedQuery(name = "Admins.findByAdminName", query = "SELECT a FROM Admins a WHERE a.adminName = :adminName"),
    @NamedQuery(name = "Admins.findByAdminUser", query = "SELECT a FROM Admins a WHERE a.adminUser = :adminUser"),
    @NamedQuery(name = "Admins.findByAdminPass", query = "SELECT a FROM Admins a WHERE a.adminPass = :adminPass"),
    @NamedQuery(name = "Admins.findByAdminDob", query = "SELECT a FROM Admins a WHERE a.adminDob = :adminDob"),
    @NamedQuery(name = "Admins.findByAdminEmail", query = "SELECT a FROM Admins a WHERE a.adminEmail = :adminEmail"),
    @NamedQuery(name = "Admins.findByAdminPhone", query = "SELECT a FROM Admins a WHERE a.adminPhone = :adminPhone"),
    @NamedQuery(name = "Admins.findByAdminAddress", query = "SELECT a FROM Admins a WHERE a.adminAddress = :adminAddress"),
    @NamedQuery(name = "Admins.findByAdminPhoto", query = "SELECT a FROM Admins a WHERE a.adminPhoto = :adminPhoto"),
    @NamedQuery(name = "Admins.findByAdminPath", query = "SELECT a FROM Admins a WHERE a.adminPath = :adminPath")})
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AdminsId")
    private Integer adminsId;
    @Size(max = 255)
    @Column(name = "AdminName")
    private String adminName;
    @Size(max = 255)
    @Column(name = "AdminUser")
    private String adminUser;
    @Size(max = 255)
    @Column(name = "AdminPass")
    private String adminPass;
    @Column(name = "AdminDob")
    @Temporal(TemporalType.DATE)
    private Date adminDob;
    @Size(max = 255)
    @Column(name = "AdminEmail")
    private String adminEmail;
    @Size(max = 255)
    @Column(name = "AdminPhone")
    private String adminPhone;
    @Size(max = 255)
    @Column(name = "AdminAddress")
    private String adminAddress;
    @Size(max = 255)
    @Column(name = "AdminPhoto")
    private String adminPhoto;
    @Size(max = 255)
    @Column(name = "AdminPath")
    private String adminPath;
    @OneToMany(mappedBy = "adminsId")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "adminsId")
    private Collection<Response> responseCollection;
    @OneToMany(mappedBy = "adminsId")
    private Collection<Tour> tourCollection;

    public Admins() {
    }

    public Admins(Integer adminsId) {
        this.adminsId = adminsId;
    }

    public Integer getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(Integer adminsId) {
        this.adminsId = adminsId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public Date getAdminDob() {
        return adminDob;
    }

    public void setAdminDob(Date adminDob) {
        this.adminDob = adminDob;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminPhoto() {
        return adminPhoto;
    }

    public void setAdminPhoto(String adminPhoto) {
        this.adminPhoto = adminPhoto;
    }

    public String getAdminPath() {
        return adminPath;
    }

    public void setAdminPath(String adminPath) {
        this.adminPath = adminPath;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Response> getResponseCollection() {
        return responseCollection;
    }

    public void setResponseCollection(Collection<Response> responseCollection) {
        this.responseCollection = responseCollection;
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
        hash += (adminsId != null ? adminsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admins)) {
            return false;
        }
        Admins other = (Admins) object;
        if ((this.adminsId == null && other.adminsId != null) || (this.adminsId != null && !this.adminsId.equals(other.adminsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Admins[ adminsId=" + adminsId + " ]";
    }
    
}
