package frames;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.ConnectionFactory;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {

    /* components: */
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    /* constructor: */
    public LoginFrame(String title) {
        super(title);
        this.setSize(360, 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents() {

        int inset = 30;

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(inset, inset, inset, inset));
        this.setContentPane(content);

        usernameLabel = new JLabel("Usuário:", JLabel.CENTER);
        passwordLabel = new JLabel("Senha:", JLabel.CENTER);

        usernameField = new JTextField(16);
        usernameField.addKeyListener(this);

        passwordField = new JPasswordField(16);
        passwordField.addKeyListener(this);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 5;
        c.insets = new Insets(5, 5, 5, 5);
        content.add(usernameLabel, c);
        c.gridy++;
        content.add(passwordLabel, c);


        c.gridx++;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        content.add(usernameField, c);
        c.gridy++;
        content.add(passwordField, c);


        c.gridx = 1;
        c.gridy++;
        c.gridwidth = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        content.add(loginButton, c);
    }

    public void debug() {
//        usernameField.setText("admin");
//        passwordField.setText("admin");
//        loginButton.doClick();

        this.dispose();
        MenuFrame frame = new MenuFrame("MasterSys", null, "Pedro");
        frame.setVisible(true);

    }

    @Override
    public void setSize(int width, int height) {

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        super.setSize(width, height);
        super.setBounds(
            (int) (screenResolution.getWidth() - width) / 2,
            (int) (screenResolution.getHeight() - getHeight()) / 2,
            width,
            height
        );
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loginButton) {
            Connection conn = null;
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.trim().isEmpty()) {
                if (!password.trim().isEmpty()) {
                    conn = ConnectionFactory.getConnection("master", username, password);
                    try {
                        // check if connection is open
                        if ((conn != null) && (!conn.isClosed())) {
                            // close login frame
                            this.dispose();
                            // open main frame
                            MenuFrame frame = new MenuFrame("MasterSys", conn, username);
                            frame.setVisible(true);
                        }
                    } catch (SQLException e) {
                        System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                    }
                } else {
                    passwordField.setText("*");
                }
            } else {
                usernameField.setText("*");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}