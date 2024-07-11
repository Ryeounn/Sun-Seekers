package com.mbbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;


@Named(value = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    private String selectedLocale = "en";
    
    public LocaleBean() {
    }
    
    public void changeLocale(String localeCode){
        selectedLocale = localeCode;
        Locale newLocale = new Locale(selectedLocale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
    }

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }
    
}
