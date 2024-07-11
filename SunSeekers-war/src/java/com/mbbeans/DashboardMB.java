package com.mbbeans;

import com.entities.Orders;
import com.entities.UserReg;
import com.sessionbeans.OrderDetailsFacadeLocal;
import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.TourFacadeLocal;
import com.sessionbeans.UserRegFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "dashboardMB")
@SessionScoped
public class DashboardMB implements Serializable {

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private UserRegFacadeLocal userRegFacade;

    @EJB
    private TourFacadeLocal tourFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    private LineChartModel orderDataModel;
    private int selected;
    private List<Object[]> orderData;

    private BarChartModel barModel;

    public DashboardMB() {
    }

    @PostConstruct
    public void init() {
        selected = 1; // Default value
        fetchOrderData();
        //        createOrderDataModel();
        createBarModel();
    }

    public void fetchOrderData() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(selected);
        orderData = ordersFacade.showOrderByDate(startDate, endDate);
        createOrderDataModel();
    }

    private void createOrderDataModel() {
        orderDataModel = new LineChartModel();
        LineChartSeries orders = new LineChartSeries();
        orders.setLabel("Orders");

        if (orderData.isEmpty()) {
            // Thêm giá trị mặc định nếu không có dữ liệu
            LocalDate currentDate = LocalDate.now();
            orders.set(currentDate.toString(), 0);
        } else {
            for (Object[] record : orderData) {
                Date dater = (Date) record[0];
                Long count = (Long) record[1];
                // Chuyển đổi Date sang LocalDate
                LocalDate localDate = dater.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                orders.set(localDate, count);
                orderDataModel.addSeries(orders);
                orderDataModel.setLegendPosition("e");
                orderDataModel.setAnimate(true);
                orderDataModel.setZoom(true);
                orderDataModel.getAxis(AxisType.Y).setLabel("Number of Orders");
                orderDataModel.getAxis(AxisType.X).setLabel("Date");
                Axis yAxis = orderDataModel.getAxis(AxisType.Y);
                yAxis.setLabel("Number of Orders");
                yAxis.setMin(0);

                DateAxis xAxis = new DateAxis();
                List<LocalDate> tickDates = orderData.stream()
                        .map(orderrecord -> ((Date) orderrecord[0]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .distinct()
                        .collect(Collectors.toList());

                xAxis.setTickCount(tickDates.size());
                xAxis.setMin(tickDates.get(tickDates.size() - 1).toString());
                xAxis.setMax(tickDates.get(0).toString());
                xAxis.setTickAngle(-50);
                xAxis.setTickFormat("%#d %b, %y");
                xAxis.setTickInterval("1");
                orderDataModel.getAxes().put(AxisType.X, xAxis);
            }
        }
    }

    private void createBarModel() {
        barModel = new BarChartModel();

        LocalDate currentDate = LocalDate.now();
        List<Object[]> topRevenues = orderDetailsFacade.showRevenue(currentDate);
        if (topRevenues != null && !topRevenues.isEmpty()) {
            ChartSeries revenueSeries = new ChartSeries();
            revenueSeries.setLabel("Revenue");
            
            Collections.sort(topRevenues, (a, b) -> ((BigDecimal) b[2]).compareTo((BigDecimal) a[2]));

            for (Object[] result : topRevenues) {
                String tourName = (String) result[1];
                BigDecimal revenue = (BigDecimal) result[2];
                revenueSeries.set(tourName, revenue);
            }
            
            barModel.addSeries(revenueSeries);
        } else {
            // Handle the case when topRevenues is null or empty
            System.out.println("No revenue data found for today.");
        }

        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);
    }
    
    public List<UserReg> showAllNewUser(){
        return userRegFacade.showAllDashboard();
    }
    
    public List<Orders> showAllOrder(){
        return ordersFacade.showOrderDashboard();
    }

    public LineChartModel getOrderDataModel() {
        return orderDataModel;
    }

    public BigDecimal sumRevenue() {
        return ordersFacade.countRevenue();
    }

    public Long sumTour() {
        return tourFacade.sumTour();
    }

    public Long sumOrder() {
        return ordersFacade.countOrder();
    }

    public Long sumUser() {
        return userRegFacade.countUser();
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public List<Object[]> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<Object[]> orderData) {
        this.orderData = orderData;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
}
