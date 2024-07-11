/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Continent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface ContinentFacadeLocal {

    void create(Continent continent);

    void edit(Continent continent);

    void remove(Continent continent);

    Continent find(Object id);

    List<Continent> findAll();

    List<Continent> findRange(int[] range);

    int count();
    
    void deleteByTourId(int tourId);
    
    List<Continent> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Continent> findNameBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
}
