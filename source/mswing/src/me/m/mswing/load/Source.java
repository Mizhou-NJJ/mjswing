package me.m.mswing.load;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Source {
    private static Font font;
    public static Font tf_OpenTTF(float size,int style){
        if (font==null) {
            File f = new File("mswing1.0.0/res/jf-open.ttf");
            if (!f.exists()){
               f=new File("jf-open.ttf");
            }
            if (f.exists()) {
                try {
                    font = Font.createFont(Font.TRUETYPE_FONT, f);
                    font = font.deriveFont(style, size);
                } catch (FontFormatException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return font;
    }
}
