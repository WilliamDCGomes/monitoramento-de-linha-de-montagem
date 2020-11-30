package screens;
import commands.Hash;
import connectionbd.ConnectionModule;
import functions.InputJustNumbers;
import functions.UpperLetter;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author willi
 */
public class EditStation extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean stationValid = false;
    
    public EditStation() {
        initComponents();
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
        URL adress = getClass().getResource("/images/icon.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(adress);
        this.setIconImage(icon);
        inputNumberOfStation.setDocument(new InputJustNumbers());
        inputLogin.setDocument(new UpperLetter());
    }
    
    private void remove(){
        int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA APAGAR ESSA ESTAÇÃO?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from stations where id = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, inputNumberOfStation.getText());
                int apagado = pst.executeUpdate();
                if(apagado>0){
                    JOptionPane.showMessageDialog(null,"ESTAÇÃO APAGADA COM SUCESSO");
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"ESTAÇÃO NÃO CADASTRADA NO BANCO DE DADOS");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void localeStation(){
        String sqlnome = "select login from stations where id = ?";
        try {
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,inputNumberOfStation.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                inputLogin.setText(rs.getString(1));
            }
            else{
                JOptionPane.showMessageDialog(null,"ESTAÇÃO NÃO ENCONTRADA NO BANCO DE DADOS");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void checkStation(){
        String sqlnome = "select * from stations where id = ? and login = ? and passwors = MD5(MD5(MD5(?)))";
        try {
            Hash hash = new Hash();
            pst = connection.prepareStatement(sqlnome);
            pst.setString(1,inputNumberOfStation.getText());
            pst.setString(2,inputLogin.getText());
            pst.setString(3,hash.DoHash(inputOldPassword.getText()));
            rs = pst.executeQuery();
            if (rs.next()) {
                String x = (rs.getString(1));
                stationValid=true;
            }
            else{
                JOptionPane.showMessageDialog(null,"INFORMAÇÕES NÃO CONFEREM COM A CADASTRADA NO BANCO DE DADOS");
                stationValid=false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO AO SE CONECTAR COM O BANCO DE DADOS\n" + e);
        }
    }
    private void updateStation(){
        String sql = "update stations set passwors=MD5(MD5(MD5(?))) where id=?";
        try {
            Hash hash = new Hash();
            pst=connection.prepareStatement(sql);
            pst.setString(1,hash.DoHash(inputPassword.getText()));
            pst.setString(2,inputNumberOfStation.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"ESTAÇÃO ATUALIZADA COM SUCESSO");
            stationValid=false;
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
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

        txtNumberOfStation = new javax.swing.JLabel();
        txtLogin = new javax.swing.JLabel();
        txtPassword = new javax.swing.JLabel();
        inputLogin = new javax.swing.JTextField();
        txtConfirmPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        inputConfirmPassword = new javax.swing.JPasswordField();
        txtNewStation = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonLocale = new javax.swing.JButton();
        txtOldPassword = new javax.swing.JLabel();
        inputOldPassword = new javax.swing.JPasswordField();
        buttonDelete = new javax.swing.JButton();
        inputNumberOfStation = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Estação");
        setResizable(false);
        getContentPane().setLayout(null);

        txtNumberOfStation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtNumberOfStation.setText("NÚMERO DA ESTAÇÃO");
        getContentPane().add(txtNumberOfStation);
        txtNumberOfStation.setBounds(24, 99, 162, 20);

        txtLogin.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtLogin.setText("LOGIN");
        getContentPane().add(txtLogin);
        txtLogin.setBounds(24, 175, 44, 20);

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtPassword.setText("NOVA SENHA");
        getContentPane().add(txtPassword);
        txtPassword.setBounds(24, 271, 93, 20);

        inputLogin.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputLoginKeyPressed(evt);
            }
        });
        getContentPane().add(inputLogin);
        inputLogin.setBounds(231, 173, 144, 24);

        txtConfirmPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtConfirmPassword.setText("CONFIRMAR SENHA");
        getContentPane().add(txtConfirmPassword);
        txtConfirmPassword.setBounds(24, 319, 140, 20);

        inputPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(inputPassword);
        inputPassword.setBounds(231, 269, 144, 24);

        inputConfirmPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputConfirmPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(inputConfirmPassword);
        inputConfirmPassword.setBounds(231, 317, 144, 24);

        txtNewStation.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtNewStation.setText("EDITAR ESTAÇÃO");
        getContentPane().add(txtNewStation);
        txtNewStation.setBounds(100, 30, 204, 32);

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
        buttonSave.setBounds(24, 367, 78, 25);

        buttonLocale.setText("LOCALIZAR");
        buttonLocale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLocaleActionPerformed(evt);
            }
        });
        buttonLocale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonLocaleKeyPressed(evt);
            }
        });
        getContentPane().add(buttonLocale);
        buttonLocale.setBounds(24, 127, 93, 25);

        txtOldPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOldPassword.setText("ANTIGA SENHA");
        getContentPane().add(txtOldPassword);
        txtOldPassword.setBounds(24, 223, 104, 20);

        inputOldPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        inputOldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputOldPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(inputOldPassword);
        inputOldPassword.setBounds(231, 221, 144, 24);

        buttonDelete.setText("APAGAR");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        buttonDelete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonDeleteKeyPressed(evt);
            }
        });
        getContentPane().add(buttonDelete);
        buttonDelete.setBounds(146, 367, 79, 25);

        inputNumberOfStation.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        getContentPane().add(inputNumberOfStation);
        inputNumberOfStation.setBounds(231, 97, 144, 24);

        setSize(new java.awt.Dimension(416, 451));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        remove();
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonLocaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLocaleActionPerformed
        if(inputNumberOfStation.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INFORME O NÚMERO DA ESTAÇÃO");
        }
        else{
            localeStation();
        }
    }//GEN-LAST:event_buttonLocaleActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(inputNumberOfStation.getText().equals("")||inputLogin.getText().equals("")||inputOldPassword.getText().equals("")||inputPassword.getText().equals("")||inputConfirmPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "POR FAVOR PREENCHA TODOS OS CAMPOS");
        }
        else{
            checkStation();
            if(stationValid == true){
                if((inputPassword.getText().equals(inputConfirmPassword.getText()))){
                    updateStation();
                }
                else{
                    JOptionPane.showMessageDialog(null, "CAMPOS DA NOVA SENHA NÃO CONFEREM");
                }
            }
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonLocaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonLocaleKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputNumberOfStation.getText().equals("")){
                JOptionPane.showMessageDialog(null, "INFORME O NÚMERO DA ESTAÇÃO");
            }
            else{
                localeStation();
            }
        }
    }//GEN-LAST:event_buttonLocaleKeyPressed

    private void inputLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputLoginKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputOldPassword.requestFocus();
        }
    }//GEN-LAST:event_inputLoginKeyPressed

    private void inputOldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputOldPasswordKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputPassword.requestFocus();
        }
    }//GEN-LAST:event_inputOldPasswordKeyPressed

    private void inputPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPasswordKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            inputConfirmPassword.requestFocus();
        }
    }//GEN-LAST:event_inputPasswordKeyPressed

    private void inputConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputConfirmPasswordKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputNumberOfStation.getText().equals("")||inputLogin.getText().equals("")||inputOldPassword.getText().equals("")||inputPassword.getText().equals("")||inputConfirmPassword.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR PREENCHA TODOS OS CAMPOS");
            }
            else{
                checkStation();
                if(stationValid == true){
                    if((inputPassword.getText().equals(inputConfirmPassword.getText()))){
                        updateStation();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "CAMPOS DA NOVA SENHA NÃO CONFEREM");
                    }
                }
            }
        }
    }//GEN-LAST:event_inputConfirmPasswordKeyPressed

    private void buttonSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonSaveKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(inputNumberOfStation.getText().equals("")||inputLogin.getText().equals("")||inputOldPassword.getText().equals("")||inputPassword.getText().equals("")||inputConfirmPassword.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR PREENCHA TODOS OS CAMPOS");
            }
            else{
                checkStation();
                if(stationValid == true){
                    if((inputPassword.getText().equals(inputConfirmPassword.getText()))){
                        updateStation();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "CAMPOS DA NOVA SENHA NÃO CONFEREM");
                    }
                }
            }
        }
    }//GEN-LAST:event_buttonSaveKeyPressed

    private void buttonDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonDeleteKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            remove();
        }
    }//GEN-LAST:event_buttonDeleteKeyPressed

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
            java.util.logging.Logger.getLogger(EditStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditStation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonLocale;
    private javax.swing.JButton buttonSave;
    private javax.swing.JPasswordField inputConfirmPassword;
    private javax.swing.JTextField inputLogin;
    private javax.swing.JTextField inputNumberOfStation;
    private javax.swing.JPasswordField inputOldPassword;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JLabel txtConfirmPassword;
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtNewStation;
    private javax.swing.JLabel txtNumberOfStation;
    private javax.swing.JLabel txtOldPassword;
    private javax.swing.JLabel txtPassword;
    // End of variables declaration//GEN-END:variables
}
