package screens;

import functions.GetDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
import functions.AfterDate;
import functions.BeforeDate;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author willi
 */
public class HourHistoricScreen extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int x=0;
    int selects=0;
    String[] begins = new String [12];
    String[] ends = new String [12];
    
    public HourHistoricScreen() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        URL adress = getClass().getResource("/images/icon.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(adress);
        this.setIconImage(icon);
    }
    private void filter(){
        clearShots();
        getBegin();
        printBegins();
        getEnd();
        printEnds();
    }
    public void clearShots(){
        for(int i=0;i<12;i++){
            begins[i] = "";
        }
        for(int i=0;i<12;i++){
            ends[i] = "";
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
    private void getBegin(){
        selects=0;
        String sqlnome = "select min(beginning) from presentshotting where dats = ? group by shot";
        try {
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,inputDateFilter.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                begins[selects] = rs.getString(1);
                selects++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getEnd(){
        selects=0;
        String sqlnome = "select max(ending) from workfinish where dats = ? group by shot";
        try {
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,inputDateFilter.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                ends[selects] = rs.getString(1);
                selects++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void printBegins(){
        for(int i=0;i<selects;i++){
            if(i==0){
                outputBeginningFirstShooting.setText(begins[0]);
            }
            else if(i==1){
                outputBeginningSecondShooting.setText(begins[1]);
            }
            else if(i==2){
                outputBeginningThirdShooting.setText(begins[2]);
            }
            else if(i==3){
                outputBeginningFourthShooting.setText(begins[3]);
            }
            else if(i==4){
                outputBeginningFifthShooting.setText(begins[4]);
            }
            else if(i==5){
                outputBeginningSixthShooting.setText(begins[5]);
            }
            else if(i==6){
                outputBeginningSeventhShooting.setText(begins[6]);
            }
            else if(i==7){
                outputBeginningEighthShooting.setText(begins[7]);
            }
            else if(i==8){
                outputBeginningNinthShooting.setText(begins[8]);
            }
            else if(i==9){
                outputBeginningTenthShooting.setText(begins[9]);
            }
            else if(i==10){
                outputBeginningEleventhShooting.setText(begins[10]);
            }
            else if(i==11){
                outputBeginningTwenlthShooting.setText(begins[11]);
            }
        }
    }
    private void printEnds(){
        for(int i=0;i<selects;i++){
            if(i==0){
                outputEndFirstShooting.setText(ends[0]);
            }
            else if(i==1){
                outputEndSecondShooting.setText(ends[1]);
            }
            else if(i==2){
                outputEndThirdShooting.setText(ends[2]);
            }
            else if(i==3){
                outputEndFourthShooting.setText(ends[3]);
            }
            else if(i==4){
                outputEndFifthShooting.setText(ends[4]);
            }
            else if(i==5){
                outputEndSixthShooting.setText(ends[5]);
            }
            else if(i==6){
                outputEndSeventhShooting.setText(ends[6]);
            }
            else if(i==7){
                outputEndEighthShooting.setText(ends[7]);
            }
            else if(i==8){
                outputEndNinthShooting.setText(ends[8]);
            }
            else if(i==9){
                outputEndTenthShooting.setText(ends[9]);
            }
            else if(i==10){
                outputEndEleventhShooting.setText(ends[10]);
            }
            else if(i==11){
                outputEndTwenlthShooting.setText(ends[11]);
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

        txtShotHistoric = new javax.swing.JLabel();
        imageBefore = new javax.swing.JLabel();
        imageAfter = new javax.swing.JLabel();
        inputDateFilter = new javax.swing.JFormattedTextField();
        buttonFilter = new javax.swing.JButton();
        txtTenthShooting = new javax.swing.JLabel();
        txtEleventhShooting = new javax.swing.JLabel();
        txtTwelfthShooting = new javax.swing.JLabel();
        txtFirstShooting = new javax.swing.JLabel();
        txtSecondShooting = new javax.swing.JLabel();
        txtThirdShooting = new javax.swing.JLabel();
        txtFourthShooting = new javax.swing.JLabel();
        txtFifthShooting = new javax.swing.JLabel();
        txtSixthShooting = new javax.swing.JLabel();
        txtSeventhShooting = new javax.swing.JLabel();
        txtEighthShooting = new javax.swing.JLabel();
        txtNinthShooting = new javax.swing.JLabel();
        txtBeginning = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        outputBeginningFirstShooting = new javax.swing.JTextField();
        outputEndFirstShooting = new javax.swing.JTextField();
        outputBeginningSecondShooting = new javax.swing.JTextField();
        outputEndSecondShooting = new javax.swing.JTextField();
        outputBeginningThirdShooting = new javax.swing.JTextField();
        outputEndThirdShooting = new javax.swing.JTextField();
        outputBeginningFourthShooting = new javax.swing.JTextField();
        outputEndFourthShooting = new javax.swing.JTextField();
        outputBeginningFifthShooting = new javax.swing.JTextField();
        outputEndFifthShooting = new javax.swing.JTextField();
        outputBeginningSixthShooting = new javax.swing.JTextField();
        outputEndSixthShooting = new javax.swing.JTextField();
        outputBeginningSeventhShooting = new javax.swing.JTextField();
        outputEndSeventhShooting = new javax.swing.JTextField();
        outputBeginningEighthShooting = new javax.swing.JTextField();
        outputEndEighthShooting = new javax.swing.JTextField();
        outputBeginningNinthShooting = new javax.swing.JTextField();
        outputEndNinthShooting = new javax.swing.JTextField();
        outputBeginningTenthShooting = new javax.swing.JTextField();
        outputEndTenthShooting = new javax.swing.JTextField();
        outputBeginningEleventhShooting = new javax.swing.JTextField();
        outputEndEleventhShooting = new javax.swing.JTextField();
        outputBeginningTwenlthShooting = new javax.swing.JTextField();
        outputEndTwenlthShooting = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Histórico de Rodagens");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        txtShotHistoric.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtShotHistoric.setText("HISTÓRICO DE RODAGENS");

        imageBefore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LeftArrow.png"))); // NOI18N
        imageBefore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageBeforeMouseClicked(evt);
            }
        });

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

        txtTenthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTenthShooting.setText("10º RODAGEM");

        txtEleventhShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEleventhShooting.setText("11º RODAGEM");

        txtTwelfthShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtTwelfthShooting.setText("12º RODAGEM");

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

        txtBeginning.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtBeginning.setText("INÍCIO");

        txtEnd.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEnd.setText("FIM");

        outputBeginningFirstShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndFirstShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningSecondShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndSecondShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningThirdShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndThirdShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningFourthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndFourthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningFifthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndFifthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningSixthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndSixthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningSeventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndSeventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningEighthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndEighthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningNinthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndNinthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningTenthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndTenthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningEleventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndEleventhShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputBeginningTwenlthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        outputEndTwenlthShooting.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtShotHistoric)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSecondShooting)
                                .addComponent(txtFirstShooting)
                                .addComponent(txtThirdShooting)
                                .addComponent(txtFourthShooting)
                                .addComponent(txtSeventhShooting)
                                .addComponent(txtEighthShooting)
                                .addComponent(txtFifthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSixthShooting))
                            .addComponent(txtNinthShooting)
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
                            .addComponent(outputBeginningSeventhShooting)
                            .addComponent(outputBeginningEighthShooting)
                            .addComponent(outputBeginningNinthShooting)
                            .addComponent(outputBeginningTenthShooting)
                            .addComponent(outputBeginningEleventhShooting)
                            .addComponent(outputBeginningTwenlthShooting)
                            .addComponent(outputBeginningSixthShooting, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(outputEndFirstShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndSecondShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndThirdShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndFourthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndFifthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndSeventhShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndEighthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndNinthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndTenthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndEleventhShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndTwenlthShooting, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputEndSixthShooting)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageBefore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageAfter)
                        .addGap(56, 56, 56)
                        .addComponent(buttonFilter)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(txtBeginning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(182, 182, 182)
                        .addComponent(txtEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(73, 73, 73)))
                .addGap(15, 15, 15))
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
                    .addComponent(outputEndTwenlthShooting, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageBefore)
                    .addComponent(imageAfter)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(inputDateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(buttonFilter)))
                .addGap(81, 81, 81))
        );

        outputBeginningSecondShooting.getAccessibleContext().setAccessibleDescription("");
        outputEndSecondShooting.getAccessibleContext().setAccessibleDescription("");
        outputBeginningThirdShooting.getAccessibleContext().setAccessibleDescription("");
        outputBeginningFourthShooting.getAccessibleContext().setAccessibleName("");

        setSize(new java.awt.Dimension(572, 651));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            getBegin();
            printBegins();
            getEnd();
            printEnds();
        }
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(HourHistoricScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HourHistoricScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HourHistoricScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HourHistoricScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HourHistoricScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
