package com.leeyf.linkedlist;

import java.util.jar.JarEntry;

/**
 * 单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创建几个节点
        HeroNode heroNode1 = new HeroNode(1, "a", "A");
        HeroNode heroNode2 = new HeroNode(2, "b", "B");
        HeroNode heroNode3 = new HeroNode(3, "c", "C");
        HeroNode heroNode4 = new HeroNode(4, "d", "D");
        //初始化链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        //singleLinkedList.add(heroNode1);
        //singleLinkedList.add(heroNode3);
        //singleLinkedList.add(heroNode4);
        //singleLinkedList.add(heroNode2);

        //按照编号加入
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();
        //HeroNode newHeroNode = new HeroNode(1, "B", "b");
        //singleLinkedList.update(newHeroNode);
        //singleLinkedList.list();
        //singleLinkedList.delete(1);
        //singleLinkedList.delete(1);
        //singleLinkedList.delete(2);
        //singleLinkedList.delete(3);
        //singleLinkedList.delete(4);
        //singleLinkedList.list();
        //求单链表有效节点个数
        //System.out.println(getLength(singleLinkedList.getHead()));
        //求倒数第一个节点
        //System.out.println(getHeroNode(singleLinkedList.getHead(),1));
        //单链表的反转
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

    }

    //方法：获取单链表的节点的个数（如果带头节点，需要不统计头节点）

    /**
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表倒数第k个节点

    /**
     * 既求整个链表第 (节点数-k +1)个节点
     *
     * @param head  头节点
     * @param index k值
     * @return 返回第k个节点, 未找到返回空
     */
    public static HeroNode getHeroNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //获取有效节点个数
        int length = getLength(head);
        //index数据校验
        if (index <= 0 || index > length) {
            return null;
        }
        //定义一个辅助变量
        HeroNode temp = head;
        for (int i = 0; i < length - index + 1; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //将单链表进项反转

    /**
     * 将单链表进项反转
     *
     * @param head 头节点
     */
    public static void reversetList(HeroNode head) {
        //如果链表为空,无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义辅助指针
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点的下一个节点
        HeroNode newHead = new HeroNode(0, "", "");
        //遍历原来的列表，每遍历一个节点将其放在心链表的前端
        while (cur != null) {
            next = cur.next; //暂时保存当前节点的下一个节点
            cur.next = newHead.next; //将cur的下一个节点指向新链表的最前端
            newHead.next = cur; //将cur连接到新的链表上
            cur = next; //cur后移

        }
        //将新的链表的头指针更换成之前的头指针
        head.next = newHead.next;
    }

}

//创建单链表，定义singleLinkedList
class SingleLinkedList {
    //先初始化一个头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到链表(无序添加)
     */
    public void add(HeroNode heroNode) {
        //辅助节点
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                break;
                //如果没有找到最后，将temp后移
            } else {
                temp = temp.next;
            }
        }

        //当退出while时，指向链表的最后
        temp.next = heroNode;
    }

    /**
     * 添加节点到链表(有序添加)
     **/
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此通过辅助指针来帮助找到添加的位置
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //说明temp已经在链表的最后
            if (temp.next == null) {
                break;
            }
            //位置找到，就在temp的后面插入
            if (temp.next.getNo() > heroNode.getNo()) {
                break;
                //说明要添加的编号已经存在
            } else if (temp.next.getNo() == heroNode.getNo()) {
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.getNo());
        } else {
            //插入链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点(根据编号修改)
     */
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false; //是否找到该节点
        while (true) {
            if (temp == null) {
                break; //链表尾
            }
            if (temp.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.setName(newHeroNode.getName());
            temp.setNickname(newHeroNode.getNickname());
        } else {
            System.out.printf("未找到编号为%d的节点,不能修改\n", newHeroNode.getNo());
        }

    }

    /**
     * 删除节点(根据编号删除)
     */
    void delete(int heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.getNo() == heroNode) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到编号为%d的节点,不能删除\n", heroNode);
        }
    }

    /**
     * 显示链表
     */
    void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("----------------------");
    }
}

//定义一个HeroNode,每个HeroNode对象 就是一个节点
class HeroNode {
    private int no;
    private String name;
    private String nickname;
    HeroNode next; //指向下一个节点

    //构造器
    HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为显示方便重新写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public HeroNode() {
    }

    int getNo() {
        return no;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNickname(String nickname) {
        this.nickname = nickname;
    }


    String getName() {
        return name;
    }

    String getNickname() {
        return nickname;
    }
}
