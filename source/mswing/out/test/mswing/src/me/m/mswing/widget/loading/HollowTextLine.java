package me.m.mswing.widget.loading;

import java.awt.*;

public class HollowTextLine extends HollowLine {
    public final static int WEST=0;
    public final static int NORTH=1;
    public final static int SOUTH=2;
    public final static int EAST=4;
    private Font font;
    private Color fontColor=Color.WHITE;
    private int textDirection=SOUTH;
    /**
     * 文本距离进度条的距离
     */
    private int fpGap=20;
    /**
     * 默认显示的前缀文本，即就是只显示 "n%"
     */
    private String preText="";
    public HollowTextLine(){
        super();
    }
    public HollowTextLine(int height,int length,Component c){
        super(height,length,c);
    }
    @Override
    protected void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        g2d.setColor(fontColor);
        if (font!=null){
            g2d.setFont(font);
        }
        FontMetrics fm=g2d.getFontMetrics();
        int fw=fm.stringWidth(progressPer+"%"+preText);
        int fh=fm.getHeight();
        int textX=0;
        int textY=0;
        switch (textDirection){
            case HollowTextLine.WEST:
               textX=marginLeft+fpGap+length;
               textY=marginTop+fh/3+height/2;
               break;
            case HollowTextLine.EAST:
                textX=marginLeft-fpGap-fw;
                textY=marginTop+fh/3+height/2;
                break;
            case HollowTextLine.NORTH:
                textX=(length-fw)/2+marginLeft;
                textY=marginTop-fpGap;
                break;
            case HollowTextLine.SOUTH:
                textX=(length-fw)/2+marginLeft;
                textY=marginTop+fpGap+height;
                break;
        }
        g2d.drawString(preText+progressPer+"%",textX,textY);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
    public String getPreText(){
        return preText;
    }
    /**
     * 设置文本(前缀文本)
     * @param preText
     */
    public void setPreText(String preText){
        this.preText=preText;
    }

    /**
     * 设置文本颜色
     * @param fontColor
     */
    public void setFontColor(Color fontColor){
        this.fontColor=fontColor;
    }

    public int getFpGap() {
        return fpGap;
    }
    /**
     * 设置文本到 进度条的距离
     * @param fpGap
     */
    public void setFpGap(int fpGap) {
        this.fpGap = fpGap;
    }
    public int getTextDirection(){
        return textDirection;
    }

    /**
     * 设置文本显示的方向
     * @param textDirection
     */
    public void setTextDirection(int textDirection){
        this.textDirection=textDirection;
    }
}
