package app.frames;

import database.models.*;
import app.tables.FaturasTableModel;
import app.panels.SearchBarPanel;

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
    private static boolean isResizable = true;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;

    /* components: */
    private SearchBarPanel searchBarPanel;
    private JTable table;
    private JScrollPane tablePanel;
    private List<Fatura> list;

    /* constructors: */
    public ProcessosConsultarFaturasFrame(Connection connection) {
        super("Consultar Faturas", isResizable, isClosable, isMaximizable, isIconifiable);

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {

        int border = 10;

        searchBarPanel = new SearchBarPanel();
        tablePanel = createTable();

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new EmptyBorder(border, border, border, border));
        content.add(searchBarPanel, BorderLayout.NORTH);
        content.add(tablePanel, BorderLayout.CENTER);

        this.setContentPane(content);
    }

    private JScrollPane createTable() {

        list = new ArrayList<Fatura>();
        list.add(new Fatura(1, new Date(2019,4,30), 150.0, new Date(2019, 5, 30), null));
        list.add(new Fatura(2, new Date(2019,3,30), 150.0, new Date(2019, 7, 30), null));
        list.add(new Fatura(3, new Date(2019,5,30), 150.0, new Date(2019, 8, 30), new Date(2019, 6, 21)));

        table = new JTable();
        table.setModel(new FaturasTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        return new JScrollPane(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBarPanel.getSearchButton()) {
            Date fromDate = searchBarPanel.getFromDate();
            Date toDate = searchBarPanel.getToDate();
            String status = searchBarPanel.getStatus();
        }
    }
}
