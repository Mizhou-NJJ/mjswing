package me.m;

import me.m.mswing.mframe.MJFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int  arr [] = new int[5];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入五个自然数:");
        for (int i=0;i<5;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println("其中偶数有:");
        for (int i=0;i<5;i++){
            if (arr[i]%2==0){
                System.out.print(arr[i]+" ");
            }
        }
        sc.close();
    }
}
