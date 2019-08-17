package com.huben.gui.model;

import com.huben.entity.Category;
import com.huben.service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * @author koishi
 */
public class CategoryTableModel implements TableModel {
    private String[] columnNames = {"分类名称", "消费次数"};
    public List<Category> cs = new CategoryService().list();

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category h=cs.get(rowIndex);
        if (columnIndex == 0) {
            return h.getName();
        }
        if (columnIndex == 1) {
            return h.getRecordNumber();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
