package panels;

import java.sql.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import database.*;

public class Login extends JFrame {

    private JTextField UsuarioField;
    private JPasswordField SenhaField;
    private JLabel UsuarioLabel = new JLabel("Usuario:");
    private JLabel SenhaLabel = new JLabel("Senha:");
    private JButton okButton = new JButton("OK");

    public Login(String title) {
    	super(title);
    }

    private void initComponents(Container pane) {
    	
    	// fields
        UsuarioField = new JTextField();
        SenhaField = new JPasswordField();

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
                    .addComponent(UsuarioLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SenhaLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UsuarioField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SenhaField)))
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
                .addComponent(UsuarioLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(SenhaLabel)
                    .addComponent(UsuarioField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(SenhaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );
        
        // container
        pane.setLayout(layout);
    }                    

    private void okButtonActionPerformed(ActionEvent evt) {
    	Connection conn;
        String username = UsuarioField.getText();
        String password = SenhaField.getPassword().toString();

        if (!username.isEmpty()) {
            if (!password.isEmpty()) {
                conn = ConnectionFactory.getConnection("master", username, password);
            } else {
                SenhaField.setText("*");
            }
        } else {
            UsuarioField.setText("*");
        }
    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Login login = new Login("Login");
                login.setSize(300, 300);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.initComponents(login.getContentPane());
                login.pack();
                login.setVisible(true);
            }
        });
    }
    
}