package app.frames;

import app.components.DateField;
import database.dao.AlunoDAO;
import database.dao.FaturaDAO;
import database.models.*;
import app.tables.FaturasTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessosConsultarFaturasFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static String[] statusList = new String[] { "Todas", "Em Aberto", "Pagas", "Canceladas" };
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private FaturaDAO faturaDAO;
    private AlunoDAO alunoDAO;

    /* components: */
    private DateField fromDateField, toDateField;
    private JComboBox<String> statusComboBox;
    private JButton searchButton;
    private JTable table;
    private List<Fatura> list;

    /* constructors: */
    public ProcessosConsultarFaturasFrame(Connection connection) {
        super("Consultar Faturas", isResizable, isClosable, isMaximizable, isIconifiable);

        this.faturaDAO = new FaturaDAO(connection);
        this.alunoDAO = new AlunoDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new EmptyBorder(border, border, border, border));
        content.add(createSearchBar(), BorderLayout.NORTH);
        content.add(createTable(), BorderLayout.CENTER);
        this.setContentPane(content);
    }

    private JPanel  createSearchBar() {

        /* components: */
        JLabel fromLabel = new JLabel("De:", JLabel.RIGHT);
        JLabel toLabel = new JLabel("Até:", JLabel.RIGHT);
        JLabel statusLabel = new JLabel("Situação:", JLabel.RIGHT);

        fromDateField = new DateField();
        fromDateField.setValue(new Date());

        toDateField = new DateField();
        toDateField.setValue(new Date());

        statusComboBox = new JComboBox<>();
        statusComboBox.setModel(new DefaultComboBoxModel<>(statusList));

        searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(this);

        JPanel panel = new JPanel();
        // panel.setBorder(new EmptyBorder(border, border, border, border));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(fromLabel, constraints);
        constraints.gridx++;
        panel.add(fromDateField, constraints);
        constraints.gridx++;
        panel.add(toLabel, constraints);
        constraints.gridx++;
        panel.add(toDateField, constraints);
        constraints.gridx++;
        panel.add(statusLabel, constraints);
        constraints.gridx++;
        panel.add(statusComboBox, constraints);
        constraints.gridx++;
        panel.add(searchButton, constraints);

        return panel;
    }

    private JScrollPane createTable() {

        list = new ArrayList<Fatura>();
        list.add(new Fatura(1, new Date(2019,4,30), 150.0, new Date(2019, 5, 30), null));
        list.add(new Fatura(2, new Date(2019,3,30), 150.0, new Date(2019, 7, 30), null));
        list.add(new Fatura(3, new Date(2019,5,30), 150.0, new Date(2019, 8, 30), new Date(2019, 6, 21)));

        table = new JTable();
        table.setModel(new FaturasTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            Date fromDate = fromDateField.getDate();
            Date toDate = toDateField.getDate();
            String status = (String) statusComboBox.getSelectedItem();

            System.out.println("Search - from: " + fromDate + ", to: " + toDate + ", status: " + status);
        }
    }
}
