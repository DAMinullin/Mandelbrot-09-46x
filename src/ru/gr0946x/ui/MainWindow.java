package ru.gr0946x.ui;

import ru.gr0946x.Converter;
import ru.gr0946x.fractals.Mandelbrot;
import ru.gr0946x.ui.painting.FractalPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sin;

public class MainWindow extends JFrame {

    private final PaintPanel mainPanel;
    private final Mandelbrot fractal;
    private final Converter conv;
    private final FractalPainter painter;

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 650));
        conv = new Converter(-2.0, 1.0, -1.0, 1.0);
        fractal = new Mandelbrot(100, 2);
        painter = new FractalPainter(conv, fractal, x -> {
            if (x == 1f) return Color.BLACK;
            var r = (float)abs(cos(8 * x * x));
            var g = (float)abs(sin(5 * x));
            var b = (float)max(0, min(log(abs(sin(10*x)*cos(4*x))+0.01), 1));
            return new Color(r, g, b);
        });
        mainPanel = new PaintPanel(painter);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                painter.setWidth(mainPanel.getWidth());
                painter.setHeight(mainPanel.getHeight());
            }
        });
        setContent();
    }

    private void setContent(){
        var gl = new GroupLayout(getContentPane());
        setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
        );
    }
}
