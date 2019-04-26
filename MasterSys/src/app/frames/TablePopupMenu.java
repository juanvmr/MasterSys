package app.frames;

import database.dao.FaturaMatriculaDAO;
import database.models.FaturaMatricula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class TablePopupMenu extends JPopupMenu implements ActionListener {

    /* attributes: */
    private FaturaMatricula faturaMatricula;
    private FaturaMatriculaDAO faturaMatriculaDAO = MenuFrame.faturaMatriculaDAO;

    /* components: */
    private JMenuItem payItem, cancelItem, alterItem;

    public TablePopupMenu(JTable table) {
        super();
        this.initComponents();
    }

    private void initComponents() {
        payItem = new JMenuItem("Pagar");
        payItem.addActionListener(this);

        cancelItem = new JMenuItem("Cancelar");
        cancelItem.addActionListener(this);

        alterItem = new JMenuItem("Desconto / Acréscimo");
        alterItem.addActionListener(this);

        this.add(payItem);
        this.add(new JSeparator());
        this.add(cancelItem);
        this.add(new JSeparator());
        this.add(alterItem);
    }

    public void show(FaturaMatricula faturaMatricula, Component invoker, int x, int y) {
        super.show(invoker, x, y);
        this.faturaMatricula = faturaMatricula;

        boolean paymentEnabled = (this.faturaMatricula.getDataPagamento() == null) && (this.faturaMatricula.getDataCancelamento() == null);
        boolean cancelEnabled = (this.faturaMatricula.getDataCancelamento() == null);
        boolean alterEnabled = paymentEnabled;

        payItem.setEnabled(paymentEnabled);
        cancelItem.setEnabled(cancelEnabled);
        alterItem.setEnabled(alterEnabled);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Date currentDate = new Date();
            // INSERT
            if (event.getSource() == payItem) {
                faturaMatricula.setDataPagamento(currentDate);
                JOptionPane.showMessageDialog(payItem, payItem.getText() + ": " + faturaMatricula);
            }
            // UPDATE
            else if (event.getSource() == cancelItem) {
                faturaMatricula.setDataCancelamento(currentDate);
                JOptionPane.showMessageDialog(cancelItem, cancelItem.getText() + ": " + faturaMatricula);
            }
            // DELETE
            else if (event.getSource() == alterItem) {
                JOptionPane.showMessageDialog(alterItem, alterItem.getText() + ": " + faturaMatricula);
            }
            if (false) faturaMatriculaDAO.update(this.faturaMatricula);
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }
    }
}
