package cn.deeproute.demo.system.log;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chongyin
 */
@Component
public class DeeprouteUserFilter implements Filter, Ordered {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String userName = req.getHeader("userId");
            if (Strings.isNotBlank(userName)) {
                MDC.put("userId", userName);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove("userId");
        }
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
