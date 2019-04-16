package frames;

import database.models.*;
import frames.tables.MatriculaTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProcessoMatriculaAlunoFrame extends JFrame implements ActionListener {

    /* attributes: */
    private Connection conneciton;
    private List<MatriculaModalidade> list;

    /* components"*/
    private ToolBarPanel toolbarPanel;
    private JPanel centralPanel;

    private JLabel matriculaLabel, alunoLabel, dataMatriculaLabel, diaVencimentoLabel;
    private JTextField matricularField, alunoField, dataMatriculaField, diaVencimentoField;
    private JButton adicionarModalidadeButton;
    private JTable table;

    /* constructor: */
    public ProcessoMatriculaAlunoFrame() {
        super("Matricular Aluno");
        this.setLayout(null);
        // this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents(Container content) {

        toolbarPanel = new ToolBarPanel();
        centralPanel = createPanel();

        content.setLayout(new BorderLayout());
        content.add(toolbarPanel, BorderLayout.NORTH);
        content.add(centralPanel, BorderLayout.CENTER);
    }

    private JPanel createPanel() {

        matriculaLabel = new JLabel("Matrícula:", JLabel.CENTER);
        alunoLabel = new JLabel("Aluno:", JLabel.CENTER);
        dataMatriculaLabel = new JLabel("Data Matrícula:", JLabel.CENTER);
        diaVencimentoLabel = new JLabel("Dia do Vencimento da Fatura:", JLabel.CENTER);

        matricularField = new JTextField(8);
        alunoField = new JTextField(8);
        dataMatriculaField = new JTextField(8);
        diaVencimentoField = new JTextField(8);

        adicionarModalidadeButton = new JButton("Adiciona Modalidade");
        adicionarModalidadeButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
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
        panel.add(alunoField, constraints);
        constraints.gridy++;
        panel.add(dataMatriculaField, constraints);

        constraints.gridx++;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        panel.add(new JTextField(16), constraints);
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

        // table panel
        constraints.gridy++;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        panel.add(createTable(), constraints);

        return panel;
    }

    private JScrollPane createTable() {

        list = new ArrayList<MatriculaModalidade>();
        list.add(new MatriculaModalidade(1, "Modalidade A", "Graduaçao 1", "Plano 1", new Date(2019, 06, 02), new Date(2020, 6, 2)));
        list.add(new MatriculaModalidade(2, "Modalidade B", "Graduaçao 1", "Plano 2", new Date(2019, 06, 02), new Date(2020, 6, 2)));
        list.add(new MatriculaModalidade(2, "Modalidade X", "Graduaçao 2", "Plano 3", new Date(2019, 04, 02), new Date(2020, 8, 2)));
        list.add(new MatriculaModalidade(2, "Modalidade Y", "Graduaçao 2", "Plano 3", new Date(2019, 04, 02), new Date(2020, 8, 2)));
        list.add(new MatriculaModalidade(2, "Modalidade H", "Graduaçao 2", "Plano 3", new Date(2019, 04, 02), new Date(2020, 8, 2)));
        list.add(new MatriculaModalidade(2, "Modalidade G", "Graduaçao 2", "Plano 3", new Date(2019, 04, 02), new Date(2020, 8, 2)));

        table = new JTable();
        table.setModel(new MatriculaTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adicionarModalidadeButton) {
            AdicinarModalidadeFrame f = new AdicinarModalidadeFrame(conneciton);
            f.setVisible(true);
        } else if (e.getSource() == toolbarPanel.getAddButton()) {
            // code
        } else if (e.getSource() == toolbarPanel.getSaveButton()) {
            // code
        } else if (e.getSource() == toolbarPanel.getSearchButton()) {
            // code
        } else if (e.getSource() == toolbarPanel.getRemoveButton()) {
            // code
        }
    }
}
