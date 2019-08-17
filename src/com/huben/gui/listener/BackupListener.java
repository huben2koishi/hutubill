package com.huben.gui.listener;

import com.huben.gui.panel.BackupPanel;
import com.huben.gui.panel.ConfigPanel;
import com.huben.gui.panel.MainPanel;
import com.huben.service.ConfigService;
import com.huben.utils.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author koishi
 */
public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (0 == mysqlPath.length()) {
            JOptionPane.showMessageDialog(p, "请先设置MySQL的路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
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
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql")) {
                file = new File(file.getParent(), file.getName() + ".sql");
                System.out.println(file);

                try {
                    MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                    JOptionPane.showMessageDialog(p, "备份成功, 备份文件位于:\r\n" + file.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(p, "备份失败\r\n错误：\r\n" + ex.getMessage());
                }
            }
        }
    }
}
