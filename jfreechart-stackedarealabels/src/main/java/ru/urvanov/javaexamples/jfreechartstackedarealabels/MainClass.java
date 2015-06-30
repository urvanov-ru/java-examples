package ru.urvanov.javaexamples.jfreechartstackedarealabels;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedAreaRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainClass {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(600,400));
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.6, "roKey1", "columnKey1");
        dataset.addValue(0.3, "roKey1", "columnKey2");
        dataset.addValue(2.3, "roKey1", "columnKey3");
        JFreeChart chart = ChartFactory.createStackedAreaChart(
            "Serial Data", // chart title
            "Domain", // domain axis label
            "Range", // range axis label
            dataset,  // initial series
            PlotOrientation.VERTICAL, // orientation
            true, // include legend
            true, // tooltips?
            false // URLs?
            );

        // set chart background
        chart.setBackgroundPaint(Color.white);

        // set a few custom plot features
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        // set the plot's axes to display integers
        //TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
        //CategoryAxis domain = (CategoryAxis) plot.getDomainAxis();
        //NumberAxis range = (NumberAxis) plot.getRangeAxis();
        //range.setStandardTickUnits(ticks);

        // render shapes and lines
        StackedAreaRenderer renderer =
            new StackedAreaRenderer();
        plot.setRenderer(renderer);

        // set the renderer's stroke
        //Stroke stroke = new BasicStroke(
        //    3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        //renderer.setBaseOutlineStroke(stroke);

        // label the points
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        StandardCategoryItemLabelGenerator generator =
            new StandardCategoryItemLabelGenerator(
                    StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING,
                format, format);
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(640, 480));
        frame.add(chartPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
