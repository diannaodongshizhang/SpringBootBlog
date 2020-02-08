package com.yrx.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//异常拦截
@ControllerAdvice
public class ControllerExceptionHandler {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	//拦截所有Exception
	@ExceptionHandler(Exception.class)
	//ModelAndView 控制页面 返回一些信息
	public ModelAndView exceptionHander(HttpServletRequest request,Exception e) throws Exception {
		logger.error("Requst URL:{},Exception:{}",request.getRequestURL(),e);
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=null) {
			throw e;
		}
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("url",request.getRequestURL());
		mv.addObject("exception",e);
		mv.setViewName("error/error");
		 return mv;
	}
}
