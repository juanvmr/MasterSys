package frames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarPanel extends JPanel {

    private int inset = 2;

    /* components: */
    private JButton addButton;
    private JButton saveButton;
    private JButton searchButton;
    private JButton removeButton;

    /* constructor: */
    public ToolBarPanel() {
        super();
        this.setBorder(new EmptyBorder(inset, inset, inset, inset));
        this.initComponents();
    }

    /* setters: */
    public JButton getAddButton() {
        return this.addButton;
    }

    public JButton getSaveButton() {
        return this.saveButton;
    }

    public JButton getSearchButton() {
        return this.searchButton;
    }

    public JButton getRemoveButton() {
        return this.removeButton;
    }

    /* methods: */
    private void initComponents() {

        addButton = new JButton("Adicionar");
        saveButton = new JButton("Salvar");
        searchButton = new JButton("Buscar");
        removeButton = new JButton("Remover");

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(addButton);
        this.add(saveButton);
        this.add(searchButton);
        this.add(removeButton);
    }
}