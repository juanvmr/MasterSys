package app.frames;

import app.components.DateField;
import app.components.MonthChooser;
import database.dao.FaturaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

public class ProcessosGerarFaturasFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private Connection connection;
    private FaturaDAO faturaDAO;

    /* components: */
    private MonthChooser monthChooser;
    private JButton submitButton;

    /* constructors: */
    public ProcessosGerarFaturasFrame(Connection connection) {
        super("Gerar Faturas", isResizable, isClosable, isMaximizable, isIconifiable);
        //this.setSize(new Dimension(300, 150));
        this.faturaDAO = new FaturaDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {

        int inset = 5;
        int border = 10;

        JLabel dataLabel = new JLabel("Data da Fatura:", JLabel.RIGHT);

        monthChooser = new MonthChooser();

        submitButton = new JButton("Gerar Faturas");
        submitButton.addActionListener(this);

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
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        content.add(monthChooser, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 3;
        constraints.weightx = 0;
        content.add(submitButton, constraints);

        this.setContentPane(content);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            Date date = monthChooser.getDate();
            System.err.println("Date: " + date.toString());
        }
    }
}
