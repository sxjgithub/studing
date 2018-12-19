package sxj;

import java.util.ArrayList;
import java.util.List;

import sxj.* ;


class FiterChain {
	List<Fiter> fiters ;
	public FiterChain() {
		this.fiters = new ArrayList<>() ;
	}
	
	public void setFiters(List<Fiter> fiters) {
		this.fiters = fiters;
	}
	
	public List<Fiter> getFiters() {
		return fiters;
	}
	public void add(Fiter fiter){
		this.fiters.add(fiter) ;
	}
	
	public String dofiters(String msg){
		String str = msg ;
		for(Fiter fiter:fiters){
			str = fiter.doFiter(str) ;
		}
		return str ;
	}
	
}
