package cn.deeproute.demo.system.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author test
 * @create 2022/2/27 19:28
 * @description
 */
public class ExceptionUtils {

    public static Throwable getRealException(Throwable throwable) {
        if (throwable instanceof UndeclaredThrowableException) {
            return getRealException((UndeclaredThrowableException) throwable);
        } else if (throwable instanceof InvocationTargetException) {
            return getRealException((InvocationTargetException) throwable);
        }
        return throwable;
    }


    public static Throwable getRealException(UndeclaredThrowableException ex) {
        return getRealException(ex.getUndeclaredThrowable());
    }

    public static Throwable getRealException(InvocationTargetException ex) {
        return getRealException(ex.getTargetException());
    }
}