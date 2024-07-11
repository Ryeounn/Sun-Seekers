/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Tour;
import com.entities.TourDetail;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface TourDetailFacadeLocal {

    void create(TourDetail tourDetail);

    void edit(TourDetail tourDetail);

    void remove(TourDetail tourDetail);

    TourDetail find(Object id);

    List<TourDetail> findAll();

    List<TourDetail> findRange(int[] range);

    int count();
    
    List<TourDetail> findSearch(String depart, String arrived, Date date);
    
    List<TourDetail> findRecommend(int id);
    
    List<TourDetail> findExplore();
    
    List<TourDetail> theTour();
    
    List<TourDetail> findPopular(String code);
    
    List<TourDetail> findBestSeller();
    
    List<TourDetail> findAllAndPagination(int pageNumber, int pageSize);
    
    List<TourDetail> findProductBySearch(String keyword, int pageNumber, int pageSize);

    long countWithSearch(String keyword);
    
    void deleteTourById(int id);
    
    List<TourDetail> findTourById(int tourId);
    
    TourDetail findByTourId(int tour);
}
