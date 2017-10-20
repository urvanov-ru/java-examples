package ru.urvanov.javaexamples.jasperreportsbarchartintervalmarker;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

public class MyTestCustomizer implements JRChartCustomizer {
    public void customize(JFreeChart chart, JRChart jasperChart) {
        ValueMarker marker = new ValueMarker(6);  // Позиция, на которой
                                                  // рисуем линию
        marker.setPaint(Color.BLUE);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.addRangeMarker(marker);
    }
}