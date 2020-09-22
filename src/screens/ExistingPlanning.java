/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import functions.AfterDate;
import functions.BeforeDate;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functions.GetDate;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class ExistingPlanning extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form ExistingPlanning
     */
    public ExistingPlanning() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        URL adress = getClass().getResource("/images/icon.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(adress);
        this.setIconImage(icon);
    }
    int x =0;
    int selects;
    String[] begins = new String [12];
    String[] endins = new String [12];
    private void filter(){
        clearShots();
        getShots();
        printShots();
    }
    public void clearShots(){
        for(int i=0;i<12;i++){
            begins[i] = "";
        }
        for(int i=0;i<12;i++){
            endins[i] = "";
        }
        outputBeginningFirstShooting.setText("");
        outputBeginningSecondShooting.setText("");
        outputBeginningThirdShooting.setText("");
        outputBeginningFourthShooting.setText("");
        outputBeginningFifthShooting.setText("");
        outputBeginningSixthShooting.setText("");
        outputBeginningSeventhShooting.setText("");
        outputBeginningEighthShooting.setText("");
        outputBeginningNinthShooting.setText("");
        outputBeginningTenthShooting.setText("");
        outputBeginningEleventhShooting.setText("");
        outputBeginningTwenlthShooting.setText("");
        outputEndFirstShooting.setText("");
        outputEndSecondShooting.setText("");
        outputEndThirdShooting.setText("");
        outputEndFourthShooting.setText("");
        outputEndFifthShooting.setText("");
        outputEndSixthShooting.setText("");
        outputEndSeventhShooting.setText("");
        outputEndEighthShooting.setText("");
        outputEndNinthShooting.setText("");
        outputEndTenthShooting.setText("");
        outputEndEleventhShooting.setText("");
        outputEndTwenlthShooting.setText("");
    }
    private void getShots(){
        selects=0;
        String sqlnome = "select beginning, ending from planning where dats = ?";
        try {
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,inputDateFilter.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                begins[selects] = rs.getString(1);
                endins[selects] = rs.getString(2);
                selects++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void printShots(){
        for(int i=0;i<selects;i++){
            if(i==0){
                outputBeginningFirstShooting.setText(begins[0]);
                outputEndFirstShooting.setText(endins[0]);
            }
            else if(i==1){
                outputBeginningSecondShooting.setText(begins[1]);
                outputEndSecondShooting.setText(endins[1]);
            }
            else if(i==2){
                outputBeginningThirdShooting.setText(begins[2]);
                outputEndThirdShooting.setText(endins[2]);
            }
            else if(i==3){
                outputBeginningFourthShooting.setText(begins[3]);
                outputEndFourthShooting.setText(endins[3]);
            }
            else if(i==4){
                outputBeginningFifthShooting.setText(begins[4]);
                outputEndFifthShooting.setText(endins[4]);
            }
            else if(i==5){
                outputBeginningSixthShooting.setText(begins[5]);
                outputEndSixthShooting.setText(endins[5]);
            }
            else if(i==6){
                outputBeginningSeventhShooting.setText(begins[6]);
                outputEndSeventhShooting.setText(endins[6]);
            }
            else if(i==7){
                outputBeginningEighthShooting.setText(begins[7]);
                outputEndEighthShooting.setText(endins[7]);
            }
            else if(i==8){
                outputBeginningNinthShooting.setText(begins[8]);
                outputEndNinthShooting.setText(endins[8]);
            }
            else if(i==9){
                outputBeginningTenthShooting.setText(begins[9]);
                outputEndTenthShooting.setText(endins[9]);
            }
            else if(i==10){
                outputBeginningEleventhShooting.setText(begins[10]);
                outputEndEleventhShooting.setText(endins[10]);
            }
            else if(i==11){
                outputBeginningTwenlthShooting.setText(begins[11]);
                outputEndTwenlthShooting.setText(endins[11]);
            }
        }
    }
    private void sendShots(){
        DailyPlanningScreen dailyPlanningScreen = new DailyPlanningScreen();
        dailyPlanningScreen.buttonCancele.setVisible(false);
        for(int i=0;i<selects;i++){
            if(i==0){
                dailyPlanningScreen.inputBeginningFirstShooting.setText(outputBeginningFirstShooting.getText());
                dailyPlanningScreen.inputEndFirstShooting.setText(outputEndFirstShooting.getText());
            }
            else if(i==1){
                dailyPlanningScreen.inputBeginningSecondShooting.setText(outputBeginningSecondShooting.getText());
                dailyPlanningScreen.inputEndSecondShooting.setText(outputEndSecondShooting.getText());
            }
            else if(i==2){
                dailyPlanningScreen.inputBeginningThirdShooting.setText(outputBeginningThirdShooting.getText());
                dailyPlanningScreen.inputEndThirdShooting.setText(outputEndThirdShooting.getText());
            }
            else if(i==3){
                dailyPlanningScreen.inputBeginningFourthShooting.setText(outputBeginningFourthShooting.getText());
                dailyPlanningScreen.inputEndFourthShooting.setText(outputEndFourthShooting.getText());
            }
            else if(i==4){
                dailyPlanningScreen.inputBeginningFifthShooting.setText(outputBeginningFifthShooting.getText());
                dailyPlanningScreen.inputEndFifthShooting.setText(outputEndFifthShooting.getText());
            }
            else if(i==5){
                dailyPlanningScreen.inputBeginningSixthShooting.setText(outputBeginningSixthShooting.getText());
                dailyPlanningScreen.inputEndSixthShooting.setText(outputEndSixthShooting.getText());
            }
            else if(i==6){
                dailyPlanningScreen.inputBeginningSeventhShooting.setText(outputBeginningSeventhShooting.getText());
                dailyPlanningScreen.inputEndSeventhShooting.setText(outputEndSeventhShooting.getText());
            }
            else if(i==7){
                dailyPlanningScreen.inputBeginningEighthShooting.setText(outputBeginningEighthShooting.getText());
                dailyPlanningScreen.inputEndEighthShooting.setText(outputEndEighthShooting.getText());
            }
            else if(i==8){
                dailyPlanningScreen.inputBeginningNinthShooting.setText(outputBeginningNinthShooting.getText());
                dailyPlanningScreen.inputEndNinthShooting.setText(outputEndNinthShooting.getText());
            }
            else if(i==9){
                dailyPlanningScreen.inputBeginningTenthShooting.setText(outputBeginningTenthShooting.getText());
                dailyPlanningScreen.inputEndTenthShooting.setText(outputEndTenthShooting.getText());
            }
            else if(i==10){
                dailyPlanningScreen.inputBeginningEleventhShooting.setText(outputBeginningEleventhShooting.getText());
                dailyPlanningScreen.inputEndEleventhShooting.setText(outputEndEleventhShooting.getText());
            }
            else if(i==11){
                dailyPlanningScreen.inputBeginningTwenlthShooting.setText(outputBeginningTwenlthShooting.getText());
                dailyPlanningScreen.inputEndTwenlthShooting.setText(outputEndTwenlthShooting.getText());
            }
        }
        this.dispose();
        dailyPlanningScreen.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtShotHistoric = new javax.swing.JLabel();
        outputEndSecondShooting = new javax.swing.JTextField();
        imageBefore = new javax.swing.JLabel();
        outputBeginningThirdShooting = new javax.swing.JTextField();
        imageAfter = new javax.swing.JLabel();
        inputDateFilter = new javax.swing.JFormattedTextField();
        outputEndThirdShooting = new javax.swing.JTextField();
        outputBeginningFourthShooting = new javax.swing.JTextField();
        buttonFilter = new javax.swing.JButton();
        outputEndFourthShooting = new javax.swing.JTextField();
        txtTenthShooting = new javax.swing.JLabel();
        outputBeginningFifthShooting = new javax.swing.JTextField();
        txtEleventhShooting = new javax.swing.JLabel();
        outputEndFifthShooting = new javax.swing.JTextField();
        txtTwelfthShooting = new javax.swing.JLabel();
        outputBeginningSixthShooting = new javax.swing.JTextField();
        txtFirstShooting = new javax.swing.JLabel();
        outputEndSixthShooting = new javax.swing.JTextField();
        txtSecondShooting = new javax.swing.JLabel();
        outputBeginningSeventhShooting = new javax.swing.JTextField();
        txtThirdShooting = new javax.swing.JLabel();
        outputEndSeventhShooting = new javax.swing.JTextField();
        txtFourthShooting = new javax.swing.JLabel();
        outputBeginningEighthShooting = new javax.swing.JTextField();
        txtFifthShooting = new javax.swing.JLabel();
        txtSixthShooting = new javax.swing.JLabel();
        outputEndEighthShooting = new javax.swing.JTextField();
        outputBeginningNinthShooting = new javax.swing.JTextField();
        outputEndNinthShooting = new javax.swing.JTextField();
        outputBeginningTenthShooting = new javax.swing.JTextField();
        outputEndTenthShooting = new javax.swing.JTextField();
        outputBeginningEleventhShooting = new javax.swing.JTextField();
        outputEndEleventhShooting = new javax.swing.JTextField();
        txtSeventhShooting = new javax.swing.JLabel();
        outputBeginningTwenlthShooting = new javax.swing.JTextField();
        txtEighthShooting = new javax.swing.JLabel();
        outputEndTwenlthShooting = new javax.swing.JTextField();
        txtNinthShooting = new javax.swing.JLabel();
        txtBeginning = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        outputBeginningFirstShooting = new javax.swing.JTextField();
        outputEndFirstShooting = new javax.swing.JTextField();
        outputBeginningSecondShooting = new javax.swing.JTextField();
        buttonEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planejamento Existente");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        txtShotHistoric.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtShotHistoric.setText("PLANEJAMENTO EXISTENTE");

        outputEndSecondShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        imageBefore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeftArrow.png"))); // NOI18N
        imageBefore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageBeforeMouseClicked(evt);
            }
        });

        outputBeginningThirdShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        imageAfter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RightArrow.png"))); // NOI18N
        imageAfter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAfterMouseClicked(evt);
            }
        });

        try {
            inputDateFilter.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputDateFilter.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        inputDateFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputDateFilterKeyPressed(evt);
            }
        });

        outputEndThirdShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningFourthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        buttonFilter.setText("FILTRAR");
        buttonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilterActionPerformed(evt);
            }
        });
        buttonFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonFilterKeyPressed(evt);
            }
        });

        outputEndFourthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTenthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTenthShooting.setText("10º RODAGEM");

        outputBeginningFifthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEleventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEleventhShooting.setText("11º RODAGEM");

        outputEndFifthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTwelfthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTwelfthShooting.setText("12º RODAGEM");

        outputBeginningSixthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFirstShooting.setText("1º RODAGEM");

        outputEndSixthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtSecondShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSecondShooting.setText("2º RODAGEM");

        outputBeginningSeventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        outputBeginningSeventhShooting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputBeginningSeventhShootingActionPerformed(evt);
            }
        });

        txtThirdShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtThirdShooting.setText("3º RODAGEM");

        outputEndSeventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtFourthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFourthShooting.setText("4º RODAGEM");

        outputBeginningEighthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtFifthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtFifthShooting.setText("5º RODAGEM");

        txtSixthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSixthShooting.setText("6º RODAGEM");

        outputEndEighthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningNinthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndNinthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningTenthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndTenthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningEleventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndEleventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtSeventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtSeventhShooting.setText("7º RODAGEM");

        outputBeginningTwenlthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEighthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEighthShooting.setText("8º RODAGEM");

        outputEndTwenlthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtNinthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtNinthShooting.setText("9º RODAGEM");

        txtBeginning.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtBeginning.setText("INÍCIO");

        txtEnd.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEnd.setText("FIM");

        outputBeginningFirstShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndFirstShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningSecondShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        buttonEdit.setText("EDITAR");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        buttonEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonEditKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtShotHistoric)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imageBefore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageAfter)
                        .addGap(56, 56, 56)
                        .addComponent(buttonFilter)
                        .addGap(34, 34, 34)
                        .addComponent(buttonEdit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(txtBeginning, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                .addGap(182, 182, 182)
                                .addComponent(txtEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSecondShooting)
                                        .addComponent(txtFirstShooting)
                                        .addComponent(txtThirdShooting)
                                        .addComponent(txtFourthShooting)
                                        .addComponent(txtFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSixthShooting)
                                        .addComponent(txtSeventhShooting)
                                        .addComponent(txtEighthShooting)
                                        .addComponent(txtNinthShooting))
                                    .addComponent(txtTenthShooting)
                                    .addComponent(txtEleventhShooting)
                                    .addComponent(txtTwelfthShooting))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outputBeginningFirstShooting)
                                    .addComponent(outputBeginningSecondShooting)
                                    .addComponent(outputBeginningThirdShooting)
                                    .addComponent(outputBeginningFourthShooting)
                                    .addComponent(outputBeginningFifthShooting)
                                    .addComponent(outputBeginningSixthShooting, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputBeginningSeventhShooting)
                                    .addComponent(outputBeginningEighthShooting)
                                    .addComponent(outputBeginningNinthShooting)
                                    .addComponent(outputBeginningTenthShooting)
                                    .addComponent(outputBeginningEleventhShooting)
                                    .addComponent(outputBeginningTwenlthShooting))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outputEndFirstShooting)
                                    .addComponent(outputEndSecondShooting)
                                    .addComponent(outputEndThirdShooting)
                                    .addComponent(outputEndFourthShooting)
                                    .addComponent(outputEndFifthShooting)
                                    .addComponent(outputEndSixthShooting)
                                    .addComponent(outputEndSeventhShooting)
                                    .addComponent(outputEndEighthShooting, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputEndNinthShooting, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputEndTenthShooting)
                                    .addComponent(outputEndEleventhShooting)
                                    .addComponent(outputEndTwenlthShooting))))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(txtShotHistoric)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBeginning, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEnd, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstShooting)
                            .addComponent(outputBeginningFirstShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(outputEndFirstShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputBeginningSecondShooting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(outputEndSecondShooting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSecondShooting))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputBeginningThirdShooting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtThirdShooting)
                                .addComponent(outputEndThirdShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFourthShooting)
                            .addComponent(outputBeginningFourthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(outputEndFourthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFifthShooting)
                        .addComponent(outputBeginningFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(outputEndFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSixthShooting)
                    .addComponent(outputBeginningSixthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndSixthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeventhShooting)
                    .addComponent(outputBeginningSeventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndSeventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEighthShooting)
                    .addComponent(outputBeginningEighthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndEighthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNinthShooting)
                    .addComponent(outputBeginningNinthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndNinthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenthShooting)
                    .addComponent(outputBeginningTenthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndTenthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEleventhShooting)
                    .addComponent(outputBeginningEleventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndEleventhShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTwelfthShooting)
                    .addComponent(outputBeginningTwenlthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputEndTwenlthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageBefore)
                    .addComponent(imageAfter)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(inputDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonFilter)
                            .addComponent(buttonEdit))))
                .addGap(57, 57, 57))
        );

        setSize(new java.awt.Dimension(617, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imageBeforeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageBeforeMouseClicked
        if(inputDateFilter.getText().equals("  /  /    ")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA!");
        }
        else if(inputDateFilter.getText().length()<10){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA CORRETA!");
        }
        else{
            BeforeDate beforeDate = new BeforeDate();
            try {
                inputDateFilter.setText(beforeDate.informDate(inputDateFilter.getText()));
                filter();
            } catch (ParseException ex) {
                Logger.getLogger(HourHistoricScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_imageBeforeMouseClicked

    private void imageAfterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAfterMouseClicked
        if(inputDateFilter.getText().equals("  /  /    ")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA!");
        }
        else if(inputDateFilter.getText().length()<10){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA CORRETA!");
        }
        else{
            AfterDate afterDate = new AfterDate();
            try {
                inputDateFilter.setText(afterDate.informDate(inputDateFilter.getText()));
                filter();
            } catch (ParseException ex) {
                Logger.getLogger(HourHistoricScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_imageAfterMouseClicked

    private void inputDateFilterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputDateFilterKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputDateFilter.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA!");
            }
            else{
                filter();
            }
        }
    }//GEN-LAST:event_inputDateFilterKeyPressed

    private void buttonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilterActionPerformed
        if(inputDateFilter.getText().equals("")){
            JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA!");
        }
        else{
            filter();
        }
    }//GEN-LAST:event_buttonFilterActionPerformed

    private void buttonFilterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonFilterKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputDateFilter.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, INSIRA UMA DATA!");
            }
            else{
                filter();
            }
        }
    }//GEN-LAST:event_buttonFilterKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            x++;
            GetDate getDate = new GetDate();
            inputDateFilter.setText(getDate.informDate());
            filter();
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        GetDate getDate = new GetDate();
        if(inputDateFilter.getText().equals(getDate.informDate())){
            sendShots();
        }
        else{
            JOptionPane.showMessageDialog(null, "VOCÊ SÓ PODE ALTERAR O PLANEJAMENTO DO DIA DE HOJE!");
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonEditKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            GetDate getDate = new GetDate();
            if(inputDateFilter.getText().equals(getDate.informDate())){
                sendShots();
            }
            else{
                JOptionPane.showMessageDialog(null, "VOCÊ SÓ PODE ALTERAR O PLANEJAMENTO DO DIA DE HOJE!");
            }
        }
    }//GEN-LAST:event_buttonEditKeyPressed

    private void outputBeginningSeventhShootingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputBeginningSeventhShootingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputBeginningSeventhShootingActionPerformed

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
            java.util.logging.Logger.getLogger(ExistingPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExistingPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExistingPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExistingPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExistingPlanning().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonFilter;
    private javax.swing.JLabel imageAfter;
    private javax.swing.JLabel imageBefore;
    private javax.swing.JFormattedTextField inputDateFilter;
    private javax.swing.JTextField outputBeginningEighthShooting;
    private javax.swing.JTextField outputBeginningEleventhShooting;
    private javax.swing.JTextField outputBeginningFifthShooting;
    private javax.swing.JTextField outputBeginningFirstShooting;
    private javax.swing.JTextField outputBeginningFourthShooting;
    private javax.swing.JTextField outputBeginningNinthShooting;
    private javax.swing.JTextField outputBeginningSecondShooting;
    private javax.swing.JTextField outputBeginningSeventhShooting;
    private javax.swing.JTextField outputBeginningSixthShooting;
    private javax.swing.JTextField outputBeginningTenthShooting;
    private javax.swing.JTextField outputBeginningThirdShooting;
    private javax.swing.JTextField outputBeginningTwenlthShooting;
    private javax.swing.JTextField outputEndEighthShooting;
    private javax.swing.JTextField outputEndEleventhShooting;
    private javax.swing.JTextField outputEndFifthShooting;
    private javax.swing.JTextField outputEndFirstShooting;
    private javax.swing.JTextField outputEndFourthShooting;
    private javax.swing.JTextField outputEndNinthShooting;
    private javax.swing.JTextField outputEndSecondShooting;
    private javax.swing.JTextField outputEndSeventhShooting;
    private javax.swing.JTextField outputEndSixthShooting;
    private javax.swing.JTextField outputEndTenthShooting;
    private javax.swing.JTextField outputEndThirdShooting;
    private javax.swing.JTextField outputEndTwenlthShooting;
    private javax.swing.JLabel txtBeginning;
    private javax.swing.JLabel txtEighthShooting;
    private javax.swing.JLabel txtEleventhShooting;
    private javax.swing.JLabel txtEnd;
    private javax.swing.JLabel txtFifthShooting;
    private javax.swing.JLabel txtFirstShooting;
    private javax.swing.JLabel txtFourthShooting;
    private javax.swing.JLabel txtNinthShooting;
    private javax.swing.JLabel txtSecondShooting;
    private javax.swing.JLabel txtSeventhShooting;
    private javax.swing.JLabel txtShotHistoric;
    private javax.swing.JLabel txtSixthShooting;
    private javax.swing.JLabel txtTenthShooting;
    private javax.swing.JLabel txtThirdShooting;
    private javax.swing.JLabel txtTwelfthShooting;
    // End of variables declaration//GEN-END:variables
}
