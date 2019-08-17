package com.huben.test;

import com.huben.utils.GUIUtil;

import javax.swing.*;

/**
 * @author koishi
 */
public class GUIUtilTest {
    public static void main(String[] args) {
        GUIUtil.useLNF();
        JPanel panel = new JPanel();
        panel.add(new JButton("按钮1"));
        panel.add(new JButton("按钮2"));
        GUIUtil.showPanel(panel);
    }
}
