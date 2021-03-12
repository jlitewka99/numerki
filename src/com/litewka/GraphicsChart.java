package com.litewka;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class GraphicsChart extends JPanel {

    public int[] xP;
    public int[] yP;

    public GraphicsChart(int[] xP, int[] yP) {
        this.xP = xP;
        this.yP = yP;
    }

    private static int[] translateX(int[] xPoints){
        int[] x = new int[xPoints.length];

        IntSummaryStatistics stat = Arrays.stream(xPoints).summaryStatistics();
        int min = stat.getMin();
        int max = stat.getMax();

        Double multiply = Math.abs(210.0/(max - min));

        for (int i =0; i<xPoints.length;i++){
            x[i] = (int)(210 + (double)xPoints[i]*multiply);
        }
        return x;
    }

    private static int[] translateY(int[] yPoints){
        int[] y = new int[yPoints.length];

        IntSummaryStatistics stat = Arrays.stream(yPoints).summaryStatistics();
        int min = stat.getMin();
        int max = stat.getMax();

        Double multiply = Math.abs(210.0/(max - min));

        for (int i =0; i<yPoints.length;i++){
            y[i] = (int)(210.0 - (double)yPoints[i]*multiply);
        }
        return y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2D = (Graphics2D) g;

        int[] xPoints = {0,10,20, -100};
        int[] yPoints = {0,39,420, -30};
        xPoints = translateX(xP);
        yPoints = translateY(yP);
        int nPoints = xPoints.length;



        g2D.setColor(Color.GREEN);
        g2D.drawLine(0, 210, 420, 210);
        g2D.drawLine(210, 0, 210, 420);
        g2D.setColor(Color.CYAN);

        g2D.drawPolyline(xPoints,yPoints,nPoints);


    }
}
