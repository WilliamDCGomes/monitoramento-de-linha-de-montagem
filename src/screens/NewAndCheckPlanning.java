/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

/**
 *
 * @author Alunos
 */
public class NewAndCheckPlanning extends javax.swing.JFrame {

    /**
     * Creates new form NewAndCheckPlanning
     */
    public NewAndCheckPlanning() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtChooseAOption = new javax.swing.JLabel();
        buttonNewPlanning = new javax.swing.JButton();
        buttonCheckPlanning = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecionar");

        txtChooseAOption.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtChooseAOption.setText("ESCOLHA A OPÇÃO DESEJADA");

        buttonNewPlanning.setText("NOVO PLANEJAMENTO");
        buttonNewPlanning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewPlanningActionPerformed(evt);
            }
        });
        buttonNewPlanning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonNewPlanningKeyPressed(evt);
            }
        });

        buttonCheckPlanning.setText("VERIFICAR PLANEJAMENTO EXISTENTE");
        buttonCheckPlanning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckPlanningActionPerformed(evt);
            }
        });
        buttonCheckPlanning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonCheckPlanningKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCheckPlanning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(buttonNewPlanning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(txtChooseAOption)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtChooseAOption)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(buttonNewPlanning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCheckPlanning)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(414, 173));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewPlanningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewPlanningActionPerformed
        DailyPlanningScreen dailyPlanningScreen = new DailyPlanningScreen();
        this.dispose();
        dailyPlanningScreen.setVisible(true);
    }//GEN-LAST:event_buttonNewPlanningActionPerformed

    private void buttonNewPlanningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonNewPlanningKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            DailyPlanningScreen dailyPlanningScreen = new DailyPlanningScreen();
            this.dispose();
            dailyPlanningScreen.setVisible(true);
        }
    }//GEN-LAST:event_buttonNewPlanningKeyPressed

    private void buttonCheckPlanningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonCheckPlanningKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            ExistingPlanning existingPlanning = new ExistingPlanning();
            this.dispose();
            existingPlanning.setVisible(true);
        }
    }//GEN-LAST:event_buttonCheckPlanningKeyPressed

    private void buttonCheckPlanningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckPlanningActionPerformed
        ExistingPlanning existingPlanning = new ExistingPlanning();
        this.dispose();
        existingPlanning.setVisible(true);
    }//GEN-LAST:event_buttonCheckPlanningActionPerformed

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
            java.util.logging.Logger.getLogger(NewAndCheckPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAndCheckPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAndCheckPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAndCheckPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewAndCheckPlanning().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCheckPlanning;
    private javax.swing.JButton buttonNewPlanning;
    private javax.swing.JLabel txtChooseAOption;
    // End of variables declaration//GEN-END:variables
}