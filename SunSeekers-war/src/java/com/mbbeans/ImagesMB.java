package com.mbbeans;

import com.entities.Images;
import com.entities.Tour;
import com.sessionbeans.ImagesFacadeLocal;
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

@Named(value = "imagesMB")
@SessionScoped
public class ImagesMB implements Serializable {

    @EJB
    private TourFacadeLocal tourFacade;

    private static final String uploadDirectory = "D:\\A22063\\JavaWeb\\FinalAssignment\\SunSeekers\\SunSeekers-war\\web\\resources\\images\\upload";

    @EJB
    private ImagesFacadeLocal imagesFacade;

    private List<Images> images;
    private int totalImage;
    private int pageSize = 5;
    private int currentPage = 1;
    private int totalPages;
    private Images img;
    private Part img1;
    private Part img2;
    private Part img3;
    private Part img4;
    private Part img5;
    private Part img6;
    private int imgId;
    private Images imgb;
    private int updateId;
    private Images imga;

    public ImagesMB() {
    }

    @PostConstruct
    public void init() {
        img = new Images();
        imagePagination();
    }

    public List<Images> imagePagination() {
        images = imagesFacade.findAll();
        totalImage = images.size();
        totalPages = (int) Math.ceil((double) totalImage / pageSize);
        images = imagesFacade.findAllAndPagination(currentPage, pageSize);
        return images;
    }

    public String createImage() {
        String commonPath = "/resources/images/upload/";
        img.setImgPath(commonPath);

        if (img1 != null) {
            String i1 = saveFile(img1, uploadDirectory);
            img.setImg1(i1);
        } else {
            System.out.println("Error fileMorning");
        }
        if (img2 != null) {
            String i2 = saveFile(img2, uploadDirectory);
            img.setImg2(i2);
        } else {
            System.out.println("Error fileAfternoon");
        }
        if (img3 != null) {
            String i3 = saveFile(img3, uploadDirectory);
            img.setImg3(i3);
        } else {
            System.out.println("Error fileEvening");
        }
        if (img4 != null) {
            String i4 = saveFile(img4, uploadDirectory);
            img.setImg4(i4);
        } else {
            System.out.println("Error fileEvening");
        }

        if (img5 != null) {
            String i5 = saveFile(img5, uploadDirectory);
            img.setImg5(i5);
        } else {
            System.out.println("Error fileEvening");
        }

        if (img6 != null) {
            String i6 = saveFile(img6, uploadDirectory);
            img.setImg6(i6);
        } else {
            System.out.println("Error fileEvening");
        }
        imagesFacade.create(img);
        imagePagination();
        return "/admin/image";
    }

    public String findById() {
        String idImage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imgId");
        if (idImage != null) {
            imgId = Integer.parseInt(idImage);
            imgb = imagesFacade.find(imgId);
        }
        return "/admin/updateImage?faces-redirect=true";
    }

    public String updatingImage() {
        String idImage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imgId");
        if (idImage != null) {
            updateId = Integer.parseInt(idImage);
            imga = imagesFacade.find(updateId);
            System.out.println("Image:" + imga);
            String commonPath = "/resources/images/upload/";
            if (imga != null) {
                if (img1 != null) {
                    String i1 = saveFile(img1, uploadDirectory);
                    imga.setImg1(i1);
                } else {
                    System.out.println("Error fileMorning");
                    imga.setImg1(imgb.getImg1());
                }
                if (img2 != null) {
                    String i2 = saveFile(img2, uploadDirectory);
                    imga.setImg2(i2);
                } else {
                    System.out.println("Error fileAfternoon");
                    imga.setImg2(imgb.getImg2());
                }
                if (img3 != null) {
                    String i3 = saveFile(img3, uploadDirectory);
                    imga.setImg3(i3);
                } else {
                    System.out.println("Error fileEvening");
                    imga.setImg3(imgb.getImg3());
                }
                if (img4 != null) {
                    String i4 = saveFile(img4, uploadDirectory);
                    imga.setImg4(i4);
                } else {
                    System.out.println("Error fileEvening");
                    imga.setImg4(imgb.getImg4());
                }

                if (img5 != null) {
                    String i5 = saveFile(img5, uploadDirectory);
                    imga.setImg5(i5);
                } else {
                    System.out.println("Error fileEvening");
                    imga.setImg5(imgb.getImg5());
                }

                if (img6 != null) {
                    String i6 = saveFile(img6, uploadDirectory);
                    imga.setImg6(i6);
                } else {
                    System.out.println("Error fileEvening");
                    imga.setImg6(imgb.getImg6());
                }
                imga.setImgPath(commonPath);
                imagesFacade.edit(imga);
                imagePagination();
            }
        }
        return "/admin/image";
    }
    
    public String deleteImage(int imgId){
        Images img = imagesFacade.find(imgId);
        Tour tour = tourFacade.find(imgId);
        if(tour != null){
            tour.setImageId(null);
            tourFacade.edit(tour);
            imagesFacade.remove(img);
        }else{
            imagesFacade.remove(img);
        }
        imagePagination();
        return "/admin/image";
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

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            imagePagination();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            imagePagination();
        }
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
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

    public Images getImg() {
        return img;
    }

    public void setImg(Images img) {
        this.img = img;
    }

    public Part getImg1() {
        return img1;
    }

    public void setImg1(Part img1) {
        this.img1 = img1;
    }

    public Part getImg2() {
        return img2;
    }

    public void setImg2(Part img2) {
        this.img2 = img2;
    }

    public Part getImg3() {
        return img3;
    }

    public void setImg3(Part img3) {
        this.img3 = img3;
    }

    public Part getImg4() {
        return img4;
    }

    public void setImg4(Part img4) {
        this.img4 = img4;
    }

    public Part getImg5() {
        return img5;
    }

    public void setImg5(Part img5) {
        this.img5 = img5;
    }

    public Part getImg6() {
        return img6;
    }

    public void setImg6(Part img6) {
        this.img6 = img6;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public Images getImgb() {
        return imgb;
    }

    public void setImgb(Images imgb) {
        this.imgb = imgb;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Images getImga() {
        return imga;
    }

    public void setImga(Images imga) {
        this.imga = imga;
    }
}
