package pe.edu.sistemas.unayoe.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class FormateadorFecha.
 */
public class FormateadorFecha {
	
	/**
	 * Formato fecha DDMMAAAA.
	 *
	 * @param date the date
	 * @return the string
	 */
	public  String formatoFechaDDMMAAAA(Date date) {
		String convertido = "";
		
		if (date.compareTo(new Date(Long.MIN_VALUE)) == 0){
			return convertido;
		}
		else{
			String formato = "dd/MM/yyyy";

			DateFormat fecha = new SimpleDateFormat(formato);
			convertido = fecha.format(date);
			System.out.println("GG"+convertido);
		    return convertido;
		}
	}
	
	/**
	 * Gets the day of the week.
	 *
	 * @param d the d
	 * @return the day of the week
	 */
	public static  int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);		
	}
}
