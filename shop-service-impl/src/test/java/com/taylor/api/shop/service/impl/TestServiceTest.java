package com.taylor.api.shop.service.impl;

import org.junit.Test;

import com.taylor.api.shop.service.TestService;

public class TestServiceTest extends BaseServiceTest {
    @Test
    public void testSeviceTest() {
        TestService test = springContext.getBean("testServiceImpl", TestService.class);
        test.test();
    }

}
