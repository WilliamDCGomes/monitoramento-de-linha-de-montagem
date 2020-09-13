package functions;
public class HourToMinute {
    public int getMinute(String begin){
        TimeDifference timeDifference = new TimeDifference();
        String[] vet = begin.split(":");
        int minutes = (Integer.parseInt(vet[0])*60) + Integer.parseInt(vet[1]);
        if(timeDifference.delay=="true"){
            return minutes * (-1);
        }
        else{
            return minutes;
        }
    }
}
