package com.izeye.sample;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link Application} with mock web environment.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationWebEnvironmentMockTests {

	// NOTE: NPE expected in JettyConfig.jettyThreadPool().
	@Ignore
	@Test
	public void test() {
	}

}
