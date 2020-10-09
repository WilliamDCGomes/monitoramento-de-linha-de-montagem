package screens;

import functions.MinuteToHour;
import functions.CheckInputDailyPlanning;
import javax.swing.JOptionPane;
import connectionbd.ConnectionModule;
import functions.AuxShot;
import functions.ComparableHour;
import functions.GetDate;
import functions.HourToMinute;
import functions.HourToMinuteFromWhile;
import functions.MoreThan24Hour;
import functions.StartShotting;
import functions.TimeDifference;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class DailyPlanningScreen extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
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
    private ArrayList getIds(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        String sql = "select id from planning";
        try {
            pst = connection.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
    private void update(int numberShotting, String start, String end, int id){
        String sql = "update planning set beginning=?,ending=?,shooting=? where id=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,start);
            pst.setString(2,end);
            pst.setInt(3,numberShotting);
            pst.setInt(4,id);
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
        if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndLastShooting.getText().equals("  :  ")){
            JOptionPane.showMessageDialog(null, "INSIRA AO MENOS UMA RODAGEM!");
        }
        else{
            AuxShot auxShot = new AuxShot();
            HourToMinute hourToMinute = new HourToMinute();
            HourToMinuteFromWhile hourToMinuteComparable = new HourToMinuteFromWhile();
            MinuteToHour minuteToHour = new MinuteToHour();
            TimeDifference timeDifference = new TimeDifference();
            ComparableHour comparableHour = new ComparableHour();
            String begin = inputBeginningFirstShooting.getText();
            int aux = 1;
            int end = auxShot.time(begin, inputShootingDuration.getText());
            int saveEnd = 0;
            while(hourToMinuteComparable.getMinute(inputStopLine.getText())>end){
                saveEnd = end;
                if(comparableHour.compare(begin, inputBeginningLanchTime.getText(), 1)&&comparableHour.compare(inputEndLanchTime.getText(), begin, 2)){
                    String lanchDurantion = timeDifference.getDifference(inputBeginningLanchTime.getText(), inputEndLanchTime.getText());
                    begin = minuteToHour.getHour(auxShot.time(begin, lanchDurantion));
                    end = auxShot.time(begin, inputShootingDuration.getText());
                }
                else if(comparableHour.compare(minuteToHour.getHour(end), inputBeginningLanchTime.getText(), 2)&&comparableHour.compare(inputEndLanchTime.getText(), minuteToHour.getHour(end), 2)){
                    String lanchDurantion = timeDifference.getDifference(inputBeginningLanchTime.getText(), inputEndLanchTime.getText());
                    String decrement = minuteToHour.getHour(auxShot.time(lanchDurantion, inputShootingDuration.getText()));
                    end = auxShot.time(begin, decrement);
                }
                add(aux, begin, minuteToHour.getHour(end));
                aux++;
                begin = minuteToHour.getHour(end);
                end = auxShot.time(begin, inputShootingDuration.getText());
            }
            end = saveEnd;
            if(error==false){
                ExistingPlanning existingPlanning = new ExistingPlanning();
                TimeDifference timeDifferences = new TimeDifference();
                if(hourToMinute.getMinute(timeDifference.getDifference(minuteToHour.getHour(end), inputEndLastShooting.getText()), timeDifferences)>0){
                    int time = hourToMinute.getMinute(timeDifference.getDifference(minuteToHour.getHour(end), inputStopLine.getText()), timeDifferences);
                    if(timeDifference.delay.equals("false")){
                        begin = minuteToHour.getHour(end);
                        String duration = minuteToHour.getHour(time);
                        end = auxShot.time(minuteToHour.getHour(end), duration);
                        add(aux, begin, minuteToHour.getHour(end));
                        int lessTime = hourToMinute.getMinute(timeDifference.getDifference(timeDifference.getDifference(begin, minuteToHour.getHour(end)), inputShootingDuration.getText()), timeDifferences);
                        if(lessTime!=0){
                            nextDay(minuteToHour.getHour(lessTime));
                        }
                    }
                    else{
                        nextDay(inputShootingDuration.getText());
                    }
                }
                JOptionPane.showMessageDialog(null,"PLANEJAMENTO DIÁRIO INSERIDO COM SUCESSO");
                this.dispose();
                existingPlanning.setVisible(true);
            }
        }
    }
    private void nextDay(String duration){
        System.out.println(duration);
    }
    private void beforeUpdate(){
        ArrayList<Integer> list = getIds();
        CheckInputDailyPlanning checkInputDailyPlanning = new CheckInputDailyPlanning();
        if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndLastShooting.getText().equals("  :  ")){
            JOptionPane.showMessageDialog(null, "INSIRA AO MENOS UMA RODAGEM!");
        }
        else{
            AuxShot auxShot = new AuxShot();
            HourToMinute hourToMinute = new HourToMinute();
            HourToMinuteFromWhile hourToMinuteComparable = new HourToMinuteFromWhile();
            MinuteToHour minuteToHour = new MinuteToHour();
            TimeDifference timeDifference = new TimeDifference();
            ComparableHour comparableHour = new ComparableHour();
            String begin = inputBeginningFirstShooting.getText();
            int aux = 1;
            int end = auxShot.time(begin, inputShootingDuration.getText());
            int saveEnd = 0;
            while(hourToMinuteComparable.getMinute(inputStopLine.getText())>end){
                saveEnd = end;
                if(comparableHour.compare(begin, inputBeginningLanchTime.getText(), 1)&&comparableHour.compare(inputEndLanchTime.getText(), begin, 2)){
                    String lanchDurantion = timeDifference.getDifference(inputBeginningLanchTime.getText(), inputEndLanchTime.getText());
                    begin = minuteToHour.getHour(auxShot.time(begin, lanchDurantion));
                    end = auxShot.time(begin, inputShootingDuration.getText());
                }
                else if(comparableHour.compare(minuteToHour.getHour(end), inputBeginningLanchTime.getText(), 2)&&comparableHour.compare(inputEndLanchTime.getText(), minuteToHour.getHour(end), 2)){
                    String lanchDurantion = timeDifference.getDifference(inputBeginningLanchTime.getText(), inputEndLanchTime.getText());
                    String decrement = minuteToHour.getHour(auxShot.time(lanchDurantion, inputShootingDuration.getText()));
                    end = auxShot.time(begin, decrement);
                }
                update(aux, begin, minuteToHour.getHour(end), list.get(0));
                list.remove(0);
                aux++;
                begin = minuteToHour.getHour(end);
                end = auxShot.time(begin, inputShootingDuration.getText());
            }
            end = saveEnd;
            if(error==false){
                ExistingPlanning existingPlanning = new ExistingPlanning();
                TimeDifference timeDifferences = new TimeDifference();
                if(hourToMinute.getMinute(timeDifference.getDifference(minuteToHour.getHour(end), inputEndLastShooting.getText()), timeDifferences)>0){
                    int time = hourToMinute.getMinute(timeDifference.getDifference(minuteToHour.getHour(end), inputStopLine.getText()), timeDifferences);
                    if(timeDifference.delay.equals("false")){
                        begin = minuteToHour.getHour(end);
                        String duration = minuteToHour.getHour(time);
                        end = auxShot.time(minuteToHour.getHour(end), duration);
                        update(aux, begin, minuteToHour.getHour(end), list.get(0));
                        list.remove(0);
                        int lessTime = hourToMinute.getMinute(timeDifference.getDifference(timeDifference.getDifference(begin, minuteToHour.getHour(end)), inputShootingDuration.getText()), timeDifferences);
                        if(lessTime!=0){
                            nextDay(minuteToHour.getHour(lessTime));
                        }
                    }
                    else{
                        nextDay(inputShootingDuration.getText());
                    }
                }
                JOptionPane.showMessageDialog(null,"PLANEJAMENTO DIÁRIO ATUALIZADO COM SUCESSO");
                this.dispose();
                existingPlanning.setVisible(true);
            }
        }
    }
    private void trySave(){
        ComparableHour comparableHour = new ComparableHour();
        MoreThan24Hour moreThan24Hour = new MoreThan24Hour();
        if(buttonCancele.isVisible()){
            StartShotting startShotting = new StartShotting();
            if(startShotting.hasAProgramming(getDate.informDate())){
                JOptionPane.showMessageDialog(null, "JÁ FOI INSERIDO UM PLANEJAMENTO PARA O DIA DE HOJE!");
            }
            else if(moreThan24Hour.check(inputBeginningFirstShooting.getText())||moreThan24Hour.check(inputEndLastShooting.getText())||moreThan24Hour.check(inputBeginningLanchTime.getText())||moreThan24Hour.check(inputEndLanchTime.getText())||moreThan24Hour.check(inputShootingDuration.getText())||moreThan24Hour.check(inputStopLine.getText())){
                JOptionPane.showMessageDialog(null, "ENTRADA DE DADOS INCORRETA!");
            }
            else if(inputBeginningFirstShooting.getText().equals(inputEndLastShooting.getText())||inputBeginningLanchTime.getText().equals(inputEndLanchTime.getText())){
                JOptionPane.showMessageDialog(null, "A RODAGEM E O ALMOÇO NÃO PODE TER O MESMO COMEÇO E O MESMO FIM!");
            }
            else if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndLastShooting.getText().equals("  :  ")||inputBeginningLanchTime.getText().equals("  :  ")||inputEndLanchTime.getText().equals("  :  ")||inputShootingDuration.getText().equals("  :  ")||inputStopLine.getText().equals("  :  ")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS!");
            }
            else if(comparableHour.compare(inputEndLastShooting.getText(), inputStopLine.getText(), 1)==false){
                JOptionPane.showMessageDialog(null, "O HORÁRIO DO FIM DO PLANEJAMENTO TEM QUE SER SUPERIOR AO HORÁRIO QUE A LINHA PARA!");
            }
            else if(inputShootingDuration.getText().equals("00:00")){
                JOptionPane.showMessageDialog(null, "A DURAÇÃO DE CADA RODAGEM NÃO PODE SER DE 0 MINUTOS!");
            }
            else if (comparableHour.compare(inputEndLastShooting.getText(), inputBeginningFirstShooting.getText(), 2)&&comparableHour.compare(inputEndLanchTime.getText(), inputBeginningLanchTime.getText(), 2)){
                beforeAdd();
            }
            else{
                JOptionPane.showMessageDialog(null, "O FIM NÃO PODE SER ANTES DO COMEÇO!");
            }
        }
        else{
            StartShotting startShotting = new StartShotting();
            if(moreThan24Hour.check(inputBeginningFirstShooting.getText())||moreThan24Hour.check(inputEndLastShooting.getText())||moreThan24Hour.check(inputBeginningLanchTime.getText())||moreThan24Hour.check(inputEndLanchTime.getText())||moreThan24Hour.check(inputShootingDuration.getText())||moreThan24Hour.check(inputStopLine.getText())){
                JOptionPane.showMessageDialog(null, "ENTRADA DE DADOS INCORRETA!");
            }
            else if(inputBeginningFirstShooting.getText().equals(inputEndLastShooting.getText())||inputBeginningLanchTime.getText().equals(inputEndLanchTime.getText())){
                JOptionPane.showMessageDialog(null, "A RODAGEM E O ALMOÇO NÃO PODE TER O MESMO COMEÇO E O MESMO FIM!");
            }
            else if(inputBeginningFirstShooting.getText().equals("  :  ")||inputEndLastShooting.getText().equals("  :  ")||inputBeginningLanchTime.getText().equals("  :  ")||inputEndLanchTime.getText().equals("  :  ")||inputShootingDuration.getText().equals("  :  ")||inputStopLine.getText().equals("  :  ")){
                JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS!");
            }
            else if(comparableHour.compare(inputEndLastShooting.getText(), inputStopLine.getText(), 1)==false){
                JOptionPane.showMessageDialog(null, "O HORÁRIO DO FIM DO PLANEJAMENTO TEM QUE SER SUPERIOR AO HORÁRIO QUE A LINHA PARA!");
            }
            else if(inputShootingDuration.getText().equals("00:00")){
                JOptionPane.showMessageDialog(null, "A DURAÇÃO DE CADA RODAGEM NÃO PODE SER DE 0 MINUTOS!");
            }
            else if (comparableHour.compare(inputEndLastShooting.getText(), inputBeginningFirstShooting.getText(), 2)&&comparableHour.compare(inputEndLanchTime.getText(), inputBeginningLanchTime.getText(), 2)){
                beforeUpdate();
            }
            else{
                JOptionPane.showMessageDialog(null, "O FIM NÃO PODE SER ANTES DO COMEÇO!");
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
        txtShoots = new javax.swing.JLabel();
        txtLanch = new javax.swing.JLabel();
        txtShootingDuration = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonCancele = new javax.swing.JButton();
        txtBeginning = new javax.swing.JLabel();
        txtEnd = new javax.swing.JLabel();
        inputEndLastShooting = new javax.swing.JFormattedTextField();
        inputBeginningFirstShooting = new javax.swing.JFormattedTextField();
        inputBeginningLanchTime = new javax.swing.JFormattedTextField();
        inputShootingDuration = new javax.swing.JFormattedTextField();
        inputEndLanchTime = new javax.swing.JFormattedTextField();
        txtStopLine = new javax.swing.JLabel();
        inputStopLine = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planejamento");
        setResizable(false);
        getContentPane().setLayout(null);

        txtPlanningDay.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtPlanningDay.setText("PLANEJAMENTO DO DIA");
        getContentPane().add(txtPlanningDay);
        txtPlanningDay.setBounds(120, 20, 277, 32);

        txtShoots.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtShoots.setText("RODAGEM");
        getContentPane().add(txtShoots);
        txtShoots.setBounds(31, 115, 75, 20);

        txtLanch.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtLanch.setText("ALMOÇO");
        getContentPane().add(txtLanch);
        txtLanch.setBounds(31, 153, 63, 20);

        txtShootingDuration.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtShootingDuration.setText("DURAÇÃO RODAGEM");
        getContentPane().add(txtShootingDuration);
        txtShootingDuration.setBounds(31, 187, 152, 20);

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
        buttonSave.setBounds(30, 260, 80, 32);

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
        buttonCancele.setBounds(150, 260, 120, 32);

        txtBeginning.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtBeginning.setText("INÍCIO");
        getContentPane().add(txtBeginning);
        txtBeginning.setBounds(280, 80, 42, 20);

        txtEnd.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtEnd.setText("FIM");
        getContentPane().add(txtEnd);
        txtEnd.setBounds(400, 80, 23, 20);

        try {
            inputEndLastShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndLastShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndLastShooting.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputEndLastShootingFocusGained(evt);
            }
        });
        inputEndLastShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndLastShootingKeyPressed(evt);
            }
        });
        getContentPane().add(inputEndLastShooting);
        inputEndLastShooting.setBounds(400, 110, 72, 24);

        try {
            inputBeginningFirstShooting.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningFirstShooting.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningFirstShooting.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputBeginningFirstShootingFocusGained(evt);
            }
        });
        inputBeginningFirstShooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningFirstShootingKeyPressed(evt);
            }
        });
        getContentPane().add(inputBeginningFirstShooting);
        inputBeginningFirstShooting.setBounds(280, 110, 73, 24);

        try {
            inputBeginningLanchTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputBeginningLanchTime.setText("12:00");
        inputBeginningLanchTime.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputBeginningLanchTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputBeginningLanchTimeFocusGained(evt);
            }
        });
        inputBeginningLanchTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBeginningLanchTimeKeyPressed(evt);
            }
        });
        getContentPane().add(inputBeginningLanchTime);
        inputBeginningLanchTime.setBounds(280, 145, 73, 24);

        try {
            inputShootingDuration.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputShootingDuration.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputShootingDuration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputShootingDurationFocusGained(evt);
            }
        });
        inputShootingDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputShootingDurationKeyPressed(evt);
            }
        });
        getContentPane().add(inputShootingDuration);
        inputShootingDuration.setBounds(280, 180, 73, 24);

        try {
            inputEndLanchTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputEndLanchTime.setText("13:00");
        inputEndLanchTime.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputEndLanchTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputEndLanchTimeFocusGained(evt);
            }
        });
        inputEndLanchTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEndLanchTimeKeyPressed(evt);
            }
        });
        getContentPane().add(inputEndLanchTime);
        inputEndLanchTime.setBounds(400, 145, 72, 24);

        txtStopLine.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtStopLine.setText("HORÁRIO EM QUE A LINHA PARA");
        getContentPane().add(txtStopLine);
        txtStopLine.setBounds(30, 220, 240, 20);

        try {
            inputStopLine.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputStopLine.setText("15:45");
        inputStopLine.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputStopLine.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputStopLineFocusGained(evt);
            }
        });
        inputStopLine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputStopLineKeyPressed(evt);
            }
        });
        getContentPane().add(inputStopLine);
        inputStopLine.setBounds(280, 215, 73, 24);

        setSize(new java.awt.Dimension(522, 330));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        trySave();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void inputBeginningFirstShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningFirstShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndLastShooting.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningFirstShootingKeyPressed

    private void inputEndLastShootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndLastShootingKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputBeginningLanchTime.requestFocus();
        }
    }//GEN-LAST:event_inputEndLastShootingKeyPressed

    private void inputBeginningLanchTimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBeginningLanchTimeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputEndLanchTime.requestFocus();
        }
    }//GEN-LAST:event_inputBeginningLanchTimeKeyPressed

    private void inputEndLanchTimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEndLanchTimeKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputShootingDuration.requestFocus();
        }
    }//GEN-LAST:event_inputEndLanchTimeKeyPressed

    private void inputShootingDurationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputShootingDurationKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputStopLine.requestFocus();
        }
    }//GEN-LAST:event_inputShootingDurationKeyPressed

    private void buttonSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonSaveKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            trySave();
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

    private void inputStopLineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputStopLineKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            trySave();
        }
    }//GEN-LAST:event_inputStopLineKeyPressed

    private void inputBeginningFirstShootingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputBeginningFirstShootingFocusGained
        inputBeginningFirstShooting.selectAll();
    }//GEN-LAST:event_inputBeginningFirstShootingFocusGained

    private void inputEndLastShootingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEndLastShootingFocusGained
        inputEndLastShooting.selectAll();
    }//GEN-LAST:event_inputEndLastShootingFocusGained

    private void inputBeginningLanchTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputBeginningLanchTimeFocusGained
        inputBeginningLanchTime.selectAll();
    }//GEN-LAST:event_inputBeginningLanchTimeFocusGained

    private void inputEndLanchTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEndLanchTimeFocusGained
        inputEndLanchTime.selectAll();
    }//GEN-LAST:event_inputEndLanchTimeFocusGained

    private void inputShootingDurationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputShootingDurationFocusGained
        inputShootingDuration.selectAll();
    }//GEN-LAST:event_inputShootingDurationFocusGained

    private void inputStopLineFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputStopLineFocusGained
        inputStopLine.selectAll();
    }//GEN-LAST:event_inputStopLineFocusGained

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
    public static javax.swing.JFormattedTextField inputBeginningLanchTime;
    public static javax.swing.JFormattedTextField inputEndLanchTime;
    public static javax.swing.JFormattedTextField inputEndLastShooting;
    public static javax.swing.JFormattedTextField inputShootingDuration;
    public static javax.swing.JFormattedTextField inputStopLine;
    private javax.swing.JLabel txtBeginning;
    private javax.swing.JLabel txtEnd;
    private javax.swing.JLabel txtLanch;
    private javax.swing.JLabel txtPlanningDay;
    private javax.swing.JLabel txtShootingDuration;
    private javax.swing.JLabel txtShoots;
    private javax.swing.JLabel txtStopLine;
    // End of variables declaration//GEN-END:variables
}
