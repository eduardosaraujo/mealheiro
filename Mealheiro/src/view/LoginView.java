package view;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import model.*;

/**
 *
 * @author ed
 */
public class LoginView extends JPanel implements Observer {

    private UserList db;

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
    }

    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
    }

    public void setController(EventListener el) {
        bLogin.addActionListener((ActionListener) el);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Login view: updated");
        if (db.getLoggedInUser() != null) {
            this.remove(this);
        }
    }

    public String getPfLoginPassword() {
        String password = this.pfLoginPassword.getText();
        if (!password.isEmpty()) {
            return password;
        } else {
            return null;
        }
    }

    public String getLoginUsername() {
        String username = this.tfLoginUsername.getText();
        if (!username.isEmpty()) {
            return username;
        } else {
            return null;
        }
    }

    public void setLoginInformation(String text) {
        lblLoginInformation.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLoginUsername = new javax.swing.JLabel();
        tfLoginUsername = new javax.swing.JTextField();
        lblLoginPassword = new javax.swing.JLabel();
        pfLoginPassword = new javax.swing.JPasswordField();
        bLogin = new javax.swing.JButton();
        lblLoginInformation = new javax.swing.JLabel();

        setName("Login"); // NOI18N

        lblLoginUsername.setText("Username");
        lblLoginUsername.setToolTipText("");

        lblLoginPassword.setText("Password");

        bLogin.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblLoginPassword)
                        .addComponent(lblLoginUsername)
                        .addComponent(pfLoginPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(tfLoginUsername))
                    .addComponent(bLogin)
                    .addComponent(lblLoginInformation))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLoginUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLoginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLoginPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLoginInformation)
                .addGap(22, 22, 22)
                .addComponent(bLogin)
                .addContainerGap(69, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLogin;
    private javax.swing.JLabel lblLoginInformation;
    private javax.swing.JLabel lblLoginPassword;
    private javax.swing.JLabel lblLoginUsername;
    private javax.swing.JPasswordField pfLoginPassword;
    private javax.swing.JTextField tfLoginUsername;
    // End of variables declaration//GEN-END:variables
}
