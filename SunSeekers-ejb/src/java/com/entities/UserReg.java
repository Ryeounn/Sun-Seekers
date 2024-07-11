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
@Table(name = "UserReg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserReg.findAll", query = "SELECT u FROM UserReg u"),
    @NamedQuery(name = "UserReg.findByUserId", query = "SELECT u FROM UserReg u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserReg.findByUserFName", query = "SELECT u FROM UserReg u WHERE u.userFName = :userFName"),
    @NamedQuery(name = "UserReg.findByUserLName", query = "SELECT u FROM UserReg u WHERE u.userLName = :userLName"),
    @NamedQuery(name = "UserReg.findByUserName", query = "SELECT u FROM UserReg u WHERE u.userName = :userName"),
    @NamedQuery(name = "UserReg.findByUserPass", query = "SELECT u FROM UserReg u WHERE u.userPass = :userPass"),
    @NamedQuery(name = "UserReg.findByUserEmail", query = "SELECT u FROM UserReg u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "UserReg.findByUserDob", query = "SELECT u FROM UserReg u WHERE u.userDob = :userDob"),
    @NamedQuery(name = "UserReg.findByUserCity", query = "SELECT u FROM UserReg u WHERE u.userCity = :userCity"),
    @NamedQuery(name = "UserReg.findByUserPhone", query = "SELECT u FROM UserReg u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "UserReg.findByUserAge", query = "SELECT u FROM UserReg u WHERE u.userAge = :userAge"),
    @NamedQuery(name = "UserReg.findByUserGender", query = "SELECT u FROM UserReg u WHERE u.userGender = :userGender"),
    @NamedQuery(name = "UserReg.findByPassPort", query = "SELECT u FROM UserReg u WHERE u.passPort = :passPort"),
    @NamedQuery(name = "UserReg.findByUserAddress", query = "SELECT u FROM UserReg u WHERE u.userAddress = :userAddress"),
    @NamedQuery(name = "UserReg.findByUserPhoto", query = "SELECT u FROM UserReg u WHERE u.userPhoto = :userPhoto"),
    @NamedQuery(name = "UserReg.findByUserPath", query = "SELECT u FROM UserReg u WHERE u.userPath = :userPath"),
    @NamedQuery(name = "UserReg.findByUserDate", query = "SELECT u FROM UserReg u WHERE u.userDate = :userDate")})
public class UserReg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Size(max = 255)
    @Column(name = "UserFName")
    private String userFName;
    @Size(max = 255)
    @Column(name = "UserLName")
    private String userLName;
    @Size(max = 255)
    @Column(name = "UserName")
    private String userName;
    @Size(max = 255)
    @Column(name = "UserPass")
    private String userPass;
    @Size(max = 255)
    @Column(name = "UserEmail")
    private String userEmail;
    @Column(name = "UserDob")
    @Temporal(TemporalType.DATE)
    private Date userDob;
    @Size(max = 255)
    @Column(name = "UserCity")
    private String userCity;
    @Size(max = 255)
    @Column(name = "UserPhone")
    private String userPhone;
    @Column(name = "UserAge")
    private Integer userAge;
    @Size(max = 255)
    @Column(name = "UserGender")
    private String userGender;
    @Size(max = 255)
    @Column(name = "PassPort")
    private String passPort;
    @Size(max = 255)
    @Column(name = "UserAddress")
    private String userAddress;
    @Size(max = 255)
    @Column(name = "UserPhoto")
    private String userPhoto;
    @Size(max = 255)
    @Column(name = "UserPath")
    private String userPath;
    @Column(name = "UserDate")
    @Temporal(TemporalType.DATE)
    private Date userDate;
    @OneToMany(mappedBy = "userId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<WishList> wishListCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<ShoppingCart> shoppingCartCollection;

    public UserReg() {
    }

    public UserReg(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getPassPort() {
        return passPort;
    }

    public void setPassPort(String passPort) {
        this.passPort = passPort;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<WishList> getWishListCollection() {
        return wishListCollection;
    }

    public void setWishListCollection(Collection<WishList> wishListCollection) {
        this.wishListCollection = wishListCollection;
    }

    @XmlTransient
    public Collection<ShoppingCart> getShoppingCartCollection() {
        return shoppingCartCollection;
    }

    public void setShoppingCartCollection(Collection<ShoppingCart> shoppingCartCollection) {
        this.shoppingCartCollection = shoppingCartCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserReg)) {
            return false;
        }
        UserReg other = (UserReg) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.UserReg[ userId=" + userId + " ]";
    }
    
}
