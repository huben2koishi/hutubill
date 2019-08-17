package com.huben.test;

import com.huben.utils.CircleProgressBar;
import com.huben.utils.ColorUtil;
import com.huben.utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author koishi
 */
public class CircleProgressBarTest {
    public static void main(String[] args) {
        GUIUtil.useLNF();

        JPanel panel = new JPanel();

        CircleProgressBar bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
        bar.setProgress(0);

        JButton button = new JButton("点击");

        panel.setLayout(new BorderLayout());
        panel.add(bar, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        GUIUtil.showPanel(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i = 0; i < 100; i++) {
                            bar.setProgress(i+1);
                            bar.setForegroundColor(ColorUtil.getByPercentage(i+1));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }

                        return null;
                    }
                }.execute();
            }
        });

    }
}
