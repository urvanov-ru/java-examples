package ru.urvanov.javaexamples.jasperreportsdynamiccolumns;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * Пример генерации отчёта в JasperReports программно.
 * @author urvanov
 * 
 */
public class App {
    public static void main(String[] args) throws JRException {
        try {
            generateReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport() throws JRException, IOException {
        JasperDesign jasperDesign = createDesign();
        JasperReport jasperReport = JasperCompileManager
                .compileReport(jasperDesign);
        JRDataSource jrDataSource = prepareDataSource();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startDate", new Date());
        params.put("endDate", new Date());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, jrDataSource);

        try (FileOutputStream baos = new FileOutputStream("dynamicReport.xls")) {
            JRXlsExporter xlsExporter = new JRXlsExporter();
            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
                    baos));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(false);
            xlsExporter.setConfiguration(configuration);
            xlsExporter.exportReport();
        }
    }

    /**
     * Здесь нам нужно развернуть данные, полученные из хранимой процедуры в
     * список объектов с нужным количеством полей. В качестве примера мы просто
     * создадим тестовые данные.
     * 
     * @return JRDataSource.
     */
    private static JRDataSource prepareDataSource() {
        List<Map<String, ?>> preparedData = new ArrayList<Map<String, ?>>();
        Map<String, Object> map;
        map = new HashMap<String, Object>();
        map.put("name", "Первый");
        map.put("value", 10);
        // В реальности нужно будет добавлять необходимые поля, сколько нужно,
        // динамически, в зависимости от параметров и данных.
        preparedData.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "Второй");
        map.put("value", 4);
        // В реальности нужно будет добавлять необходимые поля, сколько нужно,
        // динамически, в зависимости от параметров и данных.
        preparedData.add(map);
        return new JRMapCollectionDataSource(preparedData);
    }

    public static JasperDesign createDesign() throws JRException {
        // Эквивалентно StaticText в JasperStudio
        JRDesignStaticText staticText = null;

        // Эквивалентно TextField в JasperStudio
        JRDesignTextField textField = null;

        // Band. Details, Summary, Title и другие.
        JRDesignBand band = null;

        // Вычисляемое выражение. Для записи значений в JRDesignTextField.
        JRDesignExpression expression = null;

        // Для рисования линий.
        @SuppressWarnings("unused")
        JRDesignLine line = null;

        // Для добавления полей в отчёт.
        JRDesignField field = null;

        // Можно создавать условные стили.
        @SuppressWarnings("unused")
        JRDesignConditionalStyle conditionalStyle = null;

        // Рамка вокруг ячейки.
        JRLineBox lineBox = null;

        // Вычисляемое значение. Можно подсчитать, например сумму.
        JRDesignVariable variable = null;

        int x;
        int y;
        final int ROW_HEIGHT = 11;
        final int COLUMN_WIDTH = 60;

        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("dynamicColumns");
        jasperDesign.setPageWidth(600);
        jasperDesign.setPageHeight(500);
        jasperDesign.setColumnWidth(COLUMN_WIDTH);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(40);
        jasperDesign.setRightMargin(40);
        jasperDesign.setTopMargin(40);
        jasperDesign.setBottomMargin(40);
        jasperDesign.setIgnorePagination(true);
        jasperDesign
                .setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("normal");
        normalStyle.setDefault(true);
        normalStyle.setFontName("DejaVu Sans");
        normalStyle.setFontSize(8.5f);
        lineBox = normalStyle.getLineBox();
        lineBox.getTopPen().setLineWidth(0.5f);
        lineBox.getRightPen().setLineWidth(0.5f);
        lineBox.getBottomPen().setLineWidth(0.5f);
        lineBox.getLeftPen().setLineWidth(0.5f);
        jasperDesign.addStyle(normalStyle);

        JRDesignStyle headerStyle = new JRDesignStyle();
        headerStyle.setName("header");
        headerStyle.setDefault(true);
        headerStyle.setFontName("DejaVu Sans");
        headerStyle.setFontSize(8.5f);
        headerStyle.setBold(true);
        lineBox = headerStyle.getLineBox();
        lineBox.getTopPen().setLineWidth(0.5f);
        lineBox.getRightPen().setLineWidth(0.5f);
        lineBox.getBottomPen().setLineWidth(0.5f);
        lineBox.getLeftPen().setLineWidth(0.5f);
        jasperDesign.addStyle(headerStyle);

        // Параметры отчёта
        JRDesignParameter startDateParameter = new JRDesignParameter();
        startDateParameter.setName("startDate");
        startDateParameter.setValueClass(java.util.Date.class);
        jasperDesign.addParameter(startDateParameter);

        JRDesignParameter endDateParameter = new JRDesignParameter();
        endDateParameter.setName("endDate");
        endDateParameter.setValueClass(java.util.Date.class);
        jasperDesign.addParameter(endDateParameter);

        // Поля отчёта.
        field = new JRDesignField();
        field.setName("name");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("value");
        field.setValueClass(java.lang.Integer.class);
        jasperDesign.addField(field);
        // В случае отчёта с динамическими полями пробегаемся по количеству
        // полей и добавляем JRDesignField для каждого с уникальным именем.

        // Подсчитываем сумму
        variable = new JRDesignVariable();
        variable.setResetType(ResetTypeEnum.REPORT);
        variable.setValueClass(java.lang.Integer.class);
        expression = new JRDesignExpression();
        expression.setText("$F{value}");
        variable.setExpression(expression);
        variable.setCalculation(CalculationEnum.SUM);
        expression = new JRDesignExpression();
        expression.setText("0");
        variable.setInitialValueExpression(expression);
        variable.setName("summary");
        jasperDesign.addVariable(variable);

        // Title band
        band = new JRDesignBand();
        // добавляем нужные элементы в band.
        // Можно добавлять JRDesignTextField-ы и JRDesignStaticField-ы,
        // картинки и всё, что угодно. Мы пропустим для простоты.
        jasperDesign.setTitle(band);

        // Заголовки колонок.
        x = 0;
        y = 0;
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("Название");
        band.addElement(staticText);
        x += staticText.getWidth();

        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("Значение");
        band.addElement(staticText);
        x += staticText.getWidth();
        jasperDesign.setColumnHeader(band);

        // Detail band (данные)
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        x = 0;
        y = 0;
        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$F{name}");
        textField.setExpression(expression);
        textField.setStyle(normalStyle);
        band.addElement(textField);
        x += textField.getWidth();

        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$F{value}");
        textField.setExpression(expression);
        textField.setStyle(normalStyle);
        band.addElement(textField);
        x += textField.getWidth();
        // DetailsBand добавляется немного странно, да...
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);

        // Column footer
        band = new JRDesignBand();
        jasperDesign.setColumnFooter(band);

        // Подвал страницы
        band = new JRDesignBand();
        jasperDesign.setPageFooter(band);

        // Summary band
        band = new JRDesignBand();
        band.setHeight(ROW_HEIGHT);
        x = 0;
        y = 0;
        
        staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        staticText.setWidth(COLUMN_WIDTH);
        staticText.setHeight(ROW_HEIGHT);
        staticText.setStyle(headerStyle);
        staticText.setText("ИТОГО:");
        band.addElement(staticText);
        x+=staticText.getWidth();
        
        textField = new JRDesignTextField();
        textField.setX(x);
        textField.setY(y);
        textField.setWidth(COLUMN_WIDTH);
        textField.setHeight(ROW_HEIGHT);
        expression = new JRDesignExpression();
        expression.setText("$V{summary}");
        textField.setExpression(expression);
        textField.setStyle(headerStyle);
        band.addElement(textField);
        x += textField.getWidth();
        jasperDesign.setSummary(band);
        return jasperDesign;
    }
}
