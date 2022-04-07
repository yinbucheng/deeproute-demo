package cn.deeproute.demo.system.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogConfigApply implements SmartInitializingSingleton, EnvironmentAware {

    private Environment environment;

    @Override
    public void afterSingletonsInstantiated() {
        String level = environment.getProperty("deeproute.logging.level");
        switch (level) {
            case "trace":
                Configurator.setAllLevels(LogManager.getRootLogger().getName(), org.apache.logging.log4j.Level.TRACE);
                break;
            case "debug":
                Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);
                break;
            case "info":
                Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.INFO);
                break;
            case "warn":
                Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.WARN);
                break;
            case "error":
                Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
                break;
            default:
                log.error("日志级别修改失败！");
                break;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
