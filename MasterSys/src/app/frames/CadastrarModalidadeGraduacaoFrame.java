package app.frames;

import app.components.DateFormattedTextField;
import app.panels.ToolBarPanel;
import app.tables.GraduacaoTableModel;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import database.dao.GraduacaoDAO;
import database.dao.ModalidadeDAO;
import database.models.Graduacao;
import database.models.Modalidade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastrarModalidadeGraduacaoFrame extends JInternalFrame implements ActionListener, KeyListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private ModalidadeDAO modalidadeDAO;
    private GraduacaoDAO graduacaoDAO;
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

        this.modalidadeDAO = new ModalidadeDAO(connection);
        this.graduacaoDAO = new GraduacaoDAO(connection);

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();
        toolbar.getAddButton().addActionListener(this);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getSearchButton().addActionListener(this);

        toolbar.getRemoveButton().addActionListener(this);
        toolbar.getRemoveButton().setEnabled(false);

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
        modalidadeField.addKeyListener(this);

        graduacaoField = new JTextField();

        okButton = new JButton("OK");
        okButton.addActionListener(this);

        // lock input fields
        this.lock();

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

        table = new JTable();
        table.setModel(new GraduacaoTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(border, border, border, border));
        return scrollPane;
    }

    private void lock() {
        modalidadeField.setEnabled(false);
        graduacaoField.setEnabled(false);
        okButton.setEnabled(false);
    }

    private void unlock() {
        modalidadeField.setEnabled(true);
        graduacaoField.setEnabled(true);
        okButton.setEnabled(true);
    }

    private boolean isLocked() {
        return (!modalidadeField.isEnabled() || !graduacaoField.isEnabled() || !okButton.isEnabled());
    }

    private void resetTable() {
        // remove old values
        list.clear();
        // display a empty table
        table.setModel(new GraduacaoTableModel(list));
    }

    private void updateTable() {
        if ((list != null) && (list.size() > 0)) {
            table.setModel(new GraduacaoTableModel(list));
        } else {
            resetTable();
        }
    }

    private Modalidade getModalidade() {
        if (!modalidadeField.getText().trim().isEmpty()) {
            return new Modalidade(modalidadeField.getText().trim());
        }
        return null;
    }

    private Graduacao getGraduação() {
        Modalidade tmp = getModalidade();
        if (!graduacaoField.getText().trim().isEmpty() && (tmp != null)) {
            return new Graduacao(tmp.toString(), graduacaoField.getText().trim());
        }
        return null;
    }

    private void getMultipleGraduations() {
        Modalidade m = getModalidade();

        if ((m != null) && !m.toString().isEmpty()) {
            String s = graduacaoField.getText().trim();
            if (!s.isEmpty()) {
                String[] v = s.split(";");
                for (String value : v) {
                    if (!value.trim().isEmpty()) {
                        Graduacao g = new Graduacao(m.toString(), value.trim());
                        if (!list.contains(g)) {
                            list.add(g);
                        }
                    }
                }
            }
        }
    }


    private boolean isInsert = false;
    private boolean isUpdate = false;
    private boolean isSearch = false;

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == okButton) {
            // CODE
            if (graduacaoField.getText().contains(";")) {
                getMultipleGraduations();
            } else {
                Graduacao tmp = getGraduação();
                if (tmp != null) {
                    list.add(tmp);
                }
            }
            updateTable();      // update table list
        } else if (event.getSource() == toolbar.getAddButton()) {
            // CODE
            this.unlock();
            this.isInsert = true;
        } else if (event.getSource() == toolbar.getSaveButton()) {
            for (Graduacao g: list) {
                Modalidade m = new Modalidade(g.getModalidade());
                try {
                    if (!modalidadeDAO.contains(m)) {
                        modalidadeDAO.insert(m);
                    }
                    graduacaoDAO.insert(g);
                } catch (SQLException e) {
                    System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                }
            }
            this.resetTable();      // clear table content
        } else if (event.getSource() == toolbar.getSearchButton()) {
            // CODE
            if (this.isLocked()) {
                this.unlock();
            } else {
                Modalidade m = this.getModalidade();
                if (m != null) {
                    this.resetTable();
                    Graduacao g = new Graduacao();
                    g.setModalidade(m.toString());
                    try {
                        for (Object x : graduacaoDAO.getList(g)) {
                            list.add((Graduacao) x);
                        }
                    } catch (SQLException e) {
                        System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                    }
                }
            }

        } else if (event.getSource() == toolbar.getRemoveButton()) {
            // CODE
            Graduacao tmp = getGraduação();
            if (tmp != null) {
                try {
                    graduacaoDAO.delete(tmp);
                } catch (SQLException e) {
                    System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == modalidadeField) {
            this.resetTable();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
