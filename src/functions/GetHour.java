package functions;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class GetHour {
    public String informHour(){
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        if(calendar.get(Calendar.MINUTE)<10&&calendar.get(Calendar.HOUR_OF_DAY)<10){
            return "0" + calendar.get(Calendar.HOUR_OF_DAY) + ":0" + calendar.get(Calendar.MINUTE);
        }
        if(calendar.get(Calendar.MINUTE)<10){
            return calendar.get(Calendar.HOUR_OF_DAY) + ":0" + calendar.get(Calendar.MINUTE);
        }
        if(calendar.get(Calendar.HOUR_OF_DAY)<10){
            return "0" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        }
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }
}
