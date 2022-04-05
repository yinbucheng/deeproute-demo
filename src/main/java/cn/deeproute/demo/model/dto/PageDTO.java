package cn.deeproute.demo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author yinchong
 * @create 2022/4/5 21:15
 * @description
 */
@Data
public class PageDTO<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<T> list;
}
