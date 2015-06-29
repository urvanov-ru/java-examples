package ru.urvanov.javaexamples.jasperreportscharttheme;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            JasperReport jasperReport = JasperCompileManager
                    .compileReport(App.class
                            .getResourceAsStream("chart1.jrxml"));
            Map<String, Object> parameters = new HashMap<>();
            List<Map<String, ?>> data = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("x", Integer.valueOf(1));
            map.put("v",Integer.valueOf(2));
            map.put("name", "name");
            data.add(map);
            map = new HashMap<>();
            map.put("x", Integer.valueOf(2));
            map.put("v",Integer.valueOf(1));
            map.put("name", "name");
            data.add(map);
            map = new HashMap<>();
            map.put("x", Integer.valueOf(3));
            map.put("v",Integer.valueOf(3));
            map.put("name", "name");
            data.add(map);
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            try (FileOutputStream baos = new FileOutputStream("chart1.xls")) {
                JRXlsExporter xlsExporter = new JRXlsExporter();
                xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
                        baos));
                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setOnePagePerSheet(false);
                xlsExporter.setConfiguration(configuration);
                xlsExporter.exportReport();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
