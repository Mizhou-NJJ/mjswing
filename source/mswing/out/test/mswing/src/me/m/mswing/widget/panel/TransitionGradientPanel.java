package me.m.mswing.widget.panel;

import java.awt.*;

public class TransitionGradientPanel extends GradientPanel{
    private int million=5000;
    private int gap=2;
    private int sleep=50;
    public TransitionGradientPanel(){
        super();
        start();
    }
    public TransitionGradientPanel(int width,int height){
       super(width,height);
       start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
    private void start(){
//        int size = million/ sleep== 0 ? 1 : million/ sleep;
        int r=Math.abs(color1.getRed()-color2.getRed());
        int g=Math.abs(color1.getRed()-color2.getRed());
        int b=Math.abs(color1.getRed()-color2.getRed());
        int rgMax=r>g?r:g;
        int size=b>rgMax?b:rgMax;
        int Rs[] = parseRGB(color1.getRed(), color2.getRed(), size);
        int Gs[] = parseRGB(color1.getGreen(), color2.getGreen(), size);
        int Bs[] = parseRGB(color1.getBlue(), color2.getBlue(), size);
        sleep=million/size==0?1:million/size;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int off=size/10==0?1:size/10;
                boolean t=true;
               while (true){

                   if (t) {
                       color2 = new Color(Rs[off], Gs[off], Bs[off]);
                       ++off;
                       if (off>=size-1) t=!t;
                   }else{
                      color2=new Color(Rs[off],Gs[off],Bs[off]);
                      --off;
//                      color2=new Color(Rs[off],Gs[off],Bs[off]);
                      if (off<=(size/10==0?1:size/10)){
                          t=!t;
                      }
                   }
                   try {
                       Thread.sleep(sleep);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   repaint();
               }
            }
        }).start();
    }
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
        return rgbs;
    }

    public void setMillion(int million) {
        this.million = million;
    }

    public int getMillion() {
        return million;
    }
}
