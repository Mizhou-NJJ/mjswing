package me.m.mswing.widget.shadow;

import me.m.mswing.style.Style;

import java.awt.*;

/**
 * {@code SquintShadow}只会显示下边和右边的阴影,这会看起来像是斜视
 * 并且两边阴影的程度是相同的
 * 值得注意的是<strong>绘制阴影时不改变控件的宽和高，有些时候肯能无法显示下阴影,应为你的
 * {@code Rectangle}的宽高可能和你的控件的宽高一样</strong>
 */
public class SquintShadow extends MShadow {
    private int r=10;
    private int b=10;
    private Color rstart= Style.MColor.START_SHADOW;
    private Color rend= Style.MColor.END_SHADOW;
    private Color bstart= Style.MColor.START_SHADOW;
    private Color bend= Style.MColor.END_SHADOW;
    private GradientPaint Rp;
    private GradientPaint Bp;
    private int [] rxs;
    private int [] rys;
    private int [] bxs;
    private int [] bys;
    public  SquintShadow(int x,int y,int w,int h) {
        super(x,y,w,h);
        Rp=new GradientPaint(w+x,y,rstart,r+w,y,rend);
        Bp=new GradientPaint(x,y+h,bstart,x,y+h+b,bend);
        /*
        *  共4个点
        * */
        rxs=new int[]{x+w,x+w+r,x+w+r,x+w};
        rys=new int[]{y+r/2,y+r/2,y+h+r,y+h};
        bxs=new int[]{x+b,x+w,x+w+b,x+b};
        bys=new int[]{y+h,y+h,y+h+b,y+h+b};

    }
    @Override
    public void paint(Graphics g) {
       Graphics2D g2d= (Graphics2D) g;
       /*
       * right
       * */
       g2d.setPaint(Rp);
       g2d.fillPolygon(rxs,rys,4);
//       g2d.fillRect(x+w,y+r/2,r,h-r/2);
       /*
       * bottom
       * */
       g2d.setPaint(Bp);
       g2d.fillPolygon(bxs,bys,4);
//       g2d.fillRect(x+b,y+h,w-b,h);
       g2d.dispose();
    }

    public int getR() {
        return r;
    }

    public int getB() {
        return b;
    }

    /**
     * 设置有右边阴影的偏移量
     * @param r
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * 设置下阴影的偏移量
     * @param b
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * 设置右边阴影开始处的颜色
     * @param rstart
     */
    public void setRstart(Color rstart) {
        this.rstart = rstart;
    }

    /**
     * 设置右边阴影结束的颜色
     * @param rend
     */
    public void setRend(Color rend) {
        this.rend = rend;
    }

    /**
     * 设置下阴影结束颜色
     * @param bend
     */
    public void setBend(Color bend) {
        this.bend = bend;
    }

    /**
     * 设置下阴影开始颜色
     * @param bstart
     */
    public void setBstart(Color bstart) {
        this.bstart = bstart;
    }
}
