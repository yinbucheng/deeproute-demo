package cn.deeproute.demo.system.log;

import cn.deeproute.demo.system.AbstractProfileEnvironmentPostProcessor;
import cn.deeproute.demo.system.PropertySourceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

/**
 * @author test
 * @create 2022/2/27 20:33
 * @description
 */
public class LogEnvInjector extends AbstractProfileEnvironmentPostProcessor {

    @Override
    public void onDev(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onStg(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onPrd(ConfigurableEnvironment env, SpringApplication application) {

    }

    @Override
    public void onLocal(ConfigurableEnvironment env, SpringApplication application) {
    }

    @Override
    public void onAllProfiles(ConfigurableEnvironment env, SpringApplication application) {
        PropertySourceUtils.put(env, "deeproute.logging.showSql", true);
        Binder binder = Binder.get(env);
        BindResult<DeeprouteLogProperties> bindResult = binder.bind("deeproute.logging", Bindable.of(DeeprouteLogProperties.class));
        DeeprouteLogProperties deeprouteLogProperties;
        if (bindResult.isBound()) {
            deeprouteLogProperties = bindResult.get();
        } else {
            deeprouteLogProperties = new DeeprouteLogProperties();
        }
        String appName = PropertySourceUtils.getAppName(env);
        String logDir = deeprouteLogProperties.getLogDir();
        if (StringUtils.isEmpty(logDir)) {
            logDir = appName;
        }
        String fileName = "/data/logs/" + logDir + "/" + appName + ".log";
        String filePattern = fileName.substring(0, fileName.lastIndexOf(".log")) + "-%d{yyyy-MM-dd}-%i.log";
        System.setProperty("LOG_FILE_NAME", fileName);
        System.setProperty("LOG_FILE_PATTERN", filePattern);
        System.setProperty("CONSOLE_LOG_PATTERN", deeprouteLogProperties.getConsoleLogPattern());
        System.setProperty("LOG_MAX_LEN", String.valueOf(deeprouteLogProperties.getMaxLength()));
        System.setProperty("LOG_LINE_NUM", String.valueOf(deeprouteLogProperties.getLineNum()));
        String config = "logging.config";
        PropertySourceUtils.put(env, config, "classpath:log4j2-template.xml");
    }
}
