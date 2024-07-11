/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.UserReg;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ngu Công
 */
@Stateless
public class UserRegFacade extends AbstractFacade<UserReg> implements UserRegFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRegFacade() {
        super(UserReg.class);
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserReg checkLogin(String uname, String pass) {
        String enPass = md5(pass);
        String sql = "SELECT u FROM UserReg u WHERE u.userName = :userName and u.userPass = :userPass";
        Query query = em.createQuery(sql);
        query.setParameter("userName", uname);
        query.setParameter("userPass", enPass);
        try {
            return (UserReg) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public UserReg findById(int user) {
        try {
            Query query = em.createQuery("SELECT u FROM UserReg u WHERE u.userId =:user", UserReg.class);
            query.setParameter("user", user);
            return (UserReg) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void updateInfo(UserReg ur) {
        String sql = "UPDATE UserReg set userFName =:fname, userLName =:lname, userPhone =:phone, passPort =:pp, userCity =:city, userAddress =:add, userPhoto =:photo, userPath =:path WHERE userId =:user";
        Query query = em.createQuery(sql, UserReg.class);
        query.setParameter("fname", ur.getUserFName());
        query.setParameter("lname", ur.getUserLName());
        query.setParameter("phone", ur.getUserPhone());
        query.setParameter("pp", ur.getPassPort());
        query.setParameter("city", ur.getUserCity());
        query.setParameter("add", ur.getUserAddress());
        query.setParameter("user", ur.getUserId());
        query.setParameter("photo", ur.getUserPhoto());
        query.setParameter("path", ur.getUserPath());
        query.executeUpdate();
    }

    @Override
    public void updateEmail(UserReg ur) {
        String sql = "UPDATE UserReg set userEmail =:email WHERE userId =:user";
        Query query = em.createQuery(sql, UserReg.class);
        query.setParameter("email", ur.getUserEmail());
        query.setParameter("user", ur.getUserId());
        query.executeUpdate();
    }

    @Override
    public void updatePassword(UserReg ur) {
        String sql = "UPDATE UserReg set userPass =:pass WHERE userId =:user";
        Query query = em.createQuery(sql, UserReg.class);
        query.setParameter("pass", md5(ur.getUserPass()));
        query.setParameter("user", ur.getUserId());
        query.executeUpdate();
    }

    @Override
    public UserReg findPasswordForget(String email) {
        try {
            Query query = em.createQuery("SELECT u FROM UserReg u WHERE u.userEmail =:email", UserReg.class);
            query.setParameter("email", email);
            return (UserReg) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void updatePasswordForget(String email, String pass) {
        String sql = "UPDATE UserReg set userPass =:pass WHERE userEmail =:user";
        Query query = em.createQuery(sql, UserReg.class);
        query.setParameter("pass", md5(pass));
        query.setParameter("user", email);
        query.executeUpdate();
    }

    @Override
    public Long countUser() {
        Query query = em.createQuery("SELECT COUNT(u.userId) FROM UserReg u", UserReg.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<UserReg> showAllDashboard() {
        Query query = em.createQuery("SELECT u FROM UserReg u ORDER BY u.userDate Desc");
        query.setMaxResults(6);
        return query.getResultList();
    }

    @Override
    public List<UserReg> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT u FROM UserReg u");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<UserReg> findUserBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT u FROM UserReg u WHERE u.userName LIKE :username or u.userFName LIKE :fds or u.userLName LIKE :lds or u.userEmail =:email");
        query.setParameter("username", "%" + keyword + "%");
        query.setParameter("fds", "%" + keyword + "%");
        query.setParameter("lds", "%" + keyword + "%");
        query.setParameter("email", keyword);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long coutWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(u) FROM UserReg u WHERE u.userName LIKE :username or u.userEmail =:email");
        query.setParameter("username", "%" + keyword + "%");
        query.setParameter("email", keyword);
        return (long) query.getSingleResult();
    }

    @Override
    public void deleteUser(int userId) {
        Query query = em.createQuery("DELETE FROM UserReg u WHERE u.userId =:user");
        query.setParameter("user", userId);
        query.executeUpdate();
    }

    @Override
    public UserReg findByUsername(String key) {
        try {
            Query query = em.createQuery("SELECT u FROM UserReg u WHERE u.userName =:user");
            query.setParameter("user", key);
            return (UserReg) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public UserReg findByEmail(String key) {
        try {
            Query query = em.createQuery("SELECT u FROM UserReg u WHERE u.userEmail =:email");
            query.setParameter("email", key);
            return (UserReg) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
