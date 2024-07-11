/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Tour;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface TourFacadeLocal {

    void create(Tour tour);

    void edit(Tour tour);

    void remove(Tour tour);

    Tour find(Object id);

    List<Tour> findAll();

    List<Tour> findRange(int[] range);

    int count();

    List<Tour> findCode();

    List<Tour> findTour(String code);

    Long sumTour();

    void deleteTour(int id);

    Tour findByRegion(int tourId);

    Tour customCreate(Tour tour);
    
    Tour findByWareHouse(int wId);
    
}
