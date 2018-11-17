package com.izeye.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for login page.
 *
 * @author Johnny Lim
 */
@Controller
public class LoginPageController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
