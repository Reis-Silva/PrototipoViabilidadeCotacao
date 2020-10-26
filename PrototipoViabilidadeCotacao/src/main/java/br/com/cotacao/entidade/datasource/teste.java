package br.com.cotacao.entidade.datasource;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class teste {
	
	public static void main(String[] args) {
		
		long time =  System.currentTimeMillis();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time);
		System.out.println(cal);
		
		
		Calendar calendar = new GregorianCalendar();
		
		 System.out.println("Hora: " + calendar.get(Calendar.DAY_OF_MONTH));
		 System.out.println("Minuto: " + calendar.get((Calendar.MONTH)));
		 System.out.println("Segundo: " + calendar.get(Calendar.YEAR));
		 int teste = calendar.get((Calendar.MONTH))+1;
		 System.out.println(teste);
	}

}
