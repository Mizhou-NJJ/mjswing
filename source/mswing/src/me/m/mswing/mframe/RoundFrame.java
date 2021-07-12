package me.m.mswing.mframe;

import me.m.mswing.style.Style;
import me.m.mswing.widget.panel.RoundPanel;

import javax.swing.*;
import java.awt.*;

public class RoundFrame extends ResizebleFrame {
    private float radius=20f;
    private JPanel main;
    public RoundFrame(){
        super();
        _();
    }
    public RoundFrame(String title,int width,int height){
        super(title,width,height);
        _();
    }
    public RoundFrame(String title,int width,int height,Image image){
        super(title,width,height,image);
        _();
    }
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
//        Shape shape= new RoundRectangle2D.Float(0,0,getWidth(),getHeight(),radius,radius);
//        g2d.setColor(Style.MColor.PETER_RIVER);
//        g2d.fill(shape);
//        /*
//        * title bar
//        * */
////        Shape bar=new RoundRectangle2D.Float(0,0,getWidth(),30,radius,20);
////        g2d.setColor(Style.MColor.EMERALD);
////        g2d.fill(bar);
//        g2d.dispose();
//    }
    private void _(){
        /*
        *
        * RoudFrame->RoundPanel-
        * */
//        setLayout(null);
        main=new RoundPanel();
//        TBar tBar=new RoundTBar();
//        tBar.setBounds(0,0,getWidth(),30);
//        main.add(tBar);
        setLayout(null);
        JPanel jPanel=new JPanel();
        jPanel.setBackground(Style.MColor.WHITE);
        jPanel.setBounds(0,0,400,30);
        main.add(jPanel);
        super.add(main);
    }

    @Override
    public Component add(Component comp) {
        return  main.add(comp);
    }
}
