package com.leeyf.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        /*
         * 将二维数组转换成稀疏数组
         */
        //创建原始的二维数组（五子棋盘）
        //0 表示没有值 1表示黑子 2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 2;
        //输出原始二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转成稀疏数组
        //1.先遍历得到有效数据个数
        int sum = 0; //有效数据个数
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零值存入sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];

                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组：");
        for (int[] a : sparseArr) {
            for (int b : a) {
                System.out.printf("%d\t", b);
            }
            System.out.println();
        }

        //创建文件
        File file = new File("/home/leeyf/my.data");

        try {
            //文件写入
            FileOutputStream fileInputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileInputStream, "GBK");
            for (int[] a : sparseArr) {
                for (int b : a) {
                    outputStreamWriter.write(b + "\t");
                }
                outputStreamWriter.write("\n");
                outputStreamWriter.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[][] sparseArr2 = new int[sum + 1][3];
        try {
            //文件读取
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            for (int i = 0; i < sparseArr2.length; i++) {
                String s = bufferedReader.readLine();
                String[] s1 = s.split("\t");
                for (int j = 0; j < s1.length; j++) {
                    sparseArr2[i][j] = Integer.parseInt(s1[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("得到的稀疏数组2：");
        for (int[] a : sparseArr2) {
            for (int b : a) {
                System.out.printf("%d\t", b);
            }
            System.out.println();
        }
        /*
         *将稀疏数组转换为二维数组
         */
        //读取稀疏数组第一行，创建二维数组
        System.out.println(sparseArr[0][0]);
        System.out.println(sparseArr[0][1]);
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];

        }
        System.out.println("转换后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
