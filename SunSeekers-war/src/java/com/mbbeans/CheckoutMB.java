package com.mbbeans;

import com.entities.OrderDetails;
import com.entities.Orders;
import com.entities.Payment;
import com.entities.ShoppingCart;
import com.entities.UserReg;
import com.sessionbeans.OrderDetailsFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.PaymentFacadeLocal;
import com.sessionbeans.ShoppingCartFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import com.sessionbeans.WareHouseFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;

@Named(value = "checkoutMB")
@SessionScoped
public class CheckoutMB implements Serializable{

    @EJB
    private WareHouseFacadeLocal wareHouseFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;

    @EJB
    private UserRegFacadeLocal userRegFacade;

    @EJB
    private PaymentFacadeLocal paymentFacade;

    private int userId;
    private List<ShoppingCart> listCart;
    private Date currentDate;
    private int numberCard;
    private String numberName;
    private String inputDate;
    private int cvv;
    private Payment payment;
    public CheckoutMB() {
    }
    
    @PostConstruct
    public void init(){
        LocalDate localDate = LocalDate.now();
        currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        payment = new Payment();
    }

    public void checkOut() {
        System.out.println("UserId1: " + userId);
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid");
        if (idUser != null) {
            userId = Integer.parseInt(idUser);
            payment.setDateCard(inputDate);
            payment.setNameCard(numberName);
            payment.setNumberCard(numberCard);
            payment.setTypeCard("Visa");
            payment.setCvv(cvv);
            paymentFacade.create(payment);
            UserReg user = userRegFacade.find(userId);
            Orders order = new Orders();
            order.setOrdersDate(currentDate);
            order.setUserId(user);
            BigDecimal sumPrice = BigDecimal.ZERO;
            listCart = shoppingCartFacade.showAllById(userId);
            order.setTotalPrice(sumPrice);
            order.setOrderStatus("Pending");
            order.setAdminsId(null);
            order.setPaymentId(payment);
            ordersFacade.create(order);
            System.out.println("Cart");
            int quantity = 0;
            for (ShoppingCart cart : listCart) {
                BigDecimal itemPrice = cart.getTourId().getTourPrice();
                BigDecimal itemQuan = BigDecimal.valueOf(cart.getQuantity());
                BigDecimal totalPrice = itemPrice.multiply(itemQuan);
                sumPrice = sumPrice.add(totalPrice);
                int qt = cart.getQuantity();
                quantity = qt + quantity;
                OrderDetails od = new OrderDetails();
                od.setOrdersId(order);
                od.setPrice(cart.getTourId().getTourPrice());
                od.setQuantity(cart.getQuantity());
                od.setTourId(cart.getTourId());
                orderDetailsFacade.create(od);
                wareHouseFacade.updateWareHouse(cart.getTourId().getTourId(), cart.getQuantity());
                shoppingCartFacade.dltAfterCheckOut(userId);
                System.out.println("Cart Completed");
            }
            Orders order1 = ordersFacade.find(order.getOrdersId());
            order1.setTotalPrice(sumPrice);
            order1.setTotalQuantity(quantity);
            ordersFacade.edit(order1);
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<ShoppingCart> getListCart() {
        return listCart;
    }

    public void setListCart(List<ShoppingCart> listCart) {
        this.listCart = listCart;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
