package com.mbbeans;

import com.entities.Tour;
import com.entities.TourDetail;
import com.sessionbeans.TourDetailFacadeLocal;
import com.sessionbeans.TourFacade;
import com.sessionbeans.TourFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "homeMB")
@SessionScoped
public class HomeMB implements Serializable{

    @EJB
    private TourDetailFacadeLocal tourDetailFacade;
    
    List<Tour> listTour = new ArrayList<>();

    @EJB
    private TourFacadeLocal tourFacade;
    
    public HomeMB() {
        tourFacade = new TourFacade();
        
    }
    
    public List<TourDetail> theTour(){
        return tourDetailFacade.theTour();
    }
    
    public List<Tour> tourbyRegion(){
        return listTour;
    }
    
    public List<Tour> showAllExplore(){
        return tourFacade.findCode();
    }
    
    public void showRegionTour(String tourCode){
        listTour = tourFacade.findTour(tourCode);
    }

    public TourFacadeLocal getTourFacade() {
        return tourFacade;
    }

    public void setTourFacade(TourFacadeLocal tourFacade) {
        this.tourFacade = tourFacade;
    }
    
}
