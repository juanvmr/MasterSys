package panels;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.*;
import java.awt.EventQueue;


public class Login extends JFrame {

    private javax.swing.JTextField UsuarioTextField;
    private javax.swing.JButton OKJButton;
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JLabel UsuarioLabel;
    private javax.swing.JLabel SenhaLabel;

    public Login() {
        initComponents();
    }

    private void initComponents() {

        UsuarioLabel = new JLabel();
        SenhaLabel = new JLabel();
        UsuarioTextField = new JTextField();
        OKJButton = new JButton();
        SenhaField = new JPasswordField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        UsuarioLabel.setText("Usuário:");
        SenhaField.setText("Código:");
        OKJButton.setText("OK");
        OKJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OKJButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(UsuarioLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SenhaLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UsuarioTextField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SenhaField)))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OKJButton)
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
                    .addComponent(UsuarioTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(SenhaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OKJButton)
                .addContainerGap())
        );

        pack();
    }                    

    private void OKJButtonActionPerformed(ActionEvent evt) {

    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
}