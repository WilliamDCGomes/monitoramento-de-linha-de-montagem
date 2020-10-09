package functions;
public class MoreThan24Hour {
    public boolean check(String time){
        String[] vect = time.split(":");
        int hour = Integer.parseInt(vect[0]);
        int minute = Integer.parseInt(vect[1]);
        if(hour==24&&minute>0){
            return true;
        }
        else if(hour>24){
            return true;
        }
        else if(minute>59){
            return true;
        }
        else if(hour<0||minute<0){
            return true;
        }
        else{
            return false;
        }
    }
}
