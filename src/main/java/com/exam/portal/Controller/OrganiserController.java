package com.exam.portal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.portal.Model.Organiser;
import com.exam.portal.Repository.OrganiserRepository;

@Controller
public class OrganiserController {

	@Autowired
	OrganiserRepository repo;

	public static String LOGIN_ROUTE="redirect:/organiser/login";

	@GetMapping("organiser/register")
	public String register(Model model) {
		model.addAttribute("organiser",new Organiser());
		return "organiser/register";
	}
	
	@PostMapping("organiser/register")
	public String registerPost(Organiser org) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPassword=encoder.encode(org.getPassword());
		org.setPassword(encodedPassword);
		repo.save(org);
		return "redirect:/organiser/dashboard";
	}
	
	@GetMapping("organiser/login")
	public String login() {
		return "organiser/login";
	}
	
	@GetMapping("organiser/dashboard")
	public String dashboard() {
		return "organiser/dashboard";
	}
}
