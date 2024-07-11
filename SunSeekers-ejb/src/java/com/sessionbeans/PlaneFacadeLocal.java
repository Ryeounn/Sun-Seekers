/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Plane;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface PlaneFacadeLocal {

    void create(Plane plane);

    void edit(Plane plane);

    void remove(Plane plane);

    Plane find(Object id);

    List<Plane> findAll();

    List<Plane> findRange(int[] range);

    int count();
    
    List<Plane> findPlaneDepart();
    
    List<Plane> findPlaneArrive(String code);
    
    Plane findDeparture(Date planeDate, Date planeArrive, String codeDepart, String codeArrive);
    
    Plane findArrive(Date planeDate, Date planeArrive, String codeDepart, String codeArrive);
    
    void deletePlae(int planeId);
    
    List<Plane> findAllPagination(int pageNumber, int pageSize);
    
    List<Plane> findPlaneBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
    
    void deleteByPlaneId(int planeId);
    
    List<Plane> showAllPlaneDeparture();
    
    List<Plane> showAllPlaneArrive();
}
