/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Region;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface RegionFacadeLocal {

    void create(Region region);

    void edit(Region region);

    void remove(Region region);

    Region find(Object id);

    List<Region> findAll();

    List<Region> findRange(int[] range);

    int count();
    
    void deleteByToutId(int tourId);
    
    List<Region> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Region> findRegionBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
}
