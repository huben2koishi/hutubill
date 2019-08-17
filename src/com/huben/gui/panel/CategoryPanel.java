package com.huben.gui.panel;

import com.huben.entity.Category;
import com.huben.gui.listener.CategoryListener;
import com.huben.gui.model.CategoryTableModel;
import com.huben.service.CategoryService;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author koishi
 */
public class CategoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    //private String[] columnsName = {"分类名称","消费次数"};

    private CategoryTableModel ctModel = new CategoryTableModel();
    private JTable table = new JTable(ctModel);

    private CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete);

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel pSubmit = new JPanel();

        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);

        addListener();
        updateData();
    }

    public Category getSelectedCategory(){
        int index = table.getSelectedRow();
        return ctModel.cs.get(index);
    }

    @Override
    public void updateData(){
        ctModel.cs = new CategoryService().list();
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0,0);

        if (0==ctModel.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    @Override
    public void addListener(){
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }
}
