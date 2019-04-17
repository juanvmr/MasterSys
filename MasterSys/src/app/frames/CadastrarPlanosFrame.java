package app.frames;

import app.panels.ToolBarPanel;
import database.dao.ModalidadeDAO;

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
    private Connection connection;

    /* components: */
    private ToolBarPanel toolbar;
    private JLabel modalidadeLabel, planoLabel, valorLabel;
    private JTextField planoField , valorField;
    private JComboBox<Object> modalidadeComboBox;

    /* constructors: */
    public CadastrarPlanosFrame(Connection conn) {
        super("Planos", isResizable, isClosable, isMaximizable, isIconifiable);

        this.connection = conn;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container content) {

        toolbar = new ToolBarPanel();

        content.setLayout(new BorderLayout());
        content.add(toolbar, BorderLayout.NORTH);
        content.add(createPanel(), BorderLayout.CENTER);
    }

    private JPanel createPanel() {

        int inset = 5;
        int border = 10;

        modalidadeLabel = new JLabel("Modalidade:", JLabel.RIGHT);
        planoLabel = new JLabel("Plano:", JLabel.RIGHT);
        valorLabel = new JLabel("Valor:", JLabel.RIGHT);
        planoField = new JTextField();

        valorField = new JTextField();
        valorField.setText("0.00");
        valorField.addKeyListener(this);
        valorField.addMouseListener(this);

        modalidadeComboBox = new JComboBox<>();
        loadModalidadeComboBox();
        // perfilComboBox.setModel(new DefaultComboBoxModel<>(perfilList));
        // perfilComboBox.addActionListener(this);

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

    private void loadModalidadeComboBox() {
        try {
            ModalidadeDAO modalidadeDAO = new ModalidadeDAO(connection);
            List<Object> modalidadeList = modalidadeDAO.selectAll();
            modalidadeComboBox.setModel(new DefaultComboBoxModel<>(modalidadeList.toArray()));
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("WARNING: Connection not found.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toolbar.getAddButton()) {
            // code
        } else if (e.getSource() == toolbar.getSaveButton()) {
            // code
        } else if (e.getSource() == toolbar.getSearchButton()) {
            // code
        } else if (e.getSource() == toolbar.getRemoveButton()) {
            // code
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == valorField) {
            String characters = "0987654321";
            if (!characters.contains(e.getKeyChar() + "")) {
                e.consume();
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
