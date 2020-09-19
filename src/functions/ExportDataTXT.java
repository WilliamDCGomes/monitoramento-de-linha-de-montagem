package functions;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import conexaobd.ModuloConexao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import screens.Export;

public class ExportDataTXT {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> planning = new ArrayList<String>();
    ArrayList<String> production = new ArrayList<String>();
    Export export;
    String date;
    public ExportDataTXT(String dateInformed, Export exportData){
        conexao = ModuloConexao.conector();
        date = dateInformed;
        export = exportData;
    }
    public void export() throws IOException{
        File file = new File("C:/Users/Alunos/Desktop/Tabela de Comparação.txt");
        
        try{
            file.createNewFile();
            getPlannning();
            getProduction();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter write = new BufferedWriter(fileWriter);
            int aux=0;
            boolean aux2=false;
            write.write("Planejameto                                       Rodagens\n");
            while(true){
                if(planning.isEmpty()==false){
                    write.write(planning.get(aux));
                    planning.remove(aux);
                    if(production.isEmpty()){
                        write.write("\n");
                    }
                    else{
                        write.write("    ");
                    }
                }
                if(production.isEmpty()==false){
                    if(production.get(aux).equals("           Data         Rodagem   Inicio   Fim\n")){
                        write.write(production.get(aux));
                        aux2=true;
                    }
                    if(planning.isEmpty()){
                        write.write("        ");
                    }
                    if(aux2==false){
                        write.write(production.get(aux));
                    }
                    production.remove(aux);
                }
                aux2=false;
                if(planning.isEmpty()&&production.isEmpty()){
                    break;
                }
            }
            write.close();
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "DADOS EXPORTADOS COM SUCESSO!");
            export.dispose();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getPlannning(){
        String sqlnome = "select dats, shooting, beginning, ending from planning where dats = ?";
        try {
            pst = conexao.prepareStatement(sqlnome);
            pst.setString(1,date);
            rs = pst.executeQuery();
            planning.add("Data         Rodagem   Inicio   Fim");
            while (rs.next()) {
                if(Integer.parseInt(rs.getString(2))<10){
                    planning.add(rs.getString(1) + "   " + rs.getString(2) + "         " + rs.getString(3) + "    " + rs.getString(4));
                }
                else{
                    planning.add(rs.getString(1) + "   " + rs.getString(2) + "        " + rs.getString(3) + "    " + rs.getString(4));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getProduction(){
        String sqlnome = "select dats, shot, min(beginning), max(ending) from workfinish where dats = ? group by shot";
        try {
            pst = conexao.prepareStatement(sqlnome);
            pst.setString(1,date);
            rs = pst.executeQuery();
            production.add("           Data         Rodagem   Inicio   Fim\n");
            while (rs.next()) {
                if(Integer.parseInt(rs.getString(2))<10){
                    production.add("         " + rs.getString(1) + "   " + rs.getString(2) + "         " + rs.getString(3) + "    " + rs.getString(4)+"\n");
                }
                else{
                    production.add("         " + rs.getString(1) + "   " + rs.getString(2) + "        " + rs.getString(3) + "    " + rs.getString(4)+"\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}