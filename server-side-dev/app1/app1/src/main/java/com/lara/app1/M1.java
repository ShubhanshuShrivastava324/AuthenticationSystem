package com.lara.app1;

//	How we can verify email id?
public class M1 {
	public static void main(String[] args)
	{
	
	String str = 
	"abcderijesiefjfiejSEWEFIEREGEEIFKEK95413544";
		
	String token = "";
	for (int i = 1; i <= 5; i++) 
	{
		token += str.charAt((int)(str.length()*Math.random()));
	//Math.random generate double number between zero to one
	//it generate diff-diff number in compilation
	}
	
	System.out.println(token);
		
	}
}
