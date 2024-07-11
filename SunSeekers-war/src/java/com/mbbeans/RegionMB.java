package com.mbbeans;

import com.entities.Continent;
import com.entities.Region;
import com.sessionbeans.ContinentFacadeLocal;
import com.sessionbeans.RegionFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "regionMB")
@SessionScoped
public class RegionMB implements Serializable {

    @EJB
    private ContinentFacadeLocal continentFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Region> result;
    private int totalRg;
    private int totalRegion;
    private int conId;
    private Region rg;
    private int regionId;
    private Region rga;
    private Region rgb;
    private Region delRg;

    public RegionMB() {
    }

    @PostConstruct
    public void init() {
        rg = new Region();
        regionPagination();
    }

    public List<Region> regionPagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = regionFacade.findAll();
            totalRg = result.size();
            totalPages = (int) Math.ceil((double) totalRg / pageSize);
            result = regionFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalRegion = (int) regionFacade.countWithSearch(keyword);
            totalRg = result.size();
            totalPages = (int) Math.ceil((double) totalRegion / pageSize);
            result = regionFacade.findRegionBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public String createRegion() {
        Continent con = continentFacade.find(conId);
        if (con != null) {
            rg.setRegionName(rg.getRegionName());
            rg.setContinentId(con);
            regionFacade.create(rg);
        }
        regionPagination();
        return "/admin/region";
    }

    public String findById() {
        String idRegion = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("regionId");
        if (idRegion != null) {
            regionId = Integer.parseInt(idRegion);
            rga = regionFacade.find(regionId);
        }
        return "/admin/updateRegion?faces-redirect=true";
    }

    public String updatingRegion() {
        String idRegion = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("regionId");
        if (idRegion != null) {
            regionId = Integer.parseInt(idRegion);
            rgb = regionFacade.find(regionId);
            Continent con = continentFacade.find(rga.getContinentId().getContinentId());
            if (rgb != null) {
                rgb.setRegionName(rga.getRegionName());
                rgb.setContinentId(con);
                regionFacade.edit(rgb);
            }
        }
        regionPagination();
        return "/admin/region";
    }

    public String deleteRegion(int regionId) {
        delRg = regionFacade.find(regionId);
        if (delRg != null) {
            regionFacade.remove(delRg);
        }
        regionPagination();
        return "/admin/region";
    }

    public void searchRegion() {
        currentPage = 1;
        regionPagination();
    }

    public List<Continent> findAllContinent() {
        return continentFacade.findAll();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            regionPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            regionPagination();
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

    public List<Region> getResult() {
        return result;
    }

    public void setResult(List<Region> result) {
        this.result = result;
    }

    public int getTotalRg() {
        return totalRg;
    }

    public void setTotalRg(int totalRg) {
        this.totalRg = totalRg;
    }

    public int getTotalRegion() {
        return totalRegion;
    }

    public void setTotalRegion(int totalRegion) {
        this.totalRegion = totalRegion;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public Region getRg() {
        return rg;
    }

    public void setRg(Region rg) {
        this.rg = rg;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public Region getRga() {
        return rga;
    }

    public void setRga(Region rga) {
        this.rga = rga;
    }

    public Region getRgb() {
        return rgb;
    }

    public void setRgb(Region rgb) {
        this.rgb = rgb;
    }

    public Region getDelRg() {
        return delRg;
    }

    public void setDelRg(Region delRg) {
        this.delRg = delRg;
    }

}
