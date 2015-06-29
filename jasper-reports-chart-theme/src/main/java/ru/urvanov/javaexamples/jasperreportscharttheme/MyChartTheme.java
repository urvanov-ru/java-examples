package ru.urvanov.javaexamples.jasperreportscharttheme;


import net.sf.jasperreports.charts.ChartContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.fill.DefaultChartTheme;
import net.sf.jasperreports.engine.fonts.FontUtil;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.CategoryItemRenderer;


public class MyChartTheme extends DefaultChartTheme {
    
    private ChartContext chartContext;
    
    public JFreeChart createChart(ChartContext chartContext) throws JRException {
        this.chartContext = chartContext;
        return super.createChart(chartContext);
    }
    /**
    *
    */
   protected JFreeChart createStackedAreaChart() throws JRException{
       JFreeChart jfreeChart = super.createStackedAreaChart();
       for (int n  = 0; n < jfreeChart.getCategoryPlot().getRendererCount(); n++) {
           System.out.println("n="+n);
           CategoryItemRenderer renderer = jfreeChart.getCategoryPlot().getRenderer(n);
           renderer.setBaseItemLabelsVisible(true);
           renderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator)getLabelGenerator());
           renderer.setBaseItemLabelPaint(getChart().getForecolor());
           renderer.setBaseItemLabelFont(FontUtil.getInstance(chartContext.getJasperReportsContext()).getAwtFont(
                   getFont(null), 
                   getLocale()
                   ));
       }
       return jfreeChart;
   }
}