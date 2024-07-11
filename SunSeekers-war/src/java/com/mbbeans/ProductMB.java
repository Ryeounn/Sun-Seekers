package com.mbbeans;

import com.entities.Admins;
import com.entities.Attraction;
import com.entities.Continent;
import com.entities.Images;
import com.entities.Outstanding;
import com.entities.Plane;
import com.entities.Region;
import com.entities.Schedule;
import com.entities.ShoppingCart;
import com.entities.Tour;
import com.entities.TourDetail;
import com.entities.WareHouse;
import com.entities.WishList;
import com.sessionbeans.AdminsFacadeLocal;
import com.sessionbeans.AttractionFacadeLocal;
import com.sessionbeans.ContinentFacadeLocal;
import com.sessionbeans.ImagesFacadeLocal;
import com.sessionbeans.OutstandingFacadeLocal;
import com.sessionbeans.PlaneFacadeLocal;
import com.sessionbeans.RegionFacadeLocal;
import com.sessionbeans.ScheduleFacadeLocal;
import com.sessionbeans.ShoppingCartFacadeLocal;
import com.sessionbeans.TourDetailFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import com.sessionbeans.WareHouseFacadeLocal;
import com.sessionbeans.WishListFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "productMB")
@SessionScoped
public class ProductMB implements Serializable {

    @EJB
    private ImagesFacadeLocal imagesFacade;

    @EJB
    private AdminsFacadeLocal adminsFacade;

    @EJB
    private ContinentFacadeLocal continentFacade;

    @EJB
    private AttractionFacadeLocal attractionFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private OutstandingFacadeLocal outstandingFacade;

    @EJB
    private ScheduleFacadeLocal scheduleFacade;

    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;

    @EJB
    private WishListFacadeLocal wishListFacade;

    @EJB
    private WareHouseFacadeLocal wareHouseFacade;

    @EJB
    private PlaneFacadeLocal planeFacade;

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private TourDetailFacadeLocal tourDetailFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private int totalTourDetail;
    private String keyword;
    private List<TourDetail> result;
    private int totalDetail;
    private Map<Integer, Boolean> selectedTours = new HashMap<>();
    private int tourId;
    private List<TourDetail> touredList;
    private Tour toured;
    private TourDetail tourDetail;
    private int regionId;
    private int warehouseId;
    private int adminsId;
    private int imagesId;
    private int plane1;
    private int plane2;
    private Tour updateTour;
    private TourDetail updateTourDetail;
    private int tourIdUpdate;
    private int updateId;
    private Plane planeUpdate1;
    private Plane planeUpdate2;
    private int tourDetailId;

    public ProductMB() {
    }

    @PostConstruct
    public void init() {
        toured = new Tour();
        tourDetail = new TourDetail();
        updateTourDetail = new TourDetail();
        tourPagination();
    }

    public List<TourDetail> tourPagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = tourDetailFacade.findAll();
            totalTourDetail = result.size();
            totalPages = (int) Math.ceil((double) totalTourDetail / pageSize);
            result = tourDetailFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalDetail = (int) tourDetailFacade.countWithSearch(keyword);
            totalTourDetail = result.size();
            totalPages = (int) Math.ceil((double) totalDetail / pageSize);
            result = tourDetailFacade.findProductBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public List<Images> showAllImage() {
        return imagesFacade.findAll();
    }

    public List<WareHouse> showAllWareHouse() {
        return wareHouseFacade.findAll();
    }

    public void searchProducts() {
        currentPage = 1;
        tourPagination();
    }

    public boolean isAnySelected() {
        return selectedTours.values().stream().anyMatch(selected -> selected);
    }

    public String createNewTour() throws ParseException {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formater.format(date);
        Date parsedDate = formater.parse(formattedDate);
        toured.setAddDate(parsedDate);
        Region rg = regionFacade.find(regionId);
        Admins ad = adminsFacade.find(adminsId);
        WareHouse wh = wareHouseFacade.find(warehouseId);
        Images ig = imagesFacade.find(imagesId);
        if (rg != null && ad != null && wh != null && ig != null) {
            toured.setRegionId(rg);
            toured.setAdminsId(ad);
            toured.setWareHouseId(wh);
            toured.setImageId(ig);
        } else {
            System.out.println("Error FK");
        }
        Tour toures = tourFacade.customCreate(toured);
        tourDetail.setTourId(toures);
        Plane p1 = planeFacade.find(plane1);
        Plane p2 = planeFacade.find(plane2);
        if (p1 != null && p2 != null) {
            tourDetail.setPlaneId1(p1);
            tourDetail.setPlaneId2(p2);
        } else {
            System.out.println("Error PK Plane");
        }
        tourDetailFacade.create(tourDetail);
        tourPagination();
        return "/admin/product?faces-redirect=true";
    }

    public void deleteSelectedProducts(int tourId) {
        touredList = tourDetailFacade.findTourById(tourId);
        if (touredList != null) {
            WareHouse ware = wareHouseFacade.find(tourId);
            WishList wish = wishListFacade.find(tourId);
            Tour tour = tourFacade.find(tourId);
            ShoppingCart shop = shoppingCartFacade.find(tourId);
            List<Schedule> scheduler = scheduleFacade.findByTourId(tourId);
            List<Outstanding> outr = outstandingFacade.findOutstandingByTour(tourId);
            Region re = regionFacade.find(tourId);
            List<Attraction> atr = attractionFacade.findByTourId(tourId);
            Continent con = continentFacade.find(tourId);
            for (TourDetail toured : touredList) {
                Plane planeDepart = planeFacade.findDeparture(toured.getPlaneId1().getPlaneDate(), toured.getPlaneId1().getPlaneDateArrive(), toured.getPlaneId1().getAirCodeDepart(), toured.getPlaneId1().getAirCodeArrive());
                Plane planeArrive = planeFacade.findArrive(toured.getPlaneId2().getPlaneDate(), toured.getPlaneId2().getPlaneDateArrive(), toured.getPlaneId2().getAirCodeDepart(), toured.getPlaneId2().getAirCodeArrive());
                if (planeArrive != null && planeDepart != null) {
                    tourDetailFacade.deleteTourById(tourId);
                    planeFacade.deletePlae(planeDepart.getPlaneId());
                    planeFacade.deletePlae(planeArrive.getPlaneId());
                    for (Outstanding out : outr) {
                        outstandingFacade.deleteByTourId(out.getTourId().getTourId());
                        System.out.println("Out:" + out.getTourId().getTourId());
                        System.out.println("OutRegion:" + out.getTourId().getRegionId().getRegionId());
                        System.out.println("Attraction:" + out.getAttractionId().getAttractionId());
//                                    attractionFacade.deleteByTourId(out.getAttractionId().getAttractionId());
                    }
                    for (Schedule schedule : scheduler) {
                        scheduleFacade.deleteByTourId(schedule.getTourId().getTourId());
                    }

                    if (wish != null) {
                        wishListFacade.deleteWishById(wish.getTourId().getTourId());
                    } else {
                        System.out.println("Error Wish");
                    }
                    if (shop != null) {
                        shoppingCartFacade.deleteByTourId(shop.getTourId().getTourId());
                    } else {
                        System.out.println("Erro shop");
                    }

                    tourFacade.deleteTour(tourId);
                    imagesFacade.deleteById(tour.getImageId().getImageId());
                    wareHouseFacade.deleteByTourId(tour.getWareHouseId().getWareHouseId());
                } else {
                    System.out.println("Error Find Plane");
                }
            }
        } else {
            System.out.println("Error Find Tour");
        }
        selectedTours.clear();
        tourPagination();
    }

    public String findProductById() {
        String idTour = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idTourDetail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourDetailId");
        if (idTour != null && idTourDetail != null) {
            tourIdUpdate = Integer.parseInt(idTour);
            tourDetailId = Integer.parseInt(idTourDetail);
            updateTour = tourFacade.find(tourIdUpdate);
            updateTourDetail = tourDetailFacade.find(tourDetailId);
        }
        return "/admin/updateProduct?faces-redirect=true";
    }

    public String updatingProduct() {
        String idTour = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        String idTourDetail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourDetailId");
        if (idTour != null && idTourDetail != null) {
            updateId = Integer.parseInt(idTour);
            tourDetailId = Integer.parseInt(idTour);
            Tour tours = tourFacade.find(updateId);
            tourDetail = tourDetailFacade.findByTourId(updateId);
            tours.setTourCode(updateTour.getTourCode());
            tours.setTourName(updateTour.getTourName());
            tours.setTotalDate(updateTour.getTotalDate());
            tours.setTourDescription(updateTour.getTourDescription());
            WareHouse wh = wareHouseFacade.find(updateTour.getWareHouseId().getWareHouseId());
            Region rg = regionFacade.find(updateTour.getRegionId().getRegionId());
            Admins ad = adminsFacade.find(updateTour.getAdminsId().getAdminsId());
            Images ig = imagesFacade.find(updateTour.getImageId().getImageId());
            Plane tf = planeFacade.find(updateTourDetail.getPlaneId1().getPlaneId());
            Plane td = planeFacade.find(updateTourDetail.getPlaneId2().getPlaneId());
            Tour tr = tourFacade.find(updateTourDetail.getTourId().getTourId());
            tours.setAddDate(updateTour.getAddDate());
            tours.setImageId(ig);
            tours.setWareHouseId(wh);
            tours.setRegionId(rg);
            tours.setAdminsId(ad);
            tours.setTourPrice(updateTour.getTourPrice());
            tourFacade.edit(tours);         
            tourDetail.setTourId(tr);
            tourDetail.setPlaneId1(tf);
            tourDetail.setPlaneId2(td);
            tourDetailFacade.edit(tourDetail);
        }
        tourPagination();
        return "/admin/product";
    }

    public List<Region> findAllRegion() {
        return regionFacade.findAll();
    }

    public List<Admins> findAllAdmins() {
        return adminsFacade.findAll();
    }

    public List<Plane> showAllPlaneDepart() {
        return planeFacade.showAllPlaneDeparture();
    }

    public List<Plane> showAllPlaneArrive() {
        return planeFacade.showAllPlaneArrive();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            tourPagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            tourPagination();
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

    public int getTotalTourDetail() {
        return totalTourDetail;
    }

    public void setTotalTourDetail(int totalTourDetail) {
        this.totalTourDetail = totalTourDetail;
    }

    public List<TourDetail> getResult() {
        return result;
    }

    public void setResult(List<TourDetail> result) {
        this.result = result;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getTotalDetail() {
        return totalDetail;
    }

    public void setTotalDetail(int totalDetail) {
        this.totalDetail = totalDetail;
    }

    public Map<Integer, Boolean> getSelectedTours() {
        return selectedTours;
    }

    public void setSelectedTours(Map<Integer, Boolean> selectedTours) {
        this.selectedTours = selectedTours;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public List<TourDetail> getTouredList() {
        return touredList;
    }

    public void setTouredList(List<TourDetail> touredList) {
        this.touredList = touredList;
    }

    public Tour getToured() {
        return toured;
    }

    public void setToured(Tour toured) {
        this.toured = toured;
    }

    public TourDetail getTourDetail() {
        return tourDetail;
    }

    public void setTourDetail(TourDetail tourDetail) {
        this.tourDetail = tourDetail;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(int adminsId) {
        this.adminsId = adminsId;
    }

    public int getImagesId() {
        return imagesId;
    }

    public void setImagesId(int imagesId) {
        this.imagesId = imagesId;
    }

    public int getPlane1() {
        return plane1;
    }

    public void setPlane1(int plane1) {
        this.plane1 = plane1;
    }

    public int getPlane2() {
        return plane2;
    }

    public void setPlane2(int plane2) {
        this.plane2 = plane2;
    }

    public Tour getUpdateTour() {
        return updateTour;
    }

    public void setUpdateTour(Tour updateTour) {
        this.updateTour = updateTour;
    }

    public TourDetail getUpdateTourDetail() {
        return updateTourDetail;
    }

    public void setUpdateTourDetail(TourDetail updateTourDetail) {
        this.updateTourDetail = updateTourDetail;
    }

    public int getTourIdUpdate() {
        return tourIdUpdate;
    }

    public void setTourIdUpdate(int tourIdUpdate) {
        this.tourIdUpdate = tourIdUpdate;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Plane getPlaneUpdate1() {
        return planeUpdate1;
    }

    public void setPlaneUpdate1(Plane planeUpdate1) {
        this.planeUpdate1 = planeUpdate1;
    }

    public Plane getPlaneUpdate2() {
        return planeUpdate2;
    }

    public void setPlaneUpdate2(Plane planeUpdate2) {
        this.planeUpdate2 = planeUpdate2;
    }

    public int getTourDetailId() {
        return tourDetailId;
    }

    public void setTourDetailId(int tourDetailId) {
        this.tourDetailId = tourDetailId;
    }

}
