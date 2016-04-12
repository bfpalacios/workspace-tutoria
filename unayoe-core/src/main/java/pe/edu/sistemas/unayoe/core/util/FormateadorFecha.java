package pe.edu.sistemas.unayoe.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormateadorFecha {
	
	public  String formatoFechaDDMMAAAA(Date date) {
		String convertido = "";
		
		if (date.compareTo(new Date(Long.MIN_VALUE)) == 0){
			return convertido;
		}
		else{
			String formato = "dd/MM/yyyy";

			DateFormat fecha = new SimpleDateFormat(formato);
			convertido = fecha.format(date);
			System.out.println(convertido);
		    return convertido;
		}
	}
	
	public static  int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);		
	}
}
