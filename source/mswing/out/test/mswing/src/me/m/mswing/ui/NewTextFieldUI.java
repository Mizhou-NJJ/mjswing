package me.m.mswing.ui;

import me.m.mswing.style.Style;
import me.m.mswing.widget.loading.LoadingStyle;

import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class NewTextFieldUI extends BasicTextFieldUI {
    public NewTextFieldUI(){

    }

    @Override
    protected void paintBackground(Graphics g) {
//        super.paintBackground(g);
        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        g2d.setColor(Style.MColor.PETER_RIVER);
        g2d.fillRoundRect(0,0,100,30,30,30);
    }

}
