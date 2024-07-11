package com.mbbeans;

import com.entities.Tour;
import com.entities.WareHouse;
import com.sessionbeans.TourFacadeLocal;
import com.sessionbeans.WareHouseFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "warehouseMB")
@SessionScoped
public class WarehouseMB implements Serializable {

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private WareHouseFacadeLocal wareHouseFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private int keyword;
    private List<WareHouse> result;
    private int totalWare;
    private int totalWarehouse;
    private WareHouse ware;
    private int wId;
    private WareHouse updating;
    private WareHouse updated;
    
    public WarehouseMB() {
    }
    
    @PostConstruct
    public void init(){
        ware = new WareHouse();
        warehousePagination();
    }
    
    public List<WareHouse> warehousePagination(){
       if(keyword == 0){
          result = wareHouseFacade.findAll();
          totalWare = result.size();
          totalPages = (int) Math.ceil((double) totalWare / pageSize);
          result = wareHouseFacade.findAllAndPagination(currentPage, pageSize);
          return result;
       }else{
           totalWarehouse = (int) wareHouseFacade.countWithSearch(keyword);
           totalWare = result.size();
           totalPages = (int) Math.ceil((double) totalWarehouse / pageSize);
           result = wareHouseFacade.findQuantity(keyword, currentPage, pageSize);
           return result;
       }
    }
    
    public String createWarehouse(){
        wareHouseFacade.create(ware);
        warehousePagination();
        return "/admin/warehouse";
    }
    
    public String deleteById(int wareId){
        WareHouse w = wareHouseFacade.find(wareId);
        Tour t = tourFacade.findByWareHouse(wareId);
        if(t != null){
            t.setWareHouseId(null);
            tourFacade.edit(t);
            wareHouseFacade.remove(w);
            System.out.println("A");
        }else{
            wareHouseFacade.remove(w);
            System.out.println("B");
        }
        warehousePagination();
        return "/admin/warehouse";
    }
    
    public String findById(){
        String idWare = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wId");
        if(idWare != null){
            wId = Integer.parseInt(idWare);
            updating = wareHouseFacade.find(wId);
        }
        return "/admin/updateWarehouse?faces-redirect=true";
    }
    
    public String updatingWarehouse(){
        String idWare = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wId");
        if(idWare != null){
            updated = wareHouseFacade.find(wId);
            if(updated != null){
                updated.setTotal(updating.getTotal());
                updated.setInventory(updating.getInventory());
                wareHouseFacade.edit(updated);
            }
        }
        warehousePagination();
        return "/admin/warehouse";
    }
    
    public void searchWarehouse() {
        currentPage = 1;
        warehousePagination();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            warehousePagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            warehousePagination();
        }
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

    public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    public List<WareHouse> getResult() {
        return result;
    }

    public void setResult(List<WareHouse> result) {
        this.result = result;
    }

    public int getTotalWare() {
        return totalWare;
    }

    public void setTotalWare(int totalWare) {
        this.totalWare = totalWare;
    }

    public int getTotalWarehouse() {
        return totalWarehouse;
    }

    public void setTotalWarehouse(int totalWarehouse) {
        this.totalWarehouse = totalWarehouse;
    }

    public WareHouse getWare() {
        return ware;
    }

    public void setWare(WareHouse ware) {
        this.ware = ware;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public WareHouse getUpdating() {
        return updating;
    }

    public void setUpdating(WareHouse updating) {
        this.updating = updating;
    }

    public WareHouse getUpdated() {
        return updated;
    }

    public void setUpdated(WareHouse updated) {
        this.updated = updated;
    }
    
}
