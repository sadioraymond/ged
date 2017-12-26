package org.sid.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	@RequestMapping(value="/")
	public String home() {
		return "redirect:/acceuil";
	}
	@RequestMapping(value="/403")
	public String accesDenied() {
		return "403";
	}
}
