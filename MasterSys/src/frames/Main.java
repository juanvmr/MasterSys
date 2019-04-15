package frames;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // create login window
                LoginFrame frame = new LoginFrame("Login");
                frame.setSize(320, 180);
                frame.setVisible(true);
                frame.debug();
            }
        });
    }

}
