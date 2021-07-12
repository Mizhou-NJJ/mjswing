package me.m.mswing.widget.jtext;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.*;
import java.awt.*;

public class MJTextField extends JTextField {
    private int radius;
    private int widht;
    private int height;
    private Color borderColor= Style.MColor.START_SHADOW;
    private Color bgColor=Style.MColor.WHITE;
    private int x;
    private int y;
    public MJTextField(){
       super();
       setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setColor(Style.MColor.WHITE);
        g2d.fillRoundRect(0,0, widht,height,radius,radius);
        g2d.setColor(Style.MColor.PETER_RIVER);
        g2d.drawRoundRect(0,0, widht-1, height-1,radius,radius);
        super.paint(g);
        g2d.dispose();
//        g2d.setColor(Style.MColor.PETER_RIVER);

//        g2d.drawRoundRect(0,0,100,30,20,20);
//        g.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
//        super.paintBorder(g);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        this.widht=width;
        this.height=height;
        this.x=x;
        this.y=y;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.widht=width;
        this.height=height;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void setOpaque(boolean isOpaque) {
       // doNothing
    }
}
