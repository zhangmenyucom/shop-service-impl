package com.taylor.api.amq.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taylor.api.amq.service.MessageSenderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/spring-application.xml", "classpath:META-INF/spring/spring-servlet.xml" })
public class TopicMessageSenderTest {

	@Autowired
	@Qualifier("topicMessageSenderImpl")
	private MessageSenderService messageSenderService;

	@Test
	public void testSendMessage() {
		messageSenderService.sendMessage("test");
	}
}
