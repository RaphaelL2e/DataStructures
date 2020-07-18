package com.leeyf.myarraylist;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
    //初始化容器容量
    private static final int DEFAULT_CAPACITY = 10;
    //当前项数
    private int theSize;
    //基础数组
    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    //清空链表
    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    //返回当前项数
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    //缩减容器大小
    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public T set(int index, T newVal) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        //如果新设置的大小比当前容器大小小，则不执行
        if (newCapacity < size()) {
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity]; //泛型数组的创建是非法的.这里创建一个泛型类型界限的数组,然后使用一个数组进行类型转换来实现.
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(T x) {

        add(size(), x);
        return true;
    }

    public void add(int index, T x) {
        //如果链表已满,则扩容
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        //从最后一项开始到index，后移一位
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = x;
        theSize++;
    }

    public T remove(int index) {
        T removeItem = theItems[index];
        //从index到最后一项，前移一位
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }

    public java.util.Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    private class ArrayListIterator implements java.util.Iterator<T> {
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

}
