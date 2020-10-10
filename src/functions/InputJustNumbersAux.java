package functions;


/**
 *
 * @author willi
 */
public class InputJustNumbersAux{
    public String makeUp(String letter){
        if(letter.equals("0")||letter.equals("1")||letter.equals("2")||letter.equals("3")||letter.equals("4")||letter.equals("5")||letter.equals("6")||letter.equals("7")||letter.equals("8")||letter.equals("9")){
            return letter;
        }
        else{
            return null;
        }
    }
}
