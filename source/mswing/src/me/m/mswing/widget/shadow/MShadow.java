package me.m.mswing.widget.shadow;

import java.awt.*;

public abstract class MShadow {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected MShadow(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    abstract public void paint(Graphics g);
}
