package app.frames;

import app.components.*;
import app.panels.*;
import database.dao.*;
import database.models.Aluno;
import database.models.Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CadastrarAlunosFrame extends JInternalFrame implements ActionListener, ItemListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = true;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;
    private LocalDAO localDAO;
    private List<String> cidadeList, estadoList, paisList;

    /* components: */
    private ToolBarPanel toolbar;
    private JLabel alunoLabel, dataNascLabel, sexoLabel, telefoneLabel, celularLabel, emailLabel, obsLabel;
    private JLabel enderecoLabel, numeroLabel, complementoLabel, bairroLabel, cidadeLabel, estadoLabel, paisLabel, cepLabel;
    private JTextField alunoField, telefoneField, celularField, emailField, obsField;
    private JTextField enderecoField, numeroField, complementoField, bairroField, cepField;
    private DateFormattedTextField dataNascField;
    private JComboBox<String> sexoField, cidadeComboBox, estadoComboBox, paisComboBox;

    /* constructors: */
    public CadastrarAlunosFrame(Connection connection) {
        super("Cadastrar Aluno", isResizable, isClosable, isMaximizable, isIconifiable);

        this.connection = connection;

        try {
            localDAO = new LocalDAO(connection);
        } catch (SQLException e) {
            System.out.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
        toolbar.getAddButton().addActionListener(this);
        toolbar.getRemoveButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().addActionListener(this);

        JPanel infoPanel = createInfoPanel();
        JPanel addressPanel = createAddressPanel();

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(infoPanel, BorderLayout.CENTER);
        content.add(addressPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {

        alunoLabel = new JLabel("Aluno:", JLabel.RIGHT);
        dataNascLabel = new JLabel("Data de Nascimento:", JLabel.RIGHT);
        sexoLabel = new JLabel("Sexo:", JLabel.RIGHT);
        telefoneLabel = new JLabel("Telefone:", JLabel.RIGHT);
        celularLabel = new JLabel("Celular:", JLabel.RIGHT);
        emailLabel = new JLabel("E-mail:", JLabel.RIGHT);
        obsLabel = new JLabel("Obs.:", JLabel.LEFT);

        alunoField = new JTextField();
        telefoneField = new JTextField();
        celularField = new JTextField();
        emailField = new JTextField();
        obsField = new JTextField();

        dataNascField = new DateFormattedTextField();

        String[] sexoList = { "M", "F" };
        sexoField = new JComboBox<>(sexoList);

        JPanel panel = new JPanel(new GridBagLayout());
        // panel.setBorder(new EmptyBorder(border, border, border, border));
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
        panel.add(sexoField, constraints);
        constraints.gridy++;
        panel.add(celularField, constraints);

        return panel;
    }

    private JPanel createAddressPanel() {

        enderecoLabel = new JLabel("Endereço:", JLabel.RIGHT);
        numeroLabel = new JLabel("Número:", JLabel.RIGHT);
        complementoLabel = new JLabel("Complemento:", JLabel.RIGHT);
        bairroLabel = new JLabel("Bairro:", JLabel.RIGHT);
        cidadeLabel = new JLabel("Cidade", JLabel.RIGHT);
        estadoLabel = new JLabel("Estado:", JLabel.RIGHT);
        paisLabel = new JLabel("País", JLabel.RIGHT);
        cepLabel = new JLabel("CEP:", JLabel.RIGHT);

        enderecoField = new JTextField();
        numeroField = new JTextField();
        complementoField = new JTextField();
        bairroField = new JTextField();
        cepField = new JTextField();

        paisComboBox = new JComboBox<>();
        paisComboBox.addItemListener(this);
        updatePaisList();

        estadoComboBox = new JComboBox<>();
        estadoComboBox.addItemListener(this);
        updateEstadoList();

        cidadeComboBox = new JComboBox<>();
        cidadeComboBox.addItemListener(this);
        updateCidadeList();

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

    private void updatePaisList() {
        try {
            paisList = localDAO.selectPaises();

            if (paisList != null && paisList.size() > 0) {
                String[] teste = new String[paisList.size()];
                paisComboBox.setModel(new DefaultComboBoxModel<String>(paisList.toArray(teste)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("lista de paises está vazia.");
        }
    }

    private void updateEstadoList() {

        // clear list
        if (estadoList != null && estadoList.size() > 0) {
            estadoComboBox.removeAllItems();
        }

        try {
            estadoList = localDAO.selectEstados(paisComboBox.getSelectedItem().toString());

            if (estadoList != null && estadoList.size() > 0) {
                String[] estadoVector = new String[estadoList.size()];
                estadoComboBox.setModel(new DefaultComboBoxModel<>(estadoList.toArray(estadoVector)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("lista de estados está vazia.");
        }
    }

    private void updateCidadeList() {

        // clear list
        if (cidadeList != null && cidadeList.size() > 0) {
            cidadeComboBox.removeAllItems();
        }

        try {
            cidadeList = localDAO.selectCidades(estadoComboBox.getSelectedItem().toString(), paisComboBox.getSelectedItem().toString());

            if (cidadeList != null && cidadeList.size() > 0) {
                String[] cidadeVector = new String[cidadeList.size()];
                cidadeComboBox.setModel(new DefaultComboBoxModel<>(cidadeList.toArray(cidadeVector)));
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("lista de cidades está vazia.");
        }
    }

    private Aluno readInput() {

        Aluno tmp = new Aluno();

        if (!alunoField.getText().isEmpty()) tmp.setAluno(alunoField.getText().trim());
        if (dataNascField.getDate() != null) tmp.setDataNascimento(dataNascField.getDate());
        //tmp.setSexo(sexoField.getSelectedItem().toString());
        if (!telefoneField.getText().trim().isEmpty()) tmp.setTelefone(telefoneField.getText().trim());
        if (!celularField.getText().trim().isEmpty()) tmp.setCelular(celularField.getText().trim());
        if (!emailField.getText().trim().isEmpty()) tmp.setEmail(emailField.getText().trim());
        if (obsField.getText().trim().isEmpty()) tmp.setObs(obsField.getText().trim());
        if (!enderecoField.getText().trim().isEmpty()) tmp.setEndereco(enderecoField.getText().trim());
        if (!numeroField.getText().trim().isEmpty()) tmp.setNumero(numeroField.getText());
        if (!complementoField.getText().trim().isEmpty()) tmp.setComplemento(complementoField.getText().trim());
        if (!bairroField.getText().trim().isEmpty()) tmp.setBairro(bairroField.getText().trim());

        if (!cidadeComboBox.getSelectedItem().toString().trim().isEmpty() &&
            !estadoComboBox.getSelectedItem().toString().trim().isEmpty() &&
            !paisComboBox.getSelectedItem().toString().trim().isEmpty()) {
            tmp.setLocal(new Local(
                    cidadeComboBox.getSelectedItem().toString(),
                    estadoComboBox.getSelectedItem().toString(),
                    paisComboBox.getSelectedItem().toString()
            ));
        }

        if (!cepField.getText().trim().isEmpty()) tmp.setCEP(cepField.getText());

        return tmp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toolbar.getAddButton()) {
            // code
            System.out.println(new Aluno());
        } else if (e.getSource() == toolbar.getSaveButton()) {
            // code
        } else if (e.getSource() == toolbar.getSearchButton()) {
            // code
        } else if (e.getSource() == toolbar.getRemoveButton()) {
            // code
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == paisComboBox) {
            updateEstadoList();
            this.pack();
        } else if (e.getSource() == estadoComboBox) {
            updateCidadeList();
            this.pack();
        } else if (e.getSource() == cidadeComboBox) {
            this.pack();
        }
    }

}
