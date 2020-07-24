package com.leeyf.tree;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * 二叉搜索树
 */
public class BinaryTree {
    private TreeNode root;//根节点

    public BinaryTree() {
        root = null;
    }

    //根据关键字查找节点
    public TreeNode find(int key) {
        TreeNode cur = root;
        if (cur == null) {
            return null;
        }
        while (cur.val != key) {
            if (key < cur.val) {
                cur = cur.left; //如果关键字比当前节点小，转向左节点
            } else {
                cur = cur.right;
            }
            if (cur == null) {
                //搜索结束，没有结果
                return null;
            }
        }

        return cur;
    }

    //插入新节点
    public void insert(TreeNode node) {
        if (root == null) {
            root = node;
        } else {
            TreeNode cur = root;
            while (true) {
                if (node.val < cur.val) {
                    if (cur.left == null) {
                        //找到要插入节点的父节点
                        cur.left = node;
                        return;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = node;
                        return;
                    }
                    cur = cur.right;
                }
            }
        }
    }


    //删除指定节点
    public boolean delete(TreeNode node) {
        if (root == null) {
            return false;
        }
        boolean isLeftChild = true;//记录目标节点是否为父节点的左子节点
        TreeNode cur = root; //要删除节点
        TreeNode parent = null;//要删除节点的父节点

        while (cur.val!=node.val){
            //确定要删除节点和他的父节点
            parent = cur;
            if(node.val<cur.val){
                cur = cur.left;
            }else {
                isLeftChild = false;
                cur = cur.right;
            }
            if(cur==null){
                return false;//没有找到要删除节点
            }
        }
        if(cur.left == null&&cur.right==null) {
            //目标为叶子节点（无子节点）
            if (cur == root) {//要删除的为根节点
                root = null;
            } else if (isLeftChild) {
                //要删除的不是根节点，则该节点肯定有父节点，该节点删除后，需要将父节点指向它的引用置空
                parent.left = null;
            } else {
                parent.right = null;
            }
        }else if(cur.left==null){//只有又节点
            if(cur==root){
                root = cur.right;
            }else if(isLeftChild){
                parent.left = cur.right;
            }else {
                parent.right = cur.right;
            }
        }else if(cur.right==null){
            if(cur==root){
                root = cur.left;
            }else if(isLeftChild){
                parent.left = cur.left;
            }else {
                parent.right = cur.left;
            }
        }else {//有两个子节点
            //首先找到要删除节点的后继节点
            TreeNode successor = cur.right;
            TreeNode successorParent = null;
            while (successor.left!=null){
                successorParent = successor;
                successor = successorParent.left;
            }
            //欲删除节点的右子节点就是它的后继，证明该后继无左子节点，则将以后继节点为根的子树上移即可
            if(successorParent == null){
                if(cur==root){
                    root = successor;
                    root.left = cur.left;
                }else if(isLeftChild){
                    parent.left =successor;
                    successor.left = cur.left;
                }else {
                    parent.right = successor;
                    successor.left = cur.left;
                }
            }else {//欲删除节点的后继不是它的右子节点
                successorParent.left = successor.right;
                successor.right = cur.right;
                if(cur==root){
                    root =successor;
                    root.left = successor.left;
                }else if(isLeftChild){
                    parent.left = successor;
                    successor.left = cur.left;
                }else {
                    parent.right = successor;
                    successor.left = cur.left;
                }
            }
        }
        return true;
    }

    public static final int PREORDER = 1;   //前序遍历
    public static final int INORDER = 2;    //中序遍历
    public static final int POSTORDER = 3;  //中序遍历
    //遍历
    public void traverse(int type){
        switch (type){
            case 1:
                System.out.println("前序遍历：");
                preorder(root);
                break;

            case 2:
                System.out.println("中序遍历：");
                inorder(root);
                break;
            case 3:
                System.out.println("后序遍历");
                postorder(root);
                break;
        }
    }
    //前序遍历,根 左 右
    public void preorder(TreeNode node){
        if(node != null){
            System.out.println(node.val);
            preorder(node.left);
            preorder(node.right);
        }
    }

