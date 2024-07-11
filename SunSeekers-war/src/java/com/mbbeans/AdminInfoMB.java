/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbbeans;

import com.entities.Admins;
import com.sessionbeans.AdminsFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ngu Công
 */
@Named(value = "adminInfoMB")
@SessionScoped
public class AdminInfoMB implements Serializable {

    @EJB
    private AdminsFacadeLocal adminsFacade;

    private Admins admin;
    private int adminId;
    
    public AdminInfoMB() {
    }
    
    public String showAdmin(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idAdmin = externalContext.getSessionMap().get("adminId");
        if (idAdmin != null) {
            this.adminId = (int) idAdmin;
            admin = adminsFacade.find(idAdmin);
        }
        return "/admin/info";
    }
    
    public void logout()throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        externalContext.getSessionMap().remove("adminId");

        externalContext.invalidateSession();
        externalContext.redirect("/SunSeekers-war/faces/login.xhtml");
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
