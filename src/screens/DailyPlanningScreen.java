/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import functions.CheckInputDailyPlanning;
import javax.swing.JOptionPane;
import connectionbd.ConnectionModule;
import functions.AuxShot;
import functions.GetDate;
import functions.HourToMinute;
import functions.StartShotting;
import functions.TimeDifference;
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
    boolean error = false;
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            error = true;
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
        
        else{
            AuxShot auxShot = new AuxShot();
            HourToMinute hourToMinute = new HourToMinute();
            MinuteToHour minuteToHour = new MinuteToHour();
            TimeDifference timeDifference = new TimeDifference();
            String begin = inputBeginningFirstShooting.getText();
            int aux = 1;
            int end = auxShot.time(begin, inputBeginningThirdShooting.getText());
            System.out.println(hourToMinute.getMinute(inputEndFirstShooting.getText(), timeDifference)>end);
            System.out.println(hourToMinute.getMinute(inputEndFirstShooting.getText(), timeDifference));
            System.out.println(end);
            while(hourToMinute.getMinute(inputEndFirstShooting.getText(), timeDifference)>end){
                add(aux, begin, minuteToHour.getHour(end));
                aux++;
                begin = minuteToHour.getHour(end);
                end = auxShot.time(begin, inputBeginningThirdShooting.getText());
            }
            if(error==false){
                ExistingPlanning existingPlanning = new ExistingPlanning();
                JOptionPane.showMessageDialog(null,"PLANEJAMENTO DIÁRIO INSERIDO COM SUCESSO");
                this.dispose();
                existingPlanning.setVisible(true);
            }
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
        buttonSave = new javax.swing.JButton();
        buttonCancele = new javax.swing.JButton();
        txtBeginning = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        inputEndFirstShooting = new javax.swing.JFormattedTextField();
        inputBeginningFirstShooting = new javax.swing.JFormattedTextField();
        inputBeginningSecondShooting = new javax.swing.JFormattedTextField();
        inputBeginningThirdShooting = new javax.swing.JFormattedTextField();
        inputEndSecondShooting = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planejamento");
        setResizable(false);
        getContentPane().setLayout(null);

        txtPlanningDay.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtPlanningDay.setText("PLANEJAMENTO DO DIA");
        getContentPane().add(txtPlanningDay);
        txtPlanningDay.setBounds(120, 20, 277, 32);

        txtFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFirstShooting.setText("RODAGEM");
        getContentPane().add(txtFirstShooting);
        txtFirstShooting.setBounds(31, 115, 75, 20);

        txtSecondShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSecondShooting.setText("ALMOÇO");
        getContentPane().add(txtSecondShooting);
        txtSecondShooting.setBounds(31, 153, 63, 20);

        txtThirdShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtThirdShooting.setText("DURAÇÃO RODAGEM");
        getContentPane().add(txtThirdShooting);
        txtThirdShooting.setBounds(31, 187, 152, 20);

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
        getContentPane().add(buttonSave);
        buttonSave.setBounds(31, 228, 71, 23);

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
        getContentPane().add(buttonCancele);
        buttonCancele.setBounds(147, 228, 85, 23);

        txtBeginning.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtBeginning.setText("INÍCIO");
        getContentPane().add(txtBeginning);
        txtBeginning.setBounds(231, 75, 42, 20);

        txtEnd.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEnd.setText("FIM");
        getContentPane().add(txtEnd);
        txtEnd.setBounds(355, 75, 23, 20);

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
        getContentPane().add(inputEndFirstShooting);
        inputEndFirstShooting.setBounds(355, 110, 72, 26);

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
        getContentPane().add(inputBeginningFirstShooting);
        inputBeginningFirstShooting.setBounds(231, 110, 73, 26);

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
        getContentPane().add(inputBeginningSecondShooting);
        inputBeginningSecondShooting.setBounds(231, 147, 73, 26);

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
        getContentPane().add(inputBeginningThirdShooting);
        inputBeginningThirdShooting.setBounds(231, 184, 73, 26);

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
        getContentPane().add(inputEndSecondShooting);
        inputEndSecondShooting.setBounds(355, 147, 72, 26);

        setSize(new java.awt.Dimension(522, 310));
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
            
        }
    }//GEN-LAST:event_inputBeginningThirdShootingKeyPressed

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
    public static javax.swing.JFormattedTextField inputBeginningFirstShooting;
    public static javax.swing.JFormattedTextField inputBeginningSecondShooting;
    public static javax.swing.JFormattedTextField inputBeginningThirdShooting;
    public static javax.swing.JFormattedTextField inputEndFirstShooting;
    public static javax.swing.JFormattedTextField inputEndSecondShooting;
    private javax.swing.JLabel txtBeginning;
    private javax.swing.JLabel txtEnd;
    private javax.swing.JLabel txtFirstShooting;
    private javax.swing.JLabel txtPlanningDay;
    private javax.swing.JLabel txtSecondShooting;
    private javax.swing.JLabel txtThirdShooting;
    // End of variables declaration//GEN-END:variables
}
