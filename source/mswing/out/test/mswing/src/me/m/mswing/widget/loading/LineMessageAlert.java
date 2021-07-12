package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;
import me.m.mswing.terminal.Terminal;

import java.awt.*;

public class LineMessageAlert extends UpLoadingStyle{

    private float lineWidth;
    private int lineLength;
//    private int marginLeft=-1;
//    private int marginTop=-1;
    private int alertWidth=40; // 提示小窗口的宽度
    private int alertHeight=20; // 提示小窗口的高度
    private Font font;
    private int M; // M 是进度条进度点的x坐标
    private int N; // N 是进度条进度点的y坐标
    private Color baseLineColor= Style.MColor.AMERICAN_RIVER;
    private Color progressLineColor= Color.WHITE;
    private Color alertTextColor= Style.MColor.AMERICAN_RIVER;
    public LineMessageAlert(){
        lineWidth=2.0f; // 此宽度和父类中 Stroke 宽度一样，则不需要重新初始化 Stroke1
        lineLength=100;
        cal();
    }
    public LineMessageAlert(float lineWidth,int lineLength,Component c){
       super(c);
       this.lineWidth=lineWidth;
       this.lineLength=lineLength;
       cal();
    }
    public LineMessageAlert(float lineWidth,int lineLength, int marginLeft, int margTop){
       this.lineLength=lineLength;
       this.lineWidth=lineWidth;
       this.marginLeft=marginLeft;
       this.marginTop=margTop;
       cal();
    }
    private void cal(){
        width=lineLength;
        height= (int) (alertHeight+alertHeight/2+lineWidth);
        /*
        *  如果未给出marginLeft 和 marginTop 即:marginLeft=-1 marginTop=-1时
        *  则利用parent计算
        *  若未给出 parent 则 利用MFrame计算
        * */
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
           /*
           *  初始化M N
           **/
           M=marginLeft;
           N=marginTop;
    }
    @Override
    protected void paint(Graphics g) {
        // 优化 Graphics 去锯齿
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        if (font!=null) g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int fh=fm.getHeight();
        int fw=fm.stringWidth(progressPer+"%");
//        int [] fws=fm.getWidths();
//        for(short i=0;i<fws.length;i++){
//            fw+=fws[i];
//        }
//        System.out.println(fw);
        /*
        *  以默认Stroke来画
        *  如需改变Stroke 则 调用setStroke方法即可
        * */
        g2d.setStroke(LoadingStyle.stroke);
        /*画底线
        * 长度为 lineLength
        * 宽度为 lineWidth
        * 起点坐标为 (marginLeft,marginTop) 终点坐标(marginLeft+lineLength,marginTop)
        * */
        g2d.setColor(baseLineColor);
        g2d.drawLine(marginLeft,marginTop,marginLeft+lineLength,marginTop);
        /*
        *  进度条线
        *  width
        *  length= progress
        * */
        g2d.setColor(progressLineColor);
        g2d.drawLine(marginLeft,marginTop, (int) (marginLeft+progress),marginTop);
        /*
        *  进度提示框
        *
        * */
        int T=alertWidth/3;
        int harfH=alertHeight/2;
        int harfT=T/2;
        int xs[]={
                M-harfT-T,
                M-harfT-T,
                M-harfT,
                M,
                M+harfT,
                M+harfT+T,
               M+harfT+T};
        int ys[]={
               N-harfH-alertHeight,
                N-harfH,
                N-harfH,
                N,
                N-harfH,
                N-harfH,
                N-harfH-alertHeight};
        g2d.fillPolygon(xs,ys,7);
        /*
        *
        * */
        g2d.setColor(alertTextColor);
        g2d.drawString(progressPer+"%",M-harfT-T+(alertWidth-fw)/2,N-harfH-alertHeight+(alertHeight+fh)/2-fh/3);

    }

    @Override
    public void show(Graphics g) {
        // do something
        paint(g);
    }

    @Override
    public int updateProgress(int p/*p<=100*/) {
        progressPer=p;
        if (p<0) {
            Terminal.warning("p<0;给出的范围应该在0-100");
            p=0;
        }
        if (p>100){
            Terminal.warning("p>100;给出的范围应该在0-100");
            p%=100;
        }
        progress=lineLength/100f*p;
        M= (int) (progress+marginLeft);
        return (int) progress;
    }
    public Color getBaseLineColor() {
        return baseLineColor;
    }
    public void setBaseLineColor(Color baseLineColor){
        this.baseLineColor=baseLineColor;
    }

    public Color getProgressLineColor() {
        return progressLineColor;
    }
    public void setProgressLineColor(Color progressLineColor){
        this.progressLineColor=progressLineColor;
    }
    public int getLineLength(){
        return this.lineLength;
    }
    public void setLineLength(int lineLength){
       this.lineLength=lineLength;
       // 需要更新数据
        this.marginLeft=-1;
        this.marginTop=-1;
       cal();
    }
    public float getLineWidth(){
        return this.lineWidth;
    }
    public void setLineWidth(float lineWidth){
        this.lineWidth=lineWidth;
        LoadingStyle.stroke=new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        // 更新数据
        marginTop=-1;
        marginLeft=-1;
        cal();
    }
    public void setMarginLeft(int marginLeft){
       this.marginLeft=marginLeft;
    }
    public void setMarginTop(int marginTop){
       this.marginTop=marginTop;
    }
    public Font getFont(){
        return this.font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    public void setAlertWidth(int alertWeight){
        this.alertWidth=alertWeight;
    }
    public void setAlertHeight(int alertHeight){
        this.alertHeight=alertHeight;
    }
    public int getAlertHeight(){
        return this.alertHeight;
    }
    public int getAlertWidth(){
        return this.alertWidth;
    }
    public void setAlertTextColor(Color color){
        alertTextColor=color;
    }

    @Override
    public void setCompoent(Component parent) {
        super.setCompoent(parent);
        marginLeft=-1;
        marginTop=-1;
        cal();
    }
}
