package me.m.mswing.widget.loading;

import java.awt.*;

public abstract class LoadingStyle  {
    private long million=20;
    protected int width;
    protected int height;
    protected int marginLeft=-1;
    protected int marginTop=-1;
    protected Component parent;
    public static Stroke stroke=new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    public LoadingStyle(){}
    public LoadingStyle(Component parent){
        this.parent=parent;
    }
    public void setStroke(Stroke stroke){
       LoadingStyle.stroke =stroke;
    }
    protected abstract void paint(Graphics g);
    public abstract void show(Graphics g);
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public void setCompoent(Component parent){
        this.parent=parent;
    }
    public static Graphics2D optimizeGraphics(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_NORMALIZE);
        return g2d;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public long getMillion() {
        return million;
    }

    public void setMillion(long million) {
        this.million = million;
    }
}
