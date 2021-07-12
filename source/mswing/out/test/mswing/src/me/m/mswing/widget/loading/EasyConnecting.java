package me.m.mswing.widget.loading;

import me.m.mswing.style.Style;

import java.awt.*;

/**
 * {@code EasyConnecting}是一个简单的LoadingStyle
 *
 */
public class EasyConnecting extends LoadingStyle {
    protected long million =200;
    public final static int NORTH=0;
    public final static int SOUTH=1;
    protected int radius;
    protected int diameter;
    protected int circleCount;
    /**
     * 如果{@code isShowText} 为{@code true} 则会显示默认的文字"<strong>LOADING</strong>"
     */
    protected String loadingText="LOADING";
    protected Color circleColor= Color.WHITE;
    protected Color loadingCircleColor=Style.MColor.AMERICAN_RIVER;
    protected Color textColor= Color.white;
    protected int textDirection=NORTH;
    protected int off=-1;
    protected int textDisLoading=20;
    /**
     * 默认是不显示文字的
     */
    protected boolean isShowText=false;
    protected Font font;

    /**
     * <strong>7</strong>个半径为<strong>5</strong>的EasyConnecting
     */
    public EasyConnecting(){
        radius=5;
        circleCount=7;
        cal();
    }

    /**
     * 构造由circleCount个半径为radius组成的EasyConnecting
     * @param radius 小圆的半径
     * @param circleCount 小圆数量
     * @param c 构造出的EasyConnecting 会显示在c中间 或者说此LoadingStyle要显示在哪个控件上
     */
    public EasyConnecting(int radius,int circleCount,Component c){
        super(c);
        this.radius=radius;
        this.circleCount=circleCount;
    }

    protected void cal(){
        /*
         *  如果未给出marginLeft 和 marginTop 即:marginLeft=-1 marginTop=-1时
         *  则利用parent计算
         *  若未给出 parent 则 利用MFrame计算
         * */
        this.diameter=2*radius;
        width=diameter*circleCount*2;
        height=diameter+textDisLoading;
        if(marginTop==-1&&marginLeft==-1) {
            // 若未给出 parent
            if (parent == null) {
                marginLeft = (Style.MFrame.width - width) / 2;
                marginTop = (int) ((Style.MFrame.height - diameter) / 2);
            } else {
                // 利用parent的宽高进行计算
                int w = parent.getWidth();
                int h = parent.getHeight();
                marginTop = (int) ((h - width) / 2);
                marginLeft = (w - diameter) / 2;
            }
        }
    }
    @Override
    protected void paint(Graphics g) {
        Graphics2D g2d=LoadingStyle.optimizeGraphics(g);
        g2d.setStroke(stroke);
        if(font!=null){
            if (!font.equals(g2d.getFont())){
               g2d.setFont(font);
            }
        }
        g2d.setColor(circleColor);
        for(int i=0;i<circleCount;i++){
            if(off==i){
                g2d.setColor(loadingCircleColor);
            }
            if (off==circleCount){
                off=-1;
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
    }

    /**
     * 此方法最好由Component对象的paint(Graphics g)调用
     * @param g
     */
    @Override
    public void show(Graphics g) {
        paint(g);
    }

    /**
     *
     * @return 此LoadingStyle的线程休眠时间
     */
    @Override
    public long getMillion() {
        return million;
    }

    /**
     *
     * @param million 设置此LoadingStyle的线程休眠时间 也就是LoadingStyle变化的速度
     */
    @Override
    public void setMillion(long million) {
        this.million = million;
    }

    public int getRadius() {
        return radius;
    }

    /**
     * 此方法用来设置小圆的半径 通过修改半径来改变大小
        cal();
     * @param radius 小圆的半径
     */
        public void setRadius(int radius) {
            this.radius = radius;
            marginLeft=-1;
            marginTop=-1;
    }

//    public int getDiameter() {
//        return diameter;
//    }
//
//    public void setDiameter(int diameter) {
//        this.diameter = diameter;
//    }

    public int getCircleCount() {
        return circleCount;
    }

    /**
     * 此方法设置 小圆的数量
     * @param circleCount
     */
    public void setCircleCount(int circleCount) {
        this.circleCount = circleCount;
        marginLeft=-1;
        marginTop=-1;
        cal();
    }

    public String getLoadingText() {
        return loadingText;
    }

    /**
     * 设置要显示的文字 替换默认文字
     * @param loadingText
     */
    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    public Color getCircleColor() {
        return circleColor;
    }

    /**
     * 设置小圆的颜色
     * @param circleColor
     */
    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }

    public Color getLoadingCircleColor() {
        return loadingCircleColor;
    }

    /**
     * 设置跳动的小圆的颜色
     * @param loadingCircleColor
     */
    public void setLoadingCircleColor(Color loadingCircleColor) {
        this.loadingCircleColor = loadingCircleColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    /**
     * 设置文字颜色
     * @param textColor
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public int getTextDirection() {
        return textDirection;
    }

    /**
     * 设置文字显示方向
     * @param textDirection 可选值为EasyConnecting.NORTH ,EasyConnecting.SOUTH
     */
    public void setTextDirection(int textDirection) {
        this.textDirection = textDirection;
    }

    public boolean isShowText() {
        return isShowText;
    }

    /**
     * 设置文字显示或则关闭显示
     * @param isShowText t
     *
     */
    public void isShowText(boolean isShowText){
        this.isShowText=isShowText;
    }

    /**
     * 设置Font 通过设置新的Font来该表字体属性例如:
     * <ul>
     *     <li>粗细</li>
     *     <li>大小</li>
     *     <li>字形等</li>
     * </ul>
     * @param font
     */
    public void setFont(Font font){
        this.font=font;
    }
    public Font getFont(){
        return font;
    }
}
