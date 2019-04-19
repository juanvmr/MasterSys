package app.frames;

import app.panels.ToolBarPanel;
import database.dao.UsuariosDAO;
import database.models.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class SistemaUsuariosFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static String[] perfilList = { "--Selecione--", "Cadastral", "Matricular", "Financeiro", "Completo" };
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private UsuariosDAO usuarioDAO;

    /* components: */
    private ToolBarPanel toolbar;
    private JComboBox<String> perfilComboBox;
    private JTextField usuarioField;
    private JPasswordField passwordField, passwordConfirmaField;

    public SistemaUsuariosFrame(Connection connection) {
        super("Usuários", isResizable, isClosable, isMaximizable, isIconifiable);

        /* attributes: */
        this.usuarioDAO = new UsuariosDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
        toolbar.getAddButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().addActionListener(this);
        toolbar.getRemoveButton().addActionListener(this);
        toolbar.getRemoveButton().setEnabled(false);

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(createPanel(), BorderLayout.CENTER);
    }

    private JPanel createPanel() {

        int inset = 5;
        int border = 10;

        JLabel usuarioLabel = new JLabel("Usuário:", JLabel.RIGHT);
        JLabel passwordLabel = new JLabel("Senha:", JLabel.RIGHT);
        JLabel passwordConfirmaLabel = new JLabel("Confirmar Senha:", JLabel.RIGHT);
        JLabel perfilLabel = new JLabel("Perfil", JLabel.RIGHT);

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

    private Usuario readField() {
        Usuario tmp = new Usuario();

        // read username
        if (!usuarioField.getText().trim().isEmpty()) tmp.setUsername(usuarioField.getText().trim());

        // read password and check if both password match
        String password = new String(passwordField.getPassword());
        String passwordCheck = new String(passwordConfirmaField.getPassword());
        if (!password.isEmpty() && !passwordCheck.isEmpty()) {
            if (password.equals(passwordCheck)) {
                tmp.setPassword(password);
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                passwordField.setText("");
                passwordConfirmaField.setText("");
            }
        }

        // read perfil
        if ((perfilComboBox.getSelectedIndex() > 0) && (perfilComboBox.getSelectedItem() != null)) {
            tmp.setPerfil(perfilComboBox.getSelectedItem().toString());
        }

        return tmp;
    }

    private void updateFields(Usuario tmp) {
        if (tmp != null) {
            if (!tmp.getUsername().isEmpty()) usuarioField.setText(tmp.getUsername());
            if (!tmp.getPassword().isEmpty()) passwordField.setText(tmp.getPassword());
            if (!tmp.getPerfil().isEmpty()) {
                perfilComboBox.setSelectedItem(tmp.getPerfil());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // INSERT
        if (event.getSource() == toolbar.getAddButton()) {
            try {
                usuarioDAO.insert(readField());
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }
        }
        // UPDATE
        else if (event.getSource() == toolbar.getSaveButton()) {
            try {
                usuarioDAO.update(readField());
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }
        }
        // SELECT
        else if (event.getSource() == toolbar.getSearchButton()) {
            try {
                Usuario x = readField();
                Usuario y = (Usuario) usuarioDAO.select(x);
                System.out.println("READ: " + x.toString());
                System.out.println("SELECT: " + y.toString());
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }
        }
        // DELETE
        else if (event.getSource() == toolbar.getRemoveButton()) {
            try {
                usuarioDAO.delete(readField());
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }
        }
    }

}
