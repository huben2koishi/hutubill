package com.huben.gui.listener;

import com.huben.gui.panel.ConfigPanel;
import com.huben.service.ConfigService;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author koishi
 */
public class ConfigListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;

        if (!GUIUtil.checkNumber(p.tfBudget,"本月预算")){
            return;
        }
        String mysqlPath = p.tfMysqlPath.getText();
        if (0!=mysqlPath.length()){
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if (!commandFile.exists()){
                JOptionPane.showMessageDialog(p,"MySQL 路径不正确");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }

        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);

        JOptionPane.showMessageDialog(p,"设置修改成功");
    }
}
