package com.huben.gui.panel;

import com.huben.gui.page.SpendPage;
import com.huben.service.SpendService;
import com.huben.utils.CircleProgressBar;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author koishi
 */
public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel();

    private JLabel lMonthSpend = new JLabel("本月消费");
    private JLabel lTodaySpend = new JLabel("今日消费");
    private JLabel lAvgSpendPerDay = new JLabel("日均消费");
    private JLabel lMonthLeft = new JLabel("本月剩余");
    private JLabel lDayAvgAvailable = new JLabel("日均可用");
    private JLabel lMonthLeftDay = new JLabel("距离月末");

    private JLabel vMonthSpend = new JLabel("￥2300");
    private JLabel vTodaySpend = new JLabel("￥25");
    private JLabel vAvgSpendPerDay = new JLabel("￥120");
    private JLabel vMonthAvailable = new JLabel("￥2084");
    private JLabel vDayAvgAvailable = new JLabel("￥389");
    private JLabel vMonthLeftDay = new JLabel("15天");

    private CircleProgressBar bar;

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        vMonthSpend.setForeground(ColorUtil.blueColor);
        vTodaySpend.setForeground(ColorUtil.blueColor);
        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    private JPanel center() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(west(), BorderLayout.WEST);
        panel.add(bar, BorderLayout.CENTER);

        return panel;
    }

    private JPanel west() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(lMonthSpend);
        panel.add(vMonthSpend);
        panel.add(lTodaySpend);
        panel.add(vTodaySpend);

        return panel;
    }

    private JPanel south() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));
        panel.add(lAvgSpendPerDay);
        panel.add(lMonthLeft);
        panel.add(lDayAvgAvailable);
        panel.add(lMonthLeftDay);
        panel.add(vAvgSpendPerDay);
        panel.add(vMonthAvailable);
        panel.add(vDayAvgAvailable);
        panel.add(vMonthLeftDay);

        return panel;
    }

    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthAvailable.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);

        bar.setProgress(spend.usagePercentage);
        if (spend.isOverSpend){
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        }else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
    }

    @Override
    public void addListener() {

    }
}
