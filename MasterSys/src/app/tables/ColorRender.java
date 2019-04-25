package app.tables;

import database.models.FaturaMatricula;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Date;

public class ColorRender extends DefaultTableCellRenderer {

    private Color defaultColor = getBackground();
    private Color cancelColor = Color.ORANGE;
    private Color paymentColor = Color.GREEN;
    private Color warningColor = Color.RED;
    private Color errorColor = Color.MAGENTA;

    public ColorRender() {
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        FaturasTableModel tableModel = (FaturasTableModel) table.getModel();
        FaturaMatricula f = tableModel.getRow(row);

        Date currentDate = new Date();

        // print canceled faturas
        if (f.getDataCancelamento() != null) {
            component.setBackground(cancelColor);
        }
        // print pay faturas
        else if (f.getDataPagamento() != null) {
            if (f.getDataPagamento().compareTo(f.getDataVencimento()) < 0) {
                component.setBackground(paymentColor);
            } else {
                component.setBackground(errorColor);
            }
        }
        // print not pay faturas
        else {
            // print expired faturas
            if (f.getDataVencimento().compareTo(currentDate) < 0) {
                component.setBackground(warningColor);
            } else {
                component.setBackground(defaultColor);
            }
        }

        // resize columns
        TableColumn tableColumn = table.getColumnModel().getColumn(row);
        tableColumn.setPreferredWidth(component.getPreferredSize().width);

        return component;
    }

}
