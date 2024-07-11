package com.mbbeans;

import com.entities.Plane;
import com.entities.TourDetail;
import com.sessionbeans.PlaneFacadeLocal;
import com.sessionbeans.TourDetailFacadeLocal;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "tourMB")
@SessionScoped
public class TourMB implements Serializable{

    @EJB
    private TourDetailFacadeLocal tourDetailFacade;

    @EJB
    private PlaneFacadeLocal planeFacade;
    private String selectedPlaneArrive;
    private String selectedPlaneDepart;
    private Date planeDate;
    private Plane plane;
    private List<TourDetail> searchTour;
    private String planeDepart;
    private String planeArrive;
    private String formatDate;
    private List<Plane> arrivalList;
    private List<TourDetail> popularTour;

    public TourMB() {
    }
    
    @PostConstruct
    public void init(){
        popularTour = tourDetailFacade.findPopular("BK000");
    }
    
    public List<Plane> findPlane() {
        return planeFacade.findPlaneDepart();
    }

    public List<Plane> arriveTour() {
        return arrivalList;
    }

    public List<Plane> findAll() {
        return planeFacade.findAll();
    }

    public List<Plane> findArrive() {
        return arrivalList;
    }

    public List<TourDetail> searchTourDetail() {
        return searchTour;
    }
    
    public void updateArrivalList() {
        if (planeDepart != null && !planeDepart.isEmpty()) {
            arrivalList = planeFacade.findPlaneArrive(planeDepart);
        } else {
            arrivalList = null;
        }
    }
    
    public static Date formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(date);

        return formatter.parse(dateString);
    }
    
    public String formated(Date date){
        SimpleDateFormat formated = new SimpleDateFormat("MMM dd");
        String dataString = formated.format(date);
        return dataString;
    }
    
    public String getFormattedPlaneDate() {
        if (planeDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
            return sdf.format(planeDate);
        } else {
            return "";
        }
    }

    public void searchTours() throws ParseException {
        searchTour = tourDetailFacade.findAll();
        Date formatsDate = formatDate(planeDate);
        if (planeDepart != null && planeArrive != null && planeDate != null) {
            searchTour = tourDetailFacade.findSearch(planeDepart, planeArrive, formatsDate);
            System.out.println(searchTour);
        } else {
            searchTour = tourDetailFacade.findAll();
            System.out.println(searchTour);
        }
    }
    
    public List<TourDetail> selectPopular(String code){
        String codeDefault = "BK000";
        if(code.equals("BK000")){
            popularTour = tourDetailFacade.findPopular("BK000");
        }else if(code.equals("SI000")){
            popularTour = tourDetailFacade.findPopular("SI000");
        }else if(code.equals("SE000")){
            popularTour = tourDetailFacade.findPopular("SE000");
        }else if(code.equals("JP000")){
            popularTour = tourDetailFacade.findPopular("JP000");
        }else if(code.equals("CN000")){
            popularTour = tourDetailFacade.findPopular("CN000");
        }else{
            popularTour = tourDetailFacade.findPopular("BK000");
        }
        return popularTour;
    }

    public String getSelectedPlaneDepart() {
        return selectedPlaneDepart;
    }

    public void setSelectedPlaneDepart(String selectedPlaneDepart) {
        this.selectedPlaneDepart = selectedPlaneDepart;
    }

    public String getSelectedPlaneArrive() {
        return selectedPlaneArrive;
    }

    public void setSelectedPlaneArrive(String selectedPlaneArrive) {
        this.selectedPlaneArrive = selectedPlaneArrive;
    }

    public List<TourDetail> getSearchTour() {
        return searchTour;
    }

    public void setSearchTour(List<TourDetail> searchTour) {
        this.searchTour = searchTour;
    }

    public Date getPlaneDate() {
        return planeDate;
    }

    public void setPlaneDate(Date planeDate) {
        this.planeDate = planeDate;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getPlaneDepart() {
        return planeDepart;
    }

    public void setPlaneDepart(String planeDepart) {
        this.planeDepart = planeDepart;
    }

    public String getPlaneArrive() {
        return planeArrive;
    }

    public void setPlaneArrive(String planeArrive) {
        this.planeArrive = planeArrive;
    }

    public List<Plane> getArrivalList() {
        return arrivalList;
    }

    public void setArrivalList(List<Plane> arrivalList) {
        this.arrivalList = arrivalList;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public List<TourDetail> getPopularTour() {
        return popularTour;
    }

    public void setPopularTour(List<TourDetail> popularTour) {
        this.popularTour = popularTour;
    }

}
