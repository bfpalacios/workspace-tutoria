package pe.edu.sistemas.unayoe.core.util;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alexh
 * @version 0.0.1
 *
 */
@Component
public class Filter {
    public String date(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public String dayOfWeekText(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("u"); // 1..7
        String day = simpleDateFormat.format(date);

        if ("1".equals(day)) return "LUNES";
        else if ("2".equals(day)) return "MARTES";
        else if ("3".equals(day)) return "MIERCOLES";
        else if ("4".equals(day)) return "JUEVES";
        else if ("5".equals(day)) return "VIERNES";
        else if ("6".equals(day)) return "SABADO";
        else if ("7".equals(day)) return  "DOMINGO";
        return null;
    }



}
