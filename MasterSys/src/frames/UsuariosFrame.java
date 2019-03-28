package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuariosFrame extends JFrame {

    private String[] listaPerfil;

    private JButton jButtonAdicionar;
    private JButton jButtonBuscar;
    private JButton jButtonRemover;
    private JButton jButtonSalvar;
    private JComboBox<String> jComboBoxPerfil;
    private JLabel jLabelConfirmaSenha;
    private JLabel jLabelPerfil;
    private JLabel jLabelSenha;
    private JLabel jLabelUsuario;
    private JPasswordField jPasswordConfirma;
    private JPasswordField jPasswordSenha;
    private JTextField jTextFieldUsuario;

    public UsuariosFrame(String title) {
        super(title);
        this.listaPerfil = new String[]{"--Selecione--", "Cadastral", "Matricular", "Financeiro", "Completo"};
    }

    private void initComponents(Container pane) {

        jButtonBuscar = new JButton("Buscar");
        jButtonAdicionar = new JButton("Adicionar");
        jButtonRemover = new JButton("Remover");
        jButtonSalvar = new JButton("Salvar");
        jLabelUsuario = new JLabel("Usuário:");
        jLabelSenha = new JLabel("Senha:");
        jLabelConfirmaSenha = new JLabel("Confirmar Senha:");
        jLabelPerfil = new JLabel("Perfil");
        jTextFieldUsuario = new JTextField();
        jPasswordSenha = new JPasswordField();
        jPasswordConfirma = new JPasswordField();
        jComboBoxPerfil = new JComboBox<>();

        jComboBoxPerfil.setModel(new DefaultComboBoxModel<>(listaPerfil));
        jComboBoxPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelPerfil, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelConfirmaSenha, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                        .addComponent(jLabelSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonAdicionar, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonRemover, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonSalvar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextFieldUsuario)
                                        .addComponent(jPasswordSenha)
                                        .addComponent(jPasswordConfirma)
                                        .addComponent(jComboBoxPerfil, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButtonBuscar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonAdicionar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonRemover, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButtonSalvar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldUsuario, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPasswordSenha, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelConfirmaSenha, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPasswordConfirma)
                                                .addGap(5, 5, 5)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelPerfil, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxPerfil, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                UsuariosFrame frame = new UsuariosFrame("Usuário");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.initComponents(frame.getContentPane());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}
