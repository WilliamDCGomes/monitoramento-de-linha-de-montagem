package screens;

import java.awt.Frame;
import javax.swing.JOptionPane;
import connectionbd.ConnectionModule;
import functions.AuxShot;
import functions.BarProgress;
import functions.BeginPresentShot;
import functions.BeginProdution;
import functions.GetDate;
import functions.GetHour;
import functions.GetTimeOfShot;
import functions.GetYesterdayDate;
import functions.HourDefault;
import functions.HourToMinute;
import functions.InputDelay;
import functions.InsertManyTime;
import functions.ManyTime;
import functions.MinuteToHour;
import functions.RemoveDelay;
import functions.ShotAfterPlanning;
import functions.StartShotting;
import functions.StationWorking;
import functions.ThisStationIsWorking;
import functions.TimeDifference;
import functions.TimeToSet;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author willi
 */
public class WorkerScreen extends javax.swing.JFrame {
    public String login = "";
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs3 = null;
    PreparedStatement pst4 = null;
    ResultSet rs4 = null;
    TimeToSet timeToSet = new TimeToSet(this);
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
    boolean finished = false;
    public int x = 0;
    public boolean informMoreShots=false;
    String beginNoMoreShots;
    public String beginDelay;
    public String endWork;
    ShotAfterPlanning shotAfterPlanning = new ShotAfterPlanning();
    public WorkerScreen(){
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        URL adress = getClass().getResource("/images/icon.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(adress);
        this.setIconImage(icon);
    }
    private void increaseConnections(){
        String sql = "set global max_connections = 100000;";
        try {
            pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void setTime(){
        TimeDifference timeDifference = new TimeDifference();
        BarProgress barProgress = new BarProgress(this);
        int delay = 100;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 30000;  // intervalo no qual a tarefa será executada.
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ThisStationIsWorking thisStationIsWorking = new ThisStationIsWorking();
                if(finished==true && thisStationIsWorking.isWorking(login)&&(inputWorkFinish.isSelected()||inputDelay.isSelected())){
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                    finished=false;
                }
                BeginPresentShot beginPresentShot = new BeginPresentShot();
                AuxShot auxShot = new AuxShot();
                MinuteToHour minuteToHour = new MinuteToHour();
                String endTime2 = "";
                ManyTime manyTime = new ManyTime();
                endTime2 = minuteToHour.getHour(auxShot.time( beginPresentShot.getBegin(getShot()), minuteToHour.getHour(manyTime.check()) ));
                if(informMoreShots==true){
                    outputBarTime.setValue(0);
                    outputTime.setForeground(Color.green);
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                    timer.cancel();
                }
                else{
                    String difference = timeDifference.getDifference(getHour.informHour(), endTime2);
                    if(timeDifference.delay.equals("true")){
                        outputTime.setForeground(Color.red);
                        outputTime.setText(difference);
                        outputBarTime.setValue(100);
                    }
                    else{
                        outputTime.setForeground(Color.black);
                        outputTime.setText(difference);
                        barProgress.setBar(beginPresentShot.getBegin(getShot()), endTime2);
                    }
                }
            }
        }, delay, interval);
    }
    private void finish(){
        String sql = "update stations set working=0 where id=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1,Integer.parseInt(outputStation.getText()));
            pst2.executeUpdate();
            finished=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void open(){
        String sql = "update stations set working=1 where id=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setInt(1,Integer.parseInt(outputStation.getText()));
            pst2.executeUpdate();
            finished=false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void getId(){
        String sql ="select max(id) from workfinish where station = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, outputStation.getText());
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
                pst2=connection.prepareStatement(sql);
                getId();
                pst2.setInt(1, id);
                int apagado = pst2.executeUpdate();
                if(apagado>0){
                    removeDelay.remove(id);
                    open();
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                    JOptionPane.showMessageDialog(null, "DADOS DELETADOS!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void addService(){
        String sql = "insert into workfinish(dats,beginning,ending,station,shot) values(?,?,?,?,?)";
        try {
            pst2 = connection.prepareStatement(sql);
            pst2.setString(1,getDate.informDate());
            if(informMoreShots==false){
                pst2.setString(2,getBegin(getShot()));
            }
            else{
                pst2.setString(2,beginNoMoreShots);
            }
            pst2.setString(3,getHour.informHour());
            pst2.setString(4,outputStation.getText());
            if(informMoreShots==false){
                pst2.setInt(5,getShot());
            }
            else{
                pst2.setInt(5,Integer.parseInt(outputShot.getText()));
            }
            pst2.executeUpdate();
            JOptionPane.showMessageDialog(null,"SERVIÇO ADICIONADO COM SUCESSO");
            finish();
            if(informMoreShots==false && hasOtherPlanning()){
                BeginPresentShot beginPresentShot = new BeginPresentShot();
                AuxShot auxShot = new AuxShot();
                MinuteToHour minuteToHour = new MinuteToHour();
                String endTime2 = "";
                ManyTime manyTime = new ManyTime();
                endTime2 = minuteToHour.getHour(auxShot.time( beginPresentShot.getBegin(getShot()), minuteToHour.getHour(manyTime.check()) ));
                beginDelay = endTime2;
                endWork = getHour.informHour();
                TimeDifference timeDifferences = new TimeDifference();
                HourToMinute hourToMinute = new HourToMinute();
                int minuteComparable = hourToMinute.getMinute(timeDifferences.getDifference(endWork, beginDelay));
                if(timeDifferences.delay.equals("true")){
                    minuteComparable *= -1;
                }
                if(minuteComparable<0){
                    getDelay();
                    InputDelay inputDelay = new InputDelay();
                    inputDelay.makeInput(reasonDelay,typeDelay);
                    insertDelayAux();
                    if(stationWorking.hasStation()==false){
                        InsertManyTime insertManyTime = new InsertManyTime();
                        GetTimeOfShot getTimeOfShot = new GetTimeOfShot();
                        insertManyTime.update(getTimeOfShot.getTime(getShot() + 1));
                    }
                }
                else{
                    if(inputDelay.isSelected()){
                        JOptionPane.showMessageDialog(null, "O ATRASO NÃO FOI ADICIONADO PELO FATO QUE TER FINALIZADO O SERVIÇO DENTRO DO HORÁRIO");
                        groupDelay.clearSelection();
                    }
                    if(stationWorking.hasStation()==false){
                        TimeDifference times = new TimeDifference();
                        if(!(hourToMinute.getMinute(times.getDifference(beginDelay, endWork))<0)){
                            InsertManyTime insertManyTime = new InsertManyTime();
                            int timeThatHas = hourToMinute.getMinute(outputTime.getText());
                            GetTimeOfShot getTimeOfShot = new GetTimeOfShot();
                            insertManyTime.update(timeThatHas + getTimeOfShot.getTime(getShot() + 1));
                        }
                    }
                }
                timeToSet.timeSet(getShot());
            }
            else{
                groupWorkFinish.clearSelection();
                groupDelay.clearSelection();
                beginNoMoreShots = getHour.informHour();
                if(informMoreShots==false){
                    informMoreShots=true;
                    JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS PLANEJAMENTO PARA SER SEGUIDO!");
                    outputBarTime.setValue(0);
                    outputTime.setForeground(Color.green);
                    outputTime.setText("00:00");
                }
                outputShot.setText(Integer.toString(Integer.parseInt(outputShot.getText())+1));
                shotAfterPlanning.insertShotAfterPlanning(Integer.parseInt(outputShot.getText()), login, beginNoMoreShots);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void insertDelayAux(){
        String sql = "insert into insertAuxDelay(dats,shot) values(?,?)";
        try {
            pst3 = connection.prepareStatement(sql);
            pst3.setString(1,getDate.informDate());
            pst3.setInt(2,getShot());
            pst3.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean hasOtherPlanning(){
        GetYesterdayDate getYesterdayDate = new GetYesterdayDate();
        HourDefault hourDefault = new HourDefault();
        int shots = getShot()+1;
        if(startShotting.hasAProgramming(getDate.informDate())){
            String sql ="select id from planning where dats = ? and shooting = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, getDate.informDate());
                pst.setInt(2, shots);
                rs= pst.executeQuery();
                if(rs.next()){
                    return true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return false;
        }
        else if(startShotting.hasAProgramming(getYesterdayDate.informDate())){
            String sql ="select id from planning where dats = ? and shooting = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, getYesterdayDate.informDate());
                pst.setInt(2, shots);
                rs= pst.executeQuery();
                if(rs.next()){
                    return true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return false;
        }
        else if(hourDefault.getHour(shots)==null){
            return false;
        }
        else{
            return true;
        }
    }
    private String getBegin(int shot){
        String sql ="select beginning from presentShotting where dats = ? and shot = ?";
        try {
            pst=connection.prepareStatement(sql);
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
            pst=connection.prepareStatement(sql);
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
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                reasonDelay = rs.getString(1);
                typeDelay = rs.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean hasProduction(){
        String sql ="select id from workfinish where shot=? and station=? and dats=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(outputShot.getText()));
            pst.setString(2, outputStation.getText());
            pst.setString(3, getDate.informDate());
            rs= pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private boolean hasDelay(){
        String sql ="select id from delay where shot=? and localeOfDelay=? and dats=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(outputShot.getText()));
            pst.setInt(2, Integer.parseInt(outputStation.getText()));
            pst.setString(3, getDate.informDate());
            rs= pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
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
        buttonRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Linha de Montagem");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtTimeToNextWork.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTimeToNextWork.setText("TEMPO RESTANTE ATÉ A RODAGEM");

        outputTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outputTime.setText("00:00");

        outputBarTime.setBackground(new java.awt.Color(128, 128, 128));
        outputBarTime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        outputBarTime.setForeground(new java.awt.Color(0, 0, 0));
        outputBarTime.setStringPainted(true);

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
        buttonReset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonResetKeyPressed(evt);
            }
        });

        buttonLogout.setText("LOGOUT");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });
        buttonLogout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonLogoutKeyPressed(evt);
            }
        });

        txtStation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        txtStation.setText("ESTAÇÃO");

        outputStation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        outputStation.setText("1");

        outputShot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        outputShot.setText("1");

        txtShot.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtShot.setText("º   RODAGEM");

        buttonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputWorkFinish)
                            .addComponent(inputDelay))
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(outputShot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtShot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(outputBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonReset)
                            .addComponent(buttonLogout)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtStation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputStation))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimeToNextWork)
                        .addGap(63, 63, 63)
                        .addComponent(outputTime, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimeToNextWork)
                    .addComponent(outputTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputBarTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputWorkFinish)
                    .addComponent(buttonReset))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDelay)
                    .addComponent(buttonLogout))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStation)
                            .addComponent(outputStation)
                            .addComponent(outputShot)
                            .addComponent(txtShot))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRefresh)
                        .addContainerGap())))
        );

        setSize(new java.awt.Dimension(568, 258));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputDelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDelayActionPerformed
        ThisStationIsWorking thisStationIsWorking = new ThisStationIsWorking();
        if(informMoreShots==true){
            groupDelay.clearSelection();
            JOptionPane.showMessageDialog(null, "NÃO É NECESSÁRIO ADICIONAR UM ATRASO POIS VOCÊ NÃO ESTÁ MAIS SEGUINDO UM PLANEJAMENTO");
        }
        else if(informMoreShots==false&&thisStationIsWorking.isWorking(login)==false){
            JOptionPane.showMessageDialog(null, "O SERVIÇO JÁ FOI FINALIZADO, CASO NECESSÁRIO CLIQUE NO BOTÃO RESET PARA FINALIIZAR O SERVIÇO NOVAMENTE");
            TimeDifference timeDifference = new TimeDifference();
            BeginPresentShot beginPresentShot = new BeginPresentShot();
            AuxShot auxShot = new AuxShot();
            MinuteToHour minuteToHour = new MinuteToHour();
            String endTime2 = "";
            ManyTime manyTime = new ManyTime();
            endTime2 = minuteToHour.getHour(auxShot.time( beginPresentShot.getBegin(getShot()), minuteToHour.getHour(manyTime.check()) ));
            timeDifference.getDifference(getHour.informHour(), endTime2);
            if(timeDifference.delay.equals("false")){
                groupDelay.clearSelection();
            }
        }
        else{
            DelayScreen delayScreen = new DelayScreen();
            delayScreen.workerScreen = this;
            delayScreen.setVisible(true);
        }
    }//GEN-LAST:event_inputDelayActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        if(inputWorkFinish.isSelected()){
            removeService();
        }
        else{
            groupDelay.clearSelection();
        }
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
        ThisStationIsWorking thisStationIsWorking = new ThisStationIsWorking();
        if(informMoreShots==false&&thisStationIsWorking.isWorking(login)==false){
            JOptionPane.showMessageDialog(null, "O SERVIÇO JÁ FOI FINALIZADO, CASO NECESSÁRIO CLIQUE NO BOTÃO RESET PARA FINALIIZAR O SERVIÇO NOVAMENTE");
        }
        else{
            if(informMoreShots==false){
                TimeDifference timeDifference = new TimeDifference();
                String endTime2 = "";
                BeginPresentShot beginPresentShot = new BeginPresentShot();
                AuxShot auxShot = new AuxShot();
                MinuteToHour minuteToHour = new MinuteToHour();
                ManyTime manyTime = new ManyTime();
                endTime2 = minuteToHour.getHour(auxShot.time( beginPresentShot.getBegin(getShot()), minuteToHour.getHour(manyTime.check()) ));
                timeDifference.getDifference(getHour.informHour(), endTime2);
                if(timeDifference.delay.equals("true")&&inputDelay.isSelected()==false&&informMoreShots==false){
                    JOptionPane.showMessageDialog(null, "ADICIONE O MOTIVO DO ATRASO ANTES DE SALVAR!");
                    groupWorkFinish.clearSelection();
                }
                else{
                    addService();
                }
            }
            else{
                addService();
            }
        }
    }//GEN-LAST:event_inputWorkFinishActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(x==0){
            increaseConnections();
            boolean aux = false;
            x++;
            StartShotting startShotting = new StartShotting();
            startShotting.startProduction();
            BeginProdution beginProdution = new BeginProdution();
            beginTime = beginProdution.getProduction(getShot());
            outputShot.setText(Integer.toString(getShot()));
            if(aux==false){
                setTime();
                open();
            }
            if(!hasOtherPlanning()){
                aux = true;
                beginNoMoreShots = getHour.informHour();
                if(informMoreShots==false){
                    informMoreShots=true;
                    JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS PLANEJAMENTO PARA SER SEGUIDO!");
                    groupWorkFinish.clearSelection();
                    groupDelay.clearSelection();
                    outputBarTime.setValue(0);
                    outputTime.setForeground(Color.green);
                    outputTime.setText("00:00");
                    outputShot.setText(Integer.toString(shotAfterPlanning.getShotAfterPlanning(login)));
                    beginNoMoreShots = shotAfterPlanning.beginNoMoreshots;
                }
            }
            else if(hasProduction()){
                inputWorkFinish.setSelected(true);
                if(hasDelay()){
                    inputDelay.setSelected(true);
                }
                finish();
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        finish();
    }//GEN-LAST:event_formWindowClosing

    private void buttonResetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonResetKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputWorkFinish.isSelected()){
                removeService();
            }
            else{
                groupDelay.clearSelection();
            }
        }
    }//GEN-LAST:event_buttonResetKeyPressed

    private void buttonLogoutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonLogoutKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA FAZER LOGOUT?\nCASO FAÇA SEM FINALIZAR O SERVIÇO, OS DADOS SERÃO PERDIDOS!","ATENÇÃO",JOptionPane.YES_NO_OPTION);
            if(confirma==JOptionPane.YES_OPTION){
                LoginScreen loginScreen = new LoginScreen();
                Frame[] frames = getFrames(); 
                for (int i = 0; i < frames.length; i++){ 
                    frames[i].dispose(); 
                }
                loginScreen.setVisible(true);
            }
        }
    }//GEN-LAST:event_buttonLogoutKeyPressed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        String shotActual = outputShot.getText();
        outputShot.setText(Integer.toString(getShot()));
        if(!outputShot.getText().equals(shotActual)){
            groupWorkFinish.clearSelection();
            groupDelay.clearSelection();
            open();
            TimeDifference timeDifference = new TimeDifference();
            BarProgress barProgress = new BarProgress(this);
            String endTime2 = "";
            BeginPresentShot beginPresentShot = new BeginPresentShot();
            AuxShot auxShot = new AuxShot();
            MinuteToHour minuteToHour = new MinuteToHour();
            ManyTime manyTime = new ManyTime();
            endTime2 = minuteToHour.getHour(auxShot.time( beginPresentShot.getBegin(getShot()), minuteToHour.getHour(manyTime.check()) ));
            if(informMoreShots==true){
                outputBarTime.setValue(0);
                outputTime.setForeground(Color.green);
                outputTime.setText("00:00");
            }
            else{
                String difference = timeDifference.getDifference(getHour.informHour(), endTime2);
                if(timeDifference.delay=="true"){
                    outputTime.setForeground(Color.red);
                    outputTime.setText(difference);
                    outputBarTime.setValue(100);
                }
                else{
                    outputTime.setForeground(Color.black);
                    outputTime.setText(difference);
                    barProgress.setBar(beginPresentShot.getBegin(getShot()), endTime2);
                }
            }
        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        finish();
    }//GEN-LAST:event_formWindowClosed

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
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonReset;
    public static javax.swing.ButtonGroup groupDelay;
    public static javax.swing.ButtonGroup groupWorkFinish;
    private javax.swing.JCheckBox inputDelay;
    private javax.swing.JCheckBox inputWorkFinish;
    public static javax.swing.JProgressBar outputBarTime;
    public static javax.swing.JLabel outputShot;
    public static javax.swing.JLabel outputStation;
    public static javax.swing.JLabel outputTime;
    private javax.swing.JLabel txtShot;
    private javax.swing.JLabel txtStation;
    private javax.swing.JLabel txtTimeToNextWork;
    // End of variables declaration//GEN-END:variables
}
