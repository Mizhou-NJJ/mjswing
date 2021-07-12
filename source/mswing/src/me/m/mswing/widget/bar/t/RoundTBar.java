package me.m.mswing.widget.bar.t;

import me.m.mswing.style.Style;
import me.m.mswing.widget.panel.RoundPanel;

import java.awt.*;

public class RoundTBar extends TBar {
    private float radius=20;
    public RoundTBar() {
        init();
    }

    public RoundTBar(String title,float radius) {
        super(title);
        this.radius=radius;
        init();
    }

    public RoundTBar(String title, Image image,float radius) {
        super(title, image);
        this.radius=radius;
        init();
    }
    private void init(){
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        RoundPanel roundPanel=new RoundPanel();
        roundPanel.setBackground(Style.MColor.WHITE);
        roundPanel.setBounds(0,0,400,30);
        add(roundPanel);
    }
}
