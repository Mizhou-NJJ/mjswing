package me.m.mswing.widget.button;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MJRadioButton extends JButton implements ActionListener {
    public final static int TOP=0;
    public final static int LEFT=1;
    public final static int BOTTOM=2;
    public final static int RIGHT=3;
    protected Component parent;
    protected  String text;
    protected  Rectangle rectangle;
    protected FontMetrics fm;
    protected int tdp=6;// 文字与按钮的距离
    protected Stroke stroke=new BasicStroke(0.5f);
    protected int width; //这个width 是指圆的宽度
    protected int height; //同上
    protected int x; // 圆与父控件的距离 不包括文字
    protected int y;//同上
    protected int gap=3; // 外园与小圆之间的间隙
    protected int textDirection=RIGHT; // 文字位置
    protected Color color= Style.MColor.FUEL_TOWN;
    protected Font font;
    protected Color fontColor= Style.MColor.FUEL_TOWN;
    protected boolean isChecked=true;
    protected Object value;
    protected int sizeX;
    protected int sizeY;
    public MJRadioButton(String text, Component parent){
        super();
       this.text=text;
       this.parent=parent;
       this.width=16;
       this.height=16;
       this.x=0;
       this.y=0;
       addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        g2d.setStroke(stroke);

        if (font!=null){
            g2d.setFont(font);
        }
        if (rectangle==null){
            rectangle=g2d.getClip().getBounds();
        }
        g2d.setPaint(parent.getBackground());
        g2d.fillRect(0,0,(int)rectangle.getWidth(),(int)rectangle.getHeight());
//        g2d.clearRect(0,0,(int)rectangle.getWidth(),(int)rectangle.getHeight());
        fm=g2d.getFontMetrics();
        int fh=fm.getHeight();
        int fw=fm.stringWidth(text);
        switch (textDirection){
            case TOP:
                y=fh/2+height;
                x=fw/2;
                break;
            case BOTTOM:
                y=0;
                x=fw/2;
                break;
            case LEFT:
                y=2;
                x=fw+width;
                break;
            case RIGHT:
                y=2;
                x=0;
                break;
        }
        /*
        * outter
        * */
        g2d.setColor(color);
        g2d.drawOval(x,y,width,width);
        /*
        * draw inner
        * */
        if (isChecked) {
            g2d.fillArc(x + gap, y + gap, width - 2 * gap, height - 2 * gap , 0, 360);
        }else {
            g2d.clearRect(x+gap,y+gap,width-2*gap,height-2*gap );
        }
        // draw text


        g2d.setColor(fontColor);
        switch (textDirection){
            case TOP:
                g2d.drawString(text,x+width/2-fw/2,y+fh/2-tdp);
                break;
            case BOTTOM:
                g2d.drawString(text,x+width/2-fw/2,tdp+y+fh);
                break;
            case LEFT:
                g2d.drawString(text,1,y+fh/3+height/2);
                break;
            case RIGHT:
                g2d.drawString(text,x+tdp+width,y+fh/3+height/2);
                break;

        }
        g2d.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        isChecked=!isChecked;
//        parent.repaint();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

//    @Override
//    public int getWidth() {
//        return width;
//    }

//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    @Override
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }

//    @Override
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    @Override
//    public int getY() {
//        return y;
//    }

    /**
     * 设置单选按钮的 宽和高
     * @param w
     * @param h
     */
    public void setRBSize(int w,int h){
        this.width=w;
        this.height=h;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getTdp() {
        return tdp;
    }

    /**
     * 设置按钮与文字间的距离,默认为 6
     * @param tdp
     */
    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public int getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(int textDirection) {
        this.textDirection = textDirection;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Font getFont() {
        return super.getFont();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.font = font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
