package com.huben.gui.panel;

import com.huben.entity.Category;
import com.huben.gui.listener.RecordListener;
import com.huben.gui.model.CategoryComboBoxModel;
import com.huben.service.CategoryService;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @author koishi
 */
public class RecordPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datePick = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("记一笔");

    private RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 2, gap, gap));

        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datePick);

        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.SOUTH);

        addListener();
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    private void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");
        if (0!=cbModel.cs.size()){
            cbCategory.setSelectedIndex(0);
        }
        datePick.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
}
