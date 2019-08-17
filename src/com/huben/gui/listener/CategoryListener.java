package com.huben.gui.listener;

import com.huben.entity.Category;
import com.huben.gui.panel.CategoryPanel;
import com.huben.service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author koishi
 */
public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == p.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (null == name) {
                return;
            }
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        if (b == p.bEdit) {
            Category c = p.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改分类名称", c.getName());
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            new CategoryService().update(id, name);
        }
        if (b == p.bDelete) {
            Category c = p.getSelectedCategory();
            if (0 != c.getRecordNumber()) {
                JOptionPane.showMessageDialog(p, "本分类下还有消费记录存在, 不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确定要删除?")) {
                return;
            }
            int id = c.getId();
            new CategoryService().delete(id);
        }
        p.updateData();
    }
}
