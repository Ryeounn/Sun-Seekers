/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Outstanding;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface OutstandingFacadeLocal {

    void create(Outstanding outstanding);

    void edit(Outstanding outstanding);

    void remove(Outstanding outstanding);

    Outstanding find(Object id);

    List<Outstanding> findAll();

    List<Outstanding> findRange(int[] range);

    int count();
    
    List<Outstanding> findOutstandingByTour(int tourId);
    
    void deleteByTourId(int tourId);
    
    void deleteByAttractionId(int id);
    
    List<Outstanding> findByAttraction(int id);
    
    List<Outstanding> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Outstanding> findTourBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
}
