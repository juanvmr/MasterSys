package frames;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // create login window
                MasterLogin login = new MasterLogin("Login");
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.initComponents(login.getContentPane());
                login.pack();
                login.setVisible(true);
            }
        });
    }

}
