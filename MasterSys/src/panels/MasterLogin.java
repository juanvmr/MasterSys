package panels;

import java.sql.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import database.*;

public class MasterLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel = new JLabel("Usuario:");
    private JLabel passwordLabel = new JLabel("Senha:");
    private JButton okButton = new JButton("OK");

    public MasterLogin(String title) {
        super(title);
    }

    private void initComponents(Container pane) {
    	
    	// fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        
        // layout
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordField)))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );
        
        // container
        pane.setLayout(layout);
    }                    

    private void okButtonActionPerformed(ActionEvent event) {
    	Connection conn;
        String username = usernameField.getText();
        String password = passwordField.getPassword().toString();

        if (!username.isEmpty()) {
            if (!password.isEmpty()) {
                conn = ConnectionFactory.getConnection("master", username, password);
            } else {
                passwordField.setText("*");
            }
        } else {
            usernameField.setText("*");
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	MasterLogin login = new MasterLogin("MasterLogin");
                login.setSize(300, 300);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.initComponents(login.getContentPane());
                login.pack();
                login.setVisible(true);
            }
        });
    }
    
}