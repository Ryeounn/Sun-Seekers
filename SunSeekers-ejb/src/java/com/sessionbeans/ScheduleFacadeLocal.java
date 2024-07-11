/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Schedule;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface ScheduleFacadeLocal {

    void create(Schedule schedule);

    void edit(Schedule schedule);

    void remove(Schedule schedule);

    Schedule find(Object id);

    List<Schedule> findAll();

    List<Schedule> findRange(int[] range);

    int count();
    
    List<Schedule> findByTourId(int tourId);
    
    void deleteByTourId(int tourId);
    
    List<Schedule> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Schedule> findUserBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
}
