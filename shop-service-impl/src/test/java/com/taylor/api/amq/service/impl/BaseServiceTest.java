package com.taylor.api.amq.service.impl;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseServiceTest {

	static ClassPathXmlApplicationContext springContext = null;

	@Before
	public void setUp() throws Exception {
		springContext = new ClassPathXmlApplicationContext(new String[] { "META-INF/spring/spring-application.xml", "META-INF/spring/spring-servlet.xml" });
	}

	@After
	public void tearDown() throws Exception {
		springContext = null;

	}

}
