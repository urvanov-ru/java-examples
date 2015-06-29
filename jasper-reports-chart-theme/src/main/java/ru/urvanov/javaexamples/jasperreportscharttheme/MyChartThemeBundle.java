package ru.urvanov.javaexamples.jasperreportscharttheme;

import net.sf.jasperreports.charts.ChartTheme;
import net.sf.jasperreports.charts.ChartThemeBundle;


public class MyChartThemeBundle implements ChartThemeBundle {

    public MyChartThemeBundle() {
        System.out.println("test");
    }

    @Override
    public ChartTheme getChartTheme(String arg0) {
        return new MyChartTheme();
    }

    @Override
    public String[] getChartThemeNames() {
        return new String[]{"myTheme"};
    }

}

