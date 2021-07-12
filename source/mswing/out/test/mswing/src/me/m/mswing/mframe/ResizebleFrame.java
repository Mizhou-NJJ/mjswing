package me.m.mswing.mframe;

import me.m.mswing.prevate.Resizeble;

import javax.swing.*;
import java.awt.*;
public class ResizebleFrame extends JFrame{
    protected String title;
    protected Image image;
    protected Container container;
    private boolean isResizeble=true;
    public ResizebleFrame(){
        super();
    }
    public ResizebleFrame(String title,int width,int height){
        setSize(width,height);
        this.title=title;
        init();
    }
    public ResizebleFrame(String title,int width,int height,Image image){
        this.image=image;
        this.title=title;
        setSize(width,height);
        init();
    }
    protected void init(){
        Resizeble resizeble=new Resizeble(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(resizeble);
        addMouseMotionListener(resizeble);
        setUndecorated(true);
        container=getContentPane();
//        AWTUtilities.setWindowOpaque(this,false);
    }

    public boolean isResizeble() {
        return isResizeble;
    }

    /**
     * 是否固定大小
     * @param isResizeble
     */
    public void isResizable(boolean isResizeble) {
        this.isResizeble=isResizeble;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
