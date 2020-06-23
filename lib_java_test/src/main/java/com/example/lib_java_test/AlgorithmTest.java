package com.example.lib_java_test;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(countPrimes(10));
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

    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        if (m <= 0 || n <= 0) {
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
            for (int i = m - 1; i < m; i--) {
                nums1[n + i] = nums1[i];
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

}
