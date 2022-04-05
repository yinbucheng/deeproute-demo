package cn.deeproute.demo.system.web;

import org.slf4j.MDC;

public class TraceUtils {
    public static String getReqId() {
        return MDC.get("traceId");
    }
}