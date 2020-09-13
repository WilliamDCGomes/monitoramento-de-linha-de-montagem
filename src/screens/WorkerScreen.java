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
import functions.HourToMinute;
import functions.InputDelay;
import functions.RemoveDelay;
import functions.StartShotting;
import functions.StationWorking;
import functions.TimeDifference;
import functions.TimeToSet;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TimerTask;

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
    String beginTime;
    String endTime;
    int x = 0;
    public void setTime(){
        TimeDifference timeDifference = new TimeDifference();
        GetBeginOfDelay getBeginOfDelay = new GetBeginOfDelay();
        endTime = getBeginOfDelay.getBegin(getShot());
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 30000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                String difference = timeDifference.getDifference(getHour.informHour(), endTime);
                if(timeDifference.delay=="true"){
                    outputTime.setForeground(Color.red);
                    outputTime.setText(difference);
                }
                else{
                    outputTime.setForeground(Color.black);
                    outputTime.setText(difference);
                }
            }
        }, delay, interval);
    }
    private void finish(){
        String sql = "update stations set working=0 where id=?";
        try {
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
            pst2=conexao.prepareStatement(sql);
            pst2.setInt(1,Integer.parseInt(outputStation.getText()));
            pst2.executeUpdate();
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
                pst2=conexao.prepareStatement(sql);
                getId();
                pst2.setInt(1, id);
                int apagado = pst2.executeUpdate();
                if(apagado>0){
                    removeDelay.remove(id);
                    open();
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                    JOptionPane.showMessageDialog(null, "DADOS DELETADOS");
                    this.dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void addService(){
        String sql = "insert into workfinish(dats,beginning,ending,station,shot) values(?,?,?,?,?)";
        try {
            pst2 = conexao.prepareStatement(sql);
            pst2.setString(1,getDate.informDate());
            pst2.setString(2,getBegin(getShot()));
            pst2.setString(3,getHour.informHour());
            pst2.setString(4,outputStation.getText());
            pst2.setInt(5,getShot());
            pst2.executeUpdate();
            JOptionPane.showMessageDialog(null,"SERVIÇO ADICIONADO COM SUCESSO");
            GetBeginOfDelay getBeginOfDelay = new GetBeginOfDelay();
            String beginDelay = getBeginOfDelay.getBegin(getShot());
            String endWork = getHour.informHour();
            if(inputDelay.isSelected()){
                TimeDifference timeDifference = new TimeDifference();
                HourToMinute hourToMinute = new HourToMinute();
                if(hourToMinute.getMinute(timeDifference.getDifference(beginDelay, endWork))<0){
                    getDelay();
                    InputDelay inputDelay = new InputDelay();
                    inputDelay.makeInput(reasonDelay,typeDelay);
                }
                else{
                    JOptionPane.showMessageDialog(null, "O ATRASO NÃO FOI ADICIONADO PELO FATO QUE TER FINALIZADO O SERVIÇO DENTRO DO HORÁRIO");
                }
            }
            finish();
            TimeToSet timeToSet = new TimeToSet();
            timeToSet.timeSet(getShot());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private String getBegin(int shot){
        String sql ="select beginning from presentShotting where dats = ? and shot = ?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setInt(2, shot);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    private int getShot(){
        String sql ="select max(shot) from presentShotting where dats = ?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return -1;
    }
    private void getDelay(){
        String sql ="select reasonDelay, typeDelay from auxDelay order by id desc limit 1";
        try {
            pst=conexao.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                reasonDelay = rs.getString(1);
                typeDelay = rs.getString(2);
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
        outputShot = new javax.swing.JLabel();
        txtShot = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Linha de Montagem");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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

        outputShot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        outputShot.setText("1º");

        txtShot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtShot.setText("RODAGEM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDelay)
                            .addComponent(inputWorkFinish))
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonReset)
                            .addComponent(outputBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonLogout)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(outputShot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtShot))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimeToNextWork)
                        .addGap(69, 69, 69)
                        .addComponent(outputTime))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtStation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputStation)))
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
                    .addComponent(outputStation)
                    .addComponent(outputShot)
                    .addComponent(txtShot))
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
    }//GEN-LAST:event_inputWorkFinishActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            BeginProdution beginProdution = new BeginProdution();
            beginTime = beginProdution.getProduction(getShot());
            x++;
            setTime();
            open();
            outputShot.setText(Integer.toString(getShot()));
        }
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        finish();
    }//GEN-LAST:event_formWindowClosing

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
    public static javax.swing.ButtonGroup groupDelay;
    public static javax.swing.ButtonGroup groupWorkFinish;
    private javax.swing.JCheckBox inputDelay;
    private javax.swing.JCheckBox inputWorkFinish;
    private javax.swing.JProgressBar outputBarTime;
    public static javax.swing.JLabel outputShot;
    public static javax.swing.JLabel outputStation;
    private javax.swing.JLabel outputTime;
    private javax.swing.JLabel txtShot;
    private javax.swing.JLabel txtStation;
    private javax.swing.JLabel txtTimeToNextWork;
    // End of variables declaration//GEN-END:variables
}
