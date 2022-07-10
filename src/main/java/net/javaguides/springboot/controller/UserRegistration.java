package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.CustomUserService;

@RestController

public class UserRegistration {
	
	
	private CustomUserService userService;

	public UserRegistration(CustomUserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }
	
	@RequestMapping("/registration")
	  public ModelAndView getWelcomePageAsModel() {
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("registration.html");
	      return modelAndView;
	  }
	
	@PostMapping("/saveuser")
	public String registerUserAccount(@ModelAttribute ("user") User user) {
		userService.newUser(user);
		return "redirect:/registration?success";
	}
}
