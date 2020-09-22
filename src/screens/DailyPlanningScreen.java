/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import functions.CheckInputDailyPlanning;
import javax.swing.JOptionPane;
import connectionbd.ConnectionModule;
import functions.GetDate;
import functions.StartShotting;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author willi
 */
public class DailyPlanningScreen extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form DailyPlanningScreen
     */
    public DailyPlanningScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        URL adress = getClass().getResource("/images/icon.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(adress);
        this.setIconImage(icon);
    }
    GetDate getDate = new GetDate();
    int manyShotting;
    private void add(int numberShotting, String start, String end){
        String sql = "insert into planning(dats,shooting,beginning,ending)values(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,getDate.informDate());
            pst.setInt(2,numberShotting);
            pst.setString(3,start);
            pst.setString(4,end);
            pst.executeUpdate();
            manyShotting--;
            if(manyShotting==0){
                ExistingPlanning existingPlanning = new ExistingPlanning();
                JOptionPane.showMessageDialog(null,"PLANEJAMENTO DIÁRIO INSERIDO COM SUCESSO");
                this.dispose();
                existingPlanning.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void update(int numberShotting, String start, String end){
        String sql = "update planning set beginning=?,ending=? where dats=? and shooting=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,start);
            pst.setString(2,end);
            pst.setString(3,getDate.informDate());
            pst.setInt(4,numberShotting);
            pst.executeUpdate();
            manyShotting--;
            if(manyShotting==0){
                ExistingPlanning existingPlanning = new ExistingPlanning();
                JOptionPane.showMessageDialog(null,"PLANEJAMENTO DIÁRIO ATUALIZADO COM SUCESSO");
                this.dispose();
                existingPlanning.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void beforeAdd(){
        CheckInputDailyPlanning checkInputDailyPlanning = new CheckInputDailyPlanning();
        if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndFirstShooting.getText().equals("  :  ")){
            JOptionPane.showMessageDialog(null, "INSIRA AO MENOS UMA RODAGEM!");
        }
        else if(inputBeginningSecondShooting.getText().equals("  :  ")||inputEndSecondShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())){
                manyShotting = 1;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningThirdShooting.getText().equals("  :  ")||inputEndThirdShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())){
                manyShotting = 2;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningFourthShooting.getText().equals("  :  ")||inputEndFourthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())){
                manyShotting = 3;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningFifthShooting.getText().equals("  :  ")||inputEndFifthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())){
                manyShotting = 4;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningSixthShooting.getText().equals("  :  ")||inputEndSixthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())){
                manyShotting = 5;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningSeventhShooting.getText().equals("  :  ")||inputEndSeventhShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())){
                manyShotting = 6;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningEighthShooting.getText().equals("  :  ")||inputEndEighthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())){
                manyShotting = 7;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningNinthShooting.getText().equals("  :  ")||inputEndNinthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())){
                manyShotting = 8;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                add(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningTenthShooting.getText().equals("  :  ")||inputEndTenthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())){
                manyShotting = 9;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                add(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                add(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningEleventhShooting.getText().equals("  :  ")||inputEndEleventhShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())){
                manyShotting = 10;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                add(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                add(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
                add(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningTwenlthShooting.getText().equals("  :  ")||inputEndTwenlthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText())){
                manyShotting = 11;
                add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                add(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                add(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
                add(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
                add(11, inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTwenlthShooting.getText(), inputEndTwenlthShooting.getText())){
            manyShotting = 12;
            add(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
            add(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
            add(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
            add(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
            add(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
            add(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
            add(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
            add(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
            add(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
            add(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
            add(11, inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText());
            add(12, inputBeginningTwenlthShooting.getText(), inputEndTwenlthShooting.getText());
        }
        else{
            JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
        }
    }
    private void beforeUpdate(){
        CheckInputDailyPlanning checkInputDailyPlanning = new CheckInputDailyPlanning();
        if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndFirstShooting.getText().equals("  :  ")){
            JOptionPane.showMessageDialog(null, "INSIRA AO MENOS UMA RODAGEM!");
        }
        else if(inputBeginningSecondShooting.getText().equals("  :  ")||inputEndSecondShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())){
                manyShotting = 1;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningThirdShooting.getText().equals("  :  ")||inputEndThirdShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())){
                manyShotting = 2;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningFourthShooting.getText().equals("  :  ")||inputEndFourthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())){
                manyShotting = 3;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningFifthShooting.getText().equals("  :  ")||inputEndFifthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())){
                manyShotting = 4;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningSixthShooting.getText().equals("  :  ")||inputEndSixthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())){
                manyShotting = 5;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningSeventhShooting.getText().equals("  :  ")||inputEndSeventhShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())){
                manyShotting = 6;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningEighthShooting.getText().equals("  :  ")||inputEndEighthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())){
                manyShotting = 7;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningNinthShooting.getText().equals("  :  ")||inputEndNinthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())){
                manyShotting = 8;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                update(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningTenthShooting.getText().equals("  :  ")||inputEndTenthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())){
                manyShotting = 9;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                update(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                update(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningEleventhShooting.getText().equals("  :  ")||inputEndEleventhShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())){
                manyShotting = 10;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                update(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                update(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
                update(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(inputBeginningTwenlthShooting.getText().equals("  :  ")||inputEndTwenlthShooting.getText().equals("  :  ")){
            if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText())){
                manyShotting = 11;
                update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
                update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
                update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
                update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
                update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
                update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
                update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
                update(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
                update(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
                update(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
                update(11, inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
            }
        }
        else if(checkInputDailyPlanning.checkValidation(inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText())&&checkInputDailyPlanning.checkValidation(inputBeginningTwenlthShooting.getText(), inputEndTwenlthShooting.getText())){
            manyShotting = 12;
            update(1, inputBeginningFirstShooting.getText(), inputEndFirstShooting.getText());
            update(2, inputBeginningSecondShooting.getText(), inputEndSecondShooting.getText());
            update(3, inputBeginningThirdShooting.getText(), inputEndThirdShooting.getText());
            update(4, inputBeginningFourthShooting.getText(), inputEndFourthShooting.getText());
            update(5, inputBeginningFifthShooting.getText(), inputEndFifthShooting.getText());
            update(6, inputBeginningSixthShooting.getText(), inputEndSixthShooting.getText());
            update(7, inputBeginningSeventhShooting.getText(), inputEndSeventhShooting.getText());
            update(8, inputBeginningEighthShooting.getText(), inputEndEighthShooting.getText());
            update(9, inputBeginningNinthShooting.getText(), inputEndNinthShooting.getText());
            update(10, inputBeginningTenthShooting.getText(), inputEndTenthShooting.getText());
            update(11, inputBeginningEleventhShooting.getText(), inputEndEleventhShooting.getText());
            update(12, inputBeginningTwenlthShooting.getText(), inputEndTwenlthShooting.getText());
        }
        else{
            JOptionPane.showMessageDialog(null, "DADO INCORRETO! O COMEÇO NÃO PODE SER DEPOIS DO FIM");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPlanningDay = new javax.swing.JLabel();
        txtFirstShooting = new javax.swing.JLabel();
        txtSecondShooting = new javax.swing.JLabel();
        txtThirdShooting = new javax.swing.JLabel();
        txtFourthShooting = new javax.swing.JLabel();
        txtFifthShooting = new javax.swing.JLabel();
        txtSixthShooting = new javax.swing.JLabel();
        txtSeventhShooting = new javax.swing.JLabel();
        txtEighthShooting = new javax.swing.JLabel();
        txtNinthShooting = new javax.swing.JLabel();
        txtTenthShooting = new javax.swing.JLabel();
        txtEleventhShooting = new javax.swing.JLabel();
        txtTwelfthShooting = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonCancele = new javax.swing.JButton();
        txtBeginning = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        inputEndFirstShooting = new javax.swing.JFormattedTextField();
        inputBeginningFirstShooting = new javax.swing.JFormattedTextField();
        inputBeginningSecondShooting = new javax.swing.JFormattedTextField();
        inputBeginningThirdShooting = new javax.swing.JFormattedTextField();
        inputBeginningFourthShooting = new javax.swing.JFormattedTextField();
        inputBeginningFifthShooting = new javax.swing.JFormattedTextField();
        inputBeginningSeventhShooting = new javax.swing.JFormattedTextField();
        inputBeginningSixthShooting = new javax.swing.JFormattedTextField();
        inputBeginningEighthShooting = new javax.swing.JFormattedTextField();
        inputBeginningNinthShooting = new javax.swing.JFormattedTextField();
        inputBeginningTenthShooting = new javax.swing.JFormattedTextField();
        inputBeginningEleventhShooting = new javax.swing.JFormattedTextField();
        inputBeginningTwenlthShooting = new javax.swing.JFormattedTextField();
        inputEndThirdShooting = new javax.swing.JFormattedTextField();
        inputEndSecondShooting = new javax.swing.JFormattedTextField();
        inputEndFourthShooting = new javax.swing.JFormattedTextField();
        inputEndFifthShooting = new javax.swing.JFormattedTextField();
        inputEndSixthShooting = new javax.swing.JFormattedTextField();
        inputEndSeventhShooting = new javax.swing.JFormattedTextField();
        inputEndEighthShooting = new javax.swing.JFormattedTextField();
        inputEndNinthShooting = new javax.swing.JFormattedTextField();
        inputEndTenthShooting = new javax.swing.JFormattedTextField();
        inputEndEleventhShooting = new javax.swing.JFormattedTextField();
        inputEndTwenlthShooting = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planejamento");

        txtPlanningDay.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtPlanningDay.setText("PLANEJAMENTO DO DIA");

        txtFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFirstShooting.setText("1º RODAGEM");

        txtSecondShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSecondShooting.setText("2º RODAGEM");

        txtThirdShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtThirdShooting.setText("3º RODAGEM");

        txtFourthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFourthShooting.setText("4º RODAGEM");

        txtFifthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFifthShooting.setText("5º RODAGEM");

        txtSixthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSixthShooting.setText("6º RODAGEM");

        txtSeventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSeventhShooting.setText("7º RODAGEM");

        txtEighthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEighthShooting.setText("8º RODAGEM");

        txtNinthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtNinthShooting.setText("9º RODAGEM");

        txtTenthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTenthShooting.setText("10º RODAGEM");

        txtEleventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEleventhShooting.setText("11º RODAGEM");

        txtTwelfthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTwelfthShooting.setText("12º RODAGEM");

        buttonSave.setText("SALVAR");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        buttonSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonSaveKeyPressed(evt);
            }
        });

        buttonCancele.setText("CANCELAR");
        buttonCancele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCanceleActionPerformed(evt);
            }
        });
        buttonCancele.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonCanceleKeyPressed(evt);
            }
        });

        txtBeginning.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtBeginning.setText("INÍCIO");

        txtEnd.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEnd.setText("FIM");

        try {
            inputEndFirstShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndFirstShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndFirstShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningFirstShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningFirstShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningFirstShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningSecondShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningSecondShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningSecondShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningSecondShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningThirdShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningThirdShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningThirdShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningThirdShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningFourthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningFourthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningFourthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningFourthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningFifthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningFifthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningFifthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningFifthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningSeventhShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningSeventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningSeventhShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningSeventhShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningSixthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningSixthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningSixthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningSixthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningEighthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningEighthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningEighthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningEighthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningNinthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningNinthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningNinthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningNinthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningTenthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningTenthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningTenthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningTenthShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningEleventhShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningEleventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningEleventhShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningEleventhShootingKeyPressed(evt);
            }
        });

        try {
            inputBeginningTwenlthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningTwenlthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningTwenlthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningTwenlthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndThirdShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndThirdShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndThirdShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndThirdShootingKeyPressed(evt);
            }
        });

        try {
            inputEndSecondShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndSecondShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndSecondShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndSecondShootingKeyPressed(evt);
            }
        });

        try {
            inputEndFourthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndFourthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndFourthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndFourthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndFifthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndFifthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndFifthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndFifthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndSixthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndSixthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndSixthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndSixthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndSeventhShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndSeventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndSeventhShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndSeventhShootingKeyPressed(evt);
            }
        });

        try {
            inputEndEighthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndEighthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndEighthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndEighthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndNinthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndNinthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndNinthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndNinthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndTenthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndTenthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndTenthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndTenthShootingKeyPressed(evt);
            }
        });

        try {
            inputEndEleventhShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndEleventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndEleventhShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndEleventhShootingKeyPressed(evt);
            }
        });

        try {
            inputEndTwenlthShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndTwenlthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndTwenlthShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndTwenlthShootingKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtBeginning)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNinthShooting)
                            .addComponent(txtTenthShooting)
                            .addComponent(txtEleventhShooting)
                            .addComponent(txtTwelfthShooting)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonSave)
                                .addGap(45, 45, 45)
                                .addComponent(buttonCancele))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSecondShooting)
                            .addComponent(txtFirstShooting)
                            .addComponent(txtThirdShooting)
                            .addComponent(txtFourthShooting)
                            .addComponent(txtFifthShooting)
                            .addComponent(txtSixthShooting)
                            .addComponent(txtSeventhShooting)
                            .addComponent(txtEighthShooting))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputBeginningFirstShooting)
                            .addComponent(inputBeginningSecondShooting)
                            .addComponent(inputBeginningThirdShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningFourthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningFifthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningSeventhShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningSixthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningEighthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningNinthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningTenthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningEleventhShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(inputBeginningTwenlthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(txtEnd)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inputEndFirstShooting)
                            .addComponent(inputEndSecondShooting)
                            .addComponent(inputEndThirdShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndFourthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndFifthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndSixthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndSeventhShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndEighthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndNinthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndTenthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndEleventhShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(inputEndTwenlthShooting, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                        .addGap(79, 79, 79))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPlanningDay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPlanningDay)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBeginning)
                            .addComponent(txtEnd))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstShooting)
                            .addComponent(inputEndFirstShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputBeginningFirstShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSecondShooting)
                            .addComponent(inputBeginningSecondShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputEndSecondShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThirdShooting)
                            .addComponent(inputBeginningThirdShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputEndThirdShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFourthShooting)
                            .addComponent(inputBeginningFourthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputEndFourthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(txtFifthShooting))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputBeginningFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputEndFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSixthShooting)
                    .addComponent(inputBeginningSixthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndSixthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeventhShooting)
                    .addComponent(inputBeginningSeventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndSeventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEighthShooting)
                    .addComponent(inputBeginningEighthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndEighthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNinthShooting)
                    .addComponent(inputBeginningNinthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndNinthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenthShooting)
                    .addComponent(inputBeginningTenthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndTenthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEleventhShooting)
                    .addComponent(inputBeginningEleventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndEleventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTwelfthShooting)
                    .addComponent(inputBeginningTwenlthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEndTwenlthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave)
                    .addComponent(buttonCancele))
                .addGap(99, 99, 99))
        );

        setSize(new java.awt.Dimension(522, 713));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(buttonCancele.isVisible()){
            StartShotting startShotting = new StartShotting();
            if(startShotting.hasAProgramming(getDate.informDate())){
                JOptionPane.showMessageDialog(null, "JÁ FOI INSERIDO UM PLANEJAMENTO PARA O DIA DE HOJE!");
            }
            else{
                beforeAdd();
            }
        }
        else{
            beforeUpdate();
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void inputBeginningFirstShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningFirstShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndFirstShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningFirstShootingKeyPressed

    private void inputEndFirstShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndFirstShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningSecondShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndFirstShootingKeyPressed

    private void inputBeginningSecondShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningSecondShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndSecondShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningSecondShootingKeyPressed

    private void inputEndSecondShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndSecondShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningThirdShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndSecondShootingKeyPressed

    private void inputBeginningThirdShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningThirdShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndThirdShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningThirdShootingKeyPressed

    private void inputEndThirdShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndThirdShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningFourthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndThirdShootingKeyPressed

    private void inputBeginningFourthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningFourthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndFourthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningFourthShootingKeyPressed

    private void inputEndFourthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndFourthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningFifthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndFourthShootingKeyPressed

    private void inputBeginningFifthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningFifthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndFifthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningFifthShootingKeyPressed

    private void inputEndFifthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndFifthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningSixthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndFifthShootingKeyPressed

    private void inputBeginningSixthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningSixthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndSixthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningSixthShootingKeyPressed

    private void inputEndSixthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndSixthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningSeventhShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndSixthShootingKeyPressed

    private void inputBeginningSeventhShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningSeventhShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndSeventhShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningSeventhShootingKeyPressed

    private void inputEndSeventhShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndSeventhShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningEighthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndSeventhShootingKeyPressed

    private void inputBeginningEighthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningEighthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndEighthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningEighthShootingKeyPressed

    private void inputEndEighthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndEighthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningNinthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndEighthShootingKeyPressed

    private void inputBeginningNinthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningNinthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndNinthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningNinthShootingKeyPressed

    private void inputEndNinthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndNinthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningTenthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndNinthShootingKeyPressed

    private void inputBeginningTenthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningTenthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndTenthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningTenthShootingKeyPressed

    private void inputEndTenthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndTenthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningEleventhShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndTenthShootingKeyPressed

    private void inputBeginningEleventhShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningEleventhShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndEleventhShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningEleventhShootingKeyPressed

    private void inputEndEleventhShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndEleventhShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningTwenlthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputEndEleventhShootingKeyPressed

    private void inputBeginningTwenlthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningTwenlthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndTwenlthShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningTwenlthShootingKeyPressed

    private void inputEndTwenlthShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndTwenlthShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(buttonCancele.isVisible()){
                beforeAdd();
            }
            else{
                beforeUpdate();
            }
        }
    }//GEN-LAST:event_inputEndTwenlthShootingKeyPressed

    private void buttonSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonSaveKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(buttonCancele.isVisible()){
                StartShotting startShotting = new StartShotting();
                if(startShotting.hasAProgramming(getDate.informDate())){
                    JOptionPane.showMessageDialog(null, "JÁ FOI INSERIDO UM PLANEJAMENTO PARA O DIA DE HOJE!");
                }
                else{
                    beforeAdd();
                }
            }
            else{
                beforeUpdate();
            }
        }
    }//GEN-LAST:event_buttonSaveKeyPressed

    private void buttonCanceleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonCanceleKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            this.dispose();
        }
    }//GEN-LAST:event_buttonCanceleKeyPressed

    private void buttonCanceleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCanceleActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCanceleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DailyPlanningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DailyPlanningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DailyPlanningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DailyPlanningScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DailyPlanningScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton buttonCancele;
    private javax.swing.JButton buttonSave;
    public static javax.swing.JFormattedTextField inputBeginningEighthShooting;
    public static javax.swing.JFormattedTextField inputBeginningEleventhShooting;
    public static javax.swing.JFormattedTextField inputBeginningFifthShooting;
    public static javax.swing.JFormattedTextField inputBeginningFirstShooting;
    public static javax.swing.JFormattedTextField inputBeginningFourthShooting;
    public static javax.swing.JFormattedTextField inputBeginningNinthShooting;
    public static javax.swing.JFormattedTextField inputBeginningSecondShooting;
    public static javax.swing.JFormattedTextField inputBeginningSeventhShooting;
    public static javax.swing.JFormattedTextField inputBeginningSixthShooting;
    public static javax.swing.JFormattedTextField inputBeginningTenthShooting;
    public static javax.swing.JFormattedTextField inputBeginningThirdShooting;
    public static javax.swing.JFormattedTextField inputBeginningTwenlthShooting;
    public static javax.swing.JFormattedTextField inputEndEighthShooting;
    public static javax.swing.JFormattedTextField inputEndEleventhShooting;
    public static javax.swing.JFormattedTextField inputEndFifthShooting;
    public static javax.swing.JFormattedTextField inputEndFirstShooting;
    public static javax.swing.JFormattedTextField inputEndFourthShooting;
    public static javax.swing.JFormattedTextField inputEndNinthShooting;
    public static javax.swing.JFormattedTextField inputEndSecondShooting;
    public static javax.swing.JFormattedTextField inputEndSeventhShooting;
    public static javax.swing.JFormattedTextField inputEndSixthShooting;
    public static javax.swing.JFormattedTextField inputEndTenthShooting;
    public static javax.swing.JFormattedTextField inputEndThirdShooting;
    public static javax.swing.JFormattedTextField inputEndTwenlthShooting;
    private javax.swing.JLabel txtBeginning;
    private javax.swing.JLabel txtEighthShooting;
    private javax.swing.JLabel txtEleventhShooting;
    private javax.swing.JLabel txtEnd;
    private javax.swing.JLabel txtFifthShooting;
    private javax.swing.JLabel txtFirstShooting;
    private javax.swing.JLabel txtFourthShooting;
    private javax.swing.JLabel txtNinthShooting;
    private javax.swing.JLabel txtPlanningDay;
    private javax.swing.JLabel txtSecondShooting;
    private javax.swing.JLabel txtSeventhShooting;
    private javax.swing.JLabel txtSixthShooting;
    private javax.swing.JLabel txtTenthShooting;
    private javax.swing.JLabel txtThirdShooting;
    private javax.swing.JLabel txtTwelfthShooting;
    // End of variables declaration//GEN-END:variables
}
