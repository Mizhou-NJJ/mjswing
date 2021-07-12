package me.m.mswing.widget.loading;


import me.m.mswing.style.Style;

import java.awt.*;

public class TikNote extends LoadingStyle{
    /**
     *  @author Mizhou
     *  抖动的音符形状
     */
    private int maxHeight;
    private int minHeight;
    private int noteCount;
    private int noteWidth;
    private int arcWidth;
    private int arcHeight;
    private Color [] noteColors;
    private int eachHs[];
    private boolean [] isHeight;
    private int [] marginTops;
    private int [] marginLefts;
    private int marginTop=-1;
    private int marginLeft=-1;
    private Color [] randomColor={Style.MColor.LIGHT_RED, Style.MColor.PETER_RIVER, Style.MColor.NEON_BLUE, Style.MColor.BRIGHT_LILAC, Style.MColor.TURQUOISE,
            Style.MColor.EMERALD, Style.MColor.RADIAN_YELLOW, Style.MColor.LIGHT_PURPLE};
    public TikNote(){
        /**
         *  默认的构造器
         *  会构造一个拥有<strong>6</strong>个符的TikNote
         *  其jk中每个符的 BorderRaduis为10
         *  符宽为<strong>10</strong>
         *  每个符会在<strong>60</strong>至<strong>30</strong>之间波动
         */
        noteCount=6;
        arcHeight=10;
        arcWidth=10;
        noteWidth=10;
//        if(Style.MFrame.height>=10){
//            maxHeight= Style.MFrame.height/6;
//            minHeight=Style.MFrame.height/10;
//        }else{
//            maxHeight=60;
//            minHeight=30;
//        }
        maxHeight=60;
        minHeight=30;
        cal();
    }
    public TikNote(int maxHeight,int minHeight,int noteWidth,int noteCount,Component p){
        super(p);
        this.maxHeight=maxHeight;
        this.minHeight=minHeight;
        this.noteWidth=noteWidth;
        this.noteCount=noteCount;
        cal();
    }
    /**
     * @param maxHeight 符可以到达的最高度
     * @param minHeight 符的最低高度
     * @param noteWidth 符的宽度
     * @param noteCount 符的树梨
     * @param marginTop 距离容器上边距
     * @param marginLeft 距离容器右边距
     */
    public TikNote(int maxHeight,int minHeight,int noteWidth,int noteCount,int marginTop,int margintLeft){
        this.maxHeight=maxHeight;
        this.minHeight=minHeight;
        this.noteCount=noteCount;
        this.noteWidth=noteWidth;
        cal();
    }
    private void cal(){
        /**
         * 自动适配所给出的参数
         */
        width=noteCount*noteWidth*2; //整个TikNote所占宽度
        height=maxHeight; // 整个 TikNote所在 高度
        if(marginTop==-1&&marginLeft==-1) {
            if (parent == null) {
                /*
                 * 如果未给出父组件，则会调用MFrame被初始化时的宽高
                 * 若未给出MFrame的宽高,则利用默认值
                 * */
                if (Style.MFrame.width > 0) {
                    marginLeft = (Style.MFrame.width - width) / 2;
                    marginTop = (Style.MFrame.height - height) / 2;
                } else {
                    marginLeft = 0;
                    marginTop = maxHeight - minHeight; //确保完整显示在屏幕上
                }
            } else {
                int w = parent.getWidth();
                int h = parent.getHeight();
                marginLeft = (w - width) / 2;
                marginTop = (h - height) / 2;
            }
        }
        marginLefts=new int[noteCount];
        marginTops=new int[noteCount];
        isHeight=new boolean[noteCount];
        eachHs=new int[noteCount];
        int gap=(maxHeight-minHeight)/noteCount;
        for(int i=0;i<noteCount;i++){
            eachHs[i]=maxHeight-gap*i;
            marginTops[i]=marginTop+gap/2*i;
            isHeight[i]=eachHs[i]<maxHeight;
            marginLefts[i]=marginLeft+i*noteWidth*2;
        }
        /*
        * 如果未给出颜色值,则取随机颜色
        * */
        if(noteColors==null){
            noteColors=new Color[noteCount];
            for(int i=0;i<noteCount;i++){
               noteColors[i]=randomColor[(int) (Math.random()*randomColor.length)];
            }
        }
    }

    @Override
    public void setCompoent(Component parent) {
        super.setCompoent(parent);
        cal();
    }

    public void marginLeft(int marL){
        marginLeft=marL;
        cal();
    }
    public void marginTop(int mtop){
        marginTop=mtop;
        cal();
    }
    public void setArcHeight(int arcH){
        arcHeight=arcH;
        cal();
    }
    public void setArcWidth(int arcW){
        arcWidth=arcW;
        cal();
    }
    public void setNoteWidth(int w){
        noteWidth=w;
        cal();
    }
    public void setMinHeight(int h) {
        minHeight=h;
        cal();
    }
    public void setMaxHeight(int h){
        maxHeight=h;
        cal();
    }
    public void setNoteCount(int noteCount){
        this.noteCount=noteCount;
        cal();
    }
    public void setNoteColors(Color [] colors){
        this.noteColors=colors;
    }
    public Color[] getNoteColors(){
        return noteColors;
    }
    @Override
    protected void paint(Graphics g) {
       Graphics2D g2d= (Graphics2D) g;
       if(g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING)==null){
           g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(short i=0;i<noteCount;i++){
           if(isHeight[i]){
              eachHs[i]+=2;
              marginTops[i]--;
              if(eachHs[i]>maxHeight){
                  isHeight[i]=false;
              }
           }else{
               eachHs[i]-=2;
               marginTops[i]++;
               if(eachHs[i]<=minHeight){
                   isHeight[i]=true;
               }
           }
           g2d.setColor(noteColors[i]);
           g2d.fillRoundRect(marginLefts[i],marginTops[i],noteWidth,eachHs[i],arcWidth,arcHeight);
       }
    }

    @Override
    public void show(Graphics g) {
       paint(g);
    }
}
