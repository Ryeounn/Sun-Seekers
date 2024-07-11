/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.ShoppingCart;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface ShoppingCartFacadeLocal {

    void create(ShoppingCart shoppingCart);

    void edit(ShoppingCart shoppingCart);

    void remove(ShoppingCart shoppingCart);

    ShoppingCart find(Object id);

    List<ShoppingCart> findAll();

    List<ShoppingCart> findRange(int[] range);

    int count();
    
    void addcart(ShoppingCart shoppingCart);
    
    ShoppingCart findCartByTourandUser(int tourId, int userId);
    
    void updateQuantity(ShoppingCart shoppingcart, int quantity);
    
    Long sumQuantity(int userId);
    
    List<ShoppingCart> showAllById(int userId);
    
    void deleteItems(ShoppingCart shoppingcart);
    
    void deleteAll(int userId);
    
    void dltAfterCheckOut(int userId);
    
    void deleteByTourId(int tourId);
}
