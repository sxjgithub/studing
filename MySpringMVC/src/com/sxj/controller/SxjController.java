package com.sxj.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxj.annotatioin.EnjoyAutoWired;
import com.sxj.annotatioin.EnjoyController;
import com.sxj.annotatioin.EnjoyRequestMapping;
import com.sxj.annotatioin.EnjoyRequestParam;
import com.sxj.service.SxjService;

@EnjoyController
@EnjoyRequestMapping("/sxj1")
public class SxjController {
	
	@EnjoyAutoWired("SxjServiceImpl")
	private SxjService sxjService;
	    
	@EnjoyRequestMapping("/query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			@EnjoyRequestParam("name") String name, 
			@EnjoyRequestParam("age") String age){
		
		try{
			PrintWriter pw = response.getWriter();
			String result = sxjService.query(name, age);
			pw.write(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
