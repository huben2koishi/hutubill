package com.huben.utils;

import com.huben.gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author koishi
 */
public class CenterPanel extends JPanel {
    private double rate;
    private JComponent component;
    private boolean strech;

    public CenterPanel(double rate) {
        this(rate, true);
    }

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    @Override
    public void repaint() {
        if (null != component) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = component.getPreferredSize();

            if (strech) {
                component.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            } else {
                component.setSize(componentSize);
            }

            component.setLocation(containerSize.width / 2 - component.getSize().width / 2, containerSize.height / 2 - component.getSize().height / 2);
        }

        super.repaint();
    }

    public void show(JComponent component) {
        this.component = component;
        Component[] components = getComponents();
        for (Component c : components) {
            remove(c);
        }
        add(component);

        if (component instanceof WorkingPanel){
            ((WorkingPanel) component).updateData();
        }

        this.updateUI();
    }
}
