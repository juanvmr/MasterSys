package app.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Date;

public class ColorRender extends DefaultTableCellRenderer {

    private Color defaultColor = getBackground();
    private Color warningColor = Color.RED;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component dataVencimentoCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 2);
        Component dataPagamentoCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 4);

        FaturasTableModel tableModel = (FaturasTableModel) table.getModel();
        Date dataVencimento = (Date) tableModel.getValueAt(row, 2);
        Date dataPagamento = (Date) tableModel.getValueAt(row, 4);
        Date currentDate = new Date();

        if ((dataPagamento == null) && (dataVencimento.compareTo(currentDate) > 0)) {
            dataPagamentoCell.setBackground(warningColor);
        } else {
            dataPagamentoCell.setBackground(defaultColor);
        }

        return component;
    }
}
