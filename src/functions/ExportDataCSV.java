package functions;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import connectionbd.ConnectionModule;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import screens.Export;

public class ExportDataCSV {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> planning = new ArrayList<String>();
    ArrayList<String> production = new ArrayList<String>();
    Export export;
    String date;
    public ExportDataCSV(String dateInformed, Export exportData){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        date = dateInformed;
        export = exportData;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExportDataCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ExportDataCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExportDataCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ExportDataCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void export() throws IOException{
        String userDir = System.getProperty("user.home");
        JFileChooser fc = new JFileChooser(userDir +"/Desktop");
        fc.showSaveDialog(export.fileChooser);
        File file =  new File(fc.getSelectedFile() + ".csv");
        if(file.exists()){
            Object[] options = { "Sim", "Não" };
            int opt = JOptionPane.showOptionDialog(null,"ESTE ARQUIVO JÁ EXISTE. DESEJA SOBREESCREVER ELE?", "ATENÇÃO!!!",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
            if (opt == JOptionPane.YES_OPTION) {   
                insert(file);
            }     
        }
        else{
            insert(file);
        }
    }
    private void insert(File file){
        try{
            file.createNewFile();
            getPlannning();
            getProduction();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter write = new BufferedWriter(fileWriter);
            int aux=0;
            boolean aux2=false;
            write.write("Planejameto;;;;;;;Rodagens\n");
            while(true){
                if(planning.isEmpty()==false){
                    write.write(planning.get(aux));
                    planning.remove(aux);
                    if(production.isEmpty()){
                        write.write("\n");
                    }
                    else{
                        write.write(";;;;");
                    }
                }
                if(production.isEmpty()==false){
                    if(production.get(aux).equals("Data;Rodagem;Inicio;Fim\n")){
                        write.write(production.get(aux));
                        aux2=true;
                    }
                    if(planning.isEmpty()){
                        write.write(";;;;;;;;");
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
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,date);
            rs = pst.executeQuery();
            planning.add("Data;Rodagem;Inicio;Fim");
            while (rs.next()) {
                planning.add("(" + rs.getString(1) + ");" + rs.getString(2) + ";" + rs.getString(3) + ";" + rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getProduction(){
        String sqlnome = "select dats, shot, min(beginning), max(ending) from workfinish where dats = ? group by shot";
        try {
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,date);
            rs = pst.executeQuery();
            production.add("Data;Rodagem;Inicio;Fim\n");
            while (rs.next()) {
                production.add("(" + rs.getString(1) + ");" + rs.getString(2) + ";" + rs.getString(3) + ";" + rs.getString(4)+"\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
