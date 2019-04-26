package app.frames;

import app.components.DateField;
import app.tables.ColorRender;
import database.dao.AlunoDAO;
import database.dao.FaturaMatriculaDAO;
import database.models.*;
import app.tables.FaturasTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;

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
    private FaturasTableModel faturasTableModel;
    private TablePopupMenu faturasPopupMenu;

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
        faturasTableModel = new FaturasTableModel();

        faturasTable = new JTable();
        faturasTable.setModel(faturasTableModel);
        faturasTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        faturasTable.setFillsViewportHeight(true);
        faturasTable.addMouseListener(this);

        ColorRender renderer = new ColorRender();
        faturasTable.setDefaultRenderer(Date.class, renderer);
        faturasTable.setDefaultRenderer(String.class, renderer);
        faturasTable.setDefaultRenderer(Double.class, renderer);
        faturasTable.setDefaultRenderer(Integer.class, renderer);

        faturasPopupMenu = new TablePopupMenu(faturasTable);

        //Create the scroll pane and add the faturasTable to it.
        JScrollPane scrollPane = new JScrollPane(faturasTable);
        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == searchButton) {
            faturasTableModel.clear();
            try {
                Date fromDate = fromDateField.getDate();
                Date toDate = toDateField.getDate();
                String status = (String) statusComboBox.getSelectedItem();

                if ((fromDate != null) && (toDate != null) && (status != null)) {
                    for (Object obj : faturaMatriculaDAO.select(fromDate, toDate, status)) {
                        FaturaMatricula f = (FaturaMatricula) obj;
                        faturasTableModel.insert(f);
                    }
                }
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == faturasTable) {
            if (SwingUtilities.isRightMouseButton(e)) {     //if (e.getButton() == MouseEvent.BUTTON3) {
                // print out right-clicked row
                int row = faturasTable.rowAtPoint(e.getPoint());
                if ((row >= 0) && (row < faturasTable.getRowCount())) {
                    faturasTable.setRowSelectionInterval(row, row);
                    faturasPopupMenu.show(faturasTableModel.getRow(row), e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
