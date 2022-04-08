package cn.deeproute.demo.system.web;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yinchong
 * @create 2019/11/11 17:54
 * @description
 */
@Slf4j
@ControllerAdvice
public class ResultDTOBodyAdvice implements ResponseBodyAdvice, ApplicationContextAware {

    private Set<String> excludeClassSet;

    public void addExcludeClass(String className) {
        if (excludeClassSet == null) {
            excludeClassSet = new HashSet<>();
        }
        excludeClassSet.add(className);
    }

    public void addAllExcludeClass(List<String> excludeClass) {
        if (excludeClassSet == null) {
            excludeClassSet = new HashSet<>();
        }
        excludeClassSet.addAll(excludeClass);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (!converterSupports(aClass)) {
            return false;
        }
        if (excludeClassSet == null) {
            return true;
        }
        String name = methodParameter.getDeclaringClass().getName();
        return !excludeClassSet.contains(name);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body == null) {
            return ResultDTO.success();
        } else if (body instanceof ResultDTO) {
            return handleResultDTO((ResultDTO) body, serverHttpRequest);
        } else if (body instanceof File) {
            return body;
        } else if (StringHttpMessageConverter.class.isAssignableFrom(aClass)) {
            ResultDTO dto = ResultDTO.success(body);
            return JSON.toJSONString(handleResultDTO(dto, serverHttpRequest));
        } else {
            ResultDTO dto = ResultDTO.success(body);
            return handleResultDTO(dto, serverHttpRequest);
        }
    }

    private ResultDTO handleResultDTO(ResultDTO dto, ServerHttpRequest request) {
        dto.setReqId(TraceUtils.getReqId());
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            servletRequest.setAttribute("resultCode", dto.getCode());
        }
        return dto;
    }


    private boolean converterSupports(Class<? extends HttpMessageConverter<?>> aClass) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass)
                ||
                StringHttpMessageConverter.class.isAssignableFrom(aClass);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(NotAutoWrap.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Class<?> userClass = ClassUtils.getUserClass(entry.getValue().getClass());
            addExcludeClass(userClass.getName());
        }
    }
}