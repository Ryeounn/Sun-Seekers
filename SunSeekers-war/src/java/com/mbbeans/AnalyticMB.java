package com.mbbeans;

import com.sessionbeans.OrdersFacadeLocal;
import com.sessionbeans.WareHouseFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "analyticMB")
@SessionScoped
public class AnalyticMB implements Serializable {

    @EJB
    private WareHouseFacadeLocal wareHouseFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;
    
    private LineChartModel lineModel;

    private LineChartModel model;
    
    private BarChartModel barModel;

    public AnalyticMB() {
    }

    @PostConstruct
    public void init() {
        createLineModel();
        createLine();
        createBarLine();
    }

    public void createLine() {
        model = new LineChartModel();
        model.setLegendPosition("e");
        model.setAnimate(true);
        model.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Months");
        model.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Number of Revenue");

        List<Object[]> revenueList = ordersFacade.getRevenueFromLast12Months();

        Map<String, Integer> monthlyRevenueCounts = new TreeMap<>();

        for (Object[] result : revenueList) {
            int year = ((Number) result[1]).intValue();
            int month = ((Number) result[0]).intValue();
            BigDecimal revenue = (BigDecimal) result[2];
            long revenueLong = revenue.longValue();
            String yearMonthKey = String.format("%04d-%02d", year, month);
            monthlyRevenueCounts.put(yearMonthKey, (int) revenueLong);
        }

        int maxRevenue = 0;
        for (int revenue : monthlyRevenueCounts.values()) {
            if (revenue > maxRevenue) {
                maxRevenue = revenue;
            }
        }
        yAxis.setMax(maxRevenue + 1000);

        Calendar calendar = Calendar.getInstance();
        LineChartSeries revenueSeries = new LineChartSeries();
        revenueSeries.setLabel("Revenue");

        for (int i = 11; i >= 0; i--) {
            String yearMonthKey = String.format("%04d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
            int revenue = monthlyRevenueCounts.getOrDefault(yearMonthKey, 0);
            String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
            revenueSeries.set(monthName, revenue);
            calendar.add(Calendar.MONTH, -1);
        }

        model.addSeries(revenueSeries);
    }

    private void createLineModel() {
        lineModel = new LineChartModel();
        lineModel.setLegendPosition("e");
        lineModel.setAnimate(true);
        lineModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Months");
        lineModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of Orders");

        List<Object[]> orderList = ordersFacade.getOrderFromLast12Months();

        Map<String, Integer> monthlyOrderCounts = new TreeMap<>();

        for (Object[] result : orderList) {
            int month = ((Number) result[0]).intValue();
            long count = (long) result[1];
            String yearMonthKey = String.format("%04d-%02d", Calendar.getInstance().get(Calendar.YEAR), month);
            monthlyOrderCounts.put(yearMonthKey, (int) count);
        }

        int maxOrders = 0;
        for (int count : monthlyOrderCounts.values()) {
            maxOrders += count;
        }
        yAxis.setMax(maxOrders + 30);

        Calendar calendar = Calendar.getInstance();
        LineChartSeries orders = new LineChartSeries();
        orders.setLabel("Orders");

        for (int i = 11; i >= 0; i--) {
            String yearMonthKey = String.format("%04d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
            int count = monthlyOrderCounts.getOrDefault(yearMonthKey, 0);
            String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
            orders.set(monthName, count);
            calendar.add(Calendar.MONTH, -1);
        }

        lineModel.addSeries(orders);
    }
    
    public void createBarLine(){
        barModel = new BarChartModel();
        
        List<Object[]> wareHouseData = wareHouseFacade.wareHouseData();
        
        int totalSold = 0;
        int totalRemaining = 0;
        
        for (Object[] data : wareHouseData) {
            int total = ((Number) data[0]).intValue();
            int inventory = ((Number) data[1]).intValue();
            totalSold += total;
            totalRemaining += inventory;
        }
        
        ChartSeries soldSeries = new ChartSeries();
        soldSeries.setLabel("Total");
        soldSeries.set("Products", totalSold);

        ChartSeries remainingSeries = new ChartSeries();
        remainingSeries.setLabel("Inventory");
        remainingSeries.set("Products", totalRemaining);

        barModel.addSeries(soldSeries);
        barModel.addSeries(remainingSeries);

        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of Products");
        yAxis.setMin(0);
    }

    public BigDecimal totalRevenue() {
        return ordersFacade.countRevenue();
    }

    public long totalOrder() {
        return ordersFacade.countOrderByYear();
    }
    
    public long totalWareHouse(){
        return wareHouseFacade.sumWareHouse();
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
