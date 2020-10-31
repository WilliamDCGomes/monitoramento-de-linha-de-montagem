package functions;
import screens.WorkerScreen;
public class BarProgress {
    WorkerScreen workerScreen;
    GetHour getHour = new GetHour();
    HourToMinute hourToMinute = new HourToMinute();
    public BarProgress(WorkerScreen worker){
        workerScreen = worker;
    }
    public void setBar(String begin, String end){
        int beginMinutes = hourToMinute.getMinute(begin);
        int endMinutes = hourToMinute.getMinute(end);
        int now = hourToMinute.getMinute(getHour.informHour());
        while(beginMinutes>0){
            beginMinutes-=1;
            endMinutes-=1;
            now-=1;
        }
        int percentage = (100 * now) / endMinutes;
        workerScreen.outputBarTime.setValue(percentage);
    }
}
