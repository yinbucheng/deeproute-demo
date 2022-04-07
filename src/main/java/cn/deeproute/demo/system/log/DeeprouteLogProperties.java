package cn.deeproute.demo.system.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author test
 * @create 2022/2/27 21:40
 * @description
 */
@ConfigurationProperties(prefix = "deeproute.logging")
@Data
public class DeeprouteLogProperties {
    private boolean showSql = false;
    private String logDir;
    private String level = "info";
    private int maxLength = LogContants.DEFAULT_MAX_LENGTH;
    private int lineNum = LogContants.DEFAULT_LINE_NUM;
    private String consoleLogPattern = "%clr{%d{${LOG_DATEFORMAT_PATTERN}}} ${LOG_LEVEL_PATTERN} %X{userId} %X{traceId} %X{spanId} %clr{---}{faint} %clr{[%15.15t]}{faint} %clr{%-40.40c{1.}}{cyan} %clr{:}{faint} %ex{${sys:LOG_LINE_NUM}} %maxLen{%m}{${sys:LOG_MAX_LEN}}%n";
    private String fileLogPattern = "%d{${LOG_DATEFORMAT_PATTERN}} ${LOG_LEVEL_PATTERN} %X{userId} %X{traceId} %X{spanId} %.15t %c{1.} %maxLen{%m}{${sys:LOG_MAX_LEN}}%n%ex{${sys:LOG_LINE_NUM}}";

}
