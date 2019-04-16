package frames.tables;

import database.models.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatriculaTableModel extends AbstractTableModel {

    /* attributes: */
    private static String[] columnNames = { "Modalidade", "Graduação", "Plano", "Data Início", "Data Fim" };
    private List<MatriculaModalidade> list;

    /* constructor: */
    public MatriculaTableModel() {
        list = new ArrayList<MatriculaModalidade>();
    }

    public MatriculaTableModel(List<MatriculaModalidade> matriculas) {
        this.list = matriculas;
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
            case 3: case 4:
                return Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1: case 2: case 3: case 4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MatriculaModalidade tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0: return tmp.getModalidade();
            case 1: return tmp.getGraduacao();
            case 2: return tmp.getPlano();
            case 3: return tmp.getDataInicio();
            case 4: return tmp.getDataFim();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        MatriculaModalidade tmp = this.getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                tmp.setModalidade((String) value);
                break;
            case 1:
                tmp.setGraduacao((String) value);
                break;
            case 2:
                tmp.setPlano((String) value);
                break;
            case 3:
                tmp.setDataInicio((Date) value);
                break;
            case 4:
                tmp.setDataFim((Date) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    private MatriculaModalidade getRow(int rowIndex) {
        return list.get(rowIndex);
    }

    public void addMatriculaModalidade(MatriculaModalidade obj) {
        this.insert(obj, getRowCount());
    }

    public void insert(MatriculaModalidade obj, int rowIndex) {
        list.add(rowIndex, obj);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void remove(int rowIndex) {
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
