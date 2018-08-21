package com.legato.tomcat.logger.info.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, 
			HttpServletResponse response){
		String msg = request.getParameter("msg") != null ? request.getParameter("msg") : "";
		String username = request.getParameter("username");
		String password = request.getParameter("password"), action = request.getParameter("action");
		if(action != null && username!= null &&  password!= null){
			if(username.equals("admin") && password.equals("admin")){
				request.getSession().setAttribute("userContext", username);
				return new ModelAndView("redirect:/logger/home");
			}else{
				return new ModelAndView("index").addObject("actionUrl", "login").addObject("msg", "<h5 class='alert alert-danger'>Invalid Credentials</h5>");
			}
		}else if(action != null){
			msg = "Invalid Input Fields";
		}
		String logoff = request.getParameter("logoff");
		if(logoff != null && logoff.equals("logoff")){
			msg = "<h5 class='alert alert-success'>Logged out successfully</h5>";
			request.getSession().invalidate();
		}
		return new ModelAndView("index").addObject("actionUrl", "login").addObject("msg", msg);
	}
	
	@RequestMapping(value = "/logoff")
	public ModelAndView logoff(HttpServletRequest request, 
			HttpServletResponse response){
		request.getSession().invalidate();
		return new ModelAndView("redirect:/").addObject("action", "loggoff");
	}
}