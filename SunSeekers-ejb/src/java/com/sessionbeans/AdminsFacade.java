/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Admins;
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
public class AdminsFacade extends AbstractFacade<Admins> implements AdminsFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminsFacade() {
        super(Admins.class);
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
    public Admins checkLogin(String uname, String pass) {
        String enPass = md5(pass);
        String sql = "SELECT a FROM Admins a where a.adminUser = :adminUser and a.adminPass = :adminPass";
        Query query = em.createQuery(sql);
        query.setParameter("adminUser", uname);
        query.setParameter("adminPass", enPass);
        try {
            return (Admins) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Admins findById(int adminId) {
        try {
            Query query = em.createQuery("SELECT a FROM Admins a WHERE a.adminsId =:admin", Admins.class);
            query.setParameter("admin", adminId);
            return (Admins) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Admins> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT a FROM Admins a");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Admins> findAdminsBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT a FROM Admins a WHERE a.adminName LIKE :name or a.adminEmail =:email or a.adminUser LIKE :user");
        query.setParameter("name", "%" + keyword + "%");
        query.setParameter("email", keyword);
        query.setParameter("user", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(a) FROM Admins a WHERE a.adminName LIKE :name or a.adminEmail =:email or a.adminUser LIKE :user");
        query.setParameter("name", "%" + keyword + "%");
        query.setParameter("email", keyword);
        query.setParameter("user", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }

    @Override
    public void deleteByAdminId(int adminId) {
        Query query = em.createQuery("DELETE FROM Admins a WHERE a.adminsId =:admin");
        query.setParameter("admin", adminId);
        query.executeUpdate();
    }

    @Override
    public Admins findAdminByUser(String username) {
        try {
            Query query = em.createQuery("SELECT a FROM Admins a WHERE a.adminUser =:user");
            query.setParameter("user", username);
            return (Admins) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Admins findAdminByEmail(String email) {
        try {
            Query query = em.createQuery("SELECT a FROM Admins a WHERE a.adminEmail =:email");
            query.setParameter("email", email);
            return (Admins) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
