package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JT
 */
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }

    public List<Test> selectOneTest(){
        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo("1");
        return testMapper.selectByExample(testExample);
    }
}
