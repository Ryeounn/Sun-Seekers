/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.WishList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface WishListFacadeLocal {

    void create(WishList wishList);

    void edit(WishList wishList);

    void remove(WishList wishList);

    WishList find(Object id);

    List<WishList> findAll();

    List<WishList> findRange(int[] range);

    int count();
    
    List<WishList> findWishByUser(int userId);

    WishList findById(int tourId, int userId);

    void deleteAllById(int userId);
    
    Long sumQuantity(int userId);
    
    void deleteWishById(int tourId);
}
