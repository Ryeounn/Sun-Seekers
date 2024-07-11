/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.WareHouse;
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
public class WareHouseFacade extends AbstractFacade<WareHouse> implements WareHouseFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WareHouseFacade() {
        super(WareHouse.class);
    }
    
    @Override
    public void updateWareHouse(int tourId, int quantity) {
        WareHouse ware = em.createQuery("SELECT w FROM WareHouse w JOIN w.tourCollection wt WHERE wt.tourId =:tour", WareHouse.class)
                .setParameter("tour", tourId).getSingleResult();
        if(ware != null){
            int oldquantity = ware.getInventory();
            ware.setInventory(oldquantity - quantity);
            em.merge(ware);
        }
        
    }

    @Override
    public List<Object[]> wareHouseData() {
        Query query = em.createQuery("SELECT w.total,w.inventory FROM WareHouse w",WareHouse.class);
        return query.getResultList();
    }

    @Override
    public long sumWareHouse() {
        Query query = em.createQuery("SELECT SUM(w.total) FROM WareHouse w",WareHouse.class);
        return (long) query.getSingleResult();
    }

    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM WareHouse w WHERE w.wareHouseId =:ware", WareHouse.class);
        query.setParameter("ware", tourId);
        query.executeUpdate();
    }

    @Override
    public List<WareHouse> findAllAndPagination(int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT w FROM WareHouse w");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<WareHouse> findQuantity(int keyword, int pageNumber, int pageSize) {
        Query query = em.createQuery("SELECT w FROM WareHouse w WHERE w.inventory =:ware");
        query.setParameter("ware", keyword);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countWithSearch(int keyword) {
        Query query = em.createQuery("SELECT COUNT(w) FROM WareHouse w WHERE w.inventory =:ware");
        query.setParameter("ware", keyword);
        return (long) query.getSingleResult();
    }
}
