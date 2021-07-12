package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;
import me.m.mswing.terminal.Terminal;

import java.awt.*;

public class TextLine extends UpLoadingStyle {
    public final static int EAST=0;
    public final static int NORTH=1;
    public final static int SOUTH=2;
    public final static int WEST=3;
//   private int marginLeft=-1;
//   private int marginTop=-1;
   private float lineWidth;
   private int lineLength;
   private Color baseLineColor= Style.MColor.AMERICAN_RIVER;
   private Color progressLineColor= Color.white;
   private Color textColor=Color.WHITE;
   private Font font;
   private String preText="";
   private int textDisProgress=20;
   private int textDirection=SOUTH;
   public TextLine(){
       lineLength=100;
       lineWidth=2f;
       cal();
   }
   public TextLine(float lineWidth,int lineLength,Component c){
       super(c);
       this.lineWidth=lineWidth;
       this.lineLength=lineLength;
       cal();
   }
   private void cal(){
//       switch (textDirection){
//           case TextLine.EAST:
//
//       }
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

    @Override
    public void setCompoent(Component parent) {
        super.setCompoent(parent);
        marginLeft=-1;
        marginTop=-1;
    }

    @Override
    public int updateProgress(int p) {
       if (p<0){
           Terminal.warning("p<0");
           p=0;
       }
       if (p>100){
           Terminal.warning("p>0");
           p%=100;
       }
       progressPer=p;
       progress=lineLength/100f*p;
        return (int) progress;
    }

    @Override
    protected void paint(Graphics g) {
       Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
       g2d.setStroke(stroke);
       // baseLine
        g2d.setColor(baseLineColor);
        g2d.drawLine(marginLeft,marginTop,marginLeft+lineLength,marginTop);
        // progress line
        g2d.setColor(progressLineColor);
        g2d.drawLine(marginLeft,marginTop, (int) (marginLeft+progress),marginTop);
        // Text
        g2d.setColor(textColor);
        FontMetrics fm = g2d.getFontMetrics();
        int fh=fm.getHeight();
        int fw=fm.stringWidth(preText+progressPer+"%");
        if(font!=null) g2d.setFont(font);
        int textX=0;
        int textY=0;
        switch (textDirection){
            case SOUTH:
                textX=(lineLength-fw)/2+marginLeft;
                textY=marginTop+textDisProgress;
                break;
            case NORTH:
                textX=(lineLength-fw)/2+marginLeft;
                textY= (int) (marginTop-textDisProgress+lineWidth);
                break;
            case EAST:
                textX=marginLeft-textDisProgress-fw;
                textY= (int) (marginTop+fh/3);
                break;
            case WEST:
                textX=marginLeft+textDisProgress+lineLength;
                textY= (int) (marginTop+fh/3);
                break;
        }
        g2d.drawString(preText+progressPer+"%",textX,textY);
    }

    @Override
    public void show(Graphics g) {
       paint(g);
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        stroke=new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        marginLeft=-1;
        marginTop=-1;
        cal();
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
        marginTop=-1;
        marginLeft=-1;
        cal();
    }

    public Color getBaseLineColor() {
        return baseLineColor;
    }

    public void setBaseLineColor(Color baseLineColor) {
        this.baseLineColor = baseLineColor;
    }

    public Color getProgressLineColor() {
        return progressLineColor;
    }

    public void setProgressLineColor(Color progressLineColor) {
        this.progressLineColor = progressLineColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getPreText() {
        return preText;
    }

    public void setPreText(String preText) {
        this.preText = preText;
    }

    public int getTextDisProgress() {
        return textDisProgress;
    }

    public void setTextDisProgress(int textDisProgress) {
        this.textDisProgress = textDisProgress;
    }

    public int getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(int textDirection) {
        this.textDirection = textDirection;
    }
}
