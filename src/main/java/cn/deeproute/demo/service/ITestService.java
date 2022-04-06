package cn.deeproute.demo.service;

import cn.deeproute.demo.model.dto.TestDTO;
import cn.deeproute.demo.model.vo.PageVO;
import cn.deeproute.demo.model.vo.TestVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yinchong
 * @create 2022/4/5 18:26
 * @description
 */
public interface ITestService {

    void save(TestVO vo);

    PageInfo<TestDTO> list(PageVO vo);

    void updateById(TestVO vo);
}
