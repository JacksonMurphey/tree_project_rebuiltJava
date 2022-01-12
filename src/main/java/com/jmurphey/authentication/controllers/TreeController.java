package com.jmurphey.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jmurphey.authentication.models.Tree;
import com.jmurphey.authentication.models.User;
import com.jmurphey.authentication.services.TreeService;
import com.jmurphey.authentication.services.UserService;


@Controller
@RequestMapping("/tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	
	
// --- Create Tree Mapping ---
	
	@GetMapping("/new")
	public String newTree(@ModelAttribute("tree")Tree tree, HttpSession session, Model model) {
		Long userId = userController.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			return "newTree.jsp";
		}
	}
	
	
	@PostMapping("/new")
	public String createTree(@Valid @ModelAttribute("tree")Tree tree, BindingResult result) {
		if(result.hasErrors()) {
			return "newTree.jsp";
		} else {
			treeService.createTree(tree);
			return "redirect:/dashboard";
		}
	}
	
	
// --- Edit Tree Mapping ---
	
	@GetMapping("/edit/{id}")
	public String editTree(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = userController.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("user", userService.findUserById(userId));
			model.addAttribute("tree", treeService.getTreeById(id));
			return "editTree.jsp";
		}
	}
	
	@PutMapping("/{id}")
	public String udpate(@Valid @ModelAttribute("tree")Tree tree, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			redirect.addFlashAttribute("error", "No Field Can Be Blank");
			return "redirect:/tree/edit/" + tree.getId();
		} else {
			treeService.updateTree(tree);
			return "redirect:/mytrees";
		}
	}
	
// --- View Tree/Visit Tree Mapping --- 
	
	@GetMapping("/show/{id}")
	public String showTree(@PathVariable("id")Long id, Model model, HttpSession session ){
		Long userId = userController.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("tree", treeService.getTreeById(id));
			return "showTree.jsp";
		}
	}
	
//	@PostMapping("/show/addvisit/{id}")
//	public String addVisitorToTree(@RequestParam("visitor")Long id, @PathVariable("id")Long treeId) {
//		Tree tree = treeService.getTreeById(treeId);
//		User visitor = userService.findUserById(id);
//		treeService.addToUsersVisited(visitor, tree);
//		return String.format("redirect:/show/%s", treeId);
//	}
	
	@GetMapping("/{id}/join/{visitor}")
	public String addVisitorToTree(@PathVariable("id")Long id, @PathVariable("visitor")Long visitorId, HttpSession session) {
		Long userId = userController.userSessionId(session);
		Tree tree = treeService.getTreeById(id);
		if(userId == null) {
			return "redirect:/";
		} else {
			User visitorUser = userService.findUserById(visitorId);
			treeService.addToUsersVisited(visitorUser, tree);
			return String.format("redirect:/tree/show/%s", tree.getId());
		}
		
	}
	
	@GetMapping("/{id}/leave/{visitor}")
	public String removeVisitor(@PathVariable("id")Long id, @PathVariable("visitor")Long visitorId, HttpSession session) {
		Long userId = userController.userSessionId(session);
		Tree tree = treeService.getTreeById(id);
		if(userId == null) {
			return "redirect:/";
		} else {
			User visitorUser = userService.findUserById(visitorId);
			treeService.removeUserVisit(visitorUser, tree);
			return "redirect:/dashboard";
		}
	}
	
	
	
// --- Delete Tree ---
	
	@DeleteMapping("/delete/{id}")
	public String deleteTree(@PathVariable("id")Long id) {
		treeService.deleteTree(id);
		return "redirect:/dashboard";
	}
	
}