    //中序遍历 左 根 右
    public void inorder(TreeNode node){
        if(node!=null){
            inorder(node.left);
            System.out.println(node.val);
            inorder(node.right);
        }
    }

    //后序遍历 左 右 根
    public void postorder(TreeNode node){
        if(node!=null){
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.val);
        }
    }

    //获取左子树和右子树的最大深度，返回两者最大值
    private int getDepth(TreeNode node,int initDeep){
        int deep = initDeep;
        int leftDeep = initDeep;
        int rightDeep = initDeep;
        if(node.left!=null){
            leftDeep = getDepth(node.left,deep+1);
        }
        if(node.right!=null){
            rightDeep = getDepth(node.right,deep+1);
        }
        return Math.max(leftDeep,rightDeep);
    }

    //获取树的深度
    public int getTreeDepth(){
        if(root==null){
            return 0;
        }
        return getDepth(root,1);
    }

    //返回val最大的节点
    public TreeNode getMax(){
        if(root==null){
            return null;
        }
        TreeNode cur = root;
        while (cur.right!=null){
            cur = cur.right;
        }
        return cur;
    }
    //返回val最小的节点
    public TreeNode getMin(){
        if(root==null){
            return null;
        }
        TreeNode cur = root;
        while (cur.left!=null){
            cur = cur.left;
        }
        return cur;
    }

    //以树的形式打印出该树
    public void displayTree(){
        int depth = getTreeDepth();
        ArrayList<TreeNode> treeNodeArrayList = new ArrayList<>();
        treeNodeArrayList.add(root);
        int layerIndex = 1;
        while (layerIndex<=depth){
            int nodeBlankNum = (int)Math.pow(2,depth-layerIndex+1);//在节点之前和之后应该打印几个空位
            for (int i = 0; i < treeNodeArrayList.size(); i++) {
                TreeNode node = treeNodeArrayList.get(i);
                printBlank(nodeBlankNum);

                if(node==null){
                    System.out.print("\t");//如果该节点为null，用空位代替
                }else {
                    System.out.print("* "+node.val+"\t"); //打印该节点
                }
                printBlank(nodeBlankNum);  //打印节点之后的空位
                System.out.print("*\t");   //补齐空位
            }
            System.out.println();
            layerIndex++;
            treeNodeArrayList = getAllNodeOfThisLayer(treeNodeArrayList);
        }
    }

    private ArrayList<TreeNode> getAllNodeOfThisLayer(ArrayList<TreeNode> treeNodeArrayList) {
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode parentNode;
        for (int i = 0; i < treeNodeArrayList.size(); i++) {
            parentNode = treeNodeArrayList.get(i);
            if(parentNode !=null){
                if(parentNode.left!=null){
                    list.add(parentNode.left);
                }else {
                    list.add(null);
                }

                if(parentNode.right!=null){
                    list.add(parentNode.right);
                }else {
                    list.add(null);
                }
            }else {
                list.add(null);
                list.add(null);
            }

        }
        return list;
    }

    private void printBlank(int nodeBlankNum) {
        for (int i = 0; i < nodeBlankNum; i++) {
            System.out.print("\t");
        }
    }

    //判空
    public boolean isEmpty(){
        return (root == null);
    }
    //判断是否为叶子节点
    public boolean isLeaf(TreeNode node){
        return (node.left != null || node.right != null);
    }

    //获取根节点

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 1 24 7 35 68
     * 478 1 5386
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int i, int i1, int[] in, int i2, int i3) {
        if(i>i1|| i2>i3){
            return null;
        }
        TreeNode root = new TreeNode(pre[i]);
        int index = 0;
        for (int j = i2; j <i3 ; j++) {
            if(in[j]==pre[i]){
                root.left= reConstructBinaryTree(pre,i+1,j-1,in,i2,j-1);
                root.right= reConstructBinaryTree(pre,j+1,i1,in,j+1,i3);
                break;
            }
        }

        return root;
    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8},new int[]{4,7,8 ,1 ,5,3,8,6});

    }
}

