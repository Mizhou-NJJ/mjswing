package me.m.mswing.widget.panel;

import javax.swing.*;
import java.awt.*;

public class ImageFragmentPanel extends JPanel {
    private Image image;
    private Component parent;
    private boolean isLoadComplex=false;
    private Thread disposeImage;
    public ImageFragmentPanel (ImageIcon icon,Component parent){
        this.parent=parent;
        image=icon.getImage().getScaledInstance(384,216,Image.SCALE_SMOOTH);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parent.repaint();
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image,0,0,50,50,0,0,50,50,null);
        //g2d.drawImage(image,0,0,200,200,0,0,2000,2000, Style.MColor.PETER_RIVER,null);
        g2d.dispose();
    }
}
