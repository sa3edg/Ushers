package com.benchmark.ushers.presentation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController{
	
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public String handleException(HttpServletRequest request, Exception e) throws Exception{
		return "redirect:/error";
 
	}
}
