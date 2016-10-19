package com.ttnd.todo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoUtil {

	 public Date ChangeDateFormate(Date date2){
		
		 try{
			 		String dateStr = "21/08/2011";
		             System.out.println("------dat2---"+date2);
			 		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			 		dateStr= formatter.format(date2);
			 		Date date = formatter.parse(dateStr);
			 		//date2 = formatter.format(date2);
			 		System.out.println("----date--"+date);
		             System.out.println("Converted date is : " + date2);
		             System.out.println("Converted date is : " + dateStr);
		             
		
		 }catch(Exception e){
			 System.out.println("exception-----");
		 }
	    	return null;
	    }
}
