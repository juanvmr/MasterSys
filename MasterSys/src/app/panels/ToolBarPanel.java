package app.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class ToolBarPanel extends JPanel {

    /* attributes: */
    private static int inset = 2;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean save = false;

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
        this.add(searchButton);
        this.add(removeButton);
        this.add(saveButton);
    }

    public void setMode(boolean x, boolean y) {
        this.addButton.setEnabled((!x && !y) || (x && !y));
        this.saveButton.setEnabled((x && !y) || (!x && y));
        this.searchButton.setEnabled((!x && !y) || (!x && y));
        this.removeButton.setEnabled(!x && y);
    }
}
