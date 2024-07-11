package com.mbbeans;

import com.entities.Attraction;
import com.entities.Outstanding;
import com.entities.Tour;
import com.sessionbeans.AttractionFacadeLocal;
import com.sessionbeans.OutstandingFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ngu Công
 */
@Named(value = "locationMB")
@SessionScoped
public class LocationMB implements Serializable {

    @EJB
    private AttractionFacadeLocal attractionFacade;

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private OutstandingFacadeLocal outstandingFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Outstanding> result;
    private int totalOut;
    private int totalOutstanding;
    private Outstanding out;
    private int tourId;
    private int attId;
    private Outstanding outs;
    private int updateId;
    private int updateOutId;
    private Outstanding outb;

    public LocationMB() {
    }

    @PostConstruct
    public void init() {
        out = new Outstanding();
        outstandingPagination();
    }

    public List<Outstanding> outstandingPagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = outstandingFacade.findAll();
            totalOut = result.size();
            totalPages = (int) Math.ceil((double) totalOut / pageSize);
            result = outstandingFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalOutstanding = (int) outstandingFacade.countWithSearch(keyword);
            totalOut = result.size();
            totalPages = (int) Math.ceil((double) totalOutstanding / pageSize);
            result = outstandingFacade.findTourBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public String createnewOutstanding() {
        Tour tour = tourFacade.find(tourId);
        Attraction att = attractionFacade.find(attId);
        out.setTourId(tour);
        out.setAttractionId(att);
        outstandingFacade.create(out);
        outstandingPagination();
        return "/admin/outstanding";
    }
    
    public String deleteOutstanding(int outId){
        Outstanding out = outstandingFacade.find(outId);
        if(out != null){
            outstandingFacade.remove(out);
            outstandingPagination();
            return "/admin/outstanding";
        }else{
            return null;
        }
    }
    
    public String findById(){
        String idOut = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("outId");
        if (idOut != null) {
            updateId = Integer.parseInt(idOut);
            outs = outstandingFacade.find(updateId);
        }
        outstandingPagination();
        return "/admin/updateOutstanding?faces-redirect=true";
    }
    
    public String updatingOut(){
        String idOut = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("outId");
        if (idOut != null) {
            updateOutId = Integer.parseInt(idOut);
            outb = outstandingFacade.find(updateOutId);
            System.out.println("outb:" + outb);
            if(outb != null){
                Tour tous = tourFacade.find(outs.getTourId().getTourId());
                Attraction att = attractionFacade.find(outs.getAttractionId().getAttractionId());
                outb.setTourId(tous);
                outb.setAttractionId(att);
                outstandingFacade.edit(outb);
                outstandingPagination();
                return "/admin/outstanding";
            }else{
                System.out.println("Error Update");
            }
        }
        
        outstandingPagination();
        return "/admin/outstanding";
    }

    public void searchOutstanding() {
        currentPage = 1;
        outstandingPagination();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            outstandingPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            outstandingPagination();
        }
    }

    public List<Tour> findAllTour() {
        return tourFacade.findAll();
    }

    public List<Attraction> findAllAttraction() {
        return attractionFacade.findAll();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Outstanding> getResult() {
        return result;
    }

    public void setResult(List<Outstanding> result) {
        this.result = result;
    }

    public int getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(int totalOut) {
        this.totalOut = totalOut;
    }

    public int getTotalOutstanding() {
        return totalOutstanding;
    }

    public void setTotalOutstanding(int totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }

    public Outstanding getOut() {
        return out;
    }

    public void setOut(Outstanding out) {
        this.out = out;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Outstanding getOuts() {
        return outs;
    }

    public void setOuts(Outstanding outs) {
        this.outs = outs;
    }

    public int getUpdateOutId() {
        return updateOutId;
    }

    public void setUpdateOutId(int updateOutId) {
        this.updateOutId = updateOutId;
    }

    public Outstanding getOutb() {
        return outb;
    }

    public void setOutb(Outstanding outb) {
        this.outb = outb;
    }

}
