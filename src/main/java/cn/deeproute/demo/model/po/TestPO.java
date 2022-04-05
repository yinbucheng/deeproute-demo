package cn.deeproute.demo.model.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yinchong
 * @create 2022/4/5 15:33
 * @description
 */
@Table(name = "test_table",schema = "deeproute")
@Data
public class TestPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    private String version;
    private String referCnt;
    private String remark;
}
