package cn.deeproute.demo.service.impl;

import cn.deeproute.demo.mapper.TestMapper;
import cn.deeproute.demo.model.dto.TestDTO;
import cn.deeproute.demo.model.po.TestPO;
import cn.deeproute.demo.model.vo.PageVO;
import cn.deeproute.demo.model.vo.TestVO;
import cn.deeproute.demo.service.ITestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yinchong
 * @create 2022/4/5 18:26
 * @description
 */
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TestVO vo) {
        TestPO po = new TestPO();
        BeanUtils.copyProperties(vo, po);
        testMapper.insertSelective(po);
    }

    @Override
    public PageInfo<TestDTO> list(PageVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<TestDTO> list = testMapper.list(vo);
        PageInfo<TestDTO> page = new PageInfo<>(list);
        return page;
    }
}
