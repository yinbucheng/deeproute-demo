package cn.deeproute.demo.system.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author test
 * @create 2019/11/11 18:04
 * @description
 */
@Slf4j
@ControllerAdvice
public class GlobalErrorControllerAdvice {
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;


    /**
     * 处理业务异常错误
     * @param e
     * @return
     */
    @ExceptionHandler({BizRuntimeException.class})
    @ResponseBody
    public ResultDTO handleCodeException(BizRuntimeException e) {
        log.warn("biz exception happen:code:{} msg:{}", e.getCode(), e.getMessage());
        return ResultDTO.fail(e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultDTO handleNotValidException(MethodArgumentNotValidException e) {
        return processBindingResult(e.getBindingResult());
    }

    /**
     * 处理请求URL错误异常
     * @param e
     * @return
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    public ResultDTO handleNoHandlerFoundException(NoHandlerFoundException e){
        log.warn("请求URL地址错误:{}",e.getLocalizedMessage());
        return ResultDTO.fail("请求URL地址错误");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResultDTO handleMethodArgumentTypeMisMatch(MethodArgumentTypeMismatchException e) {
        log.warn("请求参数格式错误:{}", e.getName());
        return ResultDTO.fail("请求参数格式错误");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultDTO handleMissParam(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数:{}", e.getParameterName());
        return ResultDTO.fail("缺少请求参数");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultDTO handleMethodNotSupport(HttpRequestMethodNotSupportedException ex) {
        String method = ex.getMethod();
        return ResultDTO.fail(String.format("不支持%s请求方式", method));
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResultDTO handleBindException(BindException e) {
        return processBindingResult(e.getBindingResult());
    }

    @ExceptionHandler({UndeclaredThrowableException.class, InvocationTargetException.class})
    @ResponseBody
    public Object handleProxyException(Exception ex, HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        Throwable realException = ExceptionUtils.getRealException(ex);
        if (realException instanceof Exception) {
            return handlerExceptionResolver.resolveException(request, response, handlerMethod, (Exception) realException);
        } else {
            return handleThrowable(realException);
        }
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResultDTO handleMessageException(HttpMessageNotReadableException e) {
        log.warn("请求体格式错误:{}", e.getLocalizedMessage());
        return ResultDTO.fail("请求体格式错误");
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    @ResponseBody
    public ResultDTO handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        String headerName = e.getHeaderName();
        log.warn("缺少请求头{}", headerName);
        return ResultDTO.fail(String.format("缺少请求头%s", headerName));
    }

    @ExceptionHandler
    public void handleEofException(EOFException e) {
        log.warn("EOFException happen:", e);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public Object handleThrowable(Throwable ex) {
        log.error("internal error happen", ex);
        return ResultDTO.fail("网络异常");
    }


    ResultDTO processBindingResult(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        String str = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return ResultDTO.fail(str);
    }
}