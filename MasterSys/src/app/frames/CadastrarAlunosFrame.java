package app.frames;

import app.components.DateFormattedTextField;
import app.panels.ToolBarPanel;
import database.ConnectionFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CadastrarAlunosFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;

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

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
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
        sexoField = new JComboBox<>();
        dataNascField = new DateFormattedTextField();

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

        cidadeComboBox = new JComboBox<>();
        estadoComboBox = new JComboBox<>();
        paisComboBox = new JComboBox<>();

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toolbar.getAddButton()) {
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
