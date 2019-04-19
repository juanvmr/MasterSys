package app.frames;

import app.components.DateField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ProcessosGerarFaturasFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;

    /* components: */
    private JLabel dataLabel;
    private DateField dataField;
    private JButton gerarButton;

    /* constructors: */
    public ProcessosGerarFaturasFrame(Connection connection) {
        super("Gerar Faturas", isResizable, isClosable, isMaximizable, isIconifiable);

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {

        int inset = 5;
        int border = 10;

        dataLabel = new JLabel("Data da Fatura:", JLabel.RIGHT);
        dataField = new DateField();
        gerarButton = new JButton("Gerar Faturas");

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(border, border, border, border));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        content.add(dataLabel, constraints);
        constraints.gridx++;
        constraints.weightx = 1;
        content.add(dataField, constraints);
        constraints.gridy++;
        constraints.weightx = 1;
        content.add(gerarButton, constraints);

        this.setContentPane(content);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gerarButton) {
            // core
        }
    }
}
