package me.m.mswing.widget.button;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class MJButton extends JButton implements ActionListener ,  MouseListener {
    public final static int TOP_TO_BOTTOM=0;
    public final static int BOTTOM_TO_TOP=1;
    public final static int LEFT_TO_RIGHT=2;
    public final static int RIGHT_TO_LEFT=3;
    public final static int LEFT_TOP_TO_RIGHT_BOTTOM=4;
    public final static int RIGHT_BOTTOM_TO_LEFT_TOP=5;
    private static int uptime=30;
    private final static short BACKGROUND_COLOR=0;
    private final static short TEXT_COLOR=1;
    private final static short BORDER_COLOR=2;
    private Component parent;
    private String text;
    private Rectangle rectangle;
    private int rectangleWidth;
    private int rectangleHeight;
    private Font font;
    private Color fontColor;
    private Color bgColor= Style.MColor.PETER_RIVER;
    private int fontHeight;
    private int fontsWidth;
    private int borderRadius=0;
    private Color borderColor= Style.MColor.PETER_RIVER;
    private Paint borderPaint;
    private FontMetrics fontMetrics;
    private float borderBold=0;
    private Stroke stroke;
    private Paint bgPaint;
    /*
     * M 的事件
     */
    Queue<Thread> threadQueue=new LinkedList<>();
    /*           ------------------------------*/
    private Color from=bgColor;
    private Color to= Style.MColor.WHITE;
    private boolean isTransition=false;
    private Color fontTempColor=fontColor;
    private Color bgTempColor=bgColor;
    public MJButton (Component parent){
        super();
        this.parent=parent;
       init();
    }
    public MJButton(String name,Component parent){
        super(name);
        this.parent=parent;
        this.text=name;
        init();
    }


    @Override
    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawRoundRect();
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        if (stroke!=null){
            if (!g2d.getStroke().equals(stroke)){
                g2d.setStroke(stroke);
            }
        }
        if (fontMetrics==null){
            fontMetrics=g2d.getFontMetrics();
        }
        if (font==null){
            font=g2d.getFont();
        }
        if (!g2d.getFont().equals(font)){
            g2d.setFont(font);
            fontMetrics=g2d.getFontMetrics();
        }
        if (rectangle==null){
            rectangle=g2d.getClip().getBounds();
            rectangleWidth= (int) rectangle.getWidth();
            rectangleHeight= (int) rectangle.getHeight();
        }
        fontsWidth=fontMetrics.stringWidth(text);
        fontHeight=fontMetrics.getHeight();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

//        g2d.setColor(Style.MColor.PETER_RIVER);
        /*
        * 渐变
        * */
        if (bgPaint!=null){
            g2d.setPaint(bgPaint);
        }else{
            g2d.setColor(bgColor);
        }
        g2d.fillRoundRect((int) borderBold, (int) borderBold, (int) (rectangle.getWidth()-borderBold), (int) (rectangle.getHeight()-borderBold),borderRadius,borderRadius);
        /*
        * draw border
        * */
        if (borderBold>0) {
            if (borderPaint!=null){
                g2d.setPaint(borderPaint);
            }else{
                g2d.setColor(borderColor);
            }
            g2d.drawRoundRect((int)borderBold/2, (int)borderBold/2, (int) (rectangle.getWidth() - borderBold-1), (int) (rectangle.getHeight() - borderBold-1), borderRadius, borderRadius);
        }
        /*
        * draw text
        * */
        g2d.setColor(fontColor);
        g2d.drawString(this.text,(rectangleWidth-fontsWidth)/2+1,rectangleHeight/2+fontHeight/3-1);
        g2d.dispose();
    }
    private void init(){
        addActionListener(this);
        addMouseListener(this);
        Rectangle b = getBounds();
    }

    public float getBorderBold() {
        return borderBold;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderBold(float borderBold) {
        this.borderBold = borderBold;
        this.stroke=new BasicStroke(borderBold);
    }
    public void setStroke(Stroke stroke){
        this.stroke=stroke;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * 自定义边框的Paint方式
     * @param borderPaint
     */
    public void setBorderPaint(Paint borderPaint) {
        this.borderPaint = borderPaint;
    }

    /**
     * 这个方法用来设置渐变的边框颜色
     * 边框的颜色会从{@code from}过渡到{@code to}
     * @param from 开始颜色
     * @param to 终点颜色
     * @param direction 渐变方向
     */
    public void setBorderPaint(Color from,Color to,int direction){
        switch (direction){
            case MJButton.TOP_TO_BOTTOM:
                borderPaint=new GradientPaint(0,0,from,0,rectangleHeight,to);
                break;
            case MJButton.BOTTOM_TO_TOP:
                borderPaint=new GradientPaint(0,0,to,0,rectangleHeight,from);
                break;
            case MJButton.LEFT_TO_RIGHT:
                borderPaint=new GradientPaint(0,0,from,rectangleWidth,0,to);
                break;
            case MJButton.RIGHT_TO_LEFT:
                borderPaint=new GradientPaint(0,0,to,rectangleWidth,0,from);
                break;
            case MJButton.LEFT_TOP_TO_RIGHT_BOTTOM:
                borderPaint=new GradientPaint(0,0,from,rectangleWidth,rectangleHeight,to);
                break;
            case MJButton.RIGHT_BOTTOM_TO_LEFT_TOP:
                borderPaint=new GradientPaint(0,0,to,rectangleWidth,rectangleHeight,from);
                break;
        }
    }

    /**
     * 自定义背景paint方式
     * @param bgPaint
     */
    public void setBgPaint(Paint bgPaint) {
        this.bgPaint = bgPaint;
    }

    /**
     * 用来设置背景的渐变颜色
     * @param from 开始颜色
     * @param to 终点颜色
     * @param direction 渐变方向
     */
    public void setBgPaint(Color from,Color to,int direction){
        switch (direction){
            case MJButton.TOP_TO_BOTTOM:
                bgPaint=new GradientPaint(0,0,from,0,rectangleHeight,to);
                break;
            case MJButton.BOTTOM_TO_TOP:
                bgPaint=new GradientPaint(0,0,to,0,rectangleHeight,from);
                break;
            case MJButton.LEFT_TO_RIGHT:
                bgPaint=new GradientPaint(0,0,from,rectangleWidth,0,to);
                break;
            case MJButton.RIGHT_TO_LEFT:
                bgPaint=new GradientPaint(0,0,to,rectangleWidth,0,from);
                break;
            case MJButton.LEFT_TOP_TO_RIGHT_BOTTOM:
                bgPaint=new GradientPaint(0,0,from,rectangleWidth,rectangleHeight,to);
                break;
            case MJButton.RIGHT_BOTTOM_TO_LEFT_TOP:
               bgPaint=new GradientPaint(0,0,to,rectangleWidth,rectangleHeight,from);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // pass
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setForeground(Color fg) {
        this.fontColor=fg;
        fontTempColor=fontColor;
        super.setForeground(fg);
    }

    @Override
    public Color getForeground() {
        return fontColor;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        this.bgColor=bg;
        this.bgTempColor=bg;
    }

    /**
     * 设置按钮先对于父控件的位置，与自身宽高
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        rectangle=null;
        super.setBounds(x, y, width, height);
    }

    /**
     * 设置按钮边框的弧度
     * 如果该按钮是一个正方形,则{@code borderRadius}=该按钮的边长时，
     * 该按钮会被渲染成圆形
     * @param borderRadius
     */
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * 以过渡方式设置背景颜色,它会从控件现在的颜色在{@code ms}毫米内
     * 过渡至{@code toColor},比较适合在动态改变背景时使用,如按钮onHover时
     * @param toColor 最终颜色
     * @param ms 过渡时间 以毫秒(ms)为单位
     */
    public void transitionBgColorTo(Color toColor,int ms){
        transition(toColor,ms,BACKGROUND_COLOR);
    }

    /**
     * 过渡按钮颜色,与{@link MJButton#transitionTextColorTo(Color, int)}描述相同
     * @param toColor
     * @param ms
     */
    public void transitionTextColorTo(Color toColor,int ms){
       transition(toColor,ms,TEXT_COLOR);
    }

    /**
     * 过渡边框颜色，与{@link MJButton#transitionTextColorTo(Color, int)}描述相同
     * @param toColor
     * @param ms
     */
    public void transitionBorderColorTo(Color toColor,int ms){
        transition(toColor,ms,BORDER_COLOR);
    }
    private void transition(Color toColor,int ms,int tWhat){
        /*
        *  如果过渡时间为 t毫秒 ,每隔uptime刷新一次(绘制一种颜色)
        *  那么要绘制size次
        * */
            int size = ms / uptime == 0 ? 1 : ms / uptime;
            int Rs[] = parseRGB(fontColor.getRed(), toColor.getRed(), size);
            int Gs[] = parseRGB(fontColor.getGreen(), toColor.getGreen(), size);
            int Bs[] = parseRGB(fontColor.getBlue(), toColor.getBlue(), size);
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    int off=0;
                    while (off < size){
                        try {
                            Thread.sleep(size);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        switch (tWhat) {
                            case BACKGROUND_COLOR:
                                bgColor = new Color(Rs[off], Gs[off], Bs[off]);
//                                if (isExited){
//                                    bgColor=new Color(Rs[size-1],Gs[size-1],Bs[size-1]);
//                                    parent.repaint();
//                                    break;
//                                }
                                break;
                            case TEXT_COLOR:
                                fontColor = new Color(Rs[off], Gs[off], Bs[off]);
                                break;
                            case BORDER_COLOR:
                                borderColor = new Color(Rs[off], Gs[off], Bs[off]);
                                break;
                        }
                        parent.repaint();
                        off++;
                    }
                }
            });
            t.start();
            threadQueue.offer(t);
    }

  /*
  * 该方法返回一个合适大小的R、G、B、数组
  * */
    private int[] parseRGB(int fromRGB,int toRGB,int size){
        int rgbs[]=new int[size];
        int gap=Math.abs(fromRGB-toRGB)/size;
        if (gap==0) gap=1;
        if (fromRGB>toRGB){
           for (short i=0;i<size;i++){
               rgbs[i]=(fromRGB-i*gap)<0?0:(fromRGB-i*gap);
           }
        }else{
           for (int i=0;i<size;i++){
              rgbs[i]=fromRGB+i*gap>255?255:fromRGB+i*gap;
           }
        }
        // 确保最后的颜色正确
        rgbs[size-1]=toRGB;
        return rgbs;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @SuppressWarnings("deprecation")
    @Override
    public void mouseEntered(MouseEvent e) {
        if (isTransition) {
            for (Thread t : threadQueue) {
                t.stop();
//            threadQueue.poll();
            }
            threadQueue.clear();
            transitionBgColorTo(to, 500);
            transitionTextColorTo(from, 500);
            borderColor = from;
            borderBold = 1;
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public void mouseExited(MouseEvent e) {
        if (isTransition) {
            for (Thread t : threadQueue) {
                t.stop();
            }
            threadQueue.clear();
            transitionBgColorTo(from, 500);
            transitionTextColorTo(to, 500);
            bgColor = Style.MColor.PETER_RIVER;
            fontColor = Style.MColor.WHITE;
            borderBold = 0f;
            parent.repaint();
        }
    }

    public boolean isTransition() {
        return isTransition;
    }

    /**
     * 单鼠标进入按钮时，过渡从{@code from}过渡到{@code to}
     * 需要注意的时按钮开始背景颜色会由{@code from}所替换,
     * 开始字体颜色由{@code to}所替换
     * @param from
     * @param to
     */
    public void transition(Color from,Color  to){
        this.isTransition=true;
        this.from=from;
        this.bgColor=from;
        this.fontColor=to;
        this.to=to;
    }
}
