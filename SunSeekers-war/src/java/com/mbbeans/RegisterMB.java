package com.mbbeans;

import com.entities.UserReg;
import com.sessionbeans.UserRegFacadeLocal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "registerMB")
@RequestScoped
public class RegisterMB {

    @EJB
    private UserRegFacadeLocal userRegFacade;

    private UserReg userReg;
    private String pass;
    private String confirmPass;
    private Date currentDate;

    public RegisterMB() {
        LocalDate localDate = LocalDate.now();
        currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        userReg = new UserReg();
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

    public String registerUser() {
        if (pass.equals(confirmPass) && userReg.getUserEmail().equals(userReg.getUserEmail()) && userReg.getUserName().equals(userReg.getUserName())) {
            System.out.println("Pass: " + pass);
            System.out.println("Confirm: " + confirmPass);
            userReg.setUserPass(md5(pass));
            userReg.setUserDate(currentDate);
            userReg.setUserCity("Default");
            userReg.setUserPhoto("default.jpg");
            userReg.setUserPath("/resources/images/adminArea/");
            userReg.setUserDob(currentDate);
            userRegFacade.create(userReg);
        }
        return "/login?faces-redirect=true";
    }

    public String abc() {
        return "/client/tour";
    }

    public UserReg getUserReg() {
        return userReg;
    }

    public void setUserReg(UserReg userReg) {
        this.userReg = userReg;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

}
