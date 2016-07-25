package com.taylor.api.amq.service.impl;

import org.junit.Test;

import com.taylor.api.amq.service.MessageSenderService;

public class MessageSenderServiceTest extends BaseServiceTest {
	@Test
	public void testSeviceTest() {
		MessageSenderService messageSenderService = springContext.getBean("queueMessageSenderImpl", MessageSenderService.class);
		messageSenderService.sendMessage("test");
	}

}
