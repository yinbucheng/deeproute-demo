package cn.deeproute.demo.mapper;

import cn.deeproute.demo.model.dto.TestDTO;
import cn.deeproute.demo.model.po.TestPO;
import cn.deeproute.demo.model.vo.PageVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TestMapper extends Mapper<TestPO> {
    List<TestDTO> list(PageVO vo);
}
