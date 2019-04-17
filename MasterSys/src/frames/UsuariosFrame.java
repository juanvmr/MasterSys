package frames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class UsuariosFrame extends JInternalFrame implements ActionListener {

    /* attributes: */
    private Connection connection;
    private static String[] perfilList = new String[] { "--Selecione--", "Cadastral", "Matricular", "Financeiro", "Completo" };

    /* components: */
    private ToolBarPanel toolbar;

    private JComboBox<String> perfilComboBox;
    private JLabel usuarioLabel, passwordLabel, passwordConfirmaLabel, perfilLabel;
    private JTextField usuarioField;
    private JPasswordField passwordField, passwordConfirmaField;

    public UsuariosFrame(Connection connection) {
        super("Usuários");

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(createPanel(), BorderLayout.CENTER);
    }

    private JPanel createPanel() {

        int inset = 5;
        int border = 10;

        usuarioLabel = new JLabel("Usuário:", JLabel.RIGHT);
        passwordLabel = new JLabel("Senha:", JLabel.RIGHT);
        passwordConfirmaLabel = new JLabel("Confirmar Senha:", JLabel.RIGHT);
        perfilLabel = new JLabel("Perfil", JLabel.RIGHT);

        usuarioField = new JTextField();
        passwordField = new JPasswordField();
        passwordConfirmaField = new JPasswordField();

        perfilComboBox = new JComboBox<>();
        perfilComboBox.setModel(new DefaultComboBoxModel<>(perfilList));
        perfilComboBox.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(border, border, border, border));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(usuarioLabel, constraints);
        constraints.gridy++;
        panel.add(passwordLabel, constraints);
        constraints.gridy++;
        panel.add(passwordConfirmaLabel, constraints);
        constraints.gridy++;
        panel.add(perfilLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        panel.add(usuarioField, constraints);
        constraints.gridy++;
        panel.add(passwordField, constraints);
        constraints.gridy++;
        panel.add(passwordConfirmaField, constraints);
        constraints.gridy++;
        panel.add(perfilComboBox, constraints);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == perfilComboBox) {
            // code
        } else if (e.getSource() == toolbar.getAddButton()) {
            // code
        } else if (e.getSource() == toolbar.getSaveButton()) {
            // code
        } else if (e.getSource() == toolbar.getSearchButton()) {
            // code
        } else if (e.getSource() == toolbar.getRemoveButton()) {
            // code
        }
    }

}
