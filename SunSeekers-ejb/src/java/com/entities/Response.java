/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ngu Công
 */
@Entity
@Table(name = "Response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Response.findAll", query = "SELECT r FROM Response r"),
    @NamedQuery(name = "Response.findByResponseId", query = "SELECT r FROM Response r WHERE r.responseId = :responseId"),
    @NamedQuery(name = "Response.findByPosition", query = "SELECT r FROM Response r WHERE r.position = :position"),
    @NamedQuery(name = "Response.findByResContent", query = "SELECT r FROM Response r WHERE r.resContent = :resContent"),
    @NamedQuery(name = "Response.findByResDate", query = "SELECT r FROM Response r WHERE r.resDate = :resDate")})
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ResponseId")
    private Integer responseId;
    @Size(max = 255)
    @Column(name = "Position")
    private String position;
    @Size(max = 2147483647)
    @Column(name = "ResContent")
    private String resContent;
    @Column(name = "ResDate")
    @Temporal(TemporalType.DATE)
    private Date resDate;
    @JoinColumn(name = "AdminsId", referencedColumnName = "AdminsId")
    @ManyToOne
    private Admins adminsId;
    @JoinColumn(name = "CommentId", referencedColumnName = "CommentId")
    @ManyToOne
    private Comment commentId;

    public Response() {
    }

    public Response(Integer responseId) {
        this.responseId = responseId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResContent() {
        return resContent;
    }

    public void setResContent(String resContent) {
        this.resContent = resContent;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public Admins getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(Admins adminsId) {
        this.adminsId = adminsId;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responseId != null ? responseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.responseId == null && other.responseId != null) || (this.responseId != null && !this.responseId.equals(other.responseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Response[ responseId=" + responseId + " ]";
    }
    
}
