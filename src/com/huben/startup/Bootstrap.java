package com.huben.startup;

import com.huben.gui.frame.MainFrame;
import com.huben.gui.panel.MainPanel;
import com.huben.gui.panel.SpendPanel;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author koishi
 */
public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                System.out.println(GUIUtil.imageFolder);
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
