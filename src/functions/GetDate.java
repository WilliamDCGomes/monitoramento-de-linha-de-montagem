package functions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {
    public String informDate(){
        Date toDay = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(toDay);
    }
}
