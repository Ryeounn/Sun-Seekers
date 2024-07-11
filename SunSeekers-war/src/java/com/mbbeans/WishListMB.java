package com.mbbeans;

import com.entities.Tour;
import com.entities.UserReg;
import com.entities.WishList;
import com.sessionbeans.TourFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import com.sessionbeans.WishListFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "wishListMb")
@SessionScoped
public class WishListMB implements Serializable {

    @EJB
    private UserRegFacadeLocal userRegFacade;

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private WishListFacadeLocal wishListFacade;

    private int userId;
    private int tourId;
    private WishList wishlist;
    
    private List<WishList> listWish;
    private Long sumQuantity;

    public WishListMB() {
    }

    public void findWish() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            Tour tour = tourFacade.find(tourId);
            UserReg user = userRegFacade.find(userId);
            wishlist = wishListFacade.findById(tourId, userId);
            if (wishlist == null) {
                System.out.println("b");
                WishList newWish = new WishList();
                newWish.setTourId(tour);
                newWish.setUserId(user);
                wishListFacade.create(newWish);
            } else {
                WishList newWish = wishListFacade.findById(tourId, userId);
                wishListFacade.remove(newWish);
            }
            wishlist = wishListFacade.findById(tourId, userId);
        } else {
            System.out.println("Wish List Add or Delete Fail");
        }
    }

    public List<WishList> myWish() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object userIdObj = externalContext.getSessionMap().get("userId");
        if (userIdObj != null) {
            this.userId = (int) userIdObj;
            listWish = wishListFacade.findWishByUser(userId);
        } else {
            return null;
        }
        return listWish;
    }

    public void deleteWish() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (id != null && idUser != null) {
            tourId = Integer.parseInt(id);
            userId = Integer.parseInt(idUser);
            WishList newWish = wishListFacade.findById(tourId, userId);
            wishListFacade.remove(newWish);
        } else {
            System.out.println("Delete Items Wish List Fail");
        }
    }

    public void deleteWishList() {
        String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        if (idUser != null) {
            userId = Integer.parseInt(idUser);
            wishListFacade.deleteAllById(userId);
        } else {
            System.out.println("Error");
        }
    }

    public WishList findById(int tour, int userId) {
        return wishListFacade.findById(tour, userId);
    }
    
    public List<WishList> findListById(int userId){
        return wishListFacade.findWishByUser(userId);
    }
    
    public Long sumQuantity() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object userIdObj = externalContext.getSessionMap().get("userId");
        if(userIdObj != null){
            this.userId = (int) userIdObj;
            sumQuantity = wishListFacade.sumQuantity(userId);
        }else{
            sumQuantity = null;
        }
        System.out.println(sumQuantity);
        System.out.println(userId);
        return sumQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public WishList getWishlist() {
        return wishlist;
    }

    public void setWishlist(WishList wishlist) {
        this.wishlist = wishlist;
    }

    public List<WishList> getListWish() {
        return listWish;
    }

    public void setListWish(List<WishList> listWish) {
        this.listWish = listWish;
    }

    public Long getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Long sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

}
