package app.frames;

import app.components.DateField;
import database.dao.MatriculaDAO;
import database.models.Matricula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatoriosMatriculasFrame extends JInternalFrame implements ActionListener {

    /* config: */
    private static int inset = 5;
    private static int border = 10;
    private static String[] typeList = new String[] { ".HTML", ".TXT", ".DOC" };
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private MatriculaDAO matriculaDAO;

    /* components: */
    private DateField fromDateField;
    private DateField toDateField;
    private JButton submitButton;
    private JComboBox<String> typeComboBox;

    /* constructor: */
    public RelatoriosMatriculasFrame(Connection connection) {
        super("Relatório de Matriculas", isResizable, isClosable, isMaximizable, isIconifiable);

        this.matriculaDAO = new MatriculaDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents() {

        typeComboBox = new JComboBox<>();
        typeComboBox.setModel(new DefaultComboBoxModel<>(typeList));

        submitButton = new JButton("Processar");
        submitButton.addActionListener(this);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(border, border, border, border));
        this.setContentPane(content);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.gridheight = 4;
        constraints.weighty = 1;
        content.add(createPeriodPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.gridheight = 1;
        constraints.weighty = 0;
        content.add(typeComboBox, constraints);
        constraints.gridx++;
        content.add(submitButton, constraints);
    }

    private JPanel createPeriodPanel() {

        JLabel fromLabel = new JLabel("De:");
        JLabel toLabel = new JLabel("Até:");

        fromDateField = new DateField();
        fromDateField.setValue(new Date());
        fromDateField.setHorizontalAlignment(SwingConstants.CENTER);

        toDateField = new DateField();
        toDateField.setValue(new Date());
        toDateField.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Período"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        panel.add(fromLabel, constraints);
        constraints.gridy++;
        panel.add(toLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(fromDateField, constraints);
        constraints.gridy++;
        panel.add(toDateField, constraints);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submitButton) {

            try {
                Date fromDate = fromDateField.getDate();
                Date toDate = toDateField.getDate();
                String type = typeComboBox.getSelectedItem().toString();

                System.out.println("from: " + fromDate + ", to: " + toDate + ", type: " + type);

                List<Object> matriculaList = new ArrayList<Object>();

                matriculaList = matriculaDAO.select();

                for (Object obj : matriculaList) {
                    System.out.println((Matricula) obj);
                }

            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }

            this.dispose();
        }
    }
}
