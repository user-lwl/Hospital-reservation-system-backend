package com.lwl.hospital.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @author user-lwl
 *
 */
@RestController
public class IndexController {

	private final String LIST_INDEX = "redirect:/";

	private static final String PAGE_INDEX  = "frame/index";
	private static final String PAGE_MAIN  = "frame/main";
	private static final String PAGE_LOGIN  = "frame/login";
	private static final String PAGE_AUTH = "frame/auth";
	
	/**
	 * 框架首页
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(ModelMap model, HttpServletRequest request) {

		return PAGE_INDEX;
	}

	/**
	 * 框架主页
	 * @return
	 */
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String main() {

		return PAGE_MAIN;
	}

	/**
	 * 框架主页
	 *
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String auth() {
		return PAGE_AUTH;
	}

	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {

		return PAGE_LOGIN;
	}

}

