package com.mbbeans;

import com.entities.Plane;
import com.sessionbeans.PlaneFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

@Named(value = "planeMB")
@SessionScoped
public class PlaneMB implements Serializable {

    @EJB
    private PlaneFacadeLocal planeFacade;
    
    private int planeId;
    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Plane> result;
    private int totalPlane;
    private int totalPlaners;
    private Plane planner;
    private Date planeDate;
    private Date planeDateArrive;
    private Plane planing;
    
    public PlaneMB() {
    }
    
    @PostConstruct
    public void init(){
        planner = new Plane();
        planePagination();
    }
    
    public List<Plane> planePagination(){
        if(keyword == null || keyword.isEmpty()){
            result = planeFacade.findAll();
            totalPlane = result.size();
            totalPages = (int) Math.ceil((double) totalPlane / pageSize);
            result = planeFacade.findAllPagination(currentPage, pageSize);
            return result;
        }else{
            totalPlaners = (int) planeFacade.countWithSearch(keyword);
            totalPlane = result.size();
            totalPages = (int) Math.ceil((double) totalPlaners / pageSize);
            result = planeFacade.findPlaneBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }
    
    public static Date formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return formatter.parse(dateString);
    }
    
    public String findPlaneById(){
        String idPlane = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("planeId");
        if (idPlane != null) {
            planeId = Integer.parseInt(idPlane);
            planner = planeFacade.find(planeId);
            return "/admin/updatePlane?faces-redirect=true";
        }else{
            return null;
        }
    }
    
    public String createNewPlane() throws ParseException{
        System.out.println("Planner: " + planner);
//        Date formated = formatDate(planner.getPlaneDate());
//        Date formated2 = formatDate(planner.getPlaneDateArrive());
        planner.setPlaneDate(planner.getPlaneDate());
        planner.setPlaneDateArrive(planner.getPlaneDateArrive());
        if(planner.getAirlineCompany().equals("Vietnam Airlines")){
            planner.setCompanyPath("/resources/images/tour/air/vietnamairline.jpg");
        }else if(planner.getAirlineCompany().equals("Vietjet Air")){
            planner.setCompanyPath("/resources/images/tour/air/vietjetairline.jpg");
        }else if(planner.getAirlineCompany().equals("Japan Airlines")){
            planner.setCompanyPath("/resources/images/tour/air/japanairline.jpg");
        }else if(planner.getAirlineCompany().equals("Emirates")){
            planner.setCompanyPath("/resources/images/tour/air/emirate.png");
        }else if(planner.getAirlineCompany().equals("China Airlines")){
            planner.setCompanyPath("/resources/images/tour/air/chinaairline.jpg");
        }else if(planner.getAirlineCompany().equals("Air Premia")){
            planner.setCompanyPath("/resources/images/tour/air/airpre.jpg");
        }else if(planner.getAirlineCompany().equals("Eva Air")){
            planner.setCompanyPath("/resources/images/tour/air/evaiar.jpg");
        }else if(planner.getAirlineCompany().equals("Singapore Airlines")){
            planner.setCompanyPath("/resources/images/tour/air/singaporeairline.jpg");
        }else if(planner.getAirlineCompany().equals("Bambo Airways")){
            planner.setCompanyPath("/resources/images/tour/air/bamboairway.jpg");
        }else if(planner.getAirlineCompany().equals("Jetstar Airways")){
            planner.setCompanyPath("/resources/images/tour/air/jetstar.jpg");
        }else{
            planner.setCompanyPath(null);
        }
        planeFacade.create(planner);
        planePagination();
        return "/admin/plane";
    }
    
    public String updatingPlane(){
        planing = planeFacade.find(planner.getPlaneId());
        planing.setPlaneDate(planner.getPlaneDate());
        planing.setPlaneTimeDepart(planner.getPlaneTimeDepart());
        planing.setPlaneDepart(planner.getPlaneDepart());
        planing.setPlaneArrive(planner.getPlaneArrive());
        planing.setPlaneTimeArrive(planner.getPlaneTimeArrive());
        planing.setPlaneDateArrive(planner.getPlaneDateArrive());
        if(planner.getAirlineCompany().equals("Vietnam Airlines")){
            planing.setCompanyPath("/resources/images/tour/air/vietnamairline.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Vietjet Air")){
            planing.setCompanyPath("/resources/images/tour/air/vietjetairline.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Japan Airlines")){
            planing.setCompanyPath("/resources/images/tour/air/japanairline.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Emirates")){
            planing.setCompanyPath("/resources/images/tour/air/emirate.png");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("China Airlines")){
            planing.setCompanyPath("/resources/images/tour/air/chinaairline.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Air Premia")){
            planing.setCompanyPath("/resources/images/tour/air/airpre.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Eva Air")){
            planing.setCompanyPath("/resources/images/tour/air/evaiar.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Singapore Airlines")){
            planing.setCompanyPath("/resources/images/tour/air/singaporeairline.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Bambo Airways")){
            planing.setCompanyPath("/resources/images/tour/air/bamboairway.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else if(planner.getAirlineCompany().equals("Jetstar Airways")){
            planing.setCompanyPath("/resources/images/tour/air/jetstar.jpg");
            planing.setAirlineCompany(planner.getAirlineCompany());
        }else{
            planing.setCompanyPath(null);
            planing.setAirlineCompany(null);
        }
        planing.setAirNumber(planner.getAirNumber());
        planing.setPlaning(planner.getPlaning());
        planing.setAirCodeDepart(planner.getAirCodeDepart());
        planing.setAirNameDepart(planner.getAirNameDepart());
        planing.setAirCodeArrive(planner.getAirCodeArrive());
        planing.setAirNameArrive(planner.getAirNameArrive());
        planeFacade.edit(planing);
        planePagination();
        return "/admin/plane?faces-redirect=true";
    }
    
    public void deletePlaneById(int planeId){
        planeFacade.deletePlae(planeId);
        planePagination();
    }
    
    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            planePagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            planePagination();
        }
    }
    
    public void searchUsers() {
        currentPage = 1;
        planePagination();
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
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

    public List<Plane> getResult() {
        return result;
    }

    public void setResult(List<Plane> result) {
        this.result = result;
    }

    public int getTotalPlane() {
        return totalPlane;
    }

    public void setTotalPlane(int totalPlane) {
        this.totalPlane = totalPlane;
    }

    public int getTotalPlaners() {
        return totalPlaners;
    }

    public void setTotalPlaners(int totalPlaners) {
        this.totalPlaners = totalPlaners;
    }

    public Plane getPlanner() {
        return planner;
    }

    public void setPlanner(Plane planner) {
        this.planner = planner;
    }

    public Date getPlaneDate() {
        return planeDate;
    }

    public void setPlaneDate(Date planeDate) {
        this.planeDate = planeDate;
    }

    public Date getPlaneDateArrive() {
        return planeDateArrive;
    }

    public void setPlaneDateArrive(Date planeDateArrive) {
        this.planeDateArrive = planeDateArrive;
    }

    public Plane getPlaning() {
        return planing;
    }

    public void setPlaning(Plane planing) {
        this.planing = planing;
    }
    
}
