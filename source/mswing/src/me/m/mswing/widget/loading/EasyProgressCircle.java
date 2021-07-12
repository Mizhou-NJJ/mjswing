package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;
import me.m.mswing.terminal.Terminal;

import java.awt.*;

public class EasyProgressCircle extends UpLoadingStyle{
    private int diameter;

    private Color baseBorderColor= Style.MColor.FUEL_TOWN;
    private Color progressBorderColor=Color.WHITE;
    private Color textColor=Color.WHITE;
    /**
     * 圆形边的宽度
     */
    private float borderBold=2f;
//    private int endAngle=90;
    private int startAngle=90;
    private Font font;
    private boolean isShowPS=true;
    /**
     * 以直径<strong>50</strong>边框为 <strong>2.0</strong>构造
     */
    public EasyProgressCircle(){
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
    public EasyProgressCircle(int diameter,Component c){
        super(c);
        this.diameter=diameter;
        cal();
    }
    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(stroke);
        g2d.setColor(baseBorderColor);
        g2d.drawOval(marginLeft,marginTop,width,height);
        g2d.setColor(progressBorderColor);
//        if (progress>360){
//            g2d.drawArc(marginLeft,marginTop,width,height,startAngle, 360);
//            return;
//        }
        g2d.drawArc(marginLeft,marginTop,width,height,startAngle, (int) progress);
        // text
        if(font!=null){
            g2d.setFont(font);
        }
        FontMetrics fm=g2d.getFontMetrics();
        int fh=0;
        int fw=0;
        String dt="";
        if (isShowPS){
            dt=progressPer+"%";
           fh=fm.getHeight();
           fw=fm.stringWidth(dt);
        }else{
            dt= String.valueOf(progressPer);
            fh=fm.getHeight();
            fw=fm.stringWidth(dt);
        }
        g2d.setColor(textColor);
        g2d.drawString(dt,(width-fw)/2+marginLeft,height/2+fh/3+marginTop);
    }

    @Override
    public void show(Graphics g) {
        paint(g);
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
        progress=360/100f*p;
        return 0;
    }
    private void cal(){
        width= (int) (diameter+borderBold);
        height= (int) (diameter+borderBold);
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

    /**
     * 开始绘制进度的位置，基于 笛卡尔坐标而言 若{@code startAngle=0} 则是从
     * x 轴正半轴开始绘制 若{@code startAngle=90} 则是从 Y轴正半轴开始绘制
     * @param startAngle
     */
    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public Font getFont() {
        return font;
    }

    /**
     * 设置font
     * @param font
     */
    public void setFont(Font font) {
        this.font = font;
    }
    public boolean isShowPS(){
        return isShowPS;
    }

    /**
     *  设置加载进度时的文本 '%'是否可见
      * @param isShowPS  默认为true 可见
     */
    public void isShowPS(boolean isShowPS){
        this.isShowPS=isShowPS;
    }
}
