package app.frames;

import app.components.DateField;
import app.panels.ToolBarPanel;
import database.dao.AlunoDAO;
import database.dao.MatriculaDAO;
import database.dao.MatriculaModalidadeDAO;
import database.models.*;
import app.tables.MatriculaTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProcessosMatricularAlunosFrame extends JInternalFrame implements ActionListener, KeyListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;
    private AlunoDAO alunoDAO;
    private MatriculaDAO matriculaDAO;
    private MatriculaModalidadeDAO matriculaModalidadeDAO;
    private Aluno aluno;
    private Matricula matricula;
    private MatriculaModalidade matricula_modalidade;
    private List<MatriculaModalidade> list;
    private boolean insertEnabled = false;
    private boolean updateEnabled = false;

    /* components"*/
    private ToolBarPanel toolbar;
    private JTextField matricularField, keyField, alunoField, diaVencimentoField;
    private DateField dataMatriculaField;
    private JButton adicionarModalidadeButton;
    private JTable table;
    private AdicionarModalidadesDialog dialog;

    /* constructor: */
    public ProcessosMatricularAlunosFrame(Connection connection) {
        super("Matricular Aluno", isResizable, isClosable, isMaximizable, isIconifiable);

        this.connection = connection;
        this.alunoDAO = new AlunoDAO(connection);
        this.matriculaDAO = new MatriculaDAO(connection);
        this.matriculaModalidadeDAO = new MatriculaModalidadeDAO(connection);

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.setupInput();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
        toolbar.getAddButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().setEnabled(false);
        toolbar.getRemoveButton().addActionListener(this);

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(createPanel(), BorderLayout.CENTER);
        content.add(createTable(), BorderLayout.SOUTH);
    }

    private JPanel createPanel() {

        JLabel matriculaLabel = new JLabel("Matrícula:", JLabel.RIGHT);
        JLabel alunoLabel = new JLabel("Aluno:", JLabel.RIGHT);
        JLabel dataMatriculaLabel = new JLabel("Data Matrícula:", JLabel.RIGHT);
        JLabel diaVencimentoLabel = new JLabel("Dia do Vencimento da Fatura:", JLabel.RIGHT);

        matricularField = new JTextField(8);
        matricularField.setHorizontalAlignment(SwingConstants.CENTER);
        matricularField.setEnabled(false);

        alunoField = new JTextField(16);
        alunoField.setHorizontalAlignment(SwingConstants.LEFT);
        alunoField.addKeyListener(this);

        dataMatriculaField = new DateField();
        diaVencimentoField = new JTextField();

        keyField = new JTextField(8);
        keyField.setText("Teclar F9");
        keyField.setEditable(false);
        keyField.setBackground(Color.ORANGE);
        keyField.setHorizontalAlignment(SwingConstants.CENTER);

        adicionarModalidadeButton = new JButton("Adiciona Modalidade");
        adicionarModalidadeButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(border, border, border, border));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(matriculaLabel, constraints);
        constraints.gridy++;
        panel.add(alunoLabel, constraints);
        constraints.gridy++;
        panel.add(dataMatriculaLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(matricularField, constraints);
        constraints.gridy++;
        panel.add(keyField, constraints);
        constraints.gridy++;
        panel.add(dataMatriculaField, constraints);

        constraints.gridx++;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        panel.add(alunoField, constraints);
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.gridwidth = 1;
        panel.add(diaVencimentoLabel, constraints);
        constraints.gridx++;
        panel.add(diaVencimentoField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        panel.add(adicionarModalidadeButton, constraints);

        return panel;
    }

    private JScrollPane createTable() {

        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);
        updateTable();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(border, border, border, border));
        return scrollPane;
    }

    private void updateTable() {
        if (list == null) {
            list = new ArrayList<MatriculaModalidade>();
        }
        table.setModel(new MatriculaTableModel(list));
    }

    private void resetTable() {
        if (list != null) {
            list.clear();
        }
        this.updateTable();
    }

    private void setupInput() {
        boolean isEnabled = (this.insertEnabled || this.updateEnabled);
        toolbar.setMode(this.insertEnabled, this.updateEnabled);
        // matricularField.setEnabled(isEnabled);
        alunoField.setEnabled(isEnabled);
        dataMatriculaField.setEnabled(isEnabled);
        diaVencimentoField.setEnabled(isEnabled);
        adicionarModalidadeButton.setEnabled(isEnabled);
        table.setEnabled(isEnabled);

        this.enableAction((aluno != null) && (matricula != null)&& (matricula.getCodigoMatricula() > 0));
    }

    private void updateInput() {
        if (matricula != null) {
            matricularField.setText((matricula.getCodigoMatricula() > 0) ? String.valueOf(matricula.getCodigoMatricula()) : "");
            diaVencimentoField.setText((matricula.getDiaVencimento() > 0) ? String.valueOf(matricula.getDiaVencimento()) : String.valueOf(new Date().getDay()));
            dataMatriculaField.setValue((matricula.getDataMatricula() != null) ? matricula.getDataMatricula() : new Date());
        }
    }

    private void resetInput() {
        alunoField.setText("");
        matricularField.setText("");
        diaVencimentoField.setText(String.valueOf(new Date().getDay()));
        dataMatriculaField.setValue(new Date());
    }

    private Matricula getInput() {

        int codigo_aluno = ((aluno != null) && (aluno.getCodigoAluno() > 0)) ? aluno.getCodigoAluno() : 0;
        Date data_matricula = (dataMatriculaField.getDate() != null) ? dataMatriculaField.getDate() : new Date();
        int dia_vencimento = (!diaVencimentoField.getText().trim().isEmpty()) ? Integer.parseInt(diaVencimentoField.getText().trim()) : 0;

        if ((matricula != null) && (matricula.getCodigoMatricula() > 0)) {
            // update values
            if (!matricula.getDataMatricula().equals(data_matricula)) matricula.setDataMatricula(data_matricula);
            if (matricula.getDiaVencimento() != dia_vencimento) matricula.setDiaVencimento(dia_vencimento);

        } else {
            // create a new variable
            matricula = new Matricula(0, codigo_aluno, data_matricula, dia_vencimento, null);
        }
        return matricula;
    }

    private void addButtonAction() {
        this.insertEnabled = !this.insertEnabled;
        this.resetInput();
    }

    private void searchButtonAction() {
        this.updateEnabled = !this.updateEnabled;
        this.resetInput();
    }

    private void removeButtonAction() {
    }

    private void saveButtonAction() {
        // INSERT
        if (this.insertEnabled) {
            try {
                // update matricula variable changes.
                matricula = getInput();

                // if (!matriculaDAO.contains(matricula)) {
                if (matricula.getCodigoMatricula() == 0) {
                    matriculaDAO.insert(matricula);
                    matricula = (Matricula) matriculaDAO.find(matricula);
                }

                if ((list != null) && (list.size() > 0) && (matricula.getCodigoMatricula() > 0)) {
                    for (MatriculaModalidade v : list) {
                        v.setCodigoMatricula(matricula.getCodigoMatricula());
                        matriculaModalidadeDAO.insert(v);
                    }
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.insertEnabled = false;
            this.resetInput();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == adicionarModalidadeButton) {
            // open dialog to input information.
            dialog = new AdicionarModalidadesDialog(new JFrame(), connection);
            // return data from dialog form
            matricula_modalidade = dialog.getValue();
            if (matricula_modalidade != null) {
                list.add(matricula_modalidade);
                this.updateTable();
            }
        }
        // INSERT
        else if (event.getSource() == toolbar.getAddButton()) {
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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event) {
        /*
        if (event.getSource() == alunoField) {
            if ((event.getKeyCode() == KeyEvent.VK_F9) && (!alunoField.getText().trim().isEmpty())) {
                resetTable();
                try {
                    // read aluno input and search for it
                    aluno = (Aluno) alunoDAO.find(alunoField.getText().trim());
                    matricula = (Matricula) matriculaDAO.find(aluno);
                    updateInput();
                } catch (SQLException e) {
                    System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                }
            }
        }
        */
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getSource() == alunoField) {
            try {
                // get aluno name and check if aluno has already been registered.
                aluno = (Aluno) alunoDAO.find(alunoField.getText());
                // get aluno and check if aluno has already been registered.
                matricula = (Matricula) matriculaDAO.find(aluno);
                if ((matricula != null) && (matricula.getCodigoMatricula() > 0)) {
                    // convert a list of objects into a list of MatriculaModalidade
                    for (Object obj : matriculaModalidadeDAO.select(matricula)) {
                        list.add((MatriculaModalidade) obj);
                    }
                    this.updateTable();
                    this.updateInput();
                    this.enableAction(true);
                } else {
                    this.resetTable();
                    this.enableAction(false);
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
    }

    private void enableAction(boolean b) {
        this.adicionarModalidadeButton.setEnabled(b);
        this.toolbar.getSaveButton().setEnabled(b);
    }

}
