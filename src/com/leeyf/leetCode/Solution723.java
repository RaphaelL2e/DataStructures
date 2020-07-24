package com.leeyf.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Solution723 {
    /**
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * <p>
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * <p>
     * 回文串不一定是字典当中的单词。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        int num = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 2) {
                num++;
            } else {
                if (entry.getValue() % 2 != 0) {
                    num++;
                }
            }
        }
        return num == 1 || num == 0;
    }

    /**
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int f = 0, s = 0;
        int a = first.length() - second.length();
        if (a > 1 | a < -1) {
            return false;
        }
        boolean flag = false;

        for (; f < first.length() && s < second.length(); f++, s++) {

            if (first.charAt(f) == second.charAt(s)) {

                continue;
            } else if (flag) {
                return false;
            }
            if (a == 1) {
                s--;
            } else if (a == -1) {
                f--;
            }
            flag = true;

        }
        return true;
    }

    /**
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/compress-string-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param S
     * @return
     */
    public String compressString(String S) {
        char[] chars = S.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        if (S.length() < 2) {
            return S;
        }
        int num = 1;
        for (int i = 1; i < chars.length; i++) {

            if (chars[i - 1] == chars[i]) {
                num++;
            } else {
                stringBuilder.append(chars[i - 1]);
                stringBuilder.append(num);
                num = 1;
            }
        }
        stringBuilder.append(chars[chars.length - 1]).append(num);
        if (stringBuilder.toString().length() >= S.length()) {
            return S;
        } else {
            return stringBuilder.toString();
        }
    }

    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * <p>
     * 不占用额外内存空间能否做到？
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        //先交换xy坐标
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i > j) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
        }
        //每行数组逆序
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Solution723 s = new Solution723();
//        System.out.println(s.canPermutePalindrome("tactcoa"));
//        System.out.println(s.oneEditAway("teacher",
//                "eacher"));
        System.out.println(s.compressString("abbccd"));

    }
}
