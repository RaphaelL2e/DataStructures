package com.leeyf.stack;


/**
 * 初始化一个栈
 */
public class Stack<T> {
    private int size; //栈的大小
    private int top; //栈顶下标
    private Object[] stackArray;

    //构造函数
    public Stack(int size) {
        this.size = size;
        this.top = -1;
        this.stackArray = new Object[size];
    }
    //入栈，同时，栈顶元素下标加一
    public void push(T elem){
        stackArray[++top] = elem;
    }
    //出栈，删除栈顶元素，下标减一
    public Object pop(){
        return stackArray[top--];
    }
    //判断为空
    public boolean isEmpty(){
        return (top==-1);
    }

    public boolean isFull(){
        return (top == size-1);
    }
}
