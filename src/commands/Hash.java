package commands;
public class Hash {
    private int hash = 1313; 
    private int y=0;
    private int resu;
    public String DoHash(String password){
       int x = password.length();
       ToInt toInt = new ToInt();
       ToString toString = new ToString();
       for(int i=0;i<x;i++){
           y += toInt.makeInt(password.substring(i, i + 1));
       }
       resu = (int) Math.pow(hash,3) * (int) Math.pow(y,3);
       x=Integer.toString(resu).length();
       int fix = 3;
       String out = "$%";
       for(int i=0;i<x;i++){
           out += Integer.toString(resu).substring(i, i + 1);
           if(fix==0){
               fix=3;
               out += toString.makeString(Integer.toString(resu).substring(i, i + 1));
           }
           if(i==(int) x/2){
               out+="!@[]~?";
           }
           fix--;
       }
       out+="&¨$#";
       x=out.length();
       int aux = x/2;
       String out2 = "";
       for(int i=0;i<x;i++){
           if(i==aux){
               for(int j=x-1;j>0;j--){
                   out2+= out.substring(j - 1, j);
               }
           }
           else{
               out2+= out.substring(i, i + 1);
           }
       }
       out2 += "]";
       return out2;
    }
}
