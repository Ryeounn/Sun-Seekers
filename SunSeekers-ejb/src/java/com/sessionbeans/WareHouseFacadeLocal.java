/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.WareHouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface WareHouseFacadeLocal {

    void create(WareHouse wareHouse);

    void edit(WareHouse wareHouse);

    void remove(WareHouse wareHouse);

    WareHouse find(Object id);

    List<WareHouse> findAll();

    List<WareHouse> findRange(int[] range);

    int count();
    
    void updateWareHouse(int tourId, int quantity);
    
    List<Object[]> wareHouseData();
    
    long sumWareHouse();
    
    void deleteByTourId(int tourId);
    
    List<WareHouse> findAllAndPagination(int pageNumber, int pageSize);
    
    List<WareHouse> findQuantity(int keyword, int pageNumber, int pageSize);
    
    long countWithSearch(int keyword);
}
