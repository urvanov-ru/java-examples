package ru.urvanov.javaexamples.jasperreportscharttheme;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

public class MyExtensionRegistryFactory implements
        net.sf.jasperreports.extensions.ExtensionsRegistryFactory {

    @Override
    public ExtensionsRegistry createRegistry(String arg0, JRPropertiesMap arg1) {
        return new ExtensionsRegistry() {

            @SuppressWarnings("unchecked")
            public <T> List<T> getExtensions(Class<T> extensionType) {
                List<MyChartThemeBundle> result = new ArrayList<MyChartThemeBundle>();
                if (extensionType == ChartThemeBundle.class) {
                    result.add(new MyChartThemeBundle());
                }
                return (List<T>) result;
            }

        };
    }

}
