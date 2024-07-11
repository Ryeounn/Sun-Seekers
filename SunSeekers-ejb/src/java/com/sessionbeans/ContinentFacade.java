/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Continent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ngu Công
 */
@Stateless
public class ContinentFacade extends AbstractFacade<Continent> implements ContinentFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContinentFacade() {
        super(Continent.class);
    }
    
    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM Continent c WHERE c.continentId =:tour", Continent.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }

    @Override
    public List<Continent> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT c FROM Continent c");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Continent> findNameBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT c FROM Continent c WHERE c.continentName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(c) FROM Continent c WHERE c.continentName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }
}
