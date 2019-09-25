package com.leeyf.queue;


import java.util.Scanner;

/**
 * 数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key;//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(and)：添加数据到队列");
            System.out.println("g(get)：从队列读取数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQuene();
                        System.out.printf("取出数据是%d\n", res);
                    } catch (Exception e) {
                        //TODO：handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头部是%d\n", res);
                    } catch (Exception e) {
                        //TODO：handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayQueue {
    private int maxSize; //数组最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr;//该数组存放数据,模拟队列

    //创建队列的构造器
    ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部的前一位
        rear = -1; //指向队列尾部
    }

    //判断队列是否满
    private boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    private boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    int getQuene() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++; //front后移
        return arr[front];
    }

    //显示队列的所有数据
    void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头部数据(部署取出)
    int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无数据");
        }
        return arr[front + 1];
    }

}

