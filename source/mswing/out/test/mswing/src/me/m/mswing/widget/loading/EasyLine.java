package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;
import me.m.mswing.terminal.Terminal;

import java.awt.*;

public class EasyLine extends UpLoadingStyle {
    private float lineWidth;
    private int lineLength;
    private Color baselineColor= Style.MColor.AMERICAN_RIVER;
    private Color progressColor=Color.WHITE;
//    private int marginLeft=-1;
//    private int marginTop=-1;

    /**
     * 构造一个长未100 宽为 2.0 居中与MFrame的 EasyLine
     */
    public EasyLine(){
        lineLength=100;
        lineWidth=2f; // 与LoadingStyle的Stroke宽度一样
//        marginLeft=(Style.MFrame.width-lineLength)/2;
//        marginTop= (int) ((Style.MFrame.height-lineWidth)/2);
        cal();
    }

    /**
     * 以宽和高进行构造
     * @param lineWidth EasyLine的宽度(高度)
     * @param lineLength EasyLine的长度
     * @param c 此EasyLine要放置的组件 默认放置在c中间
     */
    public EasyLine(float lineWidth,int lineLength,Component c){
        super(c);
        this.lineWidth=lineWidth;
        this.lineLength=lineLength;
//        int w=c.getWidth();
//        int h=c.getHeight();
//        marginTop= (int) ((h-lineWidth)/2);
//        marginLeft=(w-lineLength)/2;
        cal();
    }
    @Override
    public int updateProgress(int p) {
        if(p<0) Terminal.warning("p<0");
        if(p>100) Terminal.warning("p>100");
        progressPer=p;
        progress=lineLength/100f*p;
        return (int) progress;
    }

    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(LoadingStyle.stroke);
        /*
        * baseLine
        * */
        g2d.setColor(baselineColor);
        g2d.drawLine(marginLeft,marginTop,marginLeft+lineLength,marginTop);
        /*
        * progressLine
        * */
        g2d.setColor(progressColor);
        g2d.drawLine(marginLeft,marginTop, (int) (marginLeft+progress),marginTop);
    }

    @Override
    public void show(Graphics g) {
        paint(g);
    }
    private void cal(){
        width=lineLength;
        height= (int) lineWidth;
        if(marginTop==-1&&marginLeft==-1) {
            // 若未给出 parent
            if (parent == null) {
                marginLeft = (Style.MFrame.width - lineLength) / 2;
                marginTop = (int) ((Style.MFrame.height - lineWidth) / 2);
            } else {
                // 利用parent的宽高进行计算
                int w = parent.getWidth();
                int h = parent.getHeight();
                marginTop = (int) ((h - lineWidth) / 2);
                marginLeft = (w - lineLength) / 2;
            }
        }
    }
    public int getMarginLeft(){
        return  this.marginLeft;
    }
    public void setMarginLeft(int marginLeft){
        this.marginLeft=marginLeft;
    }
    public int getMarginTop(){
        return marginTop;
    }
    public void setMarginTop(int marginTop){
        this.marginTop=marginTop;
    }
    public Color getBaselineColor(){
        return this.baselineColor;
    }
    public void setBaselineColor(Color baselineColor){
        this.baselineColor=baselineColor;
    }
    public Color getProgressColor(){
        return this.progressColor;
    }
    public void setProgressColor(Color progressColor){
        this.progressColor=progressColor;
    }
    public void setLineWidth(float  lineWidth){
        this.lineWidth=lineWidth;
        stroke=new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        marginLeft=-1;
        marginTop=-1;
        cal();
    }
    public void setLineLength(int lineLength){
        this.lineLength=lineLength;
        marginLeft=-1;
        marginTop=-1;
        cal();
    }

    @Override
    public void setCompoent(Component parent) {
        super.setCompoent(parent);
        marginTop=-1;
        marginLeft=-1;
        cal();
    }
}
