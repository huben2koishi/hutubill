package com.huben.utils;

import com.huben.entity.Record;
import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author koishi
 */
public class ChartUtil {
    public static int max(double[] values) {
        int max = 0;
        for (double value : values) {
            if (value > max) {
                max = (int) value;
            }
        }
        return max;
    }

    private static String[] sampleLabels(List<Record> rs) {
        String[] sampleLabels = new String[rs.size()];
        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5) {
                sampleLabels[i] = i + 1 + "日";
            }
        }
        return sampleLabels;
    }

    private static double[] sampleValues(List<Record> rs) {
        double[] sampleValues = new double[rs.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).getSpend();
        }
        return sampleValues;
    }

    public static Image getImage(List<Record> rs, int width, int height) {
        double[] sampleValues = sampleValues(rs);
        String[] sampleLabels = sampleLabels(rs);
        int max = max(sampleValues);

        Color[] sampleColors = new Color[]{ColorUtil.blueColor};

        BarChart chart = new BarChart();

        chart.setSampleCount(sampleValues.length);
        chart.setSampleValues(0, sampleValues);
        chart.setSampleLabels(sampleLabels);
        chart.setSampleColors(sampleColors);

        chart.setRange(0, max * 1.2);
        chart.setValueLinesOn(true);
        chart.setSampleLabelsOn(true);
        chart.setSampleLabelStyle(Chart.BELOW);

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        chart.setLegendOn(true);
        chart.setLegendPosition(Chart.LEFT);
        chart.setLegendLabels(new String[]{"月消费报表"});
        chart.setFont("legendFont", new Font("Diaalog", Font.BOLD, 13));
        chart.setFont("sampleLabelFont", new Font("Diaalog", Font.BOLD, 13));
        chart.setChartBackground(Color.WHITE);
        chart.setBackground(ColorUtil.backgroundColor);

        return chart.getImage(width, height);
    }
}
