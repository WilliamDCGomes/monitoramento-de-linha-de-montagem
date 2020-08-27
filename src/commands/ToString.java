package commands;
public class ToString {
    public String makeString(String letter){
        if(letter.equals("0")){
            return "q";
        }
        else if(letter.equals("1")){
            return "w";
        }
        else if(letter.equals("2")){
            return "a";
        }
        else if(letter.equals("3")){
            return "s";
        }
        else if(letter.equals("4")){
            return "z";
        }
        else if(letter.equals("5")){
            return "x";
        }
        else if(letter.equals("6")){
            return "e";
        }
        else if(letter.equals("7")){
            return "r";
        }
        else if(letter.equals("8")){
            return "d";
        }
        else{
            return "f";
        }
    }
}
