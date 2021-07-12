package me.m.mswing.widget.panel;

import me.m.mswing.style.Style;

import javax.swing.*;
import java.awt.*;

/**
 * 一个以渐变颜色为背景的Jpanel
 */
public class GradientPanel extends JPanel {
//    private int GPwidth;
//    private int GPheight;
    public final static int TOP_BOTTOM=0;
    public final static int BOTTOM_TOP=1;
    public final static int LEFT_RIGHT=2;
    public final static int RIGHT_LEFT=3;
    public final static int LEFT_TOP_RIGHT_BOTTOM=4;
    public final static int RIGHT_BOTTOM_LEFT_TOP=5;
    public final static int RIGHT_TOP_LEFT_BOTTOM=6;
    public final static int LEFT_BOTTOM_RIGHT_TOP=7;
    protected Rectangle rectangle;
    protected Color color1= Style.MColor.PETER_RIVER;
    protected Color color2= Style.MColor.RADIAN_YELLOW;
    /**
     * 渐变反向
     */
    protected int direction=LEFT_TOP_RIGHT_BOTTOM;
    /**
     * 用来设置自定义paint
     */
    protected Paint paint;

    /**
     * 会以{@code color1}为起点
     */
    public GradientPanel(){
        super();
//        setBackground(Style.MColor.PETER_RIVER);
    }
    public GradientPanel(int width,int height){
        super();
        setSize(width,height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= (Graphics2D) g;
        if (rectangle==null){
            rectangle=g2d.getClip().getBounds();
        }
        if (paint==null) {
            GradientPaint gp=null;
            int rw= (int) rectangle.getWidth();
            int rh= (int) rectangle.getHeight();
            switch (direction){
                case TOP_BOTTOM:
                    gp=new GradientPaint(0,0,color1,0,rh,color2);
                    break;
                case BOTTOM_TOP:
                    gp=new GradientPaint(0,0,color2,0,rh,color1);
                    break;
                case LEFT_RIGHT:
                    gp=new GradientPaint(0,0,color1,rw,0,color2);
                    break;
                case RIGHT_LEFT:
                    gp=new GradientPaint(0,0,color2,rw,0,color1);
                    break;
                case LEFT_TOP_RIGHT_BOTTOM:
                    gp=new GradientPaint(0,0,color1,rw,rh,color2);
                    break;
                case RIGHT_BOTTOM_LEFT_TOP:
                    gp=new GradientPaint(0,0,color2,rw,rh,color1);
                    break;
                case LEFT_BOTTOM_RIGHT_TOP:
                    gp=new GradientPaint(0,rh,color1,rw,0,color2);
                    break;
                case RIGHT_TOP_LEFT_BOTTOM:
                    gp=new GradientPaint(0,rh,color2,rw,0,color1);
                    break;
            }
            g2d.setPaint(gp);
        }
        else {
            g2d.setPaint(paint);
        }
        /*
        * 从矩形的 x=0,y=0的位置开始，
        * 以适配布局管理器
        * */
        g2d.fillRect(0,0,(int)rectangle.getWidth(),(int)rectangle.getHeight());
        g2d.dispose();
    }

    /**
     * 设置开始颜色
     * @param color1
     */
    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    /**
     * 设置结束颜色
     * @param color2
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    /**
     * 如果需要设置自定义Paint方式,如果未设置，
     * 会调用默认以{@code color1} 和 {@code color2} 来构造一个GradientPaint
     * @param paint
     */
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getDirection() {
        return direction;
    }

    /**
     * 设置渐变方向,可用值为{@link GradientPanel#TOP_BOTTOM},{@link  GradientPanel#LEFT_BOTTOM_RIGHT_TOP}等
     * 如果没有可用的direction，会抛出异常
     * @param direction
     */
    public void setDirection(final int direction) {
        if (direction<0||direction>7){
            try {
                this.direction=LEFT_TOP_RIGHT_BOTTOM;
                throw new Exception("direction必须在0-7");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.direction=direction;
    }
}
