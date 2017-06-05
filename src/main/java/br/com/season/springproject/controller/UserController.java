package br.com.season.springproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.season.springproject.entity.User;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();

	@RequestMapping("/users")
	public ModelAndView getUsers(ModelMap map){
		ModelAndView view = new ModelAndView("user");
		User user = new User();
		map.addAttribute("user", user);
		map.addAttribute("users", users);
		return view;
	}
	
	@RequestMapping(value = "/user/save")
	public String save(User user, ModelMap map){
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getCpf());
		users.add(user);
		user = new User();
		map.addAttribute("user", user);
		map.addAttribute("users", users);
		
		return "user";
	}
}
