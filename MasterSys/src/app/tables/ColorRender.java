package app.tables;

import database.models.FaturaMatricula;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Date;

public class ColorRender extends DefaultTableCellRenderer {

    private Color defaultColor = getBackground();
    private Color warningColor = Color.RED;
    private Color cancelColor = Color.ORANGE;
    private Color payColor = Color.GREEN;

    public ColorRender() {
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Component dataVencimentoCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 2);
        // Component dataPagamentoCell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 4);

        //FaturasTableModel tableModel = (FaturasTableModel) table.getModel();
        //FaturaMatricula f = tableModel.getRow(row);

       /* if (f.getDataPagamento() != null) {
            component.setBackground(payColor);
        } else if (!f.isMatriculaEncerrada()) {
            component.setBackground(cancelColor);
        } else if (f.getDataVencimento().compareTo(new Date()) < 0) {
            component.setBackground(warningColor);
        } else {
            component.setBackground(defaultColor);
        }*/

        System.out.println("ColorRender Debug");
        component.setBackground(payColor);

        return component;
    }

    protected		Color[]
            ia_color		=	null;

    /**
     Seta as cores das linhas da tabela.
     @param	la_color
     Define as cores de cada linha.
     */
    public
    void			LineColor
    (
            Color[]			la_color
    )
    {
        ia_color		=	la_color;
    }
}
