package app.components;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Test extends JFrame implements ActionListener {

    DateField date = new DateField();
    JButton button = new JButton("Submit");

    public Test() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.setSize(new Dimension(300, 150));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container contentPane) {

        button.addActionListener(this);

        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        contentPane.add(date);
        contentPane.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button) {
            System.out.println("Date: " + date.getDate());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test();
            }
        });
    }
}
