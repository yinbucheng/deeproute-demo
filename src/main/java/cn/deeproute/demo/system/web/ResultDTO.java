package cn.deeproute.demo.system.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author test
 * @create 2019/11/11 17:36
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO implements Serializable {
    private int code;
    private String message;
    private Object data;
    private String reqId;

    public static ResultDTO success(Object data, String message) {
        return new ResultDTO(200, message, data,null);
    }

    public static ResultDTO success(Object data) {
        return success(data, "执行成功");
    }

    public static ResultDTO success() {
        return successWithMsg("执行成功");
    }

    public static ResultDTO successWithMsg(String message) {
        return success(null, message);
    }

    public static ResultDTO fail(int code, String message) {
        return new ResultDTO(code, message, null,null);
    }

    public static ResultDTO fail(BizRuntimeException error) {
        return fail(error.getCode(), error.getMessage());
    }

    public static ResultDTO fail(String msg){
        return fail(-1,msg);
    }

    public static ResultDTO error(String message, String detail) {
        return new ResultDTO(-1, message, detail,null);
    }
}