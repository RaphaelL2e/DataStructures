package com.leeyf.leetCode;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Solution724 {
    /**
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            StringBuilder s = new StringBuilder();
            s.append(s1.substring(i)).append(s1.substring(0, i));
            if (s.toString().equals(s2)) {
                return true;
            }
        }
        return s1.equals(s2);
    }

    public boolean isFlipedString2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        s1 += s1;
        return s1.contains(s2);
    }

    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode tmp = head;

       while (tmp!=null){
           int val = tmp.val;
           ListNode cur =tmp;
           while (cur.next!=null){
               if(cur.next.val==val){
                   cur.next = cur.next.next;
               }else {
                   cur =cur.next;
               }
           }
           tmp = tmp.next;
       }
       return head;
    }

    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode tmp = head;
        HashSet set = new HashSet();
        if(tmp==null)return head;
        set.add(tmp.val);
        while (tmp.next!=null){
            if(set.add(tmp.next.val)){
                tmp = tmp.next;
            }else {
                tmp.next = tmp.next.next;
            }
        }
        return head;
    }

    public int kthToLast(ListNode head, int k) {
        head=reverse(head);
        for (int i = 1; i < k; i++) {
            head = head.next;
        }
        return head.val;
    }

    /**
     * h = listNode;
     *   ListNode resultList = new ListNode(-1);
     *         resultList.next= listNode;
     *         ListNode p = listNode;
     *         ListNode pNext = p.next;
     *         while (pNext!=null){
     *             p.next = pNext.next;
     *             pNext.next = resultList.next;
     *             resultList.next = pNext;
     *             pNext=p.next;
     *         }
     *         return resultList.next;
     * @param h
     * @return
     */

    public ListNode reverse(ListNode h){
        ListNode pre = null;
        ListNode next = null;
        while (h != null) {
            next = h.next;
            h.next = pre;
            pre = h;
            h = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Solution724 s = new Solution724();
//        System.out.println(s.isFlipedString("waterbotle","erbottlewat"));
        ListNode a = new ListNode(1);
        ListNode a2 = new ListNode(2);
        a.next = a2;
        ListNode a3 = new ListNode(3);
        a2.next =a3;
        ListNode a4 = new ListNode(4);
        a3.next = a4;
        ListNode a5 = new ListNode(5);
        a4.next = a5;
        a5.next = new ListNode(6);
//        System.out.println(s.removeDuplicateNodes(a));
        System.out.println(s.reverse(a));
    }
}
