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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "Schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId"),
    @NamedQuery(name = "Schedule.findBySDate", query = "SELECT s FROM Schedule s WHERE s.sDate = :sDate"),
    @NamedQuery(name = "Schedule.findBySName", query = "SELECT s FROM Schedule s WHERE s.sName = :sName"),
    @NamedQuery(name = "Schedule.findBySTime", query = "SELECT s FROM Schedule s WHERE s.sTime = :sTime"),
    @NamedQuery(name = "Schedule.findBySMorning", query = "SELECT s FROM Schedule s WHERE s.sMorning = :sMorning"),
    @NamedQuery(name = "Schedule.findBySAfternoon", query = "SELECT s FROM Schedule s WHERE s.sAfternoon = :sAfternoon"),
    @NamedQuery(name = "Schedule.findBySEverning", query = "SELECT s FROM Schedule s WHERE s.sEverning = :sEverning"),
    @NamedQuery(name = "Schedule.findBySAddress", query = "SELECT s FROM Schedule s WHERE s.sAddress = :sAddress"),
    @NamedQuery(name = "Schedule.findBySImageMor", query = "SELECT s FROM Schedule s WHERE s.sImageMor = :sImageMor"),
    @NamedQuery(name = "Schedule.findBySImageAfter", query = "SELECT s FROM Schedule s WHERE s.sImageAfter = :sImageAfter"),
    @NamedQuery(name = "Schedule.findBySImageEver", query = "SELECT s FROM Schedule s WHERE s.sImageEver = :sImageEver"),
    @NamedQuery(name = "Schedule.findBySPath", query = "SELECT s FROM Schedule s WHERE s.sPath = :sPath")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ScheduleId")
    private Integer scheduleId;
    @Size(max = 255)
    @Column(name = "SDate")
    private String sDate;
    @Size(max = 255)
    @Column(name = "SName")
    private String sName;
    @Size(max = 255)
    @Column(name = "STime")
    private String sTime;
    @Size(max = 2147483647)
    @Column(name = "SMorning")
    private String sMorning;
    @Size(max = 2147483647)
    @Column(name = "SAfternoon")
    private String sAfternoon;
    @Size(max = 2147483647)
    @Column(name = "SEverning")
    private String sEverning;
    @Size(max = 255)
    @Column(name = "SAddress")
    private String sAddress;
    @Size(max = 255)
    @Column(name = "SImageMor")
    private String sImageMor;
    @Size(max = 255)
    @Column(name = "SImageAfter")
    private String sImageAfter;
    @Size(max = 255)
    @Column(name = "SImageEver")
    private String sImageEver;
    @Size(max = 255)
    @Column(name = "SPath")
    private String sPath;
    @JoinColumn(name = "TourId", referencedColumnName = "TourId")
    @ManyToOne
    private Tour tourId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSDate() {
        return sDate;
    }

    public void setSDate(String sDate) {
        this.sDate = sDate;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSTime() {
        return sTime;
    }

    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getSMorning() {
        return sMorning;
    }

    public void setSMorning(String sMorning) {
        this.sMorning = sMorning;
    }

    public String getSAfternoon() {
        return sAfternoon;
    }

    public void setSAfternoon(String sAfternoon) {
        this.sAfternoon = sAfternoon;
    }

    public String getSEverning() {
        return sEverning;
    }

    public void setSEverning(String sEverning) {
        this.sEverning = sEverning;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getSImageMor() {
        return sImageMor;
    }

    public void setSImageMor(String sImageMor) {
        this.sImageMor = sImageMor;
    }

    public String getSImageAfter() {
        return sImageAfter;
    }

    public void setSImageAfter(String sImageAfter) {
        this.sImageAfter = sImageAfter;
    }

    public String getSImageEver() {
        return sImageEver;
    }

    public void setSImageEver(String sImageEver) {
        this.sImageEver = sImageEver;
    }

    public String getSPath() {
        return sPath;
    }

    public void setSPath(String sPath) {
        this.sPath = sPath;
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
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Schedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
