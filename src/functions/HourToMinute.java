package functions;
public class HourToMinute {
    public int getMinute(String begin, TimeDifference time){
        TimeDifference timeDifference = time;
        String[] vet = begin.split(":");
        int minutes = (Integer.parseInt(vet[0])*60) + Integer.parseInt(vet[1]);
        if(timeDifference.delay.equals("false")){
            return minutes * (-1);
        }
        else{
            return minutes;
        }
    }
}
