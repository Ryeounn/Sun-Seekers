/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Region;
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
public class RegionFacade extends AbstractFacade<Region> implements RegionFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }
    
    @Override
    public void deleteByToutId(int tourId) {
        Query query = em.createQuery("DELETE FROM Region r WHERE r.regionId=:tour",Region.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }

    @Override
    public List<Region> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT r FROM Region r");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Region> findRegionBySearch(String keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT r FROM Region r WHERE r.regionName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(String keyword) {
        Query query = em.createQuery("SELECT COUNT(r) FROM Region r WHERE r.regionName LIKE :name");
        query.setParameter("name", "%" + keyword + "%");
        return (long) query.getSingleResult();
    }
}
