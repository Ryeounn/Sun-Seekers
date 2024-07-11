package com.mbbeans;

import com.entities.Outstanding;
import com.entities.Schedule;
import com.entities.TourDetail;
import com.sessionbeans.OutstandingFacadeLocal;
import com.sessionbeans.ScheduleFacadeLocal;
import com.sessionbeans.TourDetailFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@Named(value = "detailMB")
@SessionScoped
public class DetailMB implements Serializable{

    @EJB
    private OutstandingFacadeLocal outstandingFacade;
    
    @EJB
    private ScheduleFacadeLocal scheduleFacade;
    
    @EJB
    private TourDetailFacadeLocal tourDetailFacade;
    
    private int tourId;
    private TourDetail tourDetail;
    private List<Schedule> schedule;
    private List<Outstanding> outstanding;
    private List<TourDetail> tourDetails;
    
    public DetailMB() {
    }
    
    
    public TourDetail findTourbyId() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        if (id != null) {
            tourId = Integer.parseInt(id);
            tourDetail = tourDetailFacade.find(tourId);
        }
        return tourDetail;
    }
    
    public List<TourDetail> findRecommend(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        if(id != null){
            tourId = Integer.parseInt(id);
            tourDetails = tourDetailFacade.findRecommend(tourId);
        }
        return tourDetails;
    }
    
    public List<Schedule> findSchedulebyId(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        if (id != null) {
            tourId = Integer.parseInt(id);
            schedule = scheduleFacade.findByTourId(tourId);
        }
        System.out.println(schedule);
        System.out.println(tourId);
        return schedule;
    }
    
    public List<Outstanding> findAttractionByTourId(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        if (id != null) {
            tourId = Integer.parseInt(id);
            outstanding = outstandingFacade.findOutstandingByTour(tourId);
        }
        return outstanding;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public TourDetail getTourDetail() {
        return tourDetail;
    }

    public void setTourDetail(TourDetail tourDetail) {
        this.tourDetail = tourDetail;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public List<Outstanding> getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(List<Outstanding> outstanding) {
        this.outstanding = outstanding;
    }

    public List<TourDetail> getTourDetails() {
        return tourDetails;
    }

    public void setTourDetails(List<TourDetail> tourDetails) {
        this.tourDetails = tourDetails;
    }
    
}
