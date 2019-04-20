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
    private boolean insertEnabled = false;
    private boolean searchEnabled = false;

    /* components: */
    private ToolBarPanel toolbar;
    private JComboBox<String> perfilComboBox;
    private JTextField usuarioField;
    private JPasswordField passwordField, passwordCheckField;

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
        toolbar.setMode(this.insertEnabled, this.searchEnabled);

        toolbar.getAddButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().addActionListener(this);
        toolbar.getRemoveButton().addActionListener(this);

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
        passwordCheckField = new JPasswordField();

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
        panel.add(passwordCheckField, constraints);
        constraints.gridy++;
        panel.add(perfilComboBox, constraints);

        return panel;
    }

    private boolean checkPasswords(String password, String passwordCheck) {
        if (!password.isEmpty() && !passwordCheck.isEmpty()) {
            if (password.equals(passwordCheck)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "WARNNING: Passwords do not match.");
                passwordField.setText("");
                passwordCheckField.setText("");
            }
        }
        return false;
    }

    private Usuario getInputs() {

        Usuario tmp = new Usuario();

        // read username
        if (!usuarioField.getText().trim().isEmpty()) tmp.setUsername(usuarioField.getText().trim());

        // read passwords
        String password = new String(passwordField.getPassword());
        String passwordCheck = new String(passwordCheckField.getPassword());
        if ((this.insertEnabled && checkPasswords(password, passwordCheck)) || this.searchEnabled) {
            tmp.setPassword(password);
        }

        // read perfil
        if ((perfilComboBox.getSelectedIndex() > 0) && (perfilComboBox.getSelectedItem() != null)) {
            tmp.setPerfil(perfilComboBox.getSelectedItem().toString());
        }

        return tmp;
    }

    private void updateInputs(Usuario tmp) {
        if (tmp != null) {
            if (!tmp.getUsername().isEmpty()) usuarioField.setText(tmp.getUsername());
            if (!tmp.getPassword().isEmpty()) passwordField.setText(tmp.getPassword());
            if (!tmp.getPerfil().isEmpty()) perfilComboBox.setSelectedItem(tmp.getPerfil());
        }
    }

    private void clear() {
        usuarioField.setText("");
        passwordField.setText("");
        passwordCheckField.setText("");
        perfilComboBox.setSelectedIndex(0);
    }

    private void addButtonAction() {
        this.insertEnabled = true;

        // enable actions
        this.toolbar.setMode(this.insertEnabled, this.searchEnabled);
    }

    private void searchButtonAction() {
        if (!usuarioField.getText().trim().isEmpty()) {
            try {

                Usuario tmp = new Usuario(usuarioField.getText().trim());

                // check if usuário exists
                if (usuarioDAO.contains(tmp)) {

                    // insert data into fields
                    this.updateInputs((Usuario) usuarioDAO.select(tmp));
                    this.searchEnabled = true;

                    // enable actions
                    this.toolbar.setMode(this.insertEnabled, this.searchEnabled);

                    this.usuarioField.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(null, "WARNNING: Usuário not found.");
                    this.clear();
                }

            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
    }

    private void removeButtonAction() {
        if (!usuarioField.getText().trim().isEmpty()) {
            try {
                usuarioDAO.delete(new Usuario(usuarioField.getText().trim()));
                this.clear();
                this.searchEnabled = false;
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }

        // enable actions
        this.toolbar.setMode(this.insertEnabled, this.searchEnabled);
    }

    private void saveButtonAction() {
        Usuario tmp = this.getInputs();

        // INSERT INTO DATABASE
        if (this.insertEnabled) {
            try {
                usuarioDAO.insert(tmp);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.insertEnabled = false;
        }
        // UPDATE DATABASE
        else if (searchEnabled) {
            try {
                usuarioDAO.update(tmp);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.searchEnabled = false;
        }

        // enable actions
        this.toolbar.setMode(this.insertEnabled, this.searchEnabled);

        // clear fields
        this.clear();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // INSERT
        if (event.getSource() == toolbar.getAddButton()) {
            this.addButtonAction();
        }
        // UPDATE
        else if (event.getSource() == toolbar.getSaveButton()) {
            this.saveButtonAction();
        }
        // SELECT
        else if (event.getSource() == toolbar.getSearchButton()) {
            this.searchButtonAction();
        }
        // DELETE
        else if (event.getSource() == toolbar.getRemoveButton()) {
            this.removeButtonAction();
        }
    }

}
