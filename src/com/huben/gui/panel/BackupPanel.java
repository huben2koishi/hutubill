package com.huben.gui.panel;

import com.huben.gui.listener.BackupListener;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;

/**
 * @author koishi
 */
public class BackupPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel();
    JButton bBackup = new JButton("备份");

    private BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);

        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }
}
