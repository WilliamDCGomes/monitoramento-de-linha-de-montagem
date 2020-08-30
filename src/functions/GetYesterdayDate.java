package functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetYesterdayDate {
    public String informDate(){
        Date toDay = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = new GregorianCalendar();
        cal.setTime(toDay);
        cal.add(Calendar.DAY_OF_MONTH , -1);
        return df.format(cal.getTime());
    }
}
