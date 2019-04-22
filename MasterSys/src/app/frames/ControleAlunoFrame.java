package app.frames;

import app.components.MonthChooser;
import app.tables.AssiduidadeTableModel;
import app.tables.FaturasTableModel;
import app.tables.MatriculaTableModel;
import database.ConnectionFactory;
import database.dao.AlunoDAO;
import database.dao.MatriculaDAO;
import database.models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ControleAlunoFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Matricula matricula;
    private Aluno aluno;
    private MatriculaDAO matriculaDAO;
    private AlunoDAO alunoDAO;
    private List<MatriculaModalidade> modaldiadeList;
    private List<Fatura> faturaList;
    private List<Assiduidade> assiduidadeList;

    /* components: */
    private MonthChooser monthChooser;
    private JTextField matriculaField, alunoField, statusField;
    private JButton alunoButton, matriculaButton;
    private JTable modaldiadeTable, faturaTable, assiduidadeTable;

    /* constructors: */
    public ControleAlunoFrame(Connection connection) {
        super("Controle de Alunos", isResizable, isClosable, isMaximizable, isIconifiable);

        this.matriculaDAO = new MatriculaDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {

        monthChooser = new MonthChooser();

        alunoButton = new JButton("Acessar Dados Aluno");
        alunoButton.addActionListener(this);

        matriculaButton = new JButton("Acessar Dados Matricula");
        matriculaButton.addActionListener(this);

        JPanel panel_A = createPanelA();
        JScrollPane panel_B = createPanelB();
        JPanel panel_C = createPanelC();
        JScrollPane panel_D = createPanelD();

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(inset, inset, inset, inset));
        content.setMinimumSize(new Dimension(500, 300));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        content.add(panel_A, constraints);
        constraints.gridy++;
        content.add(monthChooser, constraints);
        constraints.gridy++;
        content.add(panel_B, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        content.add(panel_C, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        content.add(alunoButton, constraints);
        constraints.gridx++;
        constraints.gridwidth = 1;
        content.add(matriculaButton, constraints);

        constraints.gridx = 1;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        content.add(panel_D, constraints);

        this.setContentPane(content);
    }

    private JPanel createPanelA() {

        int width = monthChooser.getWidth();
        int height = (int) (1.5 * width);

        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(new LineBorder(Color.BLACK, 1, false));

        return panel;
    }

    private JScrollPane createPanelB() {
        assiduidadeList = new ArrayList<>();

        assiduidadeTable = new JTable();
        assiduidadeTable.setModel(new AssiduidadeTableModel(assiduidadeList));
        assiduidadeTable.setPreferredScrollableViewportSize(new Dimension(150, 200));
        assiduidadeTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(assiduidadeTable);
        // scrollPane.setBorder(new EmptyBorder(inset, inset, inset, inset));
        return scrollPane;
    }

    private JPanel createPanelC() {

        matriculaField = new JTextField(16);
        matriculaField.setText("Matricula");
        matriculaField.setHorizontalAlignment(SwingConstants.CENTER);
        matriculaField.getDocument().addDocumentListener(new MatriculaListerner());

        alunoField = new JTextField(32);
        alunoField.setText("Aluno");
        alunoField.setEditable(false);

        statusField = new JTextField(32);
        statusField.setText("Status");
        statusField.setEditable(false);
        statusField.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(matriculaField, constraints);
        constraints.gridx++;
        constraints.gridwidth = 3;
        panel.add(alunoField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        panel.add(createTablePanel(), constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(statusField, constraints);

        return panel;
    }

    private JScrollPane createPanelD() {
        faturaList = new ArrayList<>();

        faturaTable = new JTable();
        faturaTable.setModel(new FaturasTableModel(faturaList));
        faturaTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        faturaTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(faturaTable);
        // scrollPane.setBorder(new EmptyBorder(inset, inset, inset, inset));
        return scrollPane;
    }

    private JScrollPane createTablePanel() {

        modaldiadeList = new ArrayList<>();

        modaldiadeTable = new JTable();
        modaldiadeTable.setModel(new MatriculaTableModel(modaldiadeList));
        modaldiadeTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        modaldiadeTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(modaldiadeTable);
        // scrollPane.setBorder(new EmptyBorder(inset, inset, inset, inset));
        return scrollPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Usuario user = new Usuario();
                user.setUsername("admin");
                user.setPassword("admin");

                Connection connection;
                // connection = ConnectionFactory.getConnection("master", "admin", "admin");
                connection = ConnectionFactory.getDebugConnection(user.getUsername(), user.getPassword());
                new ControleAlunoFrame(connection);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alunoButton) {
            // CODE
        } else if (e.getSource() == matriculaButton) {
            // CODE
        }
    }

    class MatriculaListerner implements DocumentListener {

        final String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            updateLog(e, "inserted into");
            int codigo_matricula = (!matriculaField.getText().trim().isEmpty()) ? Integer.parseInt(matriculaField.getText()) : 0;
            try {
                matricula = (Matricula) matriculaDAO.find(codigo_matricula);
                updateLog(e, matricula.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        public void removeUpdate(DocumentEvent e) {
            updateLog(e, "removed from");
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }

        public void updateLog(DocumentEvent e, String action) {
            Document doc = (Document)e.getDocument();
            int changeLength = e.getLength();
            statusField.setText(
                    changeLength + " character"
                            + ((changeLength == 1) ? " " : "s ")
                            + action + " " + doc.getProperty("name") + "."
                            + newline
                            + "  Text length = " + doc.getLength() + newline);
            statusField.setCaretPosition(statusField.getDocument().getLength());
        }
    }
}
