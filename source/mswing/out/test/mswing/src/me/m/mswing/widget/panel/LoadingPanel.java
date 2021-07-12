package me.m.mswing.widget.panel;


import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LineMessageAlert;
import me.m.mswing.widget.loading.LoadingStyle;
import me.m.mswing.widget.loading.TikNote;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {

    private LoadingStyle loadingStyle;
    private boolean loadingAccomplish=false;
    private long defaultMillis=20;
    private int width;
    private int height;
    /**
     *  以MFrame的宽和高构造
     */
    public LoadingPanel(){
        super();
        setSize(Style.MFrame.width,Style.MFrame.height);
        loadingStyle=new TikNote();
    }

    /**
     *  以宽高以及默认加载动画
     * @param width 此Panel 的宽
     * @param height 此Panel的高
     */
    public LoadingPanel(int width,int height){
        super();
        this.width=width;
        this.height=height;
        setSize(width,height);
        this.loadingStyle=new LineMessageAlert();
    }

    /**
     * 初始化一个 自定义宽、高、加载动画样式的 加载动画面板
     * @param width 此Panel的宽度
     * @param height 此Panel的高度
     * @param style 加载动画样式
     */
    public LoadingPanel(int width,int height,LoadingStyle style){
        super();
        this.width=width;
        this.height=height;
        this.loadingStyle=style;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }
    float ctrlX=180;
    float ctrlY=140;
    boolean isLeft=true;
    float ruand=10;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        loadingStyle.show(g);
        g.dispose();
////        loadingStyle.show(g,this);
//        Graphics2D g2d= (Graphics2D) g;
//        BasicStroke rundStroke=new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(Style.MColor.SHADOWED_STEEL);
//        g2d.setStroke(rundStroke);
//        g2d.drawLine(20,100,300,100);
//        int H=20;
//        int W=40;
//        int y=100-H-H/2;
//        int x=100;
//        int T=W/3;
//        int xs[]={x,x,x+T,x+2+T+T/2-1,x+2*T,x+W,x+W};
//        int ys[]={y,y+H,y+H,y+H+H/2,y+H,y+H,y};
//        g2d.setColor(Color.WHITE);
//        g2d.fillPolygon(xs,ys,7);


//        -----------------------
//        QuadCurve2D.Float f=new QuadCurve2D.Float();
//        f.setCurve(20,250,ctrlX,ctrlY,380,250);
//        if(isLeft){
//            ctrlY+=2;
//            if(ctrlY>=380) {
//                isLeft = false;
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }else{
//            ctrlY-=6;
//            if (ctrlY<=200) isLeft=true;
//        }
//        ctrlY++;
//        g2d.draw(f);
        //g2d.drawOval(200,200,100,100);
    }

    /**
     *
     * @return 返回加载动画对象
     */
    public LoadingStyle getLoadingStyle(){
        return loadingStyle;
    }
    private void init(){
        setLayout(null);
    }

    /**
     * 启动加载动画
     * @param millis 线程休眠时间，通过线程休眠时间控制速度
     */
    public void loading(long millis){
        this.loadingAccomplish=false;
//        TikNote tikNote= (TikNote) loadingStyle;
//        loadingStyle.setCompoent(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!loadingAccomplish){
                    try {
                        Thread.sleep(loadingStyle.getMillion());
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 启动加载动画,用默认速度加载
     */
    public void loading(){
        this.loading(defaultMillis);
    }
    public void loadingAccomplish(){
       this.loadingAccomplish=true;
    }
    public void setLoadingStyle(LoadingStyle loadingStyle){
        this.loadingStyle=loadingStyle;
    }

}
