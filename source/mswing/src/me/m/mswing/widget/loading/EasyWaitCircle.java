package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;

import java.awt.*;

public class EasyWaitCircle extends LoadingStyle{
    private int diameter;

    private Color baseBorderColor= Style.MColor.FUEL_TOWN;
    private Color progressBorderColor=Color.WHITE;
    /**
     * 圆形边的宽度
     */
    private float borderBold=2f;
    private int endAngle=90;
    private int startAngle;
    /**
     * 以直径<strong>50</strong>边框为 <strong>2.0</strong>构造
     */
    public EasyWaitCircle(){
        diameter=50;
        cal();
    }

    /**
     * 以给定直径进行构造 并且构造出来的{@code EasyWaitCircle} 会显示在
     * {@code c} 中间,当然如果想要改变位置可以通过{@link LoadingStyle#setMarginLeft(int)} 和
     * {@link LoadingStyle#setMarginTop(int)} 改变
     * @param diameter
     * @param c
     */
    public EasyWaitCircle(int diameter,Component c){
        super(c);
        this.diameter=diameter;
        cal();
    }
    private void cal(){
        width=diameter;
        height=diameter;
        setMillion(10);
        if(marginTop==-1&&marginLeft==-1) {
            // 若未给出 parent
            if (parent == null) {
                marginLeft = (Style.MFrame.width - width) / 2;
                marginTop = (int) ((Style.MFrame.height - height) / 2);
            } else {
                // 利用parent的宽高进行计算
                int w = parent.getWidth();
                int h = parent.getHeight();
                marginTop = (int) ((h - height) / 2);
                marginLeft = (w - width) / 2;
            }
        }
    }
    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(stroke);
        Rectangle rectangle=g2d.getClip().getBounds();
        g2d.setColor(baseBorderColor);
        g2d.drawOval(marginLeft,marginTop,width,height);
        g2d.setColor(progressBorderColor);
        g2d.drawArc(marginLeft,marginTop,width,height,startAngle,endAngle);
        if(endAngle>=360){
            endAngle=0;
        }
//        endAngle;
        startAngle+=2;

    }

    @Override
    public void show(Graphics g) {
        paint(g);
    }

    public float getBorderBold() {
        return borderBold;
    }

    /**
     * 设置圆圈边的宽度
     * @param borderBold 宽度
     */
    public void setBorderBold(float borderBold) {
        this.borderBold = borderBold;
        setStroke(new BasicStroke(borderBold,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
    }

    /**
     * 设置底圆的颜色
     * @param baseBorderColor
     */
    public void setBaseBorderColor(Color baseBorderColor) {
        this.baseBorderColor = baseBorderColor;
    }
    public Color getBaseBorderColor(){
        return baseBorderColor;
    }

    /**
     * 设置进度圆的颜色
     * @param progressBorderColor
     */
    public void setProgressBorderColor(Color progressBorderColor){
        this.progressBorderColor=progressBorderColor;
    }
    public Color getProgressBorderColor(){
        return progressBorderColor;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        marginTop=-1;
        marginLeft=-1;
        cal();
    }

    public int getEndAngle() {
        return endAngle;
    }

    /**
     * 设置progress 的弧长默认为 90
     * @param endAngle 可取范围为 0-360 (0即什么页没有，360 就是一个圆圈
     */
    public void setEndAngle(int endAngle) {
        this.endAngle = endAngle;
    }

    public int getStartAngle() {
        return startAngle;
    }

    /**
     * 圆圈开始绘制的位置
     * @param startAngle (0-360)
     */
    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }
}
