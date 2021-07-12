package me.m.mswing.widget.button;

import me.m.mswing.widget.loading.LoadingStyle;

import java.awt.*;

public class MJRectRadioButton extends MJRadioButton {
    private int borderRadiu=4;
    public MJRectRadioButton(String text, Component parent) {
        super(text, parent);
        width=30;
        height=16;
    }

    @Override
    public void paint(Graphics g) {
        setY(0);
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        g2d.setStroke(stroke);
        if (font!=null){
           g2d.setFont(font);
        }
        if (rectangle==null){
            rectangle=g2d.getClip().getBounds();
        }
        /*
        *  先填充背景 目的是清空上一次画的东西
        * */
        g2d.setPaint(parent.getBackground());
        g2d.fillRect(0,0,(int)rectangle.getWidth(),(int)rectangle.getHeight());
        /*
        * 计算x y 确保 所有东西正好显示
        * */
        fm=g2d.getFontMetrics();
        int fh=fm.getHeight();
        int fw=fm.stringWidth(text);
        switch (textDirection){
            case TOP:
                y=fh/2+height;
                x=fw/2;
                break;
            case BOTTOM:
                y=0;
                x=fw/2;
                break;
            case LEFT:
                y=2;
                x=fw+width;
                break;
            case RIGHT:
                y=2;
                x=0;
                break;
        }
        /*
        * outter
        * */
        g2d.setColor(color);
        g2d.drawRoundRect(x,y,width,height,borderRadiu,borderRadiu);
        /*
        * inner
        * */
        if (isChecked){
            g2d.fillRoundRect(x+gap,y+gap,width-2*gap,height-2*gap,borderRadiu,borderRadiu);
        }else{
            g2d.clearRect(x+gap,y+gap,width-2*gap,height-2*gap);
        }
        g2d.setColor(fontColor);
        switch (textDirection){
            case TOP:
                g2d.drawString(text,x+width/2-fw/2,y+fh/2-tdp);
                break;
            case BOTTOM:
                g2d.drawString(text,x+width/2-fw/2,tdp+y+fh);
                break;
            case LEFT:
                g2d.drawString(text,1,y+fh/3+height/2);
                break;
            case RIGHT:
                g2d.drawString(text,x+tdp+width,y+fh/3+height/2);
                break;

        }
        g2d.dispose();

    }

    /**
     * 设置按钮四个角的弧度
     *  默认为0
     * @param borderRadiu
     */
    public void setBorderRadiu(int borderRadiu) {
        this.borderRadiu = borderRadiu;
    }

    public int getBorderRadiu() {
        return borderRadiu;
    }
}
