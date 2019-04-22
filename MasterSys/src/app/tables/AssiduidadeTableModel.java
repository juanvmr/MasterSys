package app.tables;

import database.models.Assiduidade;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssiduidadeTableModel extends AbstractTableModel {

    /* attributes: */
    private static String[] columnNames = { "Assiduidade" };
    private List<Assiduidade> list;

    /* constructor: */
    public AssiduidadeTableModel() {
        list = new ArrayList<Assiduidade>();
    }

    public AssiduidadeTableModel(List<Assiduidade> list) {
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
            case 1: return Integer.class;
            case 2: return Date.class;
            default: return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Assiduidade tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0: return tmp.getDataEntrada();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Assiduidade tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                tmp.setDataEntrada((Date) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    private Assiduidade getRow(int rowIndex) {
        return list.get(rowIndex);
    }

    public void addAssiduidade(Assiduidade obj) {
        this.insert(obj, getRowCount());
    }

    public void insert(Assiduidade obj, int rowIndex) {
        list.add(rowIndex, obj);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void remove(int rowIndex) {
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
