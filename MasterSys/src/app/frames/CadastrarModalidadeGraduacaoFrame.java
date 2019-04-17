package app.frames;

import app.components.DateFormattedTextField;
import app.panels.ToolBarPanel;
import app.tables.GraduacaoTableModel;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import database.models.Graduacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CadastrarModalidadeGraduacaoFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;
    private List<Graduacao> list;

    /* components: */
    private ToolBarPanel toolbar;
    private JLabel modalidadeLabel, graduacaoLabel;
    private JTextField modalidadeField, graduacaoField;
    private JButton okButton;
    private JTable table;

    /* constructors: */
    public CadastrarModalidadeGraduacaoFrame(Connection connection) {
        super("Cadastrar Modalidade e Graduação", isResizable, isClosable, isMaximizable, isIconifiable);

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
        JScrollPane tableFrame = createTable();

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(infoPanel, BorderLayout.CENTER);
        content.add(tableFrame, BorderLayout.SOUTH);
    }

    private JPanel createInfoPanel() {

        modalidadeLabel = new JLabel("Modalidade:", JLabel.RIGHT);
        graduacaoLabel = new JLabel("Graduação:", JLabel.RIGHT);

        modalidadeField = new JTextField();
        graduacaoField = new JTextField();

        okButton = new JButton("OK");
        okButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(border, border, border, border));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(modalidadeLabel, constraints);
        constraints.gridy++;
        panel.add(graduacaoLabel, constraints);


        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        panel.add(modalidadeField, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(graduacaoField, constraints);

        constraints.gridx++;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(okButton, constraints);

        return panel;
    }

    private JScrollPane createTable() {

        list = new ArrayList<Graduacao>();
        list.add(new Graduacao("Modalidade 1", "Graduacao A"));
        list.add(new Graduacao("Modalidade 1", "Graduacao B"));
        list.add(new Graduacao("Modalidade 2", "Graduacao A"));
        list.add(new Graduacao("Modalidade 3", "Graduacao A"));

        table = new JTable();
        table.setModel(new GraduacaoTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(border, border, border, border));
        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            // code
        } else if (e.getSource() == toolbar.getAddButton()) {
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
