package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.model.Leave;
import net.javaguides.springboot.service.LeaveService;
import net.javaguides.springboot.service.LeaveServiceV;



@Controller
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	
	@Autowired
	private LeaveServiceV service;
	
	@GetMapping("/leaves")
	public String viewHomePage(Model model) {
		return findPaginated1(1, "empid", "asc", model);		
	}
	
	@GetMapping("/showNewLeaveForm")
	public String showNewLeaveForm(Model model) {
		// create model attribute to bind form data
		Leave leave = new Leave();
		model.addAttribute("leave", leave);
		return "add_leaves";
	}
	
	@PostMapping("/saveLeave")
	public String saveLeave(@ModelAttribute("leave") Leave leave) {
		// save employee to database
		leaveService.saveLeave(leave);
		return "redirect:/leaves";
	}
	
	@GetMapping("/showUpdate/{empid}")
	public String showUpdate(@PathVariable long empid, Model model) {
		
		// get employee from the service
		List<Leave> leaves = service.getAllByID(empid);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("leaves", leaves);
		return "leaves";
	}
	
	@GetMapping("/deleteLeave/{empid}")
	public void deleteEmployee(@PathVariable (value = "empid") long empid) {
		
		// call delete employee method 
		leaveService.deleteLeaveById(empid);
	
	}
	
	@GetMapping({"/list"})
	public ModelAndView getAllleaves() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("leaves",leaveService.getAllLeaves());
		return mav;	
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated1(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Leave> page = leaveService.findPaginated1(pageNo, pageSize, sortField, sortDir);
		List<Leave> listLeaves = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listleaves", listLeaves);
		return "leaves";
	}
	

}
