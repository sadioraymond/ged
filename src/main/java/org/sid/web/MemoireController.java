package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoireController {
	@RequestMapping("/acceuil")
	public String acceuil() {
		return "acceuil";
	}

}
