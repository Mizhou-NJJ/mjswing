package me.m.mswing.terminal;


public class Terminal {
    public static void warning(String str){
        System.out.println("\33[34;1mWarning:"+str);
    }
}
