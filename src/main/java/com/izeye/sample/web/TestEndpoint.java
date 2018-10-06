package com.izeye.sample.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

/**
 * Test endpoint.
 *
 * @author Johnny Lim
 */
@Component
@Path("/hello")
public class TestEndpoint {

	@GET
	public String message() {
		return "Hello";
	}

}
