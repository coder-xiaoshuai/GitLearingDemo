package com.example.lib_java_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class AlgorithmTest {

    public static void main(String[] args) {
//        System.out.println(Sum_Solution2(4));

        System.out.println(maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3));


//        ListNode l1 = new ListNode(1);
//        ListNode l11 = new ListNode(8);
//        l1.next = l11;
//        ListNode l2 = new ListNode(0);
//
//        addTwoNumbers(l1, l2);

        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 算法题：剪绳子
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 主要考动态规划
     */
    public static int cutRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int sum = 1;
        while (n > 4) {
            n = n - 3;
            sum = sum * 3;
        }
        return sum * n;
    }

    /**
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null) {
            return null;
        }
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int sum = 1;
            for (int i1 = 0; i1 < A.length; i1++) {
                if (i != i1) {
                    sum *= A[i1];
                }
            }
            result[i] = sum;
        }
        return result;
    }

    /**
     * 待定 总觉的有问题
     *
     * @param A
     * @return
     */
    public int[] multiply2(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            //计算下三角连乘
            for (int i = 1; i < B.length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            //B[n]就会在第一个循环的时候，计算好，而把B[0]则在下一个循环的时候计算好。
            int temp = 1;
            //计算上三角
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }


    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
     * 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            boolean addSuccess = set.add(numbers[i]);
            if (!addSuccess) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。  注意这青蛙也可以跳上n级 所以并不是斐波那契数列
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        int result = 2;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        return 2 * JumpFloorII(target - 1);
    }

    /**
     * 青蛙问题不是斐波那契数列 注意！！！！！！
     * 通项公式
     *
     * @param target
     * @return
     */
    public int JumpFloorII2(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        return (int) ((Math.pow((1 + Math.sqrt(5)) / 2, target) - Math.pow((1 - Math.sqrt(5)) / 2, target)) / Math.sqrt(5));
    }

    /**
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     *
     * @param string
     */
    public void FirstAppearingOnce(String string) {

    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）
     * 形成树的一条路径，最长路径的长度为树的深度。
     *
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = TreeDepth(root.left);
        int maxRight = TreeDepth(root.right);

        return Math.max(maxLeft, maxRight) + 1;
    }

    /**
     * 判断是否是平成二叉树
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int maxLeft = TreeDepth(root.left);
        int maxRight = TreeDepth(root.right);
        if (Math.abs(maxLeft - maxRight) <= 1) {
            return true;
        }
        return false;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     *
     * @param array
     * @param k
     * @return
     */
    public int getAppearCountOfK(int[] array, int k) {
        int count = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == k) {
                count++;
                if ((i + 1) < length && array[i + 1] != k) {
                    break;
                }
            }
        }
        return count;
    }

    public String ReverseSentence(String str) {
        int len = str.length();
        String str_rever = "";
        str = " " + str;
        Stack<Character> stack = new Stack<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != ' ') {
                stack.push(str.charAt(i));
            } else {
                while (!stack.isEmpty()) {
                    str_rever += stack.pop();
                }
                str_rever += " ";
            }
        }


        return str_rever.substring(0, len);
    }

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int sum = (int) (Math.pow(n, 2) + n);
        return sum >> 1;
    }

    /**
     * 采用递归，利用逻辑与的短路特性
     *
     * @param n
     * @return
     */
    public static int Sum_Solution2(int n) {
        int ans = n;
        boolean flag = ans > 0 && (ans += Sum_Solution2(n - 1)) > 0;
        return ans;
    }

    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
     * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || size == 0 || size > num.length) {
            return result;
        }
        int resultSize = num.length - size + 1;
        for (int i = 0; i < resultSize; i++) {
            int max = num[i];
            for (int j = i; j < i + size; j++) {
                if (num[j] > max) {
                    max = num[j];
                }
            }
            result.add(max);
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int size) {

        if (nums == null || size == 0 || size > nums.length) {
            return new int[]{};
        }
        int resultSize = nums.length - size + 1;
        int[] result = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            int max = nums[i];
            for (int j = i; j < i + size; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
     * 并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int temp = target - nums[i];
            for (int j = i + 1; j < length; j++) {
                if (temp == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode header = null;
        int temp = 0;
        int jinwei = 0;
        while (l1 != null && l2 != null) {
            temp = l1.val + l2.val + jinwei;
            jinwei = 0;
            //临时节点
            ListNode tempNode = new ListNode(0);
            if (header == null) {
                header = tempNode;
                result = header;
            } else {
                header.next = tempNode;
                header = tempNode;
            }
            if (temp >= 10) {
                header.val = temp - 10;
                jinwei = 1;
            } else {
                header.val = temp;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        int jinwei2 = 0;
        while (l1 != null) {
            //临时节点
            if (header.next == null) {
                header.next = new ListNode(0);
                header = header.next;
            }
            int temp1 = l1.val + jinwei + jinwei2;
            jinwei2 = 0;
            jinwei = 0;
            if (temp1 >= 10) {
                jinwei2 = 1;
            }
            header.val = temp1 >= 10 ? temp1 - 10 : temp1;
            l1 = l1.next;
        }

        while (l2 != null) {
            if (header.next == null) {
                header.next = new ListNode(0);
                header = header.next;
            }
            int temp1 = l2.val + jinwei + jinwei2;
            jinwei2 = 0;
            jinwei = 0;
            if (temp1 >= 10) {
                jinwei2 = 1;
            }
            header.val = temp1 >= 10 ? temp1 - 10 : temp1;
            l2 = l2.next;
        }
        if (jinwei != 0) {
            if (header.next == null) {
                header.next = new ListNode(0);
                header = header.next;
            }
            header.val = jinwei;
        }

        if (jinwei2 != 0) {
            if (header.next == null) {
                header.next = new ListNode(0);
                header = header.next;
            }
            header.val = jinwei2;
        }
        return result;
    }


    /**
     * 网上实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        boolean liHasNext = true;
        boolean l2HasNext = true;

        int sum = 0;
        ListNode listNode = new ListNode(0);
        ListNode endlistNode = new ListNode(0);
        listNode.next = endlistNode;
        while (liHasNext || l2HasNext || sum > 0) {
            int val = 0;
            int va2 = 0;
            if (liHasNext) {
                val = l1.val;
                l1 = l1.next;
                if (l1 == null) {
                    liHasNext = false;
                }
            } else {
                liHasNext = false;
            }
            if (l2HasNext) {
                va2 = l2.val;
                l2 = l2.next;
                if (l2 == null) {
                    l2HasNext = false;
                }
            } else {
                l2HasNext = false;
            }

            sum += val + va2;
            ListNode listNode1 = new ListNode(sum % 10);
            endlistNode.next = listNode1;
            endlistNode = listNode1;
            sum /= 10;
        }
        return listNode.next.next;
    }


    /**
     * 网上实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        Set<Character> set = new HashSet();
        int length = s.length();
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            set.clear();
            for (int j = i; j < length; j++) {
                if (set.add(s.charAt(j))) {
                    if (set.size() > result) {
                        result = set.size();
                    }
                }else {
                    break;
                }
            }
        }
        return result;
    }
}
