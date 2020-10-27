package br.com.cotacao.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	
	public String todayAsString() {
		Date today = new Date();
		
		return dateAsString(today);	 
	}

	
	public String dateAsString (Date data) {
		String formato = "MM-dd-yyyy";
		DateFormat df = new SimpleDateFormat(formato);
		String dataAsString = df.format(data);
		
		return dataAsString;
	}
}
