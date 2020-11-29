package functions;

/**
 *
 * @author willi
 */
public class MaskJustNumbersAux {
    public String getString(String letter){
        if(letter.equals("0")){
            return "0";
        }
        else if(letter.equals("1")){
            return "1";
        }
        else if(letter.equals("2")){
            return "2";
        }
        else if(letter.equals("3")){
            return "3";
        }
        else if(letter.equals("4")){
            return "4";
        }
        else if(letter.equals("5")){
            return "5";
        }
        else if(letter.equals("6")){
            return "6";
        }
        else if(letter.equals("7")){
            return "7";
        }
        else if(letter.equals("8")){
            return "8";
        }
        else if(letter.equals("9")){
            return "9";
        }
        else if(letter.equals(".")){
            return ".";
        }
        else{
            return "";
        }
    }
}
