package functions;
public class ComparableHour {
    public boolean compare(String more, String less, int time){
        String[] vectMore = more.split(":");
        String[] vectLess = less.split(":");
        int minute1 = (Integer.parseInt(vectMore[0])*60) + Integer.parseInt(vectMore[1]);
        int minute2 = (Integer.parseInt(vectLess[0])*60) + Integer.parseInt(vectLess[1]);
        if(minute1>=minute2&&time==1){
            return true;
        }
        else if(minute1>minute2&&time==2){
            return true;
        }
        else{
            return false;
        }
    }
}
