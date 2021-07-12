package me.m.mswing.widget.panel;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundPanel extends JPanel {
    private Color bgc= Style.MColor.PETER_RIVER;
    private float radius=20f;
    public RoundPanel(){
        super();
        init();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        Shape shape=new RoundRectangle2D.Float(0,0,getWidth(),getHeight(),radius,radius);
        g2d.setColor(bgc);
        g2d.fill(shape);
        g2d.dispose();

    }
    private void init(){
        setLayout(null);
        setBackground(new Color(0,0,0,0));
    }

//    @Override
//    public Color getBackground() {
//        return bgc;
//    }
//
//    @Override
//    public void setBackground(Color bg) {
//       bgc=bg;
//    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
