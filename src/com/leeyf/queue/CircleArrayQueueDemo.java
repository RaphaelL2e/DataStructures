package com.leeyf.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列案例！");
        int arrLength = 3;
        CircleArrayQueue arrayQueue = new CircleArrayQueue(arrLength+1);
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
                        int res = arrayQueue.getQueue();
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
    }}

    //环形队列
    class CircleArrayQueue {
        private int maxSize; //数组最大容量
        private int front; //队列头
        private int rear; //队列尾
        private int[] arr;//该数组存放数据,模拟队列

        CircleArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            front = 0;
            rear = 0;
            arr = new int[maxSize];
        }

        //判断队列是否满
        private boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断是否为空
        private boolean isEmpty() {
            return rear == front;
        }

        //添加数据添加到队列
        void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列满，不能添加数据");
                return;
            }
            arr[rear] = n;
            //rear后移
            rear = (rear + 1) % maxSize;
        }

        //出队列
        int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            //TODO
            int value = front;
            //front后移
            front = (front +1 % maxSize);
            return arr[value];
        }

        //显示队列的所有数据
        void showQueue() {
            if (isEmpty()) {
                System.out.println("队列空的，没有数据");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            return arr[front];
        }

        //有效数据的个数
        private int size() {
            return (rear + maxSize - front) % maxSize;
        }
    }

