package cn.deeproute.demo.utils;

import cn.deeproute.demo.model.dto.PageDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yinchong
 * @create 2022/4/5 22:35
 * @description
 */
public class PageUtils {

    public static <T> PageDTO<T> pageRt(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPageNum(pageInfo.getPageNum());
        pageDTO.setPageSize(pageInfo.getPageSize());
        pageDTO.setList(list);
        pageDTO.setTotal(pageInfo.getTotal());
        return pageDTO;
    }
}
