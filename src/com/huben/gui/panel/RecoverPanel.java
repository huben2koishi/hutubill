package com.huben.gui.panel;

import com.huben.gui.listener.RecoverListener;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;

/**
 * @author koishi
 */
public class RecoverPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover = new JButton("恢复");

    private RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);

        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}