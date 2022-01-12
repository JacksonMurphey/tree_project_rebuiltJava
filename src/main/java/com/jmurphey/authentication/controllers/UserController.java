package com.jmurphey.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jmurphey.authentication.models.User;
import com.jmurphey.authentication.services.TreeService;
import com.jmurphey.authentication.services.UserService;
import com.jmurphey.authentication.validators.UserValidator;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private TreeService treeService;
	
	
	
// --- Login and Registration GET/POST Methods ---
	
	
	@GetMapping("")
	public String index(@ModelAttribute("user")User user) {
		return "loginRegistration.jsp";
	}
	
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "loginRegistration.jsp";
		} else {
			User newUser = userService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session, RedirectAttributes redirect) {
		if(userService.authenticateUser(email, password)) {
			User user = userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		} else {
			redirect.addFlashAttribute("error", "Invalid Email or Password");
			return "redirect:/";
		}
	}
	


	
// --- Below Routes Require User to be logged-in / in-session ---
	
	
	// Method to Call to check if User is in Session
	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return null;
		} else {
			return (Long)session.getAttribute("userId");
		}
	}
	
	
	@GetMapping("/dashboard")
	public String dash(HttpSession session, Model model) {
		Long userId = userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("trees", treeService.allTrees());
			return "dashboard.jsp";
		}
	}
	
	
	@GetMapping("/mytrees")
	public String userTrees(HttpSession session, Model model) {
		Long userId = userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("user", userService.findUserById(userId));
			return "myTrees.jsp";
		}
	}
	

	
// --- Logout User --- 	

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
