package app.frames;

import app.components.*;
import app.panels.*;
import database.dao.*;
import database.models.Aluno;
import database.models.Local;
import database.models.Matricula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CadastrarAlunosFrame extends JInternalFrame implements ActionListener, ItemListener, KeyListener {

    /* config: */
    private static String[] sexoList = { "M", "F" };
    private static int inset = 5;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private LocalDAO localDAO;
    private AlunoDAO alunoDAO;
    private MatriculaDAO matriculaDAO;
    private Aluno aluno;
    private List<String> cidadeList, estadoList, paisList;
    private boolean insertEnabled = false;
    private boolean searchEnabled = false;

    /* components: */
    private ToolBarPanel toolbar;
    private JTextField alunoField, telefoneField, celularField, emailField, obsField;
    private JTextField enderecoField, numeroField, complementoField, bairroField, cepField;
    private DateField dataNascField;
    private JComboBox<String> sexoComboBox, cidadeComboBox, estadoComboBox, paisComboBox;

    /* constructors: */
    public CadastrarAlunosFrame(Connection connection) {
        super("Cadastrar Aluno", isResizable, isClosable, isMaximizable, isIconifiable);

        this.localDAO = new LocalDAO(connection);
        this.alunoDAO = new AlunoDAO(connection);
        this.matriculaDAO = new MatriculaDAO(connection);

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.setupInput();
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
        toolbar.getAddButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().addActionListener(this);
        toolbar.getRemoveButton().addActionListener(this);

        JPanel infoPanel = createInfoPanel();
        JPanel addressPanel = createAddressPanel();

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(infoPanel, BorderLayout.CENTER);
        content.add(addressPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {

        JLabel alunoLabel = new JLabel("Aluno:", JLabel.RIGHT);
        JLabel dataNascLabel = new JLabel("Data de Nascimento:", JLabel.RIGHT);
        JLabel sexoLabel = new JLabel("Sexo:", JLabel.RIGHT);
        JLabel telefoneLabel = new JLabel("Telefone:", JLabel.RIGHT);
        JLabel celularLabel = new JLabel("Celular:", JLabel.RIGHT);
        JLabel emailLabel = new JLabel("E-mail:", JLabel.RIGHT);
        JLabel obsLabel = new JLabel("Obs.:", JLabel.LEFT);

        alunoField = new JTextField();

        telefoneField = new JTextField();
        celularField = new JTextField();
        emailField = new JTextField();
        obsField = new JTextField();

        dataNascField = new DateField();

        sexoComboBox = new JComboBox<>(sexoList);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Informações"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(alunoLabel, constraints);
        constraints.gridy++;
        panel.add(dataNascLabel, constraints);
        constraints.gridy++;
        panel.add(telefoneLabel, constraints);
        constraints.gridy++;
        panel.add(emailLabel, constraints);
        constraints.gridy++;
        panel.add(obsLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        panel.add(obsField, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        panel.add(alunoField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(dataNascField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(telefoneField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        panel.add(emailField, constraints);

        constraints.gridx++;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(sexoLabel, constraints);
        constraints.gridy++;
        panel.add(celularLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(sexoComboBox, constraints);
        constraints.gridy++;
        panel.add(celularField, constraints);

        return panel;
    }

    private JPanel createAddressPanel() {

        JLabel enderecoLabel = new JLabel("Endereço:", JLabel.RIGHT);
        JLabel numeroLabel = new JLabel("Número:", JLabel.RIGHT);
        JLabel complementoLabel = new JLabel("Complemento:", JLabel.RIGHT);
        JLabel bairroLabel = new JLabel("Bairro:", JLabel.RIGHT);
        JLabel cidadeLabel = new JLabel("Cidade", JLabel.RIGHT);
        JLabel estadoLabel = new JLabel("Estado:", JLabel.RIGHT);
        JLabel paisLabel = new JLabel("País", JLabel.RIGHT);
        JLabel cepLabel = new JLabel("CEP:", JLabel.RIGHT);

        enderecoField = new JTextField();
        numeroField = new JTextField();
        complementoField = new JTextField();
        bairroField = new JTextField();
        cepField = new JTextField();

        paisComboBox = new JComboBox<>();
        paisComboBox.addItemListener(this);
        updatePaisComboBox();

        estadoComboBox = new JComboBox<>();
        estadoComboBox.addItemListener(this);
        updateEstadoComboBox();

        cidadeComboBox = new JComboBox<>();
        cidadeComboBox.addItemListener(this);
        updateCidadeComboBox();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Endereço"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(enderecoLabel, constraints);
        constraints.gridy++;
        panel.add(complementoLabel, constraints);
        constraints.gridy++;
        panel.add(bairroLabel, constraints);
        constraints.gridy++;
        panel.add(estadoLabel, constraints);
        constraints.gridy++;
        panel.add(cepLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(enderecoField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 3;
        panel.add(complementoField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        panel.add(bairroField, constraints);
        constraints.gridy++;
        constraints.weightx = 1;
        panel.add(estadoComboBox, constraints);
        constraints.gridy++;
        panel.add(cepField, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(numeroLabel, constraints);
        constraints.gridy += 2;
        panel.add(cidadeLabel, constraints);
        constraints.gridy++;
        panel.add(paisLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(numeroField, constraints);
        constraints.gridy += 2;
        panel.add(cidadeComboBox, constraints);
        constraints.gridy++;
        panel.add(paisComboBox, constraints);

        return panel;
    }

    private void updatePaisComboBox() {
        try {
            // get a distinct list of paises from database
            paisList = localDAO.selectPaises();
            // check database result
            if ((paisList != null) && (paisList.size() > 0)) {
                // insert result into combobox
                String[] vector = new String[paisList.size()];
                paisComboBox.setModel(new DefaultComboBoxModel<String>(paisList.toArray(vector)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
    }

    private void updateEstadoComboBox() {
        if (paisComboBox.getSelectedItem() == null) return;

        // clear estado-list
        if ((estadoList != null) && (estadoList.size() > 0)) {
            estadoComboBox.removeAllItems();
        }

        try {
            // get a distinct list of estados from database
            estadoList = localDAO.selectEstados(paisComboBox.getSelectedItem().toString());
            // check database result
            if ((estadoList != null) && (estadoList.size() > 0)) {
                // insert result into combobox
                String[] vector = new String[estadoList.size()];
                estadoComboBox.setModel(new DefaultComboBoxModel<>(estadoList.toArray(vector)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
    }

    private void updateCidadeComboBox() {
        if (paisComboBox.getSelectedItem() == null) return;
        if (estadoComboBox.getSelectedItem() == null) return;

        // clear combobox
        if ((cidadeList != null) && (cidadeList.size() > 0)) {
            cidadeComboBox.removeAllItems();
        }

        try {
            // get a list of cidades based on estado and pais.
            cidadeList = localDAO.selectCidades(estadoComboBox.getSelectedItem().toString(), paisComboBox.getSelectedItem().toString());
            // check database result
            if ((cidadeList != null) && (cidadeList.size() > 0)) {
                // insert result into combobox
                String[] vector = new String[cidadeList.size()];
                cidadeComboBox.setModel(new DefaultComboBoxModel<>(cidadeList.toArray(vector)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
    }

    private void setupInput() {

        boolean state = (this.insertEnabled || this.searchEnabled);

        toolbar.setMode(insertEnabled, searchEnabled);

        alunoField.setEditable(state);
        telefoneField.setEditable(state);
        celularField.setEditable(state);
        emailField.setEditable(state);
        obsField.setEditable(state);
        enderecoField.setEditable(state);
        numeroField.setEditable(state);
        complementoField.setEditable(state);
        bairroField.setEditable(state);
        cepField.setEditable(state);
        dataNascField.setEditable(state);
        sexoComboBox.setEnabled(state);
        cidadeComboBox.setEnabled(state);
        estadoComboBox.setEnabled(state);
        paisComboBox.setEnabled(state);
    }

    private Aluno getInput(Aluno tmp) {

        if (!alunoField.getText().isEmpty()) tmp.setAluno(alunoField.getText().trim());
        if (dataNascField.getDate() != null) tmp.setDataNascimento(dataNascField.getDate());
        if (sexoComboBox.getSelectedItem() != null) tmp.setSexo(sexoComboBox.getSelectedItem().toString().charAt(0));
        if (!telefoneField.getText().trim().isEmpty()) tmp.setTelefone(telefoneField.getText().trim());
        if (!celularField.getText().trim().isEmpty()) tmp.setCelular(celularField.getText().trim());
        if (!emailField.getText().trim().isEmpty()) tmp.setEmail(emailField.getText().trim());
        if (!obsField.getText().trim().isEmpty()) tmp.setObs(obsField.getText().trim());
        if (!enderecoField.getText().trim().isEmpty()) tmp.setEndereco(enderecoField.getText().trim());
        if (!numeroField.getText().trim().isEmpty()) tmp.setNumero(numeroField.getText());
        if (!complementoField.getText().trim().isEmpty()) tmp.setComplemento(complementoField.getText().trim());
        if (!bairroField.getText().trim().isEmpty()) tmp.setBairro(bairroField.getText().trim());
        if (!cepField.getText().trim().isEmpty()) tmp.setCEP(cepField.getText());

        if ((cidadeComboBox.getSelectedItem() != null) && (estadoComboBox.getSelectedItem() != null) && (paisComboBox.getSelectedItem() != null)) {
            if (!cidadeComboBox.getSelectedItem().toString().trim().isEmpty() &&
                    !estadoComboBox.getSelectedItem().toString().trim().isEmpty() &&
                    !paisComboBox.getSelectedItem().toString().trim().isEmpty()) {
                tmp.setLocal(new Local(
                        cidadeComboBox.getSelectedItem().toString(),
                        estadoComboBox.getSelectedItem().toString(),
                        paisComboBox.getSelectedItem().toString()
                ));
            }
        }

        return tmp;
    }

    private Aluno getInput() {
        return getInput(new Aluno());
    }

    private void updateInput(Aluno tmp) {
        if (tmp != null) {
            if (!tmp.getAluno().isEmpty()) alunoField.setText(tmp.getAluno());
            if (tmp.getDataNascimento() != null) dataNascField.setValue(tmp.getDataNascimento());
            sexoComboBox.setSelectedItem(String.format("%c", tmp.getSexo()));
            if ((tmp.getTelefone() != null) && !tmp.getTelefone().isEmpty()) telefoneField.setText(tmp.getTelefone());
            if ((tmp.getCelular() != null) && !tmp.getCelular().isEmpty()) celularField.setText(tmp.getCelular());
            if ((tmp.getEmail() != null) && !tmp.getEmail().isEmpty()) emailField.setText(tmp.getEmail());
            if ((tmp.getObs() != null) && !tmp.getObs().isEmpty()) obsField.setText(tmp.getObs());
            if ((tmp.getEndereco() != null) && !tmp.getEndereco().isEmpty()) enderecoField.setText(tmp.getEndereco());
            if ((tmp.getNumero() != null) && !tmp.getNumero().isEmpty()) numeroField.setText(tmp.getNumero());
            if ((tmp.getComplemento() != null) && !tmp.getComplemento().isEmpty()) complementoField.setText(tmp.getComplemento());
            if ((tmp.getBairro() != null) && !tmp.getBairro().isEmpty()) bairroField.setText(tmp.getBairro());
            if ((tmp.getCEP() != null) && !tmp.getCEP().isEmpty()) cepField.setText(tmp.getCEP());

            if (tmp.getLocal() != null) {
                if (!tmp.getLocal().getPais().isEmpty()) paisComboBox.setSelectedItem(tmp.getLocal().getPais());
                if (!tmp.getLocal().getEstado().isEmpty()) estadoComboBox.setSelectedItem(tmp.getLocal().getEstado());
                if (!tmp.getLocal().getCidade().isEmpty()) cidadeComboBox.setSelectedItem(tmp.getLocal().getCidade());
            }
        }
    }

    private void resetInput() {
        alunoField.setText("");
        dataNascField.setText("");
        sexoComboBox.setSelectedIndex(0);
        telefoneField.setText("");
        celularField.setText("");
        emailField.setText("");
        obsField.setText("");
        enderecoField.setText("");
        numeroField.setText("");
        complementoField.setText("");
        bairroField.setText("");
        cepField.setText("");
        paisComboBox.setSelectedIndex(0);
        estadoComboBox.setSelectedIndex(0);
        cidadeComboBox.setSelectedIndex(0);
    }

    private void addButtonAction() {
        this.insertEnabled = true;
        this.aluno = null;
    }

    private void saveButtonAction() {
        // INSERT INTO DATABASE
        if (insertEnabled) {
            try {
                aluno = this.getInput();
                if ((aluno != null) && (!alunoDAO.contains(aluno))) {
                    // insert into database
                    alunoDAO.insert(aluno);
                    // get codigo_aluno
                    aluno = (Aluno) alunoDAO.find(aluno);
                } else {
                    JOptionPane.showMessageDialog(null,  String.format("Aluno %s already exists on database.", aluno.getAluno()));
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            insertEnabled = false;
        }
        // UPDATE DATABASE
        else if (searchEnabled) {
            try {
                // apply modifications to aluno variable
                getInput(aluno);
                // update database
                alunoDAO.update(aluno);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.searchEnabled = false;
            this.aluno = null;
        }

        this.resetInput();
    }

    private void searchButtonAction() {
        if (!this.searchEnabled) {
            this.alunoField.addKeyListener(this);
        } else {
            this.alunoField.removeActionListener(this);
            this.resetInput();
        }
        this.searchEnabled = !this.searchEnabled;
    }

    private void removeButtonAction() {
        try {
            if (aluno != null) {
                // matriculaDAO.delete(aluno.getCodigoAluno());
                alunoDAO.delete(aluno);
                resetInput();
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }
        this.searchEnabled = false;
        this.aluno = null;
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
        this.setupInput();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == paisComboBox) {
            updateEstadoComboBox();
            this.pack();
        } else if (e.getSource() == estadoComboBox) {
            updateCidadeComboBox();
            this.pack();
        } else if (e.getSource() == cidadeComboBox) {
            this.pack();
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        if (event.getSource() == alunoField) {
            if (alunoField.getText().isEmpty()) {
                this.resetInput();
                // this.aluno = null;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getSource() == alunoField) {
            try {
                aluno = (Aluno) alunoDAO.find(getInput());
                this.updateInput(aluno);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
    }

}
