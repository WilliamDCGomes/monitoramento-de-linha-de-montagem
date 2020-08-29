package functions;
public class CheckInputDailyPlanning {
    public boolean checkValidation(String beginer, String finish){
        String []vetBegin = beginer.split(":");
        int [] begin = new int [2];
        begin[0] = Integer.parseInt(vetBegin[0]);
        begin[1] = Integer.parseInt(vetBegin[1]);
        String []vetEnd = finish.split(":");
        int [] end = new int [2];
        end[0] = Integer.parseInt(vetEnd[0]);
        end[1] = Integer.parseInt(vetEnd[1]);
        if((end[0]==begin[0]&&end[1]>begin[1])||(end[0]>begin[0])){
            return true;
        }
        else{
            return false;
        }
    }
}
