package com.huben.gui.panel;

import com.huben.gui.listener.ConfigListener;
import com.huben.service.ConfigService;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author koishi
 */
public class ConfigPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static ConfigPanel instance = new ConfigPanel();

    private JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField("0");
    private JLabel lMysql = new JLabel("MySQL 安装目录");
    public JTextField tfMysqlPath = new JTextField("");

    private JButton bSubmit = new JButton("更新");

    private ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 1, gap, gap));

        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysqlPath);
        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.SOUTH);

        addListener();
    }

    @Override
    public void addListener() {
        ConfigListener listener = new ConfigListener();
        bSubmit.addActionListener(listener);
    }

    @Override
    public void updateData() {
        ConfigService service = new ConfigService();
        String budget = service.get(ConfigService.budget);
        String mysqlPath = service.get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();
    }
}
