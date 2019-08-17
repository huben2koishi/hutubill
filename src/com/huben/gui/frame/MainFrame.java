package com.huben.gui.frame;

import com.huben.gui.panel.MainPanel;

import javax.swing.*;

/**
 * @author koishi
 */
public class MainFrame extends JFrame {
    public static MainFrame instance=new MainFrame();

    private MainFrame(){
        this.setSize(600,500);
        this.setTitle("一本糊涂账");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
