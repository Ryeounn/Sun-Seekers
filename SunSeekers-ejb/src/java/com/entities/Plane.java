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
@Table(name = "Plane")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plane.findAll", query = "SELECT p FROM Plane p"),
    @NamedQuery(name = "Plane.findByPlaneId", query = "SELECT p FROM Plane p WHERE p.planeId = :planeId"),
    @NamedQuery(name = "Plane.findByPlaneDate", query = "SELECT p FROM Plane p WHERE p.planeDate = :planeDate"),
    @NamedQuery(name = "Plane.findByPlaneTimeDepart", query = "SELECT p FROM Plane p WHERE p.planeTimeDepart = :planeTimeDepart"),
    @NamedQuery(name = "Plane.findByPlaneDepart", query = "SELECT p FROM Plane p WHERE p.planeDepart = :planeDepart"),
    @NamedQuery(name = "Plane.findByPlaneTimeArrive", query = "SELECT p FROM Plane p WHERE p.planeTimeArrive = :planeTimeArrive"),
    @NamedQuery(name = "Plane.findByPlaneArrive", query = "SELECT p FROM Plane p WHERE p.planeArrive = :planeArrive"),
    @NamedQuery(name = "Plane.findByAirNumber", query = "SELECT p FROM Plane p WHERE p.airNumber = :airNumber"),
    @NamedQuery(name = "Plane.findByAirlineCompany", query = "SELECT p FROM Plane p WHERE p.airlineCompany = :airlineCompany"),
    @NamedQuery(name = "Plane.findByCompanyPath", query = "SELECT p FROM Plane p WHERE p.companyPath = :companyPath"),
    @NamedQuery(name = "Plane.findByPlaneDateArrive", query = "SELECT p FROM Plane p WHERE p.planeDateArrive = :planeDateArrive"),
    @NamedQuery(name = "Plane.findByPlaning", query = "SELECT p FROM Plane p WHERE p.planing = :planing"),
    @NamedQuery(name = "Plane.findByAirCodeDepart", query = "SELECT p FROM Plane p WHERE p.airCodeDepart = :airCodeDepart"),
    @NamedQuery(name = "Plane.findByAirNameDepart", query = "SELECT p FROM Plane p WHERE p.airNameDepart = :airNameDepart"),
    @NamedQuery(name = "Plane.findByAirCodeArrive", query = "SELECT p FROM Plane p WHERE p.airCodeArrive = :airCodeArrive"),
    @NamedQuery(name = "Plane.findByAirNameArrive", query = "SELECT p FROM Plane p WHERE p.airNameArrive = :airNameArrive")})
public class Plane implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PlaneId")
    private Integer planeId;
    @Column(name = "PlaneDate")
    @Temporal(TemporalType.DATE)
    private Date planeDate;
    @Size(max = 255)
    @Column(name = "PlaneTimeDepart")
    private String planeTimeDepart;
    @Size(max = 255)
    @Column(name = "PlaneDepart")
    private String planeDepart;
    @Size(max = 255)
    @Column(name = "PlaneTimeArrive")
    private String planeTimeArrive;
    @Size(max = 255)
    @Column(name = "PlaneArrive")
    private String planeArrive;
    @Size(max = 255)
    @Column(name = "AirNumber")
    private String airNumber;
    @Size(max = 255)
    @Column(name = "AirlineCompany")
    private String airlineCompany;
    @Size(max = 255)
    @Column(name = "CompanyPath")
    private String companyPath;
    @Column(name = "PlaneDateArrive")
    @Temporal(TemporalType.DATE)
    private Date planeDateArrive;
    @Size(max = 255)
    @Column(name = "Planing")
    private String planing;
    @Size(max = 255)
    @Column(name = "AirCodeDepart")
    private String airCodeDepart;
    @Size(max = 255)
    @Column(name = "AirNameDepart")
    private String airNameDepart;
    @Size(max = 255)
    @Column(name = "AirCodeArrive")
    private String airCodeArrive;
    @Size(max = 255)
    @Column(name = "AirNameArrive")
    private String airNameArrive;
    @OneToMany(mappedBy = "planeId1")
    private Collection<TourDetail> tourDetailCollection;
    @OneToMany(mappedBy = "planeId2")
    private Collection<TourDetail> tourDetailCollection1;

    public Plane() {
    }

    public Plane(Integer planeId) {
        this.planeId = planeId;
    }

    public Integer getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Integer planeId) {
        this.planeId = planeId;
    }

    public Date getPlaneDate() {
        return planeDate;
    }

    public void setPlaneDate(Date planeDate) {
        this.planeDate = planeDate;
    }

    public String getPlaneTimeDepart() {
        return planeTimeDepart;
    }

    public void setPlaneTimeDepart(String planeTimeDepart) {
        this.planeTimeDepart = planeTimeDepart;
    }

    public String getPlaneDepart() {
        return planeDepart;
    }

    public void setPlaneDepart(String planeDepart) {
        this.planeDepart = planeDepart;
    }

    public String getPlaneTimeArrive() {
        return planeTimeArrive;
    }

    public void setPlaneTimeArrive(String planeTimeArrive) {
        this.planeTimeArrive = planeTimeArrive;
    }

    public String getPlaneArrive() {
        return planeArrive;
    }

    public void setPlaneArrive(String planeArrive) {
        this.planeArrive = planeArrive;
    }

    public String getAirNumber() {
        return airNumber;
    }

    public void setAirNumber(String airNumber) {
        this.airNumber = airNumber;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getCompanyPath() {
        return companyPath;
    }

    public void setCompanyPath(String companyPath) {
        this.companyPath = companyPath;
    }

    public Date getPlaneDateArrive() {
        return planeDateArrive;
    }

    public void setPlaneDateArrive(Date planeDateArrive) {
        this.planeDateArrive = planeDateArrive;
    }

    public String getPlaning() {
        return planing;
    }

    public void setPlaning(String planing) {
        this.planing = planing;
    }

    public String getAirCodeDepart() {
        return airCodeDepart;
    }

    public void setAirCodeDepart(String airCodeDepart) {
        this.airCodeDepart = airCodeDepart;
    }

    public String getAirNameDepart() {
        return airNameDepart;
    }

    public void setAirNameDepart(String airNameDepart) {
        this.airNameDepart = airNameDepart;
    }

    public String getAirCodeArrive() {
        return airCodeArrive;
    }

    public void setAirCodeArrive(String airCodeArrive) {
        this.airCodeArrive = airCodeArrive;
    }

    public String getAirNameArrive() {
        return airNameArrive;
    }

    public void setAirNameArrive(String airNameArrive) {
        this.airNameArrive = airNameArrive;
    }

    @XmlTransient
    public Collection<TourDetail> getTourDetailCollection() {
        return tourDetailCollection;
    }

    public void setTourDetailCollection(Collection<TourDetail> tourDetailCollection) {
        this.tourDetailCollection = tourDetailCollection;
    }

    @XmlTransient
    public Collection<TourDetail> getTourDetailCollection1() {
        return tourDetailCollection1;
    }

    public void setTourDetailCollection1(Collection<TourDetail> tourDetailCollection1) {
        this.tourDetailCollection1 = tourDetailCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeId != null ? planeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plane)) {
            return false;
        }
        Plane other = (Plane) object;
        if ((this.planeId == null && other.planeId != null) || (this.planeId != null && !this.planeId.equals(other.planeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Plane[ planeId=" + planeId + " ]";
    }
    
}
