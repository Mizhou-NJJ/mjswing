package me.m.mswing.widget.table;

import javax.swing.*;
import java.awt.*;

public class MJTable extends JTable {
    public MJTable(){
        super();
    }
    public MJTable(int row,int col){
        super(row,col);
        setCellSelectionEnabled(true);
//        setShowGrid(false);
        setBorder(BorderFactory.createMatteBorder(-2,-2,4,4,new ImageIcon("res/g.jpg")));
        setEnabled(false);
        setShowVerticalLines(false);
//        setSelectionBackground(Style.MColor.PETER_RIVER);
//        setBackground(Style.MColor.EMERALD);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
