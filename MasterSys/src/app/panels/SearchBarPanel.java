package app.panels;

import app.components.DateFormattedTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SearchBarPanel extends JPanel {

    private static String[] statusList = new String[] { "Todas", "Em Aberto", "Pagas", "Canceladas" };

    /* components: */
    private JLabel fromLabel, toLabel, statusLabel;
    private DateFormattedTextField fromField, toField;
    private JComboBox<String> statusComboBox;
    private JButton searchButton;

    /* constructors: */
    public SearchBarPanel() {
        super(new GridBagLayout());
        this.initComponents();
    }

    /* getter and setter: */
    public JButton getSearchButton() {
        return this.searchButton;
    }

    /* methods: */
    private void initComponents() {

        int inset = 5;

        fromLabel = new JLabel("De:", JLabel.RIGHT);
        toLabel = new JLabel("Até:", JLabel.RIGHT);
        statusLabel = new JLabel("Situação:", JLabel.RIGHT);

        fromField = new DateFormattedTextField();
        toField = new DateFormattedTextField();

        statusComboBox = new JComboBox<>();
        statusComboBox.setModel(new DefaultComboBoxModel<>(statusList));

        searchButton = new JButton("Pesquisar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        this.add(fromLabel, constraints);
        constraints.gridx++;
        this.add(fromField, constraints);
        constraints.gridx++;
        this.add(toLabel, constraints);
        constraints.gridx++;
        this.add(toField, constraints);
        constraints.gridx++;
        this.add(statusLabel, constraints);
        constraints.gridx++;
        this.add(statusComboBox, constraints);
        constraints.gridx++;
        this.add(searchButton, constraints);
    }

    public Date getFromDate() {
        return null;
    }

    public Date getToDate() {
        return null;
    }

    public String getStatus() {
        return (String) statusComboBox.getSelectedItem();
    }

}
