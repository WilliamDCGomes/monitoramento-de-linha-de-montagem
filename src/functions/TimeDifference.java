package functions;
public class TimeDifference {
    public String delay;
    public String getDifference(String begin, String end){
        delay="false";
        String[] vetB = begin.split(":");
        String[] vetE = end.split(":");
        int minutesB = (Integer.parseInt(vetB[0])*60) + Integer.parseInt(vetB[1]);
        int minutesE = (Integer.parseInt(vetE[0])*60) + Integer.parseInt(vetE[1]);
        int dif = minutesE - minutesB;
        int hour = 0;
        if(dif<0){
            dif *= (-1);
            delay = "true";
        }
        else{
            delay = "false";
        }
        while(dif>=60){
            dif-=60;
            hour++;
        }
        if(hour>0&&hour<10&&dif>0&&dif<10){
            return "0" + hour + ":0" + dif;
        }
        if(hour>0&&hour<10&&dif==0){
            return "0" + hour + ":00";
        }
        if(hour==0&&dif>0&&dif<10){
            return "00:0" + dif;
        }
        if(hour==0&&dif==0){
            return "00:00";
        }
        if(hour>0&&hour<10){
            return "0" + hour + ":" + dif;
        }
        if(dif>0&&dif<10){
            return hour + ":0" + dif;
        }
        if(dif==0){
            return hour + ":00";
        }
        if(hour==0){
            return "00:" + dif;
        }
        return hour + ":" + dif;
    }
}
