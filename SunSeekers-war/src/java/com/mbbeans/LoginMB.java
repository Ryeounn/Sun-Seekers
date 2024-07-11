package com.mbbeans;

import com.entities.Admins;
import com.entities.UserReg;
import com.sessionbeans.AdminsFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{

    @EJB
    private UserRegFacadeLocal userRegFacade;

    @EJB
    private AdminsFacadeLocal adminsFacade;

    private String username;
    private String password;
    private String userFName;
    private String userLName;
    private int userId;
    private Date userDob;
    private Date userDate;
    private String formattedDate;
    private String formattedDob;
    private String userPhoto;
    private String userPath;
    private UserReg userReg;
    private Admins admins;
    private int adminId;
    private Admins admin;
    public LoginMB() {
    }

    public String checkLogin(){
        if(userRegFacade.checkLogin(username, password) != null){
            userFName = userRegFacade.checkLogin(username, password).getUserFName();
            userLName = userRegFacade.checkLogin(username, password).getUserLName();
            userId = userRegFacade.checkLogin(username, password).getUserId();
            userDob = userRegFacade.checkLogin(username, password).getUserDob();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            formattedDob = sdf.format(userDob);
            userDate = userRegFacade.checkLogin(username, password).getUserDate();
            SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = sdfdate.format(userDate);
            userPhoto = userRegFacade.checkLogin(username, password).getUserPhoto();
            userPath = userRegFacade.checkLogin(username, password).getUserPath();
            userReg = userRegFacade.checkLogin(username, password);
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.getSessionMap().put("userId", userId);
            
            return "/client/home?faces-redirect=true";
        }else if(adminsFacade.checkLogin(username, password) != null){
            adminId = adminsFacade.checkLogin(username, password).getAdminsId();
            admin = adminsFacade.checkLogin(username, password);
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.getSessionMap().put("adminId", adminId);
            
            return "/admin/dashboard?faces-redirect=true";
        }else{
            return "/login";
        }
    }
    
    public void logout()throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        externalContext.getSessionMap().remove("userId");

        externalContext.invalidateSession();
        externalContext.redirect("/SunSeekers-war/faces/login.xhtml");
    }
    

    public UserReg getUserReg() {
        return userReg;
    }

    public void setUserReg(UserReg userReg) {
        this.userReg = userReg;
    }

    public Admins getAdmins() {
        return admins;
    }

    public void setAdmins(Admins admins) {
        this.admins = admins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getFormattedDob() {
        return formattedDob;
    }

    public void setFormattedDob(String formattedDob) {
        this.formattedDob = formattedDob;
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }
    
}
