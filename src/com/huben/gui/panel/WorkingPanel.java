package com.huben.gui.panel;

import javax.swing.*;

/**
 * @author koishi
 */
public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();

    public abstract void addListener();
}
