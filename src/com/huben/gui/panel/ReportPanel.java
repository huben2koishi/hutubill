package com.huben.gui.panel;

import com.huben.entity.Record;
import com.huben.service.ReportService;
import com.huben.utils.ChartUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author koishi
 */
public class ReportPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();

    private JLabel label = new JLabel();

    private ReportPanel() {
        this.setLayout(new BorderLayout());
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image image = ChartUtil.getImage(rs, 400, 300);
        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
        this.add(label);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        label.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
