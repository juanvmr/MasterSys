package app.tables;

import database.models.Graduacao;
import database.models.MatriculaModalidade;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraduacaoTableModel extends AbstractTableModel {

    /* attributes: */
    private static String[] columnNames = { "Modalidade", "Graduação" };
    private List<Graduacao> list;

    /* constructor: */
    public GraduacaoTableModel() {
        list = new ArrayList<Graduacao>();
    }

    public GraduacaoTableModel(List<Graduacao> list) {
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
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1: case 2:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Graduacao tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0: return tmp.getModalidade();
            case 1: return tmp.getGraduacao();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Graduacao tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                tmp.setModalidade((String) value);
                break;
            case 1:
                tmp.setGraduacao((String) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    private Graduacao getRow(int rowIndex) {
        return list.get(rowIndex);
    }

    public void addGraduacao(Graduacao obj) {
        this.insert(obj, getRowCount());
    }

    public void insert(Graduacao obj, int rowIndex) {
        list.add(rowIndex, obj);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void remove(int rowIndex) {
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
