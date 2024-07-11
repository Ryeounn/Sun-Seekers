/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Response;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface ResponseFacadeLocal {

    void create(Response response);

    void edit(Response response);

    void remove(Response response);

    Response find(Object id);

    List<Response> findAll();

    List<Response> findRange(int[] range);

    int count();
    
}
