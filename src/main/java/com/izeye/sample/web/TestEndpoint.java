package com.izeye.sample.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	@Path("/{name}")
	public String message(@PathParam("name") String name) {
		return "Hello, " + name + "!";
	}

}
