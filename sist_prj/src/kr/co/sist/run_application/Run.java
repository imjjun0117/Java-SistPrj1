package kr.co.sist.run_application;

import kr.co.sist.log.LogIn;

public class Run {
	
	LogIn log ;
	public Run(LogIn log) {
		this.log=log;
	}
	

	public static void main(String[] args) {
		new Run(new LogIn());
	
	}//main

}//class
