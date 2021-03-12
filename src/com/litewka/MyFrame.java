package com.litewka;

import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame {




    public MyFrame(int[] xP, int[] yP){
        GraphicsChart graphicsChart = new GraphicsChart(xP, yP);
        this.setSize(420,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphicsChart);
        this.setVisible(true);
        this.setResizable(false);

    }

}
