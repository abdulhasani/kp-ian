/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    private RegRwtJalan rg = new RegRwtJalan();

    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    private Boolean cekkingLogin(String userName, String password) {
        Boolean cek = false;
        try {
            DBAdministrator login = DatabaseHelper.getAdministrator().getLogin(userName, password);
            if (login instanceof DBAdministrator) {

                if (login.getUsername().equals(userName)
                        && login.getPassword().equals(password)) {
                    cek = true;
                    if (login.getBagian().equals("Admin")) {
                        rg.getMenuUtaman1().getLetakMenu1().getRenderDokter1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderObat1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPembayaran1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPeriksa1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderUser1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getBtnPasien().setEnabled(true);
                    } else if (login.getBagian().equals("Loket")) {
                        rg.getMenuUtaman1().getLetakMenu1().getRenderDokter1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderObat1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPembayaran1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPeriksa1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderUser1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getBtnPasien().setEnabled(true);
                    } else if (login.getBagian().equals("Periksa")) {
                        rg.getMenuUtaman1().getLetakMenu1().getRenderDokter1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderObat1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPembayaran1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPeriksa1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderUser1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getBtnPasien().setEnabled(false);
                    } else if (login.getBagian().equals("Apotek")) {
                        rg.getMenuUtaman1().getLetakMenu1().getRenderDokter1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderObat1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPembayaran1().setEnabled(true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPeriksa1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderUser1().setEnabled(false);
                        rg.getMenuUtaman1().getLetakMenu1().getBtnPasien().setEnabled(true);
                    } else {
                        rg.getMenuUtaman1().getLetakMenu1().getRenderDokter1().setEnabled(!true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderObat1().setEnabled(!true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPembayaran1().setEnabled(!true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderPeriksa1().setEnabled(!true);
                        rg.getMenuUtaman1().getLetakMenu1().getRenderUser1().setEnabled(!true);
                        rg.getMenuUtaman1().getLetakMenu1().getBtnPasien().setEnabled(!true);
                    }
                } else {
                    lblWarning.setForeground(Color.WHITE);
                    lblWarning.setText("Cek user name dan password");
                }
            } else {
                lblWarning.setForeground(Color.WHITE);
                lblWarning.setText("Cek user name dan password");
            }
        } catch (AdministratorException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renderPanelLogin1 = new Nciz.RegisterRawatJalan.frame.panel.RenderPanelLogin();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new com.uiMIF.JPasswordEx();
        lblWarning = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        renderPanelLogin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        lblWarning.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOGIN NDISIT BRO");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("   --------------------------------");

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Nciz/RegisterRawatJalan/Gambar/login.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Nciz/RegisterRawatJalan/Gambar/keluar.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout renderPanelLogin1Layout = new javax.swing.GroupLayout(renderPanelLogin1);
        renderPanelLogin1.setLayout(renderPanelLogin1Layout);
        renderPanelLogin1Layout.setHorizontalGroup(
            renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
            .addGroup(renderPanelLogin1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(renderPanelLogin1Layout.createSequentialGroup()
                .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(renderPanelLogin1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(renderPanelLogin1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(14, 14, 14)
                        .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        renderPanelLogin1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        renderPanelLogin1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtPassword, txtUserName});

        renderPanelLogin1Layout.setVerticalGroup(
            renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(renderPanelLogin1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(renderPanelLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        renderPanelLogin1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtPassword, txtUserName});

        getContentPane().add(renderPanelLogin1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            String trim = txtUserName.getText().trim();
            char[] password = txtPassword.getPassword();
            String pas = new String(password);
            if (trim.isEmpty()
                    && pas.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("Cek user name dan password");
            } else if (trim.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("User Name masih kosong");
            } else if (pas.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("password masih kosong");
            } else {
                if (cekkingLogin(trim, pas) == true) {
                    dispose();
                    rg.requestFocus();
                    rg.setVisible(true);
                }
            }
        }
        
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        
            String trim = txtUserName.getText().trim();
            char[] password = txtPassword.getPassword();
            String pas = new String(password);
            if (trim.isEmpty()
                    && pas.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("Cek user name dan password");
            } else if (trim.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("User Name masih kosong");
            } else if (pas.isEmpty()) {
                lblWarning.setForeground(Color.red);
                lblWarning.setText("password masih kosong");
            } else {
                if (cekkingLogin(trim, pas) == true) {
                    dispose();
                    rg.requestFocus();
                    rg.setVisible(true);
                }
            }
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnBatalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblWarning;
    private Nciz.RegisterRawatJalan.frame.panel.RenderPanelLogin renderPanelLogin1;
    private com.uiMIF.JPasswordEx txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
