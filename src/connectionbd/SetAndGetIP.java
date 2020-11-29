package connectionbd;

import functions.MaskJustNumbers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author willi
 */
public class SetAndGetIP extends javax.swing.JFrame {
    boolean validade = true;
    String ip = null;
    public SetAndGetIP() {
        initComponents();
        inputIP.setDocument(new MaskJustNumbers());
    }
    private void setFile() throws IOException{
        String userDir = System.getProperty("user.home");
        String adress = userDir + "\\Documents";
        File file =  new File(adress + "\\ipAdress.txt");
        if(file.exists()){ 
            insert(file);
        }
        else{
            insert(file);
        }
    }
    private void insert(File file){
        try{
            file.createNewFile();
            ip = inputIP.getText();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter write = new BufferedWriter(fileWriter);
            write.write(ip);
            write.close();
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "IP ATUALIZADO COM SUCESSO\nPOR FAVOR ABRA O SISTEMA NOVAMENTE");
            this.dispose();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public String getIP(){
        try {
            String userDir = System.getProperty("user.home");
            String adress = userDir + "\\Documents";
            FileReader file = new FileReader(adress + "\\ipAdress.txt");
            BufferedReader lerArq = new BufferedReader(file);
            String line = lerArq.readLine();
            while (line != null) {
              inputIP.setText(line);
              line = lerArq.readLine(); 
            }
            file.close();
            return inputIP.getText();
        } 
        catch (IOException e) {
            String error = "java.io.FileNotFoundException: " + System.getProperty("user.home") + "\\Documents\\ipAdress.txt (O sistema não pode encontrar o arquivo especificado)";
            if(e.toString().equals(error)){
                JOptionPane.showMessageDialog(null, "O ARQUIVO ONDE ESTÁ LOCALIZADO O IP DO SERVIDOR NÃO EXISTE\nCHAME O SUPERVISOR PARA RECONFIGURAR O IP");
                if(!this.isVisible()){
                    validade = false;
                    this.setVisible(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "ERRO: " + e);
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtConfigIP = new javax.swing.JLabel();
        inputIP = new javax.swing.JTextField();
        buttonRefresh = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IP");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtConfigIP.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtConfigIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtConfigIP.setText("CONFIGURANDO IP DO SERVIDOR");
        getContentPane().add(txtConfigIP);
        txtConfigIP.setBounds(20, 0, 430, 80);

        inputIP.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        inputIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputIP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputIPFocusGained(evt);
            }
        });
        getContentPane().add(inputIP);
        inputIP.setBounds(100, 80, 270, 50);

        buttonRefresh.setText("ATUALIZAR");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(buttonRefresh);
        buttonRefresh.setBounds(100, 160, 110, 25);

        buttonCancel.setText("CANCELAR");
        getContentPane().add(buttonCancel);
        buttonCancel.setBounds(230, 160, 120, 25);

        setSize(new java.awt.Dimension(484, 256));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(validade){
            validade = false;
            getIP();
        }
    }//GEN-LAST:event_formWindowActivated

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        if(inputIP.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INSIRA O IP DO SERVIDOR ANTES DE SALVAR");
        }
        else{
            try {
                setFile();
            } catch (IOException ex) {
                Logger.getLogger(SetAndGetIP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void inputIPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputIPFocusGained
        inputIP.selectAll();
    }//GEN-LAST:event_inputIPFocusGained

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
            java.util.logging.Logger.getLogger(SetAndGetIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetAndGetIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetAndGetIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetAndGetIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetAndGetIP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JTextField inputIP;
    private javax.swing.JLabel txtConfigIP;
    // End of variables declaration//GEN-END:variables
}
