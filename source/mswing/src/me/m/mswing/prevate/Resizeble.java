package me.m.mswing.prevate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 如果希望Frame可以改变大小，可以试试{@code Resizeble}
 */
public class Resizeble implements MouseListener , MouseMotionListener {
    private Frame frame;
    private int barH=20;
    private int barN=5;

    private int dragX;
    private int dragY;
    private boolean isPressed=false;
    private boolean ns=false;
    private boolean ss=false;
    private boolean es=false;
    private boolean ws=false;
    public Resizeble(Frame frame){
        this.frame=frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX()>barN&&e.getY()>=barN&&e.getY()<=barH){
            dragX=e.getX();
            dragY=e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();
            int bxo = e.getXOnScreen();
            int byo = e.getYOnScreen();
            /*
            * change pos
            * */
            if (y>barN&&y<=barH) {
                frame.setLocation(bxo-dragX, byo-dragY);

            }
            /*
            * Zoom in the west
            * x>h-barN
            * lx=f.x
            * ly=f.y
            * w=bxo-frame.
            * */
            else if(x>=frame.getWidth()-barN){
              frame.setSize(bxo-frame.getX(),frame.getHeight());
            }
            /*
            * Zoom in the south
            * */
            else if(y>=frame.getHeight()-barN){
                frame.setSize(frame.getWidth(),byo-frame.getY());
            }
//            /*
//            * Zoom in east
//            * */
//            else if (y<=barN){
//                frame.setSize(frame.getWidth()-bxo,frame.getHeight());
//            }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int xx=e.getXOnScreen();
        int yy=e.getYOnScreen();
        if (x <= barN) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (x >= frame.getWidth() - barN) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        } else if (y <= barN) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else if (y >= frame.getHeight() - barN) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
//        if (y>barN&&y<barH){
//            frame.setLocation(xx,yy);
//        }
    }
}
