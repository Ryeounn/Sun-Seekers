package com.mbbeans;

import com.entities.Continent;
import com.sessionbeans.ContinentFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "continentMB")
@SessionScoped
public class ContinentMB implements Serializable {

    @EJB
    private ContinentFacadeLocal continentFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Continent> result;
    private int totalCon;
    private int totalContinent;
    private Continent cons;
    private Continent delCon;
    private Continent updateCon;
    private int updateId;
    private Continent upb;
    
    public ContinentMB() {
    }
    
    @PostConstruct
    public void init(){
        cons = new Continent();
        continentPagination();
    }
    
    public List<Continent> continentPagination(){
        if(keyword == null || keyword.isEmpty()){
            result = continentFacade.findAll();
            totalCon = result.size();
            totalPages = (int) Math.ceil((double) totalCon / pageSize);
            result = continentFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        }else{
            totalContinent = (int) continentFacade.countWithSearch(keyword);
            totalCon = result.size();
            totalPages = (int) Math.ceil((double) totalContinent / pageSize);
            result = continentFacade.findNameBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }
    
    public String createContinent(){
        cons.setContinentName(cons.getContinentName());
        continentFacade.create(cons);
        continentPagination();
        return "/admin/continent";
    }
    
    public String findById(){
        String idCon = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("conId");
        if (idCon != null) {
            updateId = Integer.parseInt(idCon);
            updateCon = continentFacade.find(updateId);
        }
        continentPagination();
        return "/admin/updateContinent?faces-redirect=true";
    }
    
    public String updatingContinent(){
        String idCon = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("conId");
        if (idCon != null) {
            updateId = Integer.parseInt(idCon);
            upb = continentFacade.find(updateId);
            if(upb != null){
                upb.setContinentName(updateCon.getContinentName());
                continentFacade.edit(upb);
            }
        }
        continentPagination();
        return "/admin/continent";
    }
    
    public String deleteContinent(int conId){
        delCon = continentFacade.find(conId);
        if(delCon != null){
            continentFacade.remove(delCon);
        }
        continentPagination();
        return "/admin/continent";
    }
    
    public void searchContinent() {
        currentPage = 1;
        continentPagination();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            continentPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            continentPagination();
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Continent> getResult() {
        return result;
    }

    public void setResult(List<Continent> result) {
        this.result = result;
    }

    public int getTotalCon() {
        return totalCon;
    }

    public void setTotalCon(int totalCon) {
        this.totalCon = totalCon;
    }

    public int getTotalContinent() {
        return totalContinent;
    }

    public void setTotalContinent(int totalContinent) {
        this.totalContinent = totalContinent;
    }

    public Continent getCons() {
        return cons;
    }

    public void setCons(Continent cons) {
        this.cons = cons;
    }

    public Continent getDelCon() {
        return delCon;
    }

    public void setDelCon(Continent delCon) {
        this.delCon = delCon;
    }

    public Continent getUpdateCon() {
        return updateCon;
    }

    public void setUpdateCon(Continent updateCon) {
        this.updateCon = updateCon;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Continent getUpb() {
        return upb;
    }

    public void setUpb(Continent upb) {
        this.upb = upb;
    }
    
}
