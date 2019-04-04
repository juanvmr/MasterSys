package frames;

import javax.swing.*;
import java.awt.*;

public class PanelTemplate extends JFrame {

    /* elements: */
    private JButton button;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");

    /* constructor: */
    public PanelTemplate(String title) {
        super(title);
        this.setSize(500, 100);
        this.setResizable(false);
        this.setBounds(0, 0, 400, 300);

        // layout
        GridLayout grid = new GridLayout(3, 3, 15, 15);
        this.setLayout(grid);
    }

    /* methods: */
    public void build(Container pane) {

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        button = new JButton("MasterLogin");

        pane.add(usernameLabel);
        pane.add(usernameField);
        pane.add(passwordLabel);
        pane.add(passwordField);
        pane.add(button);


    }

    public void init() {
        // create window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setup content pane
        this.build(this.getContentPane());
        // display window
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PanelTemplate p = new PanelTemplate("MasterLogin");
                p.init();
            }
        });
    }

}
