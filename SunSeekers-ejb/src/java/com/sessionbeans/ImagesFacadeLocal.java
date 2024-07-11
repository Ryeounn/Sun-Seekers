/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Images;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface ImagesFacadeLocal {

    void create(Images images);

    void edit(Images images);

    void remove(Images images);

    Images find(Object id);

    List<Images> findAll();

    List<Images> findRange(int[] range);

    int count();
    
    void deleteById(int imageId);
    
    List<Images> findAllAndPagination(int pageNumber, int pageSize);
}
