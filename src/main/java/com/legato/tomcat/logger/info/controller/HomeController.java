package com.legato.tomcat.logger.info.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView view = new ModelAndView("index");
		String action = (request.getAttribute("action") != null && request.getAttribute("action") instanceof String) ? (String)request.getAttribute("action") : "";
		if(action.equals("loggoff")){
			view.addObject("msg", "Logged out successfully.");
		}
		view.addObject("actionUrl", "login");
		return view;
	}
}