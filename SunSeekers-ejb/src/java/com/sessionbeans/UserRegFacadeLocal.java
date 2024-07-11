/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sessionbeans;

import com.entities.UserReg;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ngu Công
 */
@Local
public interface UserRegFacadeLocal {

    void create(UserReg userReg);

    void edit(UserReg userReg);

    void remove(UserReg userReg);

    UserReg find(Object id);

    List<UserReg> findAll();

    List<UserReg> findRange(int[] range);

    int count();
    
    UserReg checkLogin(String uname, String passowrd);
    
    UserReg findById(int userId);
    
    void updateInfo(UserReg ur);
    
    void updateEmail(UserReg ur);
    
    void updatePassword(UserReg ur);
    
    UserReg findPasswordForget(String pass);
    
    void updatePasswordForget(String email, String pass);
    
    Long countUser();
    
    List<UserReg> showAllDashboard();
    
    List<UserReg> findAllAndPagination(int pageNumber, int pageSize);

    List<UserReg> findUserBySearch(String keyword, int pageNumber, int pageSize);

    long coutWithSearch(String keyword);
    
    void deleteUser(int userId);
    
    UserReg findByUsername(String key);
    
    UserReg findByEmail(String key);
}
