package com.mbbeans;

import com.entities.TourDetail;
import com.sessionbeans.TourDetailFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "exploreMB")
@SessionScoped
public class ExploreMB implements Serializable {

    @EJB
    private TourDetailFacadeLocal tourDetailFacade;

    public ExploreMB() {
    }
    
    public List<TourDetail> theBestSeller(){
        return tourDetailFacade.findBestSeller();
    }
}
