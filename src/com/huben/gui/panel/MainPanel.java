package com.huben.gui.panel;

import com.huben.gui.listener.ToolbarListener;
import com.huben.utils.CenterPanel;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author koishi
 */
public class MainPanel extends JPanel{
    static {
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel();

    private JToolBar toolBar = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;


    private MainPanel() {
        GUIUtil.setImageIcon(bSpend,"home.png","消费一览");
        GUIUtil.setImageIcon(bRecord,"record.png","记一笔");
        GUIUtil.setImageIcon(bCategory,"category2.png","消费记录");
        GUIUtil.setImageIcon(bReport,"report.png","月消费报表");
        GUIUtil.setImageIcon(bConfig,"config.png","设置");
        GUIUtil.setImageIcon(bBackup,"backup.png","备份");
        GUIUtil.setImageIcon(bRecover,"restore.png","恢复");

        toolBar.add(bSpend);
        toolBar.add(bRecord);
        toolBar.add(bCategory);
        toolBar.add(bReport);
        toolBar.add(bConfig);
        toolBar.add(bBackup);
        toolBar.add(bRecover);
        toolBar.setFloatable(false);

        workingPanel=new CenterPanel(0.8);

        setLayout(new BorderLayout());
        add(toolBar,BorderLayout.NORTH);
        add(workingPanel,BorderLayout.CENTER);

        addListener();
    }

    private void addListener(){
        ToolbarListener listener = new ToolbarListener();
        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
    }
}
