package com.mbbeans;

import com.entities.OrderDetails;
import com.entities.Orders;
import com.entities.UserReg;
import com.sessionbeans.OrderDetailsFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@Named(value = "userInfoMB")
@SessionScoped
public class UserInfoMB implements Serializable {
    private static final String uploadDirectory = "D:\\A22063\\JavaWeb\\FinalAssignment\\SunSeekers\\SunSeekers-war\\web\\resources\\images\\upload";

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private UserRegFacadeLocal userRegFacade;

    private List<String> orderByUserid;
    private int userId;
    private List<Orders> orderList;
    private List<OrderDetails> orderdetail;
    private int pageSize = 3;
    private int currentPage = 1;
    private long totalOrders;
    private int totalPages;
    private UserReg userProfile = new UserReg();

    private String userFName;
    private String userLName;
    private String userPhone;
    private String passPort;
    private String userCity;
    private String userAddress;
    private String userEmail;
    private String newEmail;
    private String newPass;
    private String confirmPass;
    private Part img;

    public UserInfoMB() {
    }

    public void saveProfile() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idUser = externalContext.getSessionMap().get("userId");
        if (idUser != null) {
            this.userId = (int) idUser;
            String commonPath = "/resources/images/upload/";
            UserReg user = userRegFacade.findById(userId);
            if (user != null) {
                user.setUserFName(userFName);
                user.setUserLName(userLName);
                user.setUserPhone(userPhone);
                user.setPassPort(passPort);
                user.setUserCity(userCity);
                user.setUserAddress(userAddress);
                user.setUserPath(commonPath);
                if (img != null) {
                    String i1 = saveFile(img, uploadDirectory);
                    user.setUserPhoto(i1);
                    System.out.println("Path: " + i1);
                    System.out.println("Img Succes");
                } else {
                    System.out.println("Error fileMorning");
                }
                userRegFacade.updateInfo(user);
                System.out.println("Succes");
            } else {
                System.out.println("Error Session");
            }
        }
    }

    public void saveEmail() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idUser = externalContext.getSessionMap().get("userId");
        if (idUser != null) {
            this.userId = (int) idUser;
            UserReg user = userRegFacade.findById(userId);
            if (user != null) {
                user.setUserEmail(newEmail);
                userRegFacade.updateEmail(user);
                newEmail = "";
            } else {
                System.out.println("Error Change Email");
            }
        } else {
            System.out.println("Error Session email");
        }
    }

    public UserReg showProfile() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idUser = externalContext.getSessionMap().get("userId");
        if (idUser != null) {
            this.userId = (int) idUser;
            userProfile = userRegFacade.findById(userId);
            userFName = userProfile.getUserFName();
            userLName = userProfile.getUserLName();
            userPhone = userProfile.getUserPhone();
            passPort = userProfile.getPassPort();
            userCity = userProfile.getUserCity();
            userAddress = userProfile.getUserAddress();
            userEmail = userProfile.getUserEmail();
            return userProfile;
        } else {
            return null;
        }
    }

    public List<Orders> findByUser() {
        orderList = ordersFacade.findOrderByUserID(userId);
        totalOrders = orderList.size();
        totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        orderList = ordersFacade.findOrderByUser(userId, currentPage, pageSize);
        return orderList;
    }

    public List<OrderDetails> findOrderdetailbyOrder(int orderId) {
        return orderDetailsFacade.findOrderByUser(orderId);
    }

    public void savePass() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idUser = externalContext.getSessionMap().get("userId");
        if (idUser != null) {
            this.userId = (int) idUser;
            UserReg user = userRegFacade.findById(userId);
            if (user != null) {
                if (newPass.equals(confirmPass)) {
                    user.setUserPass(newPass);
                    userRegFacade.updatePassword(user);
                    newPass = "";
                    confirmPass = "";
                } else {
                    System.out.println("Not Match");
                }
            } else {
                System.out.println("Error Change Email");
            }
        } else {
            System.out.println("Error Session Password");
        }
    }

    private String saveFile(Part file, String commonPath) {
        try (InputStream input = file.getInputStream()) {
            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
            String filePath = commonPath + File.separator + fileName;
            Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            return fileName; // Return only the file name
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void findOrderByUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object idUser = externalContext.getSessionMap().get("userId");
        if (idUser != null) {
            this.userId = (int) idUser;
        }
    }

    private List<Orders> applyFilters() {
        List<Orders> result = ordersFacade.findOrderByUserID(userId);
        totalOrders = result.size();
        totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        return result;
    }

    public void updatePaginatedList() {
        orderList = applyFilters();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("pagination");
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            updatePaginatedList();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updatePaginatedList();
        }
    }

    public void applyFilter() {
        currentPage = 1;
        updatePaginatedList();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Orders> orderList) {
        this.orderList = orderList;
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

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public UserReg getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserReg userProfile) {
        this.userProfile = userProfile;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassPort() {
        return passPort;
    }

    public void setPassPort(String passPort) {
        this.passPort = passPort;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public List<String> getOrderByUserid() {
        return orderByUserid;
    }

    public void setOrderByUserid(List<String> orderByUserid) {
        this.orderByUserid = orderByUserid;
    }

    public List<OrderDetails> getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(List<OrderDetails> orderdetail) {
        this.orderdetail = orderdetail;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }
}
