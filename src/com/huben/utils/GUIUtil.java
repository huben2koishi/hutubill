package com.huben.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author koishi
 */
public class GUIUtil {
    public static boolean checkEmpty(JTextField field, String input) {
        String text = field.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + "不能为空");
            field.grabFocus();
            return false;
        }
        return true;
    }

    public static boolean checkNumber(JTextField field, String input) {
        if (!checkEmpty(field, input)) {
            return false;
        }
        String text = field.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            field.grabFocus();
            return false;
        }
    }

    public static boolean checkZero(JTextField field, String input) {
        if (!checkNumber(field, input)) {
            return false;
        }
        String text = field.getText().trim();
        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为0");
            field.grabFocus();
            return false;
        }
        return true;
    }

    public static void setColor(Color color, JComponent... components) {
        for (JComponent component : components) {
            component.setForeground(color);
        }
    }

   public static String imageFolder = "e:/IdeaProjects/hutubill/img";

    public static void setImageIcon(JButton button, String fileName, String tip) {
        ImageIcon icon = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(61, 81));
        button.setToolTipText(tip);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setText(tip);
    }

    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPanel(JPanel panel,double strech){
        GUIUtil.useLNF();
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        CenterPanel centerPanel = new CenterPanel(strech);
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        centerPanel.show(panel);
    }

    public static void showPanel(JPanel panel) {
        showPanel(panel,0.85);
    }

    public static void main(String[] args) {
        System.out.println(GUIUtil.imageFolder);
    }
}
