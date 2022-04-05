package cn.deeproute.demo.model.dto;

import lombok.Data;

/**
 * @author yinchong
 * @create 2022/4/5 15:34
 * @description
 */
@Data
public class TestDTO {
    private Integer id;
    private String title;
    private String subTitle;
    private Integer referCnt;
    private String remark;
}
