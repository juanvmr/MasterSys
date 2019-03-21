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
import database.ConnectionFactory;
import java.sql.Connection;


public class Login extends JFrame {

    private JTextField UsuarioField;
    private JPasswordField SenhaField;
    private JLabel UsuarioLabel = new JLabel("Usuario:");
    private JLabel SenhaLabel = new JLabel("Senha:");
    private JButton OKJButton = new JButton("OK");

    public Login(String title) {
    	super(title);
        initComponents();
    }

    private void initComponents() {

        UsuarioField = new JTextField();
        SenhaField = new JPasswordField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                        .addComponent(UsuarioField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(UsuarioField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        Connection conn;

        if(!UsuarioField.getText().isEmpty()){
            if(!SenhaField.getText().isEmpty()){
                conn = ConnectionFactory.getConnection("Master",UsuarioField.getText(), SenhaField.getText());
            }
            else{
                SenhaField.setText("*");
            }
        }
        else{
            UsuarioField.setText("*");
        }
    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login("Login");
                login.setVisible(true);
            }
        });
    }
    
}