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
@Table(name = "Images")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery(name = "Images.findByImageId", query = "SELECT i FROM Images i WHERE i.imageId = :imageId"),
    @NamedQuery(name = "Images.findByImg1", query = "SELECT i FROM Images i WHERE i.img1 = :img1"),
    @NamedQuery(name = "Images.findByImg2", query = "SELECT i FROM Images i WHERE i.img2 = :img2"),
    @NamedQuery(name = "Images.findByImg3", query = "SELECT i FROM Images i WHERE i.img3 = :img3"),
    @NamedQuery(name = "Images.findByImg4", query = "SELECT i FROM Images i WHERE i.img4 = :img4"),
    @NamedQuery(name = "Images.findByImg5", query = "SELECT i FROM Images i WHERE i.img5 = :img5"),
    @NamedQuery(name = "Images.findByImg6", query = "SELECT i FROM Images i WHERE i.img6 = :img6"),
    @NamedQuery(name = "Images.findByImgPath", query = "SELECT i FROM Images i WHERE i.imgPath = :imgPath")})
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ImageId")
    private Integer imageId;
    @Size(max = 255)
    @Column(name = "Img1")
    private String img1;
    @Size(max = 255)
    @Column(name = "Img2")
    private String img2;
    @Size(max = 255)
    @Column(name = "Img3")
    private String img3;
    @Size(max = 255)
    @Column(name = "Img4")
    private String img4;
    @Size(max = 255)
    @Column(name = "Img5")
    private String img5;
    @Size(max = 255)
    @Column(name = "Img6")
    private String img6;
    @Size(max = 255)
    @Column(name = "ImgPath")
    private String imgPath;
    @OneToMany(mappedBy = "imageId")
    private Collection<Tour> tourCollection;

    public Images() {
    }

    public Images(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getImg6() {
        return img6;
    }

    public void setImg6(String img6) {
        this.img6 = img6;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Images[ imageId=" + imageId + " ]";
    }
    
}
