package com.course.server.service;

import com.course.server.domain.Test;
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

    public Test list(){
        return testMapper.selectByPrimaryKey("1");
    }
}
