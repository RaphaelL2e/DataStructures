package com.leeyf.newcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

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
        int a=0 ,b=1;
        while (a<sum &&b<sum&& a<b){
            int sum2 = a;
            for (int i = a; i <=b ; i++) {
                sum2 +=b;
            }
            if(sum2<sum){
                b++;
            }else if(sum2>sum){
                a++;
            }else {
                ArrayList<Integer> list = new ArrayList();
                for (int i = a; i <=b ; i++) {
                    list.add(i);
                }
                ans.add(list);
                b++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        solution6.FindContinuousSequence(100);
    }
}