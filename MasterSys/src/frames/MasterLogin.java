package frames;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.ConnectionFactory;

public class MasterLogin extends JFrame {

    private JLabel usernameLabel = new JLabel("Usuário:");
    private JLabel passwordLabel = new JLabel("Senha:");
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Login");

    public MasterLogin(String title) {
        super(title);
        this.setSize(800, 600);
        this.setResizable(true);
        this.setPreferredSize(new Dimension(300, 170));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    public void initComponents(Container pane) {

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    okButtonActionPerformed(null);
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    okButtonActionPerformed(null);
                }
            }
        });

        // button
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                okButtonActionPerformed(event);
            }
        });
        
        // layout
        pane.setLayout(new GridBagLayout());
        pane.setBounds(15, 15, this.getWidth() - 15, this.getHeight() - 15);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        pane.add(usernameLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 2;
        pane.add(usernameField, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.gridwidth = 1;
        pane.add(passwordLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        pane.add(passwordField, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0;
        c.gridwidth = 1;
        pane.add(loginButton, c);
    }                    

    private void okButtonActionPerformed(ActionEvent event) {
    	Connection conn = null;
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        if (!username.isEmpty()) {
            if (!password.isEmpty()) {
                conn = ConnectionFactory.getConnection("master", username, password);
                try {
                    // check if connection is open
                    if ((conn != null) && (!conn.isClosed())) {
                        // open new window
                        // janela.setVisible(true);

                        // close login window
                        this.dispose();
                        new MasterMenu("MasterSys", conn, username);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                passwordField.setText("*");
            }
        } else {
            usernameField.setText("*");
        }

    }
    
}