/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbbeans;

import com.entities.Schedule;
import com.entities.Tour;
import com.sessionbeans.ScheduleFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Ngu Công
 */
@Named(value = "scheduleMB")
@SessionScoped
public class ScheduleMB implements Serializable {

    private static final String uploadDirectory = "D:\\A22063\\JavaWeb\\FinalAssignment\\SunSeekers\\SunSeekers-war\\web\\resources\\images\\upload";

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private ScheduleFacadeLocal scheduleFacade;

    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private String keyword;
    private List<Schedule> result;
    private int totalSche;
    private int totalSchedule;
    private Part fileMorning;
    private Part fileAfternoon;
    private Part fileEvening;
    private Schedule sc;
    private int tourId;
    private int scheduleId;
    private Schedule sca;
    private Schedule scb;
    private int updateId;
    private int updateTourId;

    public ScheduleMB() {
    }

    @PostConstruct
    public void init() {
        sc = new Schedule();
        schedulePagination();
    }

    public List<Schedule> schedulePagination() {
        if (keyword == null || keyword.isEmpty()) {
            result = scheduleFacade.findAll();
            totalSche = result.size();
            totalPages = (int) Math.ceil((double) totalSche / pageSize);
            result = scheduleFacade.findAllAndPagination(currentPage, pageSize);
            return result;
        } else {
            totalSchedule = (int) scheduleFacade.countWithSearch(keyword);
            totalSche = result.size();
            totalPages = (int) Math.ceil((double) totalSchedule / pageSize);
            result = scheduleFacade.findUserBySearch(keyword, currentPage, pageSize);
            return result;
        }
    }

    public String createSchedule() {
        Tour tour = tourFacade.findByRegion(tourId);
        System.out.println("SC: " + tour);
        if (tour != null) {
            String commonPath = "/resources/images/upload/";
            sc.setTourId(tour);
            if (fileMorning != null) {
                String morningImageName = saveFile(fileMorning, uploadDirectory);
                sc.setSImageMor(morningImageName);
            } else {
                System.out.println("Error fileMorning");
            }
            if (fileAfternoon != null) {
                String afternoonImageName = saveFile(fileAfternoon, uploadDirectory);
                sc.setSImageAfter(afternoonImageName);
            } else {
                System.out.println("Error fileAfternoon");
            }
            if (fileEvening != null) {
                String eveningImageName = saveFile(fileEvening, uploadDirectory);
                sc.setSImageEver(eveningImageName);
            } else {
                System.out.println("Error fileEvening");
            }
            sc.setSPath(commonPath);
            scheduleFacade.create(sc);
        }
        schedulePagination();
        return "/admin/schedule";
    }

    public void deleteSchedule(int scheduleId) {
        Schedule scs = scheduleFacade.find(scheduleId);
        if (scs != null) {
            scheduleFacade.remove(scs);
        } else {
            System.out.println("Not Find Object Schedule");
        }
        schedulePagination();
    }

    public String findByScheduleId() {
        String idSche = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("scheduleId");
        if (idSche != null) {
            scheduleId = Integer.parseInt(idSche);
            sca = scheduleFacade.find(scheduleId);
        }
        return "/admin/updateSchedule?faces-redirect=true";
    }

    private String saveFile(Part file, String commonPath) {
        try (InputStream input = file.getInputStream()) {
            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
            String filePath = commonPath + File.separator + fileName;
            Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            return fileName; // Return only the file name
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updatingSchedule() {
        String idSche = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("scheduleId");
        String idTour = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tourId");
        if (idSche != null && idTour != null) {
            updateId = Integer.parseInt(idSche);
            updateTourId = Integer.parseInt(idTour);
            scb = scheduleFacade.find(updateId);
            Tour tours = tourFacade.findByRegion(updateTourId);
            if (scb != null) {
                String commonPath = "/resources/images/upload/";
                if (fileMorning != null) {
                    String morningImageName = saveFile(fileMorning, uploadDirectory);
                    scb.setSImageMor(morningImageName);
                } else {
                    System.out.println("abc:" + sca.getSImageMor());
                    scb.setSImageMor(sca.getSImageMor());
                    System.out.println("Error fileMorning");
                }
                if (fileAfternoon != null) {
                    String afternoonImageName = saveFile(fileAfternoon, uploadDirectory);
                    scb.setSImageAfter(afternoonImageName);
                } else {
                    scb.setSImageAfter(sca.getSImageAfter());
                    System.out.println("Error fileAfternoon");
                }
                if (fileEvening != null) {
                    String eveningImageName = saveFile(fileEvening, uploadDirectory);
                    scb.setSImageEver(eveningImageName);
                } else {
                    scb.setSImageEver(sca.getSImageEver());
                    System.out.println("Error fileEvening");
                }
                scb.setSPath(commonPath);
                scb.setTourId(tours);
                scb.setSDate(sca.getSDate());
                scb.setSName(sca.getSName());
                scb.setSTime(sca.getSTime());
                scb.setSAddress(sca.getSAddress());
                scb.setSMorning(sca.getSMorning());
                scb.setSAfternoon(sca.getSAfternoon());
                scb.setSEverning(sca.getSEverning());
                scheduleFacade.edit(scb);
            } else {
                System.out.println("Error Find Scb");
            }
        }
        schedulePagination();
        return "/admin/schedule";
    }

    public void searchSchedule() {
        currentPage = 1;
        schedulePagination();
    }

    public List<Tour> findAllTour() {
        return tourFacade.findAll();
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            schedulePagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            schedulePagination();
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

    public List<Schedule> getResult() {
        return result;
    }

    public void setResult(List<Schedule> result) {
        this.result = result;
    }

    public int getTotalSche() {
        return totalSche;
    }

    public void setTotalSche(int totalSche) {
        this.totalSche = totalSche;
    }

    public int getTotalSchedule() {
        return totalSchedule;
    }

    public void setTotalSchedule(int totalSchedule) {
        this.totalSchedule = totalSchedule;
    }

    public Part getFileMorning() {
        return fileMorning;
    }

    public void setFileMorning(Part fileMorning) {
        this.fileMorning = fileMorning;
    }

    public Part getFileAfternoon() {
        return fileAfternoon;
    }

    public void setFileAfternoon(Part fileAfternoon) {
        this.fileAfternoon = fileAfternoon;
    }

    public Part getFileEvening() {
        return fileEvening;
    }

    public void setFileEvening(Part fileEvening) {
        this.fileEvening = fileEvening;
    }

    public Schedule getSc() {
        return sc;
    }

    public void setSc(Schedule sc) {
        this.sc = sc;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule getSca() {
        return sca;
    }

    public void setSca(Schedule sca) {
        this.sca = sca;
    }

    public Schedule getScb() {
        return scb;
    }

    public void setScb(Schedule scb) {
        this.scb = scb;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getUpdateTourId() {
        return updateTourId;
    }

    public void setUpdateTourId(int updateTourId) {
        this.updateTourId = updateTourId;
    }

}
