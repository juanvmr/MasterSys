package app.frames;

import app.components.DateField;
import app.tables.ColorRender;
import database.dao.AlunoDAO;
import database.dao.FaturaMatriculaDAO;
import database.models.*;
import app.tables.FaturasTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessosConsultarFaturasFrame extends JInternalFrame implements ActionListener, MouseListener {

    /* config: */
    private static String[] statusList = new String[] { "Todas", "Em Aberto", "Pagas", "Canceladas" };
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private FaturaMatriculaDAO faturaMatriculaDAO = MenuFrame.faturaMatriculaDAO;
    private AlunoDAO alunoDAO = MenuFrame.alunoDAO;

    /* components: */
    private DateField fromDateField, toDateField;
    private JComboBox<String> statusComboBox;
    private JButton searchButton;
    private JTable faturasTable;
    private List<FaturaMatricula> faturaMatriculaList;

    /* constructors: */
    public ProcessosConsultarFaturasFrame() {
        super("Consultar Faturas", isResizable, isClosable, isMaximizable, isIconifiable);

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

        Date currentDate = new Date();

        /* components: */
        JLabel fromLabel = new JLabel("De:", JLabel.RIGHT);
        JLabel toLabel = new JLabel("Até:", JLabel.RIGHT);
        JLabel statusLabel = new JLabel("Situação:", JLabel.RIGHT);

        fromDateField = new DateField();
        fromDateField.setValue(new Date(currentDate.getYear(), currentDate.getMonth(), 01));

        toDateField = new DateField();
        toDateField.setValue(new Date(currentDate.getYear(), currentDate.getMonth() + 1, 30));

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

        faturaMatriculaList = new ArrayList<>();

        faturasTable = new JTable();
        faturasTable.setModel(new FaturasTableModel());
        faturasTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        faturasTable.setFillsViewportHeight(true);
        faturasTable.addMouseListener(this);

        ColorRender renderer = new ColorRender();
        faturasTable.setDefaultRenderer(Date.class, renderer);
        faturasTable.setDefaultRenderer(String.class, renderer);
        faturasTable.setDefaultRenderer(Double.class, renderer);
        faturasTable.setDefaultRenderer(Integer.class, renderer);

        //Create the scroll pane and add the faturasTable to it.
        JScrollPane scrollPane = new JScrollPane(faturasTable);
        return scrollPane;
    }

    private void updateTable() {
        if ((faturaMatriculaList != null)) {
            faturasTable.setModel(new FaturasTableModel(faturaMatriculaList));
        } else {
            faturaMatriculaList = new ArrayList<FaturaMatricula>();
        }
    }

    private void clearTable() {
        if ((faturaMatriculaList != null) && (faturaMatriculaList.size() > 0)) {
            faturaMatriculaList.clear();
            this.updateTable();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == searchButton) {
            this.clearTable();
            try {
                Date fromDate = fromDateField.getDate();
                Date toDate = toDateField.getDate();
                String status = (String) statusComboBox.getSelectedItem();

                if ((fromDate != null) && (toDate != null) && (status != null)) {
                    for (Object obj : faturaMatriculaDAO.select(fromDate, toDate, status)) {
                        FaturaMatricula f = (FaturaMatricula) obj;
                        faturaMatriculaList.add(f);
                        this.updateTable();
                    }
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == faturasTable) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                // print out right-clicked row
                int row = faturasTable.rowAtPoint(e.getPoint());
                System.err.println(((FaturasTableModel) faturasTable.getModel()).getRow(row));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
