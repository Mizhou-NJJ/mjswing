package me.m.mswing.widget.shadow;

import me.m.mswing.style.Style;

import java.awt.*;

public class EnvisageShadow extends MShadow {
    private int r=10;
    private int t=10;
    private int b=10;
    private int l=10;
    private Color start=Style.MColor.START_SHADOW;
    private Color end= Style.MColor.END_SHADOW;
    public EnvisageShadow(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    @Override
    public void paint(Graphics g) {
    }
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public Color getStart() {
        return start;
    }

    public void setStart(Color start) {
        this.start = start;
    }

    public Color getEnd() {
        return end;
    }

    public void setEnd(Color end) {
        this.end = end;
    }
}
