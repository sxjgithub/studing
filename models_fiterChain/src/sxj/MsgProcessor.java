package sxj;

import java.util.ArrayList;
import java.util.List;

import sxj.*;


class MsgProcessor {
	private String msg ;
	//private List<Fiter> listFiter = new ArrayList<Fiter>();
	private FiterChain fiterChain ;
	/*public MsgProcessor(String msg) {
		this.msg = msg ;
		
		//this.listFiter.add(new Html_fiter()) ;
		//this.listFiter.add(new Sentive_fiter()) ;
	}*/
	
	public void setFiterChain(FiterChain fiterChain) {
		this.fiterChain = fiterChain;
	}
	public FiterChain getFiterChain() {
		return fiterChain;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	
	public String process() {
		//String msg_processed = this.msg.replace("<", "[").replace(">", "]") ;
		//String msg_processed = this.Html_fiter(this.msg) ;
		String str = this.msg ;
		str = fiterChain.dofiters(str);
		
		return str;
	}
	
	/*
	public String html_fiter(String){
		this.msg.replace 
	}*/
}
