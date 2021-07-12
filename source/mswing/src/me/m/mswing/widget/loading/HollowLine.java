package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;
import me.m.mswing.terminal.Terminal;

import java.awt.*;

/**
 * 空心的进度条
 */
public class HollowLine extends UpLoadingStyle {
    protected int height;
    protected int length;
    protected Color baseColor= Style.MColor.SILVER;
    protected Color progressColor= Style.MColor.PETER_RIVER;
    protected int padding;
    protected int arcWidth=20;
    protected int arcHeight=20;

    public HollowLine(){
        this.height=20;
        this.length=100;
        this.padding=2;
        cal();
    }
    public HollowLine(int height,int length,Component c){
        super(c);
        this.height=height;
        this.length=length;
        this.padding=2;
        cal();
    }
    private void cal(){
        super.width=length;
        super.height=height;
        if(marginTop==-1&&marginLeft==-1) {
            // 若未给出 parent
            if (parent == null) {
                marginLeft = (Style.MFrame.width - length) / 2;
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
    public int updateProgress(int p) {
        if (p<0){
            Terminal.warning("p<0");
            p=0;
        }
        if (p>100){
            Terminal.warning("p>100");
            p%=100;
        }
        progressPer=p;
        progress=(length)/100f*p;
        return 0;
    }

    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(stroke);
        // base
        g2d.setColor(baseColor);
        g2d.fillRoundRect(marginLeft,marginTop,length,this.height,arcWidth,arcHeight);
        // progress
        g2d.setColor(progressColor);
        if (progress >= length - padding / 2){
            g2d.fillRoundRect(marginLeft+padding/2,marginTop+padding/2,length-padding,height-padding,arcWidth,arcHeight);
            return;
        }
        g2d.fillRoundRect(marginLeft+padding/2,marginTop+padding/2, (int) progress,height-padding,arcWidth,arcHeight);
    }

    @Override
    public void show(Graphics g) {
        paint(g);
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        marginLeft=-1;
        marginTop=-1;
        cal();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        marginTop=-1;
        marginLeft=-1;
        cal();
    }

    public Color getBaseColor() {
        return baseColor;
    }

    /**
     * 设置外轮廓图形的颜色
     * @param baseColor
     */
    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public Color getProgressColor() {
        return progressColor;
    }

    /**
     * 设计进度条的颜色
     * @param progressColor
     */
    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
    }

    public int getPadding() {
        return padding;
    }

    /**
     * 设置进度条与轮廓的距离 当然，当
     * 进度条为100% 后，这个距离看起来像线
     * ,也可看说padding是这条线的宽度
     * @param padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }
    public int getBorderRadius(){
        return this.arcWidth;
    }

    /**
     * 设置矩形四个角的弧度(弯曲程度)
     * @param radius
     */
    public void setBorderRadius(int radius){
        this.arcHeight=arcWidth=radius;
    }
}
