package com.mbbeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class CalendarView implements Serializable {

    private Date date;
    private Date date1;
    @Future
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    private Date date7;
    private Date date8;
    private Date date9;
    private Date dateDe;
    @Future
    private Date date10;
    private Date date11;
    private Date date12;
    private Date date13;
    private Date date14;
    private Date date15;
    private Date date16;
    private Date dateTimeDe;
    private Date dateTimeMillis;
    private List<Date> multi;
    private List<Date> range;
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private List<Date> invalidDatess;
    private List<Integer> invalidDayss;
    private Date minDate;
    private Date maxDate;
    private Date minTime;
    private Date maxTime;
    private Date minDateTime;
    private Date maxDateTime;

    @PostConstruct
    public void init() {
//        invalidDates = new ArrayList<>();
//        Date today = new Date();
//        invalidDates.add(today);
        long oneDay = 24 * 60 * 60 * 1000;
//        for (int i = 0; i < 5; i++) {
//            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
//        }
//
//        invalidDays = new ArrayList<>();
//        invalidDays.add(0);
//        /* the first day of week is disabled */
//        invalidDays.add(3);
        invalidDates = new ArrayList<>();
        invalidDatess = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
// Đặt ngày hiện tại
        Date today = calendar.getTime();
// Số ngày loại trừ sẽ là số ngày trong tháng trừ đi ngày hiện tại
        int numberOfDaysToExclude = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);

// Lặp qua các tháng sau tháng hiện tại
        for (int i = 1; i <= 11; i++) { // Bắt đầu từ tháng sau và lặp qua 11 tháng tiếp theo
            // Đặt lịch vào ngày đầu tiên của tháng tiếp theo
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            // Lấy số ngày trong tháng
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // Thêm vào số ngày của tháng đó vào tổng số ngày loại trừ
            numberOfDaysToExclude += daysInMonth;
        }

// Thêm vào số ngày mà bạn muốn loại trừ
        for (int i = 1; i <= numberOfDaysToExclude; i++) {
            calendar.setTime(today);
            // Loại bỏ i ngày từ ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, i);
            invalidDates.add(calendar.getTime());
        }

        calendar.setTime(today);
        for (int i = 1; i <= 365; i++) { // Vô hiệu hóa 1 năm trước
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            invalidDatess.add(calendar.getTime());
        }

        minDate = new Date(today.getTime() - (365 * oneDay));
        maxDate = new Date(today.getTime() + (365 * oneDay));

        Calendar tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 9);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        minTime = tmp.getTime();

        tmp = Calendar.getInstance();
        tmp.set(Calendar.HOUR_OF_DAY, 17);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        tmp.set(Calendar.MILLISECOND, 0);
        maxTime = tmp.getTime();

        minDateTime = new Date(today.getTime() - (7 * oneDay));
        maxDateTime = new Date(today.getTime() + (7 * oneDay));

        dateDe = GregorianCalendar.getInstance().getTime();
        dateTimeDe = GregorianCalendar.getInstance().getTime();
        dateTimeMillis = GregorianCalendar.getInstance().getTime();
    }

    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Date getDate4() {
        return date4;
    }

    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    public Date getDate5() {
        return date5;
    }

    public void setDate5(Date date5) {
        this.date5 = date5;
    }

    public Date getDate6() {
        return date6;
    }

    public void setDate6(Date date6) {
        this.date6 = date6;
    }

    public Date getDate7() {
        return date7;
    }

    public void setDate7(Date date7) {
        this.date7 = date7;
    }

    public Date getDate8() {
        return date8;
    }

    public void setDate8(Date date8) {
        this.date8 = date8;
    }

    public Date getDate9() {
        return date9;
    }

    public void setDate9(Date date9) {
        this.date9 = date9;
    }

    public Date getDate10() {
        return date10;
    }

    public void setDate10(Date date10) {
        this.date10 = date10;
    }

    public Date getDate11() {
        return date11;
    }

    public void setDate11(Date date11) {
        this.date11 = date11;
    }

    public Date getDate12() {
        return date12;
    }

    public void setDate12(Date date12) {
        this.date12 = date12;
    }

    public Date getDate13() {
        return date13;
    }

    public void setDate13(Date date13) {
        this.date13 = date13;
    }

    public Date getDate14() {
        return date14;
    }

    public void setDate14(Date date14) {
        this.date14 = date14;
    }

    public List<Date> getMulti() {
        return multi;
    }

    public void setMulti(List<Date> multi) {
        this.multi = multi;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }

    public List<Date> getInvalidDates() {
        return invalidDates;
    }

    public void setInvalidDates(List<Date> invalidDates) {
        this.invalidDates = invalidDates;
    }

    public List<Integer> getInvalidDays() {
        return invalidDays;
    }

    public void setInvalidDays(List<Integer> invalidDays) {
        this.invalidDays = invalidDays;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getDateTimeDe() {
        return dateTimeDe;
    }

    public void setDateTimeDe(Date dateTimeDe) {
        this.dateTimeDe = dateTimeDe;
    }

    public Date getDateDe() {
        return dateDe;
    }

    public void setDateDe(Date dateDe) {
        this.dateDe = dateDe;
    }

    public Date getDateTimeMillis() {
        return dateTimeMillis;
    }

    public void setDateTimeMillis(Date dateTimeMillis) {
        this.dateTimeMillis = dateTimeMillis;
    }

    public Date getDate15() {
        return date15;
    }

    public void setDate15(Date date15) {
        this.date15 = date15;
    }

    public Date getMinTime() {
        return minTime;
    }

    public void setMinTime(Date minTime) {
        this.minTime = minTime;
    }

    public Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }

    public Date getDate16() {
        return date16;
    }

    public void setDate16(Date date16) {
        this.date16 = date16;
    }

    public Date getMinDateTime() {
        return minDateTime;
    }

    public void setMinDateTime(Date minDateTime) {
        this.minDateTime = minDateTime;
    }

    public Date getMaxDateTime() {
        return maxDateTime;
    }

    public void setMaxDateTime(Date maxDateTime) {
        this.maxDateTime = maxDateTime;
    }

    public List<Date> getInvalidDatess() {
        return invalidDatess;
    }

    public void setInvalidDatess(List<Date> invalidDatess) {
        this.invalidDatess = invalidDatess;
    }

    public List<Integer> getInvalidDayss() {
        return invalidDayss;
    }

    public void setInvalidDayss(List<Integer> invalidDayss) {
        this.invalidDayss = invalidDayss;
    }

}
