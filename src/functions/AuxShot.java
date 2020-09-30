package functions;

import java.util.ArrayList;

public class AuxShot {
    ArrayList<String> list = new ArrayList<String>();
    public int actual = 0;
    public int time(String begin, String time){
        String[]vectb = begin.split(":");
        String[]vectt = time.split(":");
        int b = Integer.parseInt(vectb[0])*60;
        b += Integer.parseInt(vectb[1]);
        int t = Integer.parseInt(vectt[0])*60;
        t += Integer.parseInt(vectt[1]);
        int hour = 0;
        b+=t;
        int fullTime = b;
        while(b>=60){
            hour+=1;
            b-=60;
        }
        String finish = hour + ":" + b;
        list.add(begin + ";" + finish);
        return fullTime;
    }
}
