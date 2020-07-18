package com.leeyf.ccf;

import java.util.Scanner;

public class Main {
    int M = 0,N=0;
    int T =0,k=0;
    int d=0;int c=0;
    String mn;
    String tree;
    public void aVoid(){
        Scanner scanner = new Scanner(System.in);
        mn= scanner.nextLine();
        String[] s= mn.split(" ");
        N =Integer.parseInt(s[0]);
        M =Integer.parseInt(s[1]);
        for (int i = 0; i < N; i++) {
           int P=0;
           d = scanner.nextInt();
           T +=d;
           tree=scanner.nextLine();
           String[] ss = tree.split(" ");
            for (int j = 1; j <=M; j++) {
                d = Integer.parseInt(ss[j]);
                T +=d;
                P -=d;
            }
            if(P>c){
                c =P;
                k =i+1;
            }
        }
        System.out.println(T+" "+k+" "+c);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.aVoid();
    }
}
