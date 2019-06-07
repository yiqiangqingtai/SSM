package com.yuntu.dpm.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;



public class DateFormatConv implements Converter<String, Date>{

	public Date convert(String source) {
		// TODO Auto-generated method stub
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            return sdf.parse(source);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        } catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		return null;
	}

	
	  

}
