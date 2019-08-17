package com.huben.gui.model;

import com.huben.entity.Category;
import com.huben.service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * @author koishi
 */
public class CategoryComboBoxModel implements ComboBoxModel {
    public List<Category> cs = new CategoryService().list();

    private Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty()) {
            c = cs.get(0);
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (!cs.isEmpty()) {
            return c;
        } else {
            return null;
        }
    }
}
