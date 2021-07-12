package me.m.mswing.widget.bar.t;

import me.m.mswing.style.Style;

import javax.swing.*;
import java.awt.*;

public abstract class TBar extends JPanel{
    /**
     * 标题位置 {@code CENTER}
     */
    public final static int CENTER=0;
    public final static int LEFT=1;
    protected String title;
    protected Image image;
    protected Color bg= Style.MColor.WHITE;
    protected int tpos=CENTER;
    protected Rectangle rectangle;
    public TBar(){

    }
    public TBar(String title){
        this.title=title;
    }
    public TBar(String title,Image image){
        this.title=title;
        this.image=image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
