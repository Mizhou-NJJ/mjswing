package me.m.mswing.widget.loading;

import me.m.mswing.terminal.Terminal;

import java.awt.*;

/**
 * 该类相比于{@code EasyConnecting} 怎加了尾部的影子
 * 影子会持续两个，颜色颜色相于第一个圆会逐渐淡化
 * 当然 淡化的幅度可以设置
 */
public class EasyShadowConnecting extends EasyConnecting {
    private Color preColor;
    private Color prePreColor;
    private int colorGap=15;
    private int loff=-2;
    private int lloff=-3;
    public EasyShadowConnecting()
    {
        super();
        colorAdapter();
    }
    public EasyShadowConnecting(int radius, int circleCount, Component c)
    {
        super(radius,circleCount,c);
        colorAdapter();
    }
    @Override
    protected void cal(){
        super.cal();
        colorAdapter();
    }
    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(stroke);
        g2d.setColor(circleColor);
        for(short i=0;i<circleCount;i++){
            if(i==off){
                g2d.setColor(loadingCircleColor);
            }
            if(off==circleCount+2){
                off=-1;
            }
            if(loff==i){
                g2d.setColor(preColor);
            }
            if (loff==circleCount+1){
                loff=-2;
            }
            if (i==lloff){
                g2d.setColor(prePreColor);
            }
            if (lloff==circleCount){
                lloff=-3;
            }
            g2d.fillOval(marginLeft+i*diameter*2,marginTop,radius*2,radius*2);
            g2d.setColor(circleColor);
        }
        // draw text
        if(isShowText) {
            g2d.setColor(textColor);
            FontMetrics fm = g2d.getFontMetrics();
            int fw = fm.stringWidth(loadingText);
            int fh = fm.getHeight();
            int textX = (width - fw) / 2 + marginLeft;
            int textY = 0;
            switch (textDirection) {
                case EasyShadowConnecting.NORTH:
                    textY = marginTop - textDisLoading;
                    g2d.drawString(loadingText, textX, textY);
                    break;
                case EasyShadowConnecting.SOUTH:
                    textY = marginTop + textDisLoading+height;
                    g2d.drawString(loadingText, textX, textY);
                    break;
            }
        }
        off++;
        loff++;
        lloff++;

    }

    @Override
    public void show(Graphics g) {
        paint(g);
    }
    private void colorAdapter(){
        int R=loadingCircleColor.getRed();
        int G=loadingCircleColor.getGreen();
        int B=loadingCircleColor.getBlue();
        int doubleGap=colorGap*2;
        if (R+doubleGap>255){
            Terminal.warning("Color parameter outside of expected range:Red");
        }
        if (G+doubleGap>255){
            Terminal.warning("Color parameter outside of expected range:Green");
        }
        if(B+doubleGap>255){
            Terminal.warning("Color parameter outside of expected range:Blue");
        }
        preColor=new Color(Math.min((R + colorGap), 255),Math.min((G+colorGap),255),Math.min((B+colorGap),255));
        prePreColor=new Color(Math.min(R+doubleGap,255),Math.min(G+doubleGap,255),Math.min(B+doubleGap,255));
    }


    /**
     * 获取颜色淡化曲度
     * @return
     */
    public int getColorGap() {
        return colorGap;
    }

    /**
     * 设置颜色变化曲度
     * @param colorGap
     */
    public void setColorGap(int colorGap) {
        this.colorGap = colorGap;
        cal();
    }


}
