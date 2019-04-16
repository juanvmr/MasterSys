package frames.test;

import frames.UsuariosFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class MainFrameTest extends JFrame {

    /* attributes: */
    private Connection connection;
    private String username;

    /* components: */
    private JDesktopPane desktop;

    /* construction: */
    public MainFrameTest(String title, Connection connection, String username){
        super(title);

        this.connection = connection;
        this.username = username;

        this.setDefaultSize(0.35);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new Menus(connection, username));
        this.initComponents(this.getContentPane());
        // this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void setDefaultSize(double percentage) {

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (percentage * screenResolution.getWidth());
        int height = (int) (percentage * screenResolution.getHeight());
        int x = (int) (screenResolution.getWidth() - width) / 2;
        int y = (int) (screenResolution.getHeight() - height) / 2;

        this.setBounds(x, y, width, height);
    }

    private void initComponents(Container container) {

        int inset = 30;

        JLabel label = new JLabel(this.getTitle(), JLabel.CENTER);

        desktop = new JDesktopPane();
        desktop.setLayout(null);
        desktop.setBackground(new Color(32, 32, 32, 32));
        desktop.add(label);

        label.setBounds(inset, inset, desktop.getWidth() - (2 * inset), desktop.getHeight() - (2 * inset));

        container.setLayout(new FlowLayout());
        container.add(desktop);
    }

}
