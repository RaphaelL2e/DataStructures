package com.leeyf.newcoder;

import com.leeyf.tree.TreeNode;

import java.util.*;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class Solution {
    static int num =0;
    HashMap<Integer,Character> map = new HashMap();

    int index = 0; //答案的下标
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        map.put(num++,ch);
    }
  //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
      while (index<map.size()){
          char tmp = map.get(index);
          map.remove(index,tmp);
          if(!map.containsKey(tmp)) {
              map.put(index,tmp);
              return tmp;
          }else {
              map.put(index, tmp);
              index++;
          }
      }
      return '#';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.Insert('h');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('e');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('l');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('l');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('o');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('w');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('o');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('r');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('l');
        System.out.println(solution.FirstAppearingOnce());
        solution.Insert('d');
        System.out.println(solution.FirstAppearingOnce());

    }
}
 class Solution1 {
    ArrayList<Integer> list = new ArrayList();
    HashMap<Integer,Integer> map = new HashMap<>();
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        for (int i = 0; i < length; i++) {
            if(map.containsKey(numbers[i])){
                map.put(numbers[i],map.get(numbers[i])+1);
            }else {
                map.put(numbers[i],1);
            }
            list.add(numbers[i]);
            for(int num:list){
                if(map.get(num)>1){
                    duplication[0] = num;
                    return true;
                }
            }
        }
        return false;
    }


}
class Solution2 {
    ArrayList<Integer> list = new ArrayList();
    public int LastRemaining_Solution(int n, int m) {
        if(n==0){return -1;}
        //初始化
        for(int j = 0;j<n;j++){
            list.add(j,j);
        }
        int i = 0,j=m-1,r=n;
        for(int k = 0;k<r-1;k++){
            n = list.size();
            //位置i

            if(i+j<n){
                i +=j;

            }else{
                i = (i+j)%n;
            }
            if(list.get(i)!=null){
                list.remove(i);

            }
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.LastRemaining_Solution(5,3);
    }
}

class Solution3 {
    public boolean isContinuous(int [] numbers) {
        if(numbers==null) return false;
        //排序
        Arrays.sort(numbers);
        //计算0的个数
        int zero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i]==0){
                zero++;
            }
        }
        int tmp = zero;
        if(zero >3) return true;
        int num =0;
        //判断
        for (int j = zero;zero>0||j<numbers.length-1;j++){
            if(numbers[j]==numbers[j+1]){
                return false;
            }
            if(numbers[j]+zero+1>=numbers[j+1]){
                zero -= numbers[j+1] - numbers[j] -1;
            }else {
                return false;
            }
            num++;
        }

        if((num==0?5:num+1)+tmp==numbers.length-1){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution3 s      = new Solution3();
        s.isContinuous(new int[]{0,3,0,0,0});
    }
}

/**
 * 左旋转字符串
 */
class Solution4 {
    public String LeftRotateString(String str,int n) {
        char[] chars = str.toCharArray();
        if(str.length()==0){
            return "";
        }
        char[] ans = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
           ans[i] = chars[(i+n+str.length())%str.length()];
        }
        return String.copyValueOf(ans);
    }

    public String LeftRotateString2(String str,int n) {
        char[] chars = str.toCharArray();
        if(str.length()==0){
            return "";
        }
        char[] ans = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char tmp = chars[i];
            int s= (i+n+str.length())%str.length();
            ans[i] = chars[s];
            chars[s] =tmp;


        }
        return String.copyValueOf(ans);
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.LeftRotateString2("abcd",2));
    }
}
 class Solution5 {
     /**
      * 和为S的两个数字
      * @param array
      * @param sum
      * @return
      */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int left =0,right = array.length-1;
        ArrayList<Integer> arrayList = new ArrayList();
        int min = Integer.MAX_VALUE;
        while (left<right){

            if(array[left]+array[right]==sum){
                if(min !=Math.min(array[left]*array[right],min )){
                    min =Math.min(array[left]*array[right],min);
                    arrayList.clear();
                    arrayList.add(array[left++]);
                    arrayList.add(array[right--]);
                }else {
                    if(array[left]+array[right]>sum){
                        right--;
                    }else {
                        left++;
                    }
                }
            }else if(array[left]+array[right]>sum){
                right--;
            }else {
                left++;
            }

        }
        return arrayList;

    }

     public static void main(String[] args) {
         Solution5 solution5 = new Solution5();
         solution5.FindNumbersWithSum(new int[]{1,2,3,4,5,6,7},8);
     }
}

