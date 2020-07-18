package com.leeyf.queue;

import com.leeyf.tree.Node;

/**
 * 优先级队列
 */
public class PriorityQueue {
    private Node first; //优先级最高的元素
    private int length;
    transient int modCount; //被修改次数

    public PriorityQueue() {
        length = 0;
        first = null;
    }


}
