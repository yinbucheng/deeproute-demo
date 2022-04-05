package cn.deeproute.demo.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yinchong
 * @create 2022/4/5 15:33
 * @description
 */
@Data
public class TestVO implements Serializable {
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "副标题不能为空")
    private String subTitle;
}
