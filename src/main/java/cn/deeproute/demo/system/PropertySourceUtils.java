package cn.deeproute.demo.system;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author test
 * @create 2022/2/27 21:06
 * @description
 */
public class PropertySourceUtils {
    private static final String CUSTOM_PROPERTIES = "custom.properties";
    private static final String PRIORITY_PROPERTIES = "priority.properties";
    private static final String INNER_PROPERTIES = "inner.properties";

    public static void put(ConfigurableEnvironment environment, String name, Object object) {
        Map<String, Object> location = prepareOrGetDefaultLocation(environment);
        location.put(name, object);
    }

    public static Object get(ConfigurableEnvironment environment, String name) {
        Map<String, Object> location = prepareOrGetDefaultLocation(environment);
        return location.get(name);
    }

    private static final String SCAN_PACKAGES = "custom_scan_packages";

    public static List<String> getBasePackages(ConfigurableEnvironment environment) {
        Map<String, Object> location = prepareOrGetInnerLocation(environment);
        return (List<String>) location.get(SCAN_PACKAGES);
    }

    private static Map<String, Object> prepareOrGetInnerLocation(ConfigurableEnvironment environment) {
        return prepareOrGetMapSource(environment, INNER_PROPERTIES, MutablePropertySources::addLast);
    }

    public static void setBasePackages(ConfigurableEnvironment environment, List<String> packages) {
        Map<String, Object> location = prepareOrGetInnerLocation(environment);
        location.put(SCAN_PACKAGES, packages);
    }

    private static final String EXCLUDE_NAME = "spring.autoconfig.exclude";

    public static void excludeAutoConfiguration(ConfigurableEnvironment environment, String name) {
        String old = environment.getProperty(EXCLUDE_NAME);
        if (StringUtils.hasText(old)) {
            putPriority(environment, EXCLUDE_NAME, old + "," + name);
        } else {
            putPriority(environment, EXCLUDE_NAME, name);
        }
    }

    public static String getAppName(Environment environment) {
        String appName = environment.getProperty("spring.application.name");
        return appName;
    }

    public static Map<String, Object> prepareOrGetDefaultLocation(ConfigurableEnvironment environment) {
        return prepareOrGetMapSource(environment, CUSTOM_PROPERTIES, MutablePropertySources::addLast);
    }

    public static void putPriority(ConfigurableEnvironment environment, String name, Object object) {
        Map<String, Object> location = prepareOrGetPriorityLocation(environment);
        location.put(name, object);
    }

    public static Map<String, Object> prepareOrGetPriorityLocation(ConfigurableEnvironment environment) {
        return prepareOrGetMapSource(environment, PRIORITY_PROPERTIES, MutablePropertySources::addFirst);
    }

    public static Map<String, Object> prepareOrGetMapSource(ConfigurableEnvironment environment, String sourceName, BiConsumer<MutablePropertySources, MapPropertySource> sourceLocFunction) {
        MutablePropertySources propertySources = environment.getPropertySources();
        MapPropertySource mapPropertySource = (MapPropertySource) propertySources.get(sourceName);
        Map<String, Object> source;
        if (mapPropertySource == null) {
            source = new HashMap<>();
            mapPropertySource = new MapPropertySource(sourceName, source);
            sourceLocFunction.accept(propertySources, mapPropertySource);
        } else {
            source = mapPropertySource.getSource();
        }
        return source;
    }
}