package com.mbbeans;

import com.entities.Admins;
import com.sessionbeans.AdminsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "adminMB")
@SessionScoped
public class AdminMB implements Serializable {

    @EJB
    private AdminsFacadeLocal adminsFacade;

    private int adminId;
    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Admins> result;
    private int totalAdmins;
    private int totalAdministrator;
    private Admins adminser;
    private String confirm;
    private Admins updateAdmins;
    
    public AdminMB() {
    }
    
    @PostConstruct
    public void init() {
        adminser = new Admins();
        adminPagination();
    }
    
    public Admins showInfo(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idAdmin = externalContext.getSessionMap().get("adminId");
        if (idAdmin != null) {
            this.adminId = (int) idAdmin;
            return adminsFacade.findById(adminId);
        }else{
            return null;
        }
        
    }
    
    public List<Admins> adminPagination(){
        if(keyword == null || keyword.isEmpty()){
            result = adminsFacade.findAll();
            totalAdmins = result.size();
            totalPages = (int) Math.ceil((double) totalAdmins / pageSize);
            result = adminsFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        }else{
            totalAdministrator = (int) adminsFacade.countWithSearch(keyword);
            totalAdmins = result.size();
            totalPages = (int) Math.ceil((double) totalAdministrator / pageSize);
            result = adminsFacade.findAdminsBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }
    
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String createNewAdmins(){
        Admins adminUser = adminsFacade.findAdminByUser(adminser.getAdminUser());
        Admins adminEmail = adminsFacade.findAdminByEmail(adminser.getAdminEmail());
        if(adminUser == null && adminEmail == null){
            if(adminser.getAdminPass().equals(confirm)){
                adminser.setAdminPass(md5(confirm));
                adminser.setAdminPath("/resources/images/adminArea/");
                adminser.setAdminPhoto("default.jpg");
                adminser.setAdminDob(null);
                adminsFacade.create(adminser);
            }
        }
        adminPagination();
        return "/admin/admins";
    }
    
    public String findAdminsById() {
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("adminId");
        if (idUser != null) {
            adminId = Integer.parseInt(idUser);
            adminser = adminsFacade.findById(adminId);
            return "/admin/updateAdmins?faces-redirect=true";
        } else {
            System.out.println("Error Get");
            return null;
        }
    }
    
    public String updateAdmins(){
        String idAdmins = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("adminId");
        if (idAdmins != null) {
            adminId = Integer.parseInt(idAdmins);
            updateAdmins = adminsFacade.findById(adminId);
            updateAdmins.setAdminName(adminser.getAdminName());
            updateAdmins.setAdminDob(adminser.getAdminDob());
            updateAdmins.setAdminEmail(adminser.getAdminEmail());
            updateAdmins.setAdminAddress(adminser.getAdminAddress());
            updateAdmins.setAdminPhone(adminser.getAdminPhone());
            adminsFacade.edit(updateAdmins);
        }
        adminPagination();
        return "/admin/admins";
    }
    
    public void deleteSelectAdmis(int adminsId){
        adminsFacade.deleteByAdminId(adminsId);
        adminPagination();
    }
    
    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            adminPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            adminPagination();
        }
    }

    public void searchUsers() {
        currentPage = 1;
        adminPagination();
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Admins> getResult() {
        return result;
    }

    public void setResult(List<Admins> result) {
        this.result = result;
    }

    public int getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(int totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public int getTotalAdministrator() {
        return totalAdministrator;
    }

    public void setTotalAdministrator(int totalAdministrator) {
        this.totalAdministrator = totalAdministrator;
    }

    public Admins getAdminser() {
        return adminser;
    }

    public void setAdminser(Admins adminser) {
        this.adminser = adminser;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Admins getUpdateAdmins() {
        return updateAdmins;
    }

    public void setUpdateAdmins(Admins updateAdmins) {
        this.updateAdmins = updateAdmins;
    }
}
