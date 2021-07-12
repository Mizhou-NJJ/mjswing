package me.m.mswing.widget.loading;

import java.awt.*;

public abstract class UpLoadingStyle extends LoadingStyle {
    protected float progress;
    protected int progressPer;
    public UpLoadingStyle(){}
    public UpLoadingStyle(Component c){
        super(c);
    }
    public  abstract int updateProgress(int p);
    float getProgress(){
        return this.progress;
    }
}
