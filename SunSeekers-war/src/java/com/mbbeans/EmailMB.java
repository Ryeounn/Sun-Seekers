package com.mbbeans;

import com.entities.UserReg;
import com.sessionbeans.UserRegFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named(value = "emailMB")
@SessionScoped
public class EmailMB implements Serializable {

    @EJB
    private UserRegFacadeLocal userRegFacade;

    String from = "phangialac2406@gmail.com";
    final String username = "phangialac2406@gmail.com";
    final String password = "adgk jcne xxmo qbot";
    private int userId;

    public EmailMB() {
    }

    private String email;
    private String verificationCode;
    private String inputCode;
    private String newPassword;
    private String confirmPassword;
    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;
    private String total;

    @PostConstruct
    public void init() {

    }

    public String sendVerificationCode() {
        UserReg user = userRegFacade.findPasswordForget(email);
        if (user != null) {
            userId = user.getUserId();
            Random random = new Random();
            verificationCode = String.format("%06d", random.nextInt(999999));
            String content = "<div style='font-size:18px;margin-bottom:.5em;color:black'>This is an email containing the verification code for the Sun Seekers app.<br/></div>"
                    + "<div style='margion-top:.5em;color:red;font-size:20px;'>Please do not share this email with anyone!</div>"
                    + "<div style='margin-top:.5em;font-size:20px;'>Your authentication code is: <b style='font-size:24px;color:red;font-weight:bold'>" + verificationCode + "</b></div>"
                    + "<div style='margin-top:.5em;font-size:18px;color:black'>If you are not the person sending this request, please change your account password immediately to avoid unauthorized access.<div>";
            sendMail(email, "Sun Seekers app authentication code", content);
            return "/client/verify";
        }else{
            return "/client/forget";
        }
    }

    public String verifyCode() {
        total = one + two + three + four + five + six;
        System.out.println("Total: " + total);
        if (verificationCode.equals(total)) {
            return "/client/change";
        } else {
            System.out.println("Verify Fail");
            return null;
        }
    }

    public String resetPassword() {
        if (newPassword.equals(confirmPassword)) {
            
            userRegFacade.updatePasswordForget(email, newPassword);
            return "/login";
        } else {
            // Show error message
            return null;
        }
    }

    public void sendMail(String to, String subject, String content) {

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
