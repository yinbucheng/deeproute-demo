package cn.deeproute.demo.system.log;

import cn.deeproute.demo.system.ConfigChangeObserver;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chongyin
 */
@Component
@Slf4j
public class LogConfigChangeObserver extends ConfigChangeObserver {

    @Override
    public void handle(String key, Object value) {
        if (key.equals("deeproute.logging.maxLength") || key.equals("deeproute.logging.max-length")) {
            DeeprouteMaxLengthConverter.setMaxLength(Integer.parseInt(value + ""));
            return;
        }
        if (key.equals("deeproute.logging.lineNum")) {
            DeepRouteThrowablePatternConverter.updateLineNum(String.valueOf(value));
            return;
        }
        if (key.equals("deeproute.logging.level")) {
            switch ((String) value) {
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
    }

    @Override
    public Set<String> matchKey() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("deeproute.logging.maxLength");
        hashSet.add("deeproute.logging.max-length");
        hashSet.add("deeproute.logging.lineNum");
        hashSet.add("deeproute.logging.level");
        return hashSet;
    }
}
