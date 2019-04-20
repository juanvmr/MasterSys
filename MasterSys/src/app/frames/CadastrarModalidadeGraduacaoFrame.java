package app.frames;

import app.panels.ToolBarPanel;
import app.tables.GraduacaoTableModel;
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
    private static String SPACE_TOKEN = ";";

    /* attributes: */
    private ModalidadeDAO modalidadeDAO;
    private GraduacaoDAO graduacaoDAO;
    private List<Graduacao> list;
    private boolean insertEnabled = false;
    private boolean updateEnabled = false;

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
        this.enableInput();
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

    private void enableInput() {
        boolean enabled = (this.insertEnabled) || (this.updateEnabled);
        toolbar.setMode(this.insertEnabled, this.updateEnabled);
        modalidadeField.setEnabled(enabled);
        if (this.updateEnabled) {
            modalidadeField.addKeyListener(this);
        } else {
            modalidadeField.removeActionListener(this);
        }
        graduacaoField.setEnabled(enabled);
        okButton.setEnabled(enabled);
    }

    private Modalidade getModalidadeInput() {
        String m = modalidadeField.getText().trim();
        if (!m.isEmpty()) {
            return new Modalidade(m);
        }
        return null;
    }

    private Graduacao getGraduacaoInput() {
        String m = modalidadeField.getText().trim();
        String g = graduacaoField.getText().trim();
        if (!m.isEmpty() && !g.isEmpty()) {
            return new Graduacao(m, g);
        }
        return null;
    }

    private void getMultGraducaoInput() {
        String m = modalidadeField.getText().trim();
        String g = graduacaoField.getText().trim();
        if (!m.isEmpty() && !g.isEmpty()) {
            String[] v = g.split(SPACE_TOKEN);
            for (String s : v) {
                if (!s.trim().isEmpty()) {
                    Graduacao tmp = new Graduacao(m, s.trim());
                    if (!list.contains(tmp)) {
                        list.add(tmp);
                    }
                }
            }
        }
    }

    private void resetTable() {
        list.clear();
        table.setModel(new GraduacaoTableModel(list));
    }

    private void updateTable() {
        if ((list != null) && (list.size() > 0)) {
            table.setModel(new GraduacaoTableModel(list));
        } else {
            list = new ArrayList<>();
            resetTable();
        }
    }

    private void addButtonAction() {
        this.insertEnabled = true;
        enableInput();
    }

    private void searchButtonAction() {
        this.updateEnabled = true;
        enableInput();
        resetTable();
    }

    private void saveButtonAction() {
        // INSERT
        if (this.insertEnabled) {
            for (Graduacao g : list) {
                Modalidade m = new Modalidade(g.getModalidade());
                try {
                    if (!modalidadeDAO.contains(m)) {
                        modalidadeDAO.insert(m);
                    }
                    if (!graduacaoDAO.contains(g)) {
                        graduacaoDAO.insert(g);
                    }
                } catch (SQLException e) {
                    System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                }
            }
            this.insertEnabled = false;
        }
        // UPDATE
        else if (this.updateEnabled) {
            Graduacao g = getGraduacaoInput();
            try {
                graduacaoDAO.update(g);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.updateEnabled = false;
        }
    }

    private void removeButtonAction() {
        Modalidade m = getModalidadeInput();
        Graduacao g = getGraduacaoInput();
        if (g != null) {

        } else if (m != null) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == okButton) {
            if (graduacaoField.getText().contains(SPACE_TOKEN)) {
                getMultGraducaoInput();
            } else {
                Graduacao g = getGraduacaoInput();
                if ((g != null) && (!list.contains(g))) {
                    list.add(g);
                }
            }
            updateTable();
        } else if (event.getSource() == toolbar.getAddButton()) {
            this.addButtonAction();
        } else if (event.getSource() == toolbar.getSaveButton()) {
            this.saveButtonAction();
        } else if (event.getSource() == toolbar.getSearchButton()) {
            this.searchButtonAction();
        } else if (event.getSource() == toolbar.getRemoveButton()) {
            this.removeButtonAction();
        }

        enableInput();
    }

    @Override
    public void keyTyped(KeyEvent event) {
        if (event.getSource() == modalidadeField) {
            Modalidade m = getModalidadeInput();
            if (m != null) {
                resetTable();
                Graduacao g = new Graduacao();
                g.setModalidade(m.toString());
                try {
                    for (Object obj : graduacaoDAO.getList(g)) {
                        list.add((Graduacao) obj);
                    }
                } catch (SQLException e) {
                    System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
