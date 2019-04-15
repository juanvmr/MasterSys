package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class UsuariosFrame extends JInternalFrame implements ActionListener {

    /* attributes: */
    private static String[] listaPerfil = new String[] { "--Selecione--", "Cadastral", "Matricular", "Financeiro", "Completo" };

    /* components: */
    private JButton adicionarButton;
    private JButton buscarButton;
    private JButton removerButton;
    private JButton salvarButton;
    private JComboBox<String> perfilComboBox;
    private JLabel confirmaSenhaLabel;
    private JLabel perfilLabel;
    private JLabel senhaLabel;
    private JLabel usuarioLabel;
    private JPasswordField passwordConfirmaField;
    private JPasswordField passwordField;
    private JTextField usuarioField;

    public UsuariosFrame(Connection Connection) {
        super("Usuários");
        this.setSize(500, 300);
        this.initComponents(this.getContentPane());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container container) {

        usuarioLabel = new JLabel("Usuário:", JLabel.CENTER);
        senhaLabel = new JLabel("Senha:", JLabel.CENTER);
        confirmaSenhaLabel = new JLabel("Confirmar Senha:", JLabel.CENTER);
        perfilLabel = new JLabel("Perfil", JLabel.CENTER);
        usuarioField = new JTextField();
        passwordField = new JPasswordField();
        passwordConfirmaField = new JPasswordField();

        perfilComboBox = new JComboBox<>();
        perfilComboBox.setModel(new DefaultComboBoxModel<>(listaPerfil));
        perfilComboBox.addActionListener(this);

        buscarButton = new JButton("Buscar");
        adicionarButton = new JButton("Adicionar");
        removerButton = new JButton("Remover");
        salvarButton = new JButton("Salvar");

        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(perfilLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(confirmaSenhaLabel, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                        .addComponent(senhaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usuarioLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buscarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(salvarButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(usuarioField)
                                        .addComponent(passwordField)
                                        .addComponent(passwordConfirmaField)
                                        .addComponent(perfilComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(buscarButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(salvarButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(usuarioLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usuarioField, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(senhaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(confirmaSenhaLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(passwordConfirmaField)
                                                .addGap(5, 5, 5)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(perfilLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(perfilComboBox, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == perfilComboBox) {

        }
    }
}
