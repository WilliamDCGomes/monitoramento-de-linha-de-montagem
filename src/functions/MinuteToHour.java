package functions;
public class MinuteToHour {
    public String getHour(int time){
        int h = 0;
        while(time>=60){
            h+=1;
            time-=60;
        }
        if(h>9&&time>9){
            return h + ":" + time;
        }
        else if(time>9&&h<10){
            return "0" + h + ":" + time;
        }
        else if(h>9&&time<10){
            return h + ":0" + time;
        }
        else{
            return "0" + h + ":0" + time;
        }
    }
}
