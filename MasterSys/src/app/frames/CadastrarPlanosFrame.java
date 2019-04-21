package app.frames;

import app.panels.ToolBarPanel;
import database.dao.ModalidadeDAO;
import database.dao.PlanoDAO;
import database.models.Modalidade;
import database.models.Plano;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CadastrarPlanosFrame extends JInternalFrame implements ActionListener, KeyListener, MouseListener {

    /* config: */
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private ModalidadeDAO modalidadeDAO;
    private PlanoDAO planoDAO;
    private List<Object> modalidadeList;
    private boolean insertEnabled = false;
    private boolean updateEnabled = false;

    /* components: */
    private ToolBarPanel toolbar;
    private JTextField planoField , valorField;
    private JComboBox<Object> modalidadeComboBox;

    /* constructors: */
    public CadastrarPlanosFrame(Connection connection) {
        super("Planos", isResizable, isClosable, isMaximizable, isIconifiable);

        this.modalidadeDAO = new ModalidadeDAO(connection);
        this.planoDAO = new PlanoDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.setupInput();
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

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(createPanel(), BorderLayout.CENTER);
    }

    private JPanel createPanel() {

        int inset = 5;
        int border = 10;

        JLabel modalidadeLabel = new JLabel("Modalidade:", JLabel.RIGHT);
        JLabel planoLabel = new JLabel("Plano:", JLabel.RIGHT);
        JLabel valorLabel = new JLabel("Valor:", JLabel.RIGHT);

        planoField = new JTextField();

        valorField = new JTextField();
        valorField.setText("0.00");
        valorField.addKeyListener(this);
        valorField.addMouseListener(this);

        modalidadeComboBox = new JComboBox<>();
        updateModalidadeComboBox();

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
        panel.add(planoLabel, constraints);
        constraints.gridy++;
        panel.add(valorLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        panel.add(modalidadeComboBox, constraints);
        constraints.gridy++;
        panel.add(planoField, constraints);
        constraints.gridy++;
        panel.add(valorField, constraints);

        return panel;
    }

    private void updateModalidadeComboBox() {
        try {
            modalidadeList = modalidadeDAO.select();
            modalidadeComboBox.setModel(new DefaultComboBoxModel<>(modalidadeList.toArray()));
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("WARNING: Connection not found.");
        }
    }

    private void setupInput() {
        boolean state = (this.insertEnabled || this.updateEnabled);

        toolbar.setMode(this.insertEnabled, this.updateEnabled);

        modalidadeComboBox.setEnabled(state);
        if (this.updateEnabled) {
            modalidadeComboBox.addKeyListener(this);
        } else {
            modalidadeComboBox.removeKeyListener(this);
        }

        planoField.setEnabled(state);
        if (this.updateEnabled) {
            planoField.addKeyListener(this);
        } else {
            planoField.removeKeyListener(this);
        }

        valorField.setEnabled(state);
    }

    private void resetInput() {
        modalidadeComboBox.setSelectedIndex(0);
        planoField.setText("");
        valorField.setText("0.00");
    }

    private void updateInput(Plano p) {
        if (p != null) {
            modalidadeComboBox.setSelectedItem(p.getModalidade());
            planoField.setText(p.getPlano());
            valorField.setText(String.format("%.2f", p.getValor()));
        }
    }

    private Plano getPlanoInput() {
        Plano p = new Plano();
        Modalidade m = (Modalidade) modalidadeComboBox.getSelectedItem();
        if (m != null) p.setModalidade(m);
        if (!planoField.getText().trim().isEmpty()) p.setPlano(planoField.getText().trim());
        if (!valorField.getText().trim().isEmpty()) p.setValor(Double.parseDouble(valorField.getText().trim()));
        return p;
    }

    private void addButtonAction() {
        this.insertEnabled = true;
    }

    private void searchButtonAction() {
        this.updateEnabled = true;
    }

    private void saveButtonAction() {
        Plano p = getPlanoInput();
        // INSERT
        if (this.insertEnabled) {
            try {
                planoDAO.insert(p);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.insertEnabled = false;
        }
        // UPDATE
        else if (this.updateEnabled) {
            try {
                planoDAO.update(p);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
            this.updateEnabled = false;
        }
        // clear fields
        resetInput();
    }

    private void removeButtonAction() {
        Plano p = getPlanoInput();
        try {
            planoDAO.delete(p);
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }
        this.updateEnabled = false;
        // clear fields
        resetInput();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toolbar.getAddButton()) {
            addButtonAction();
        } else if (e.getSource() == toolbar.getSaveButton()) {
            saveButtonAction();
        } else if (e.getSource() == toolbar.getSearchButton()) {
            searchButtonAction();
        } else if (e.getSource() == toolbar.getRemoveButton()) {
            removeButtonAction();
        }
        setupInput();
    }

    @Override
    public void keyTyped(KeyEvent event) {
        if ((event.getSource() == modalidadeComboBox) || (event.getSource() == planoField)) {
            Plano p = getPlanoInput();
            try {
                Plano tmp = (Plano) planoDAO.find(p);
                updateInput(tmp);
                // event.consume();
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        } else if (event.getSource() == valorField) {
            String characters = "0987654321";
            if (!characters.contains(event.getKeyChar() + "")) {
                event.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == valorField) {
            valorField.setText("");
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
