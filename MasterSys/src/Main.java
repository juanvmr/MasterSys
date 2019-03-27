import javax.swing.*;
import java.awt.*;

import panels.*;

public class Main {

    public static void build(Container pane) {

        pane.add(new MasterMenu("Menu Principal"), BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {

        // main frame
        JFrame frame = new JFrame("MasterSys");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        build(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

}
