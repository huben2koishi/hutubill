package com.huben.gui.listener;

import com.huben.gui.panel.ConfigPanel;
import com.huben.gui.panel.MainPanel;
import com.huben.gui.panel.RecoverPanel;
import com.huben.service.ConfigService;
import com.huben.utils.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author koishi
 */
public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel p = RecoverPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (0 == mysqlPath.length()) {
            JOptionPane.showMessageDialog(p, "恢复前请先配置MySQL的路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n错误：\r\n" + ex.getMessage());
            }
        }
    }
}
