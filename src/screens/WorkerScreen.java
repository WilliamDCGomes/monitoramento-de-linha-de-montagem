/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.awt.Frame;
import javax.swing.JOptionPane;
import conexaobd.ModuloConexao;
import functions.BeginProdution;
import functions.GetBeginOfDelay;
import functions.GetDate;
import functions.GetHour;
import functions.InputDelay;
import functions.RemoveDelay;
import functions.StartShotting;
import functions.StationWorking;
import functions.TimeDifference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TimerTask;
import javax.swing.Timer;

/**
 *
 * @author Alunos
 */
public class WorkerScreen extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    /**
     * Creates new form WorkerScreen
     */
    public WorkerScreen() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    StationWorking stationWorking = new StationWorking();
    StartShotting startShotting = new StartShotting();
    GetDate getDate = new GetDate();
    GetHour getHour = new GetHour();
    RemoveDelay removeDelay = new RemoveDelay();
    public String reasonDelay;
    public String typeDelay;
    private int id;
    int x = 0;
    String beginTime;
    String endTime;
    private void setTime(){
        TimeDifference timeDifference = new TimeDifference();
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 30000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                outputTime.setText(timeDifference.getDifference(getHour.informHour(), endTime));
            }
        }, delay, interval);
    }
    private void finish(){
        String sql = "update stations set working=0 where id=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(outputStation.getText()));
            pst.executeUpdate();
            pst2=conexao.prepareStatement(sql);
            pst2.setInt(1,Integer.parseInt(outputStation.getText()));
            pst2.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void open(){
        String sql = "update stations set working=1 where id=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(outputStation.getText()));
            pst.executeUpdate();
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getId(){
        String sql ="select id from workfinish where dats = ? and station = ? and shot = ?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setString(2, outputStation.getText());
            pst.setInt(3, getShot());
            rs= pst.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void removeService(){
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA RESETAR SUA EXECUÇÃO?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from workfinish where id = ?";
            try {
                pst=conexao.prepareStatement(sql);
                getId();
                pst.setInt(1, id);
                int apagado = pst.executeUpdate();
                if(apagado>0){
                    removeDelay.remove(id);
                    open();
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void addService(){
        String sql = "insert into workfinish(dats,beginning,ending,station,shot) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,getDate.informDate());
            pst.setString(2,getBegin(getShot()));
            pst.setString(3,getHour.informHour());
            pst.setString(4,outputStation.getText());
            pst.setInt(5,getShot());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"SERVIÇO ADICIONADO COM SUCESSO");
            finish();
            if(stationWorking.hasStation()==false){
                startShotting.keepProduction(getShot()+1, getHour.informHour());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private String getBegin(int shot){
        String sql ="select beginning from presentShotting where dats = ? and shot = ?";
        try {
            pst2=conexao.prepareStatement(sql);
            pst2.setString(1, getDate.informDate());
            pst2.setInt(2, shot);
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return rs2.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    private int getShot(){
        String sql ="select shot from presentShotting where dats = ? order by id desc limit 1";
        try {
            pst2=conexao.prepareStatement(sql);
            pst2.setString(1, getDate.informDate());
            rs2= pst2.executeQuery();
            if(rs2.next()){
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return -1;
    }
    private void getDelay(){
        String sql ="select reasonDelay, typeDelay from auxDelay order by id desc limit 1";
        try {
            pst2=conexao.prepareStatement(sql);
            rs2= pst2.executeQuery();
            if(rs2.next()){
                reasonDelay = rs2.getString(1);
                typeDelay = rs2.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        groupDelay = new javax.swing.ButtonGroup();
        groupWorkFinish = new javax.swing.ButtonGroup();
        txtTimeToNextWork = new javax.swing.JLabel();
        outputTime = new javax.swing.JLabel();
        outputBarTime = new javax.swing.JProgressBar();
        inputWorkFinish = new javax.swing.JCheckBox();
        inputDelay = new javax.swing.JCheckBox();
        buttonReset = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        txtStation = new javax.swing.JLabel();
        outputStation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Linha de Montagem");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        txtTimeToNextWork.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTimeToNextWork.setText("TEMPO RESTANTE ATÉ A RODAGEM");

        outputTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTime.setText("00:00");

        outputBarTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        groupWorkFinish.add(inputWorkFinish);
        inputWorkFinish.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputWorkFinish.setText("SERVIÇO FINALIZADO");
        inputWorkFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputWorkFinishActionPerformed(evt);
            }
        });

        groupDelay.add(inputDelay);
        inputDelay.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        inputDelay.setText("ATRASO");
        inputDelay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDelayActionPerformed(evt);
            }
        });

        buttonReset.setText("RESET");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        buttonLogout.setText("LOGOUT");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        txtStation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStation.setText("ESTAÇÃO");

        outputStation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputStation.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtStation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputStation))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDelay)
                            .addComponent(inputWorkFinish))
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonReset)
                            .addComponent(outputBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonLogout)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimeToNextWork)
                        .addGap(69, 69, 69)
                        .addComponent(outputTime)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimeToNextWork)
                    .addComponent(outputTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputWorkFinish)
                    .addComponent(buttonReset))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDelay)
                    .addComponent(buttonLogout))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStation)
                    .addComponent(outputStation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(560, 252));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputDelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDelayActionPerformed
        DelayScreen delayScreen = new DelayScreen();
        delayScreen.setVisible(true);
    }//GEN-LAST:event_inputDelayActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        removeService();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA FAZER LOGOUT?\nCASO FAÇA SEM FINALIZAR O SERVIÇO, OS DADOS SERÃO PERDIDOS!","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            LoginScreen loginScreen = new LoginScreen();
            Frame[] frames = getFrames(); 
            for (int i = 0; i < frames.length; i++){ 
                frames[i].dispose(); 
            }
            loginScreen.setVisible(true);
        }
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void inputWorkFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputWorkFinishActionPerformed
        StartShotting startShotting = new StartShotting();
        startShotting.startProduction();
        
        addService();
        if(inputDelay.isSelected()){
            InputDelay inputDelay = new InputDelay();
            getDelay();
            inputDelay.makeInput(reasonDelay,typeDelay);
        }
    }//GEN-LAST:event_inputWorkFinishActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            GetBeginOfDelay getBeginOfDelay = new GetBeginOfDelay();
            BeginProdution beginProdution = new BeginProdution();
            x++;
            beginTime = beginProdution.getProduction(getShot());
            endTime = getBeginOfDelay.getBegin(getShot());
            setTime();
        }
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(WorkerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkerScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonReset;
    private javax.swing.ButtonGroup groupDelay;
    private javax.swing.ButtonGroup groupWorkFinish;
    private javax.swing.JCheckBox inputDelay;
    private javax.swing.JCheckBox inputWorkFinish;
    private javax.swing.JProgressBar outputBarTime;
    public static javax.swing.JLabel outputStation;
    private javax.swing.JLabel outputTime;
    private javax.swing.JLabel txtStation;
    private javax.swing.JLabel txtTimeToNextWork;
    // End of variables declaration//GEN-END:variables
}
