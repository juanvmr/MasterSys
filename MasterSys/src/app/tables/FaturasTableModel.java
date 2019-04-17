package app.tables;

import database.models.Fatura;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaturasTableModel extends AbstractTableModel {

    /* attributes: */
    private static String[] columnNames = { "Matrícula", "Aluno", "Vencimento", "Valor", "Pagamento", "Cancelamento" };
    private List<Fatura> list;

    /* constructor: */
    public FaturasTableModel() {
        list = new ArrayList<Fatura>();
    }

    public FaturasTableModel(List<Fatura> list) {
        this.list = list;
    }

    /* methods: */
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 3: return Double.class;
            default: return Date.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 2: case 4: case 5:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fatura tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0: return tmp.getCodigoMatricula();
            case 1:
                // PRECISA RETORNAR NOME DO ALUNO
                return null;
            case 2: return tmp.getDataVencimento();
            case 3: return tmp.getValor();
            case 4: return tmp.getDataPagamento();
            case 5: return tmp.getDataCancelamento();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Fatura tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                tmp.setCodigoMatricula((int) value);
                break;
            case 1:
                // tmp.setName((String) value);
                break;
            case 2:
                tmp.setDataVencimento((Date) value);
                break;
            case 3:
                tmp.setValor((double) value);
                break;
            case 4:
                tmp.setDataPagamento((Date) value);
                break;
            case 5:
                tmp.setDataCancelamento((Date) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    private Fatura getRow(int rowIndex) {
        return list.get(rowIndex);
    }

    public void addFatura(Fatura obj) {
        this.insert(obj, getRowCount());
    }

    public void insert(Fatura obj, int rowIndex) {
        list.add(rowIndex, obj);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void remove(int rowIndex) {
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
