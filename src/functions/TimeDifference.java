package functions;
public class TimeDifference {
    public String getDifference(String begin, String end){
        String[] vetB = begin.split(":");
        String[] vetE = end.split(":");
        int minutesB = (Integer.parseInt(vetB[0])*60) + Integer.parseInt(vetB[1]);
        int minutesE = (Integer.parseInt(vetE[0])*60) + Integer.parseInt(vetE[1]);
        int dif = minutesE - minutesB;
        int hour = 0;
        while(dif>60){
            dif-=60;
            hour++;
        }
        return hour + ":" + dif;
    }
}