/**
 * 和为S的连续正数序列
 */
 class Solution6 {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int a=1 ,b=2;
       if (sum<=a){
           return ans;
       }
       while (a<sum || b<sum && a<b){
           //a-b的和
           int sum2 = 0;
           for (int i = a; i <=b ; i++) {
               sum2 += i;

           }
           if(sum2==sum){
               ArrayList<Integer> list = new ArrayList();
               for (int i = a; i <=b ; i++) {
                   list.add(i);
               }
               ans.add(list);
               a++;
           }else if (sum2<sum){
               b++;
           }else {
               a++;
           }
       }

        return ans;
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        solution6.FindContinuousSequence(3);
    }
}

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
class Solution7 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:array){
            if(!map.containsKey(num) ){
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }
        for (Map.Entry<Integer,Integer> kv:map.entrySet()){
            if(kv.getValue()==1 &&num1[0] ==0){
                num1[0] = kv.getKey();
            }else if(kv.getValue()==1 &&num2[0] ==0){
                num2[0] = kv.getKey();
            }
        }

    }
}

 class Solution8 {
    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root)!=-1;
    }

    int depth(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftDepth = depth(node.left);
        if(leftDepth==-1) return -1;
        int rightDepth = depth(node.right);
        if(rightDepth == -1)return -1;
        if(leftDepth-rightDepth<(-1)||leftDepth-rightDepth>1){
            return -1;
        }else {
            return 1+(Math.max(leftDepth, rightDepth));
        }
    }

}

class Solution9 {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public int GetNumberOfK(int [] array , int k) {
        int ans = 0;
        for (int num :array){
            if(k==num){
                ans++;
            }
        }
        return ans;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 ==null || pHead2 == null){
            return null;
        }
        ListNode tmp1 = pHead1;

        while (tmp1!=null){
            ListNode tmp2 = pHead2;
           while (tmp2!=null){
               if(tmp1.val == tmp2.val){
                   return tmp1;
               }else {
                   tmp2 = tmp2.next;
               }
           }
           tmp1 =tmp1.next;
        }
        return null;
    }

    /**
     * 例子如n=1234，high=1, pow=1000, last=234
     *
     * 可以将数字范围分成两部分1~999和1000~1234
     *
     * 1~999这个范围1的个数是f(pow-1)
     * 1000~1234这个范围1的个数需要分为两部分：
     * 千分位是1的个数：千分位为1的个数刚好就是234+1(last+1)，注意，这儿只看千分位，不看其他位
     * 其他位是1的个数：即是234中出现1的个数，为f(last)
     * 所以全部加起来是f(pow-1) + last + 1 + f(last);
     *
     * 例子如3234，high=3, pow=1000, last=234
     *
     * 可以将数字范围分成两部分1~999，1000~1999，2000~2999和3000~3234
     *
     * 1~999这个范围1的个数是f(pow-1)
     * 1000~1999这个范围1的个数需要分为两部分：
     * 千分位是1的个数：千分位为1的个数刚好就是pow，注意，这儿只看千分位，不看其他位
     * 其他位是1的个数：即是999中出现1的个数，为f(pow-1)
     * 2000~2999这个范围1的个数是f(pow-1)
     * 3000~3234这个范围1的个数是f(last)
     * 所以全部加起来是pow + high*f(pow-1) + f(last);
     *
     * 作者：xujunyi
     * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/javadi-gui-by-xujunyi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        return helper(n);
    }

    //计算每个数的n
    int helper(int n){
        if(n<=0){
            return 0;
        }
        String s = String.valueOf(n);
        int high = s.charAt(0) -'0'; //首位
        int pow = (int) Math.pow(10,s.length()-1); //比n小的10的幂次
        int last = n - high*pow;//其余位数
        if(high==1){

            return helper(pow-1) +last+1+helper(last);
        }else {
            return pow + high*helper(pow-1)+helper(last);
        }

    }


    public  static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(solution9.NumberOf1Between1AndN_Solution(100));
    }
}

class Solution10 {
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] p = new int[array.length];
        p[0] = 0;
        int ret = array[0];
        for (int i = 1; i < array.length; i++) {
            p[i] = Math.max(array[i-1],p[i-1]+array[i-1]);
            ret = Math.max(ret, p[i]);
        }
        return p[array.length-1];
    }

    public static void main(String[] args) {
        System.out.println('9'-'1');
    }
}
