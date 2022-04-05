package cn.deeproute.demo.system.web;

import lombok.Data;

/**
 * @author test
 * @create 2019/11/11 17:42
 * @description
 */
@Data
public class BizRuntimeException extends RuntimeException {
    private int code;
    private String message;

    public BizRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizRuntimeException(BusinessError error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public BizRuntimeException(BusinessError error, String message) {
        this.code = error.getCode();
        this.message = message;
    }

}