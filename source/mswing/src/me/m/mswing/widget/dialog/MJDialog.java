package me.m.mswing.widget.dialog;

import com.sun.awt.AWTUtilities;
import me.m.mswing.style.Style;
import me.m.mswing.widget.button.MJButton;
import me.m.mswing.widget.shadow.EnvisageShadow;
import me.m.mswing.widget.shadow.MShadow;
import me.m.mswing.widget.shadow.SquintShadow;
import javax.swing.*;
import java.awt.*;

public class MJDialog extends JDialog{
    private Container container;
    protected Component parent;
    private int mjWidth=200;
    private int model=0;
    private boolean isShowShadow=true;
    private int mjHeight=200;
    private int sw=15;
    private int radius=10;
    private MShadow mShadow;
    public MJDialog(Component parent){
       super();
       this.parent=parent;
    }
    public MJDialog(Frame owner,Component parent,MShadow mShadow){
       super(owner);
       this.parent=parent;
       this.mShadow=mShadow;
       init();
    }
    public MJDialog(Frame owner,Component parent,MShadow mShadow,int w,int h){
        super(owner);
        this.mShadow=mShadow;
        this.parent=parent;
        this.mjWidth=w;
        this.mjHeight=h;
    }
    private void init(){
        this.container=getContentPane();
        setDefaultCloseOperation(MJDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getRootPane());
        setUndecorated(true);
        setSize(mjWidth,mjHeight);
        AWTUtilities.setWindowOpaque(this,false);
        setLayout(null);
        //add   JC-------------##########################################
        JPanel main=new JPanel();
        main.setBackground(Style.MColor.EMERALD);
        main.setLayout(null);
//        main.setSize(200,200);
        if (mShadow==null) {
            main.setBounds(0, 0, mjWidth, mjHeight);
        }else if(mShadow instanceof SquintShadow){
            SquintShadow ss= (SquintShadow) mShadow;
            int r=ss.getR();
            int b=ss.getB();
            /*
            * 加上阴影的宽度为 mjWidth和高度mjHeight
            * 所以需要-去阴影的高度和宽度
            * */
            main.setBounds(0,0,mjWidth-r,mjHeight-b);
        }else if(mShadow instanceof EnvisageShadow){
            EnvisageShadow es= (EnvisageShadow) mShadow;
            int t=es.getT();//top
            int b=es.getB();//bottom
            int l=es.getL();//left
            int r=es.getR();//right
            main.setBounds(l,t,mjWidth-l-r,mjHeight-t-b);
        }
        /*Bar*/
        JPanel bar=new JPanel();
        bar.setLayout(null);
        bar.setBounds(0,0,main.getWidth(),30);
        bar.setBackground(Style.MColor.WHITE);
        MJButton closeB=new MJButton("x",bar);
//        closeB.setBackground(Style.MColor.LIGHT_RED);
//        closeB.setForeground(Style.MColor.WHITE);
        closeB.transition(Style.MColor.RED_ORANGE, Style.MColor.WHITE);
        closeB.setBorderRadius(30);
        closeB.setBounds(bar.getWidth()-30,0,30,30);
        closeB.addActionListener((e)->{
            this.dispose();
        });
        bar.add(closeB);

        main.add(bar);
        container.add(main);
    }
    @Override
    public void paint(Graphics g) {
//        Graphics2D g2d= LoadingStyle.optimizeGraphics(g);
        super.paint(g);
        Graphics2D g2d= (Graphics2D) g;
//        g2d.setColor(Style.MColor.WHITE);
//        g2d.fillRect(0,0,190,190);
        // 阴影
        if (mShadow!=null) {
            mShadow.paint(g);
        }
        g2d.setColor(Style.MColor.PETER_RIVER);
        g2d.dispose();
    }

    public int getMjHeight() {
        return mjHeight;
    }

    public int getMjWidth() {
        return mjWidth;
    }

    public void setMjHeight(int mjHeight) {
        this.mjHeight = mjHeight;
    }

    public void setMjWidth(int mjWidth) {
        this.mjWidth = mjWidth;
    }
}
