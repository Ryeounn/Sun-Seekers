/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sessionbeans;

import com.entities.Attraction;
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
public class AttractionFacade extends AbstractFacade<Attraction> implements AttractionFacadeLocal {

    @PersistenceContext(unitName = "SunSeekers-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttractionFacade() {
        super(Attraction.class);
    }
    
    @Override
    public void deleteByTourId(int tourId) {
        Query query = em.createQuery("DELETE FROM Attraction a WHERE a.attractionId =:tour", Attraction.class);
        query.setParameter("tour", tourId);
        query.executeUpdate();
    }

    @Override
    public List<Attraction> findByTourId(int tourId) {
        Query query = em.createQuery("SELECt a FROM Attraction a WHERE a.regionId.regionId =:tour",Attraction.class);
        query.setParameter("tour", tourId);
        return query.getResultList();
    }
}
