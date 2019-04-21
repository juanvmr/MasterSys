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

        enableInput();
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
        list = new ArrayList<>();
        table.setModel(new MatriculaTableModel(list));
    }

    private void enableInput() {
        boolean isEnabled = (this.insertEnabled || this.updateEnabled);
        toolbar.setMode(this.insertEnabled, this.updateEnabled);
        // matricularField.setEnabled(isEnabled);
        alunoField.setEnabled(isEnabled);
        dataMatriculaField.setEnabled(isEnabled);
        diaVencimentoField.setEnabled(isEnabled);
        adicionarModalidadeButton.setEnabled(isEnabled);
        table.setEnabled(isEnabled);
    }

    private Matricula getInput() {

        int dia_vencimento = (!diaVencimentoField.getText().trim().isEmpty()) ? Integer.parseInt(diaVencimentoField.getText().trim()) : 0;
        Date data_matricula = (dataMatriculaField.getDate() != null) ? dataMatriculaField.getDate() : new Date();
        Date data_encarramento = new Date(data_matricula.getYear(), data_matricula.getMonth(), dia_vencimento);

        if (aluno != null) {
            return new Matricula(aluno.getCodigoAluno(), data_matricula, dia_vencimento, data_encarramento);
        }
        return null;
    }

    private void updateInput() {
        if (matricula != null) {
            matricularField.setText((matricula.getCodigoMatricula() > 0) ? String.valueOf(matricula.getCodigoMatricula()) : "");
            diaVencimentoField.setText((matricula.getDiaVencimento() > 0) ? String.valueOf(matricula.getDiaVencimento()) : "");
            dataMatriculaField.setValue((matricula.getDataMatricula() != null) ? matricula.getDataMatricula() : new Date());
        } else {
            matricularField.setText("");
            diaVencimentoField.setText("");
            dataMatriculaField.setValue(new Date());
        }
    }

    private void resetInput() {
        alunoField.setText("");
        matricularField.setText("");
        diaVencimentoField.setText("");
        dataMatriculaField.setValue(new Date());
    }

    private void addButtonAction() {
        this.insertEnabled = true;
        resetInput();
    }

    private void searchButtonAction() {
        this.updateEnabled = true;
        updateInput();
    }

    private void removeButtonAction() {
        resetInput();
    }

    private void saveButtonAction() {
        // INSERT
        if (this.insertEnabled) {

            try {
                Matricula tmp = getInput();

                if (tmp != null) {
                    matriculaDAO.insert(tmp);
                }

                if ((list != null) && (list.size() > 0)) {
                    for (MatriculaModalidade v : list) {
                        matriculaModalidadeDAO.insert(v);
                    }
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }

            this.insertEnabled = false;
        }
        // clear all fields
        resetInput();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == adicionarModalidadeButton) {

            dialog = new AdicionarModalidadesDialog(new JFrame(), connection, matricula_modalidade);
            matricula_modalidade = dialog.getValue();

            if (matricula_modalidade != null) {
                list.add(matricula_modalidade);
                updateTable();
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
        enableInput();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event) {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
