package functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AfterDate {
    public String informDate(String dat) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date toDay = (Date) df.parse(dat);
        Calendar cal = new GregorianCalendar();
        cal.setTime(toDay);
        cal.add(Calendar.DAY_OF_MONTH , +1);
        return df.format(cal.getTime());
    }
}
