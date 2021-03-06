package br.com.season.springproject.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.entity.UserProfile;
import br.com.season.springproject.service.UserService;
import br.com.season.springproject.utils.UserProfileUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileUtils userProfileUtils;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    binder.registerCustomEditor(UserProfile.class, this.userProfileUtils);
	}
	
	@ModelAttribute("profiles")
    public List<UserProfile> initializeProfiles() {
        return userService.findAllRoles();
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUsers(ModelMap map){
		ModelAndView view = new ModelAndView("user");
		User user = new User();
		List<User> users = userService.findAll();
		map.addAttribute("user", user);
		map.addAttribute("users", users);
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(User user, ModelMap map, BindingResult result){
		userService.save(user);
		user = new User();
		map.addAttribute("user", user);
		map.addAttribute("users", userService.findAll());
		
		return "user";
	}
	
	@RequestMapping(value = "/{userId}", method=RequestMethod.PUT)
	public ModelAndView update(@PathVariable("userId") Integer userId, User user){
		ModelAndView view = new ModelAndView("redirect:/user");
		User foundUser = userService.findById(userId);
//		foundUser.setCpf(user.getCpf());
//		foundUser.setFirstName(user.getFirstName());
//		foundUser.setLastName(user.getLastName());
		
		BeanUtils.copyProperties(user, foundUser, "id");
		foundUser = userService.update(foundUser);
		view.addObject("user", foundUser);
		return view;
	}
	
	@RequestMapping(value = "/{userId}", method=RequestMethod.GET)
	public String getById(@PathVariable("userId") Integer userId, ModelMap map){
		User found = userService.findById(userId);
		List<User> list = userService.findAll();
		
		map.addAttribute("user", found);
		map.addAttribute("users", list);
		return "user";
	}
	
	@RequestMapping("/getCpf/{cpf}")
	public @ResponseBody User findByCpf(@PathVariable String cpf){
		return userService.findByCpf(cpf);
	}
	
	@RequestMapping("/find-by")
	public ModelAndView findBy(@RequestParam("lastName") String lastName,
			@RequestParam("firstName") String firstName,
			@RequestParam("cpf") String cpf,
			ModelMap map
			){
		ModelAndView view = new ModelAndView("user");
		List<User> users = userService.findBy(lastName, firstName, cpf);
		map.addAttribute("user", new User());
		map.addAttribute("users", users);
		
		return view;
	}
	
	@RequestMapping("/delete/{userId}")
	public String delete(@PathVariable("userId") Integer userId, ModelMap map){
		User found = userService.findById(userId);
		if(found != null){
			userService.delete(found);
		}
		map.addAttribute("user", new User());
		map.addAttribute("users", userService.findAll());
		return "user";
	}
	
	
	
	
	
	
	
}
