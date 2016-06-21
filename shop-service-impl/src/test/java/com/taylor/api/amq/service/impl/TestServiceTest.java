package com.taylor.api.amq.service.impl;

import org.junit.Test;

import com.taylor.api.amq.service.MessageSenderService;

public class TestServiceTest extends BaseServiceTest {
	@Test
	public void testSeviceTest() {
		MessageSenderService messageSenderService = springContext.getBean("sendQueueMessageImpl", MessageSenderService.class);
		messageSenderService.sendMessage("test");
	}

}
