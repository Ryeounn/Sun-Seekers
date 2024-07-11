/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbbeans;

import com.entities.UserReg;
import com.sessionbeans.UserRegFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "manageUser")
@SessionScoped
public class ManageUser implements Serializable {

    @EJB
    private UserRegFacadeLocal userRegFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<UserReg> result;
    private int totalUser;
    private int totalUserReg;
    private Map<Integer, Boolean> selectedUser = new HashMap<>();
    private int userId;
    private UserReg usered;
    private String comfirm;
    private int userRegId;
    private UserReg userUpdate;

    public ManageUser() {
    }

    @PostConstruct
    public void init() {
        usered = new UserReg();
        userPagination();
    }

    public boolean isAnySelected() {
        return selectedUser.values().stream().anyMatch(selected -> selected);
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

    public String createNewUsser() {
        UserReg user = userRegFacade.findByUsername(usered.getUserName());
        UserReg useremail = userRegFacade.findByEmail(usered.getUserEmail());
        if (user == null && useremail == null) {
            if (usered.getUserPass().equals(comfirm)) {
                try {
                    usered.setUserPass(md5(comfirm));
                    usered.setUserCity(usered.getUserCity());
                    usered.setUserAge(null);
                    usered.setPassPort(null);
                    usered.setUserGender(null);
                    usered.setUserAddress(null);
                    usered.setUserPhoto("default.jpg");
                    usered.setUserPath("/resources/images/adminArea/");
                    usered.setUserDob(null);
                    Date date = new Date();
                    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = formater.format(date);
                    Date parsedDate = formater.parse(formattedDate);
                    System.out.println("Dater:" + parsedDate);
                    usered.setUserDate(parsedDate);
                    userRegFacade.create(usered);
                } catch (ParseException e) {
                    System.out.println("Error");
                }

            }
        }
        return "/admin/users";
    }

    public List<UserReg> userPagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = userRegFacade.findAll();
            totalUser = result.size();
            totalPages = (int) Math.ceil((double) totalUser / pageSize);
            result = userRegFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalUserReg = (int) userRegFacade.coutWithSearch(keyword);
            totalUser = result.size();
            totalPages = (int) Math.ceil((double) totalUserReg / pageSize);
            result = userRegFacade.findUserBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public String findUserById() {
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (idUser != null) {
            userRegId = Integer.parseInt(idUser);
            usered = userRegFacade.findById(userRegId);
            return "/admin/updateUser?faces-redirect=true";
        } else {
            System.out.println("Error Get");
            return null;
        }
    }

    public String updatingUser() {
        userUpdate = userRegFacade.find(usered.getUserId());
        userUpdate.setUserFName(usered.getUserFName());
        userUpdate.setUserLName(usered.getUserLName());
        UserReg ad = userRegFacade.findByEmail(usered.getUserEmail());
        if (ad != null) {
            userUpdate.setUserEmail(usered.getUserEmail());
        } else {
            System.out.println("Email invalid");
        }
        userUpdate.setUserPhone(usered.getUserPhone());
        userUpdate.setPassPort(usered.getPassPort());
        userUpdate.setUserAddress(usered.getUserAddress());
        userUpdate.setUserAge(usered.getUserAge());
        userUpdate.setUserGender(usered.getUserGender());
        userUpdate.setUserCity(usered.getUserCity());
        userUpdate.setUserDob(usered.getUserDob());
        userUpdate.setUserPath("/resources/images/adminArea/");
        userUpdate.setUserPhoto("default.jpg");
        userRegFacade.edit(userUpdate);
        userPagination();
        return "/admin/users?faces-redirect=true";
    }

    public void deleteSelectedProducts(int userId) {
        userRegFacade.deleteUser(userId);
        selectedUser.clear();
        userPagination();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            userPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            userPagination();
        }
    }

    public void searchUsers() {
        currentPage = 1;
        userPagination();
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

    public List<UserReg> getResult() {
        return result;
    }

    public void setResult(List<UserReg> result) {
        this.result = result;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public Map<Integer, Boolean> getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Map<Integer, Boolean> selectedUser) {
        this.selectedUser = selectedUser;
    }

    public int getTotalUserReg() {
        return totalUserReg;
    }

    public void setTotalUserReg(int totalUserReg) {
        this.totalUserReg = totalUserReg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserReg getUsered() {
        return usered;
    }

    public void setUsered(UserReg usered) {
        this.usered = usered;
    }

    public String getComfirm() {
        return comfirm;
    }

    public void setComfirm(String comfirm) {
        this.comfirm = comfirm;
    }

    public int getUserRegId() {
        return userRegId;
    }

    public void setUserRegId(int userRegId) {
        this.userRegId = userRegId;
    }

    public UserReg getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserReg userUpdate) {
        this.userUpdate = userUpdate;
    }
}
