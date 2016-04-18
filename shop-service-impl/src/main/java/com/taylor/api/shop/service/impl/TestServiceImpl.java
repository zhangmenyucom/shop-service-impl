package com.taylor.api.shop.service.impl;

import org.springframework.stereotype.Service;

import com.taylor.api.shop.entity.Test;
import com.taylor.api.shop.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

    @Override
    public void test() {
        System.out.println("hello world!");
    }

    /**
     * @desc getHello(测试)
     * @return
     * @author taylor
     * @date 2016年4月17日 下午7:33:56
     */
    @Override
    public String getHello() {
        return "hello world!";
    }

}
