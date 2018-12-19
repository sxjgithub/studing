package com.sxj.service.impl;

import com.sxj.annotatioin.EnjoyService;
import com.sxj.service.SxjService;

@EnjoyService("SxjServiceImpl")
public class SxjServiceImpl implements SxjService{

	@Override
	public String query(String name, String age) {
		
		return "{name=" + name + "age=" + age + "}";
	}

	@Override
	public String insert(String param) {
		// TODO Auto-generated method stub
		return "insert successful....";
	}

	@Override
	public String update(String param) {
		// TODO Auto-generated method stub
		return "update successful....";
	}
	
}
