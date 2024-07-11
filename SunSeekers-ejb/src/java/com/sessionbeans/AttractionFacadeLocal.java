/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Attraction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface AttractionFacadeLocal {

    void create(Attraction attraction);

    void edit(Attraction attraction);

    void remove(Attraction attraction);

    Attraction find(Object id);

    List<Attraction> findAll();

    List<Attraction> findRange(int[] range);

    int count();
    
    List<Attraction> findByTourId(int tourId);
    
    void deleteByTourId(int tourId);
}
