package com.example.lib_java_test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
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

//        System.out.println(lengthOfLongestSubstring("pwwkew"));

//        System.out.println(reverse(1534236469));

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        reverseList2(node1);
//        System.out.println(countPrimes(10));
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(wordBreak("leetcode", list));


        System.out.println(search(new int[]{2, 3, 4, 6, 7, 8, 9, 10}, 5));
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
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;
            res = res * 10 + temp;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int) res;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '[')
                stack.push(']');
            else if (c == '{')
                stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }

        return stack.isEmpty();
    }


    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (set.add(nums[i])) {
                if (index != i) {
                    int temp = nums[index];
                    nums[index] = nums[i];
                    nums[i] = temp;
                }
                index++;
            }
        }
        return set.size();
    }


    /**
     * 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1
     * @param l2
     * @return
     */
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        while (l1 != null && l2 != null) {
//            ListNode temp = new ListNode(0);
//            if (l1.val > l2.val){
//                temp = l1.next;
//
//            }
//        }
//    }


    /**
     * 翻转链表-- 迭代法
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        if (head == null) {
            return null;
        }
        //每一次循环都释放一个Node
        while (head != null) {
            //用来保存下一个node
            ListNode tempNext = head.next;
            head.next = preNode;
            //preNode其实就是最后翻转后链表的head
            preNode = head;
            //遍历head指向下一个Node
            head = tempNext;
        }
        return preNode;
    }


    /**
     * 递归法
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public int squareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * <p>
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * <p>
     * 解题思路：
     * 根据我们的探索，我们猜测会有以下三种可能。
     * <p>
     * 最终会得到 111。
     * 最终会进入循环。
     * 值会越来越大，最后接近无穷大。（这种情况通过分析不会出现）
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        ;
        return slow == 1;
    }


    public static boolean isZhiShu(int n) {
        if (n < 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }


    /**
     * 可以进行优化
     * <p>
     * <p>
     * 换句话说，i 不需要遍历到 n，而只需要到 sqrt(n) 即可。为什么呢，我们举个例子，假设 n = 12。
     * <p>
     * 12 = 2 × 6
     * 12 = 3 × 4
     * 12 = sqrt(12) × sqrt(12)
     * 12 = 4 × 3
     * 12 = 6 × 2
     * <p>
     * 作者：labuladong
     * 链接：https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    boolean isPrime2(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }

    public static int countPrimes(int n) {
        if (n < 0) {
            return 0;
        }
        int result = 0;
        for (int i = 2; i < n; i++) {
            if (isZhiShu(i)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 最优解法
     *
     * @param n
     * @return
     */
    private int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    /**
     * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
     * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
     * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        // 先判断一些特殊情况
        if (pattern.equals("a") || pattern.equals("b")) {
            return true;
        }
        if (pattern.length() == 0) {
            return value.length() == 0;
        }
        char[] pChars = pattern.toCharArray();
        char[] vChars = value.toCharArray();
        // value为空时，判断pattern是否只有a或只有b
        if (value.length() == 0) {
            boolean aExist = false;
            boolean bExist = false;
            for (char c : pChars) {
                if (c == 'a') {
                    aExist = true;
                } else {
                    bExist = true;
                }
                if (aExist && bExist) {
                    return false;
                }
            }
            return true;
        }
        // 计算pattern里a和b的个数
        int countA = 0;
        int countB = 0;
        for (char c : pChars) {
            if (c == 'a') {
                countA++;
            } else {
                countB++;
            }
        }
        int lenV = vChars.length;
        // a或b的数量为0，判断value能否被等分
        if (countA * countB == 0) {
            int count = countA + countB;
            if (lenV % count != 0) {
                return false;
            } else {
                int len = lenV / count;
                for (int i = len; i < lenV; i += len) {
                    if (!stringEquals(vChars, 0, i, len)) {
                        return false;
                    }
                }
                return true;
            }
        }
        // i代表a字符串的长度
        for (int i = 0; i <= lenV; i++) {
            // a字符串过长就break
            if (lenV - countA * i < 0) {
                break;
            }
            int lenB = (lenV - countA * i) / countB;
            // lenB满足条件才进行判断
            if (lenB * countB + i * countA == lenV) {
                int index = 0;
                int[] ab = new int[2];
                // 初始化a和b的初始索引之前设置为-1
                ab[0] = -1;
                ab[1] = -1;
                boolean notMatch = false;
                for (char c : pChars) {
                    if (c == 'a') {
                        if (ab[0] == -1) {
                            ab[0] = index;
                        } else {
                            // 每次为a都和初始的字符串比较
                            if (!stringEquals(vChars, ab[0], index, i)) {
                                notMatch = true;
                                break;
                            }
                        }
                        index += i;
                    } else {
                        if (ab[1] == -1) {
                            ab[1] = index;
                        } else {
                            if (!stringEquals(vChars, ab[1], index, lenB)) {
                                notMatch = true;
                                break;
                            }
                        }
                        index += lenB;
                    }
                    // 判断a和b是否相同
                    if (lenB == i) {
                        if (ab[0] != -1 && ab[1] != -1) {
                            if (stringEquals(vChars, ab[0], ab[1], lenB)) {
                                notMatch = true;
                                break;
                            }
                        }
                    }
                }
                // notMatch为false说明之前的几个判断里面都不是因为break跳出
                if (!notMatch) {
                    return true;
                }
            }
        }
        return false;
    }

    // 判断字符串是否相等
    private boolean stringEquals(char[] chars, int i, int j, int len) {
        for (int k = 0; k < len; k++) {
            if (chars[i + k] != chars[j + k]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 题目：
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * <p>
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * <p>
     * 解题方案
     * 思路
     * <p>
     * 整体思路是将两个字符串较短的用 000 补齐，使得两个字符串长度一致，然后从末尾进行遍历计算，得到最终结果。
     * <p>
     * 本题解中大致思路与上述一致，但由于字符串操作原因，不确定最后的结果是否会多出一位进位，所以会有 2 种处理方式：
     * <p>
     * 第一种，在进行计算时直接拼接字符串，会得到一个反向字符，需要最后再进行翻转
     * 第二种，按照位置给结果字符赋值，最后如果有进位，则在前方进行字符串拼接添加进位
     * <p>
     * 时间复杂度：O(n)
     */
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     * <p>
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length == 0 || nums2.length == 0) {
            return;
        }
        //nums1的最大值小于numbs2的最小值
        if (nums1[m - 1] <= nums2[0]) {
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
        }
        if (nums2[n - 1] <= nums1[0]) {
            //先移动nums1里面的元素
            for (int i = m - 1; i < m; i--) {
                nums1[n + i] = nums1[i];
            }
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
        //第三种情况

    }

    /**
     * 方法一 : 合并后排序
     * <p>
     * 直觉
     * <p>
     * 最朴素的解法就是将两个数组合并之后再排序。该算法只需要一行(Java是2行)，时间复杂度较差，为O((n+m)log⁡(n+m))O((n + m)\log(n + m))O((n+m)log(n+m))。这是由于这种方法没有利用两个数组本身已经有序这一点。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


    /**
     * 直觉
     * <p>
     * 一般而言，对于有序数组可以通过 双指针法 达到O(n+m)O(n + m)O(n+m)的时间复杂度。
     * <p>
     * 最直接的算法实现是将指针p1 置为 nums1的开头， p2为 nums2的开头，在每一步将最小值放入输出数组中。
     * <p>
     * 由于 nums1 是用于输出的数组，需要将nums1中的前m个元素放在其他地方，也就需要 O(m)O(m)O(m) 的空间复杂度。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /**
     * 方法三 : 双指针 / 从后往前
     * <p>
     * 直觉
     * <p>
     * 方法二已经取得了最优的时间复杂度O(n+m)O(n + m)O(n+m)，但需要使用额外空间。这是由于在从头改变nums1的值时，需要把nums1中的元素存放在其他位置。
     * <p>
     * 如果我们从结尾开始改写 nums1 的值又会如何呢？这里没有信息，因此不需要额外空间。
     * <p>
     * 这里的指针 p 用于追踪添加元素的位置。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }


    /**
     * 这种解法不对  可能一个升序一个降序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        if (m <= 0 && n <= 0) {
            return;
        }
        if (m == 0 && n != 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (m != 0 && n == 0) {
            return;
        }
        //nums1的最大值小于numbs2的最小值
        if (nums1[m - 1] <= nums2[0]) {
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
            return;
        }
        if (nums2[n - 1] <= nums1[0]) {
            //先移动nums1里面的元素
            for (int i = m - 1; i >= 0; i--) {
                nums1[m + n - 1 - i] = nums1[i];
            }
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 解题方案
     * 思路
     * <p>
     * 标签：排序和双指针
     * 本题目因为要计算三个数，如果靠暴力枚举的话时间复杂度会到 O(n3)O(n^3)O(n3)，需要降低时间复杂度
     * 首先进行数组排序，时间复杂度 O(nlogn)O(nlogn)O(nlogn)
     * 在数组 nums 中，进行遍历，每遍历一个值利用其下标i，形成一个固定值 nums[i]
     * 再使用前指针指向 start = i + 1 处，后指针指向 end = nums.length - 1 处，也就是结尾处
     * 根据 sum = nums[i] + nums[start] + nums[end] 的结果，判断 sum 与目标 target 的距离，如果更近则更新结果 ans
     * 同时判断 sum 与 target 的大小关系，因为数组有序，如果 sum > target 则 end--，如果 sum < target 则 start++，如果 sum == target 则说明距离为 0 直接返回结果
     * 整个遍历过程，固定值为 n 次，双指针为 n 次，时间复杂度为 O(n2)O(n^2)O(n2)
     * 总时间复杂度：O(nlogn)+O(n2)=O(n2)O(nlogn) + O(n^2) = O(n^2)O(nlogn)+O(n2)=O(n2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

    /**
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        int size = wordDict.size();
        boolean flag = false;
        out:
        while (s.length() != 0) {
            for (int i = 0; i < size; i++) {
                if (s.length() == 0) {
                    break;
                }
                if (s.contains(wordDict.get(i))) {
                    flag = true;
                    s = s.substring(0, s.indexOf(wordDict.get(i))) + s.substring(s.indexOf(wordDict.get(i)) + wordDict.get(i).length());
                } else {
                    flag = false;
                    break out;
                }
            }
        }
        return flag;
    }


    /**
     * 两数之和
     * 方法一：暴力法
     * <p>
     * 暴力法很简单，遍历每个元素 xxx，并查找是否存在一个值与 target−xtarget - xtarget−x 相等的目标元素。
     */

    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法二：两遍哈希表
     * <p>
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     * <p>
     * 通过以空间换取速度的方式，我们可以将查找时间从 O(n)O(n)O(n) 降低到 O(1)O(1)O(1)。哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)O(n)O(n)。但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)O(1)O(1)。
     * <p>
     * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target−nums[i]target - nums[i]target−nums[i]）是否存在于表中。注意，该目标元素不能是 nums[i]nums[i]nums[i] 本身！
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 解题方案
     * 思路
     * <p>
     * 标签：数组遍历
     * 首先对数组进行排序，排序后固定一个数 nums[i]nums[i]nums[i]，再使用左右指针指向 nums[i]nums[i]nums[i]后面的两端，数字分别为 nums[L]nums[L]nums[L] 和 nums[R]nums[R]nums[R]，
     * 计算三个数的和 sumsumsum 判断是否满足为 000，满足则添加进结果集
     * 如果 nums[i]nums[i]nums[i]大于 000，则三数之和必然无法等于 000，结束循环
     * 如果 nums[i]nums[i]nums[i] == nums[i−1]nums[i-1]nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sumsumsum == 000 时，nums[L]nums[L]nums[L] == nums[L+1]nums[L+1]nums[L+1] 则会导致结果重复，应该跳过，L++L++L++
     * 当 sumsumsum == 000 时，nums[R]nums[R]nums[R] == nums[R−1]nums[R-1]nums[R−1] 则会导致结果重复，应该跳过，R−−R--R−−
     * 时间复杂度：O(n2)O(n^2)O(n2)，nnn 为数组长度
     * <p>
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }

    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode result = head;
        ListNode pre = null;
        while (head != null) {
            if (set.add(head.val)) {
                pre = head;
            } else {
                ListNode next = head.next;
                pre.next = next;
                head = next;
            }
        }
        return result;

    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left != right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle;
            } else {
                return middle;
            }
        }
        return 0;
    }

    public int search2(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) return pivot;
            if (target < nums[pivot]) right = pivot - 1;
            else left = pivot + 1;
        }
        return -1;
    }


    /**
     * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     * <p>
     * 如果不能形成任何面积不为零的三角形，返回 0。
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i)
            if (A[i] + A[i + 1] > A[i + 2])
                return A[i] + A[i + 1] + A[i + 2];
        return 0;
    }


    /**
     * 滑动窗口最大值 暴力法
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MAX_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
                output[i] = max;

            }

        }
        return output;
    }

    ArrayDeque<Integer> deq = new ArrayDeque<>();
    int[] nums;

    public void clean_deque(int i, int k) {
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }

    /**
     * 如何优化时间复杂度呢？首先想到的是使用堆，因为在最大堆中 heap[0] 永远是最大的元素。在大小为 k 的堆中插入一个元素消耗 log⁡(k)\log(k)log(k) 时间，因此算法的时间复杂度为 O(Nlog⁡(k)){O}(N \log(k))O(Nlog(k))。
     * <p>
     * 能否得到只要 O(N){O}(N)O(N) 的算法？
     * <p>
     * 我们可以使用双向队列，该数据结构可以从两端以常数时间压入/弹出元素。
     * <p>
     * 存储双向队列的索引比存储元素更方便，因为两者都能在数组解析中使用。
     * <p>
     * 算法
     * <p>
     * 算法非常直截了当：
     * <p>
     * 处理前 k 个元素，初始化双向队列。
     * <p>
     * 遍历整个数组。在每一步 :
     * <p>
     * 清理双向队列 :
     * <p>
     * - 只保留当前滑动窗口中有的元素的索引。
     * <p>
     * - 移除比当前元素小的所有元素，它们不可能是最大的。
     * <p>
     * 将当前元素添加到双向队列中。
     * 将 deque[0] 添加到输出中。
     * 返回输出数组。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        //int deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            if (nums[i] > nums[max_idx]) {
                max_idx = i;
            }
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];
        //build output
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }


    /***
     * 直觉
     *
     * 这是另一个 O(N){O}(N)O(N) 的算法。本算法的优点是不需要使用 数组 / 列表 之外的任何数据结构。
     *
     * 算法的思想是将输入数组分割成有 k 个元素的块。
     * 若 n % k != 0，则最后一块的元素个数可能更少。
     *
     * image.png
     *
     * 开头元素为 i ，结尾元素为 j 的当前滑动窗口可能在一个块内，也可能在两个块中。
     *
     * image.png
     *
     * 情况 1 比较简单。 建立数组 left， 其中 left[j] 是从块的开始到下标 j 最大的元素，方向 左->右。
     *
     * image.png
     *
     * 为了处理更复杂的情况 2，我们需要数组 right，其中 right[j] 是从块的结尾到下标 j 最大的元素，方向 右->左。right 数组和 left 除了方向不同以外基本一致。
     *
     * image.png
     *
     * 两数组一起可以提供两个块内元素的全部信息。考虑从下标 i 到下标 j的滑动窗口。 根据定义，right[i] 是左侧块内的最大元素， left[j] 是右侧块内的最大元素。因此滑动窗口中的最大元素为 max(right[i], left[j])。
     *
     * image.png
     *
     * 算法
     *
     * 算法十分直截了当：
     *
     *     从左到右遍历数组，建立数组 left。
     *
     *     从右到左遍历数组，建立数组 right。
     *
     *     建立输出数组 max(right[i], left[i + k - 1])，其中 i 取值范围为 (0, n - k + 1)。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetcode-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }


    /**
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
     * <p>
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
     * <p>
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     * <p>
     * 方法一：暴力法
     * <p>
     * 暴力法是最直观的方法。初始化子数组的最小长度为无穷大，枚举数组 nums\text{nums}nums 中的每个下标作为子数组的开始下标，对于每个开始下标 iii，需要找到大于或等于 iii 的最小下标 jjj，使得从 nums[i]\text{nums}[i]nums[i] 到 nums[j]\text{nums}[j]nums[j] 的元素和大于或等于 sss，并更新子数组的最小长度（此时子数组的长度是 j−i+1j-i+1j−i+1）。
     * <p>
     * 注意：使用 Python 语言实现方法一会超出时间限制。
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /**
     * 方法二：前缀和 + 二分查找
     * <p>
     * 方法一的时间复杂度是 O(n2)O(n^2)O(n2)，因为在确定每个子数组的开始下标后，找到长度最小的子数组需要 O(n)O(n)O(n) 的时间。如果使用二分查找，则可以将时间优化到 O(log⁡n)O(\log n)O(logn)。
     * <p>
     * 为了使用二分查找，需要额外创建一个数组 sums\text{sums}sums 用于存储数组 nums\text{nums}nums 的前缀和，其中 sums[i]\text{sums}[i]sums[i] 表示从 nums[0]\text{nums}[0]nums[0] 到 nums[i−1]\text{nums}[i-1]nums[i−1] 的元素和。得到前缀和之后，对于每个开始下标 iii，可通过二分查找得到大于或等于 iii 的最小下标 bound\textit{bound}bound，使得 sums[bound]−sums[i−1]≥s\text{sums}[\textit{bound}]-\text{sums}[i-1] \ge ssums[bound]−sums[i−1]≥s，并更新子数组的最小长度（此时子数组的长度是 bound−(i−1)\textit{bound}-(i-1)bound−(i−1)）。
     * <p>
     * 因为这道题保证了数组中每个元素都为正，所以前缀和一定是递增的，这一点保证了二分的正确性。如果题目没有说明数组中每个元素都为正，这里就不能使用二分来查找这个位置了。
     * <p>
     * 在很多语言中，都有现成的库和函数来为我们实现这里二分查找大于等于某个数的第一个位置的功能，比如 C++ 的 lower_bound，Java 中的 Arrays.binarySearch，C# 中的 Array.BinarySearch，Python 中的 bisect.bisect_left。但是有时面试官可能会让我们自己实现一个这样的二分查找函数，这里给出一个 C# 的版本，供读者参考：
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法三：双指针
     * <p>
     * 在方法一和方法二中，都是每次确定子数组的开始下标，然后得到长度最小的子数组，因此时间复杂度较高。为了降低时间复杂度，可以使用双指针的方法。
     * <p>
     * 定义两个指针 start\textit{start}start 和 end\textit{end}end 分别表示子数组的开始位置和结束位置，维护变量 sum\textit{sum}sum 存储子数组中的元素和（即从 nums[start]\text{nums}[\textit{start}]nums[start] 到 nums[end]\text{nums}[\textit{end}]nums[end] 的元素和）。
     * <p>
     * 初始状态下，start\textit{start}start 和 end\textit{end}end 都指向下标 000，sum\textit{sum}sum 的值为 000。
     * <p>
     * 每一轮迭代，将 nums[end]\text{nums}[end]nums[end] 加到 sum\textit{sum}sum，如果 sum≥s\textit{sum} \ge ssum≥s，则更新子数组的最小长度（此时子数组的长度是 end−start+1\textit{end}-\textit{start}+1end−start+1），然后将 nums[start]\text{nums}[start]nums[start] 从 sum\textit{sum}sum 中减去并将 start\textit{start}start 右移，直到 sum<s\textit{sum} < ssum<s，在此过程中同样更新子数组的最小长度。在每一轮迭代的最后，将 end\textit{end}end 右移。
     * <p>
     * <p>
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    Random random = new Random();

    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public int kthSmallest1(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
    }

    /**
     * 思路及算法
     * <p>
     * 由题目给出的性质可知，这个矩阵的每一行均为一个有序数组。问题即转化为从这 nnn 个有序数组中找第 kkk 大的数，可以想到利用归并排序的做法，归并到第 kkk 个数即可停止。
     * <p>
     * 一般归并排序是两个数组归并，而本题是 nnn 个数组归并，所以需要用小根堆维护，以优化时间复杂度。
     * <p>
     * 具体如何归并，可以参考力扣 23. 合并K个排序链表。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }

    /**
     * 思路及算法
     * <p>
     * 由题目给出的性质可知，这个矩阵内的元素是从左上到右下递增的（假设矩阵左上角为 matrix[0][0]matrix[0][0]matrix[0][0]）。以下图为例：
     * <p>
     * fig1
     * <p>
     * 我们知道整个二维数组中 matrix[0][0]matrix[0][0]matrix[0][0] 为最小值，matrix[n−1][n−1]matrix[n - 1][n - 1]matrix[n−1][n−1] 为最大值，现在我们将其分别记作 lll 和 rrr。
     * <p>
     * 可以发现一个性质：任取一个数 midmidmid 满足 l≤mid≤rl\leq mid \leq rl≤mid≤r，那么矩阵中不大于 midmidmid 的数，肯定全部分布在矩阵的左上角。
     * <p>
     * 例如下图，取 mid=8mid=8mid=8：
     * <p>
     * fig2
     * <p>
     * 我们可以看到，矩阵中大于 midmidmid 的数就和不大于 midmidmid 的数分别形成了两个板块，沿着一条锯齿线将这个矩形分开。其中左上角板块的大小即为矩阵中不大于 midmidmid 的数的数量。
     * <p>
     * 读者也可以自己取一些 midmidmid 值，通过画图以加深理解。
     * <p>
     * 我们只要沿着这条锯齿线走一遍即可计算出这两个板块的大小，也自然就统计出了这个矩阵中不大于 midmidmid 的数的个数了。
     * <p>
     * 走法演示如下，依然取 mid=8mid=8mid=8：
     * <p>
     * fig3
     * <p>
     * 可以这样描述走法：
     * <p>
     * 初始位置在 matrix[n−1][0]matrix[n - 1][0]matrix[n−1][0]（即左下角）；
     * <p>
     * 设当前位置为 matrix[i][j]matrix[i][j]matrix[i][j]。若 matrix[i][j]≤midmatrix[i][j] \leq midmatrix[i][j]≤mid，则将当前所在列的不大于 midmidmid 的数的数量（即 i+1i + 1i+1）累加到答案中，并向右移动，否则向上移动；
     * <p>
     * 不断移动直到走出格子为止。
     * <p>
     * 我们发现这样的走法时间复杂度为 O(n)O(n)O(n)，即我们可以线性计算对于任意一个 midmidmid，矩阵中有多少数不大于它。这满足了二分查找的性质。
     * <p>
     * 不妨假设答案为 xxx，那么可以知道 l≤x≤rl\leq x\leq rl≤x≤r，这样就确定了二分查找的上下界。
     * <p>
     * 每次对于「猜测」的答案 midmidmid，计算矩阵中有多少数不大于 midmidmid ：
     * <p>
     * 如果数量不少于 kkk，那么说明最终答案 xxx 不大于 midmidmid；
     * 如果数量少于 kkk，那么说明最终答案 xxx 大于 midmidmid。
     * <p>
     * 这样我们就可以计算出最终的结果 xxx 了。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

//    public class TreeNode{
//        int val;
//        private TreeNode left;
//        private TreeNode right;
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return null;
    }

    /**
     * 中序遍历，总是选择中间位置左边的数字作为根节点
     * 选择中间位置左边的数字作为根节点，则根节点的下标为 \textit{mid}=(\textit{left}+\textit{right})/2mid=(left+right)/2，此处的除法为整数除法。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode helper1(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = helper1(nums, left, middle - 1);
        root.right = helper1(nums, middle + 1, right);
        return root;
    }

    public TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }

    private Random random3 = new Random();

    public TreeNode helper3(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择任意一个中间位置数字作为根节点
        int mid = (left + right + random3.nextInt(2)) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper3(nums, left, mid - 1);
        root.right = helper3(nums, mid + 1, right);
        return root;
    }

    /**
     * 广度优先
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @param sum
     * @return
     */

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * 思路及算法
     * <p>
     * 观察要求我们完成的函数，我们可以归纳出它的功能：询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum。
     * <p>
     * 假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
     * <p>
     * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k < 2) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int result[] = new int[k + 1];
        int index = 0;
        for (int i = k; i >= 0; i--, index++) {
            result[index] = shorter * i + longer * (k - i);
        }
        return result;
    }

    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        //枚举可行根节点
        for (int i = start; i <= end; i++) {
            //获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            //获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            //从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = leftTree;
                    currentNode.right = rightTree;
                    allTrees.add(currentNode);
                }
            }
        }
        return allTrees;
    }

}
