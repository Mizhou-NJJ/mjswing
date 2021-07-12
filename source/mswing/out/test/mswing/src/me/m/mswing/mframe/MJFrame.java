package me.m.mswing.mframe;
import me.m.mswing.style.Style;
import me.m.mswing.load.Source;
import me.m.mswing.widget.loading.*;
import me.m.mswing.widget.panel.LoadingPanel;

import javax.swing.*;
import java.awt.*;

public class MJFrame extends JFrame {
    private Container container;
    private boolean th=true;
    private int innerX=0;
    private int innerY=0;
    public MJFrame (){
        super();
    }
    public MJFrame(String title,int width,int height){
        super(title);
        setSize(width,height);
        Style.MFrame.width=width;
        Style.MFrame.height=height;
        init();
    }

    public MJFrame(String title,String imgURL){
       super(title);
       this.setIconImage(new ImageIcon(imgURL).getImage());
    }
    public  MJFrame(String title,Image image){
        super(title);
        this.setIconImage(image);
    }
    public void mshow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
//        setResizable(true);
    }
    private void init(){
        container=getContentPane();
        container.setBackground(Style.MColor.WHITE);
        LoadingPanel loadingPanel =new LoadingPanel();
        loadingPanel.setBackground(Style.MColor.PETER_RIVER);
        UpLoadingStyle us = new LineMessageAlert();
        loadingPanel.setLoadingStyle(us);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<=100){
                    us.updateProgress(i++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        container.add(loadingPanel);
        loadingPanel.loading();

    }
//    @Override
//    public int getHeight() {
//        return height;
//    }
//
//    @Override
//    public int getWidth() {
//        return width;
//    };

}
