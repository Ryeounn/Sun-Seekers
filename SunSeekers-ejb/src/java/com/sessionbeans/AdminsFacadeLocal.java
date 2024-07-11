/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.Admins;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface AdminsFacadeLocal {

    void create(Admins admins);

    void edit(Admins admins);

    void remove(Admins admins);

    Admins find(Object id);

    List<Admins> findAll();

    List<Admins> findRange(int[] range);

    int count();
    
    public Admins checkLogin(String uname, String password);
    
    Admins findById(int adminId);
    
    List<Admins> findAllAndPagination(int pageNumber, int pageSize);
    
    List<Admins> findAdminsBySearch(String keyword, int pageNumber, int pageSize);
    
    long countWithSearch(String keyword);
    
    void deleteByAdminId(int adminId);
    
    Admins findAdminByUser(String user);
    
    Admins findAdminByEmail(String email);
}
