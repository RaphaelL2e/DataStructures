package com.leeyf.tree;

/**
 * 霍夫曼树的节点
 */
public class Node {
    private String key; //树节点存储的关键字，如果是非叶子节点为空
    private int frequency; //关键字词频
    private Node left;//左子节点
    private Node right;//右子节点
    private Node next;//优先级队列中指向下一个节点的引用

    public Node(int frequency,String key) {
        this.frequency = frequency;
        this.key = key;
    }

    public Node(int frequency) {
        this.frequency = frequency;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

