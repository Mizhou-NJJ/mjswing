package me.m.mswing.widget.panel;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class TP extends JPanel {
    public TP(){
       super();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        Point2D.Float pf=new Point2D.Float(50,50);
        float radius=100;
        float dists[]={0.1f,1f};
        Color cs[]={Style.MColor.START_SHADOW, Style.MColor.END_SHADOW};
        RadialGradientPaint rgp=new RadialGradientPaint(pf,radius,dists,cs);
        g2d.setPaint(rgp);
        g2d.fillRect(10,10,120,120);
        g2d.setColor(Style.MColor.PETER_RIVER);
        g2d.fillRect(10,10,100,100);
        g2d.dispose();
    }
}
