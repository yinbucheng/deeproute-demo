package cn.deeproute.demo.system.time;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author 尹冲
 * @date 2022/4/1 下午6:35
 * @desc
 */
@Component
@Aspect
@Slf4j
public class DeeprouteLogAspect {

    @Before("@annotation(args)")
    public void beforeMethodInvoke(JoinPoint point, PrintArgs args) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        log.info("{} args:{}", signature.toString(), point.getArgs());
    }

    @Around("@annotation(time)")
    public Object aroundMethodInvoke(ProceedingJoinPoint point, PrintTime time) {
        StopWatch stopWatch = new StopWatch();
        MethodSignature signature = (MethodSignature) point.getSignature();
        try {
            stopWatch.start();
            return point.proceed();
        } catch (Throwable e) {
            log.error("methodInvoke around fail");
            throw new RuntimeException(e);
        } finally {
            stopWatch.stop();
            log.info("{} use {} time", signature.toString(), stopWatch.getTotalTimeMillis());
        }

    }

    @Around("@annotation(rt)")
    public Object afterMethod(ProceedingJoinPoint point, PrintRT rt) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        try {
            Object rtVal = point.proceed();
            log.info("{} rt:{}", signature.toString(), rtVal);
            return rtVal;
        } catch (Throwable throwable) {
            log.error("{} execute fail,error detail:", signature.toString(), throwable);
            throw new RuntimeException(throwable);
        }
    }

}
