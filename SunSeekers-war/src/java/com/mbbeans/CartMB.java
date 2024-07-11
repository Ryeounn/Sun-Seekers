package com.mbbeans;

import com.entities.ShoppingCart;
import com.entities.Tour;
import com.entities.TourDetail;
import com.entities.UserReg;
import com.sessionbeans.ShoppingCartFacadeLocal;
import com.sessionbeans.TourDetailFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "cartMB")
@SessionScoped
public class CartMB implements Serializable {

    @EJB
    private TourDetailFacadeLocal tourDetailFacade;

    @EJB
    private UserRegFacadeLocal userRegFacade;

    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;

    @EJB
    private TourFacadeLocal tourFacade;

    private int tourId;
    private int userId;

    private int quantity = 1;
    private Long sumQuantity;

    private List<ShoppingCart> showCart;
    private List<TourDetail> showExplore;

    public CartMB() {
    }

    public String addCart() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            Tour tour = tourFacade.find(tourId);
            UserReg user = userRegFacade.find(userId);
            ShoppingCart cart = shoppingCartFacade.findCartByTourandUser(tourId, userId);
            if (cart != null) {
                int newquantity = cart.getQuantity() + quantity;
                shoppingCartFacade.updateQuantity(cart, newquantity);
            } else {
                ShoppingCart newCart = new ShoppingCart();
                newCart.setTourId(tour);
                newCart.setQuantity(quantity);
                newCart.setUserId(user);
                shoppingCartFacade.addcart(newCart);
            }
        }
        return "/client/detail?tourId=" + tourId;
    }

    public Long sumQuantity() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object userIdObj = externalContext.getSessionMap().get("userId");
        if (userIdObj != null) {
            this.userId = (int) userIdObj;
            sumQuantity = shoppingCartFacade.sumQuantity(userId);
        } else {
            sumQuantity = null;
        }
        return sumQuantity;
    }

    public Float sumPrice() {
        float sum = 0;
        for (ShoppingCart item : showCart) {
            sum += item.getQuantity().floatValue() * item.getTourId().getTourPrice().floatValue();
        }
        return sum;
    }

    public List<ShoppingCart> showAllByUserId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object userIdObj = externalContext.getSessionMap().get("userId");
        if (userIdObj != null) {
            this.userId = (int) userIdObj;
            showCart = shoppingCartFacade.showAllById(userId);
        } else {
            showCart = null;
        }
        return showCart;
    }

    public List<TourDetail> showExplore() {
        showExplore = tourDetailFacade.findExplore();
        return showExplore;
    }

    public String increaseCart() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            Tour tour = tourFacade.find(tourId);
            UserReg user = userRegFacade.find(userId);
            ShoppingCart cart = shoppingCartFacade.findCartByTourandUser(tourId, userId);
            if (cart != null) {
                int newquantity = cart.getQuantity() + quantity;
                shoppingCartFacade.updateQuantity(cart, newquantity);
            } else {
                quantity = cart.getQuantity();
            }
        }
        return "/client/cart";
    }

    public String decreaseCart() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            Tour tour = tourFacade.find(tourId);
            UserReg user = userRegFacade.find(userId);
            ShoppingCart cart = shoppingCartFacade.findCartByTourandUser(tourId, userId);
            if (cart != null) {
                int newquantity = cart.getQuantity();
                if (newquantity > 1) {
                    newquantity = cart.getQuantity() - quantity;
                    shoppingCartFacade.updateQuantity(cart, newquantity);
                } else{
                    shoppingCartFacade.deleteItems(cart);
                }
            } else {
                quantity = cart.getQuantity();
            }
        }
        return "/client/cart";
    }

    public String deleteTour() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            ShoppingCart cart = shoppingCartFacade.findCartByTourandUser(tourId, userId);
            if (cart != null) {
                shoppingCartFacade.deleteItems(cart);
            } else {
                System.out.println("No Delete");
            }
        }
        return "/client/cart";
    }

    public void deleteAllTour() {
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (idUser != null) {
            userId = Integer.parseInt(idUser);
            shoppingCartFacade.deleteAll(userId);
        } else {
            System.out.println("No Delete");
        }
    }

    public List<ShoppingCart> findCartById(int userId) {
        return shoppingCartFacade.showAllById(userId);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Long sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public List<ShoppingCart> getShowCart() {
        return showCart;
    }

    public void setShowCart(List<ShoppingCart> showCart) {
        this.showCart = showCart;
    }

    public List<TourDetail> getShowExplore() {
        return showExplore;
    }

    public void setShowExplore(List<TourDetail> showExplore) {
        this.showExplore = showExplore;
    }
}
