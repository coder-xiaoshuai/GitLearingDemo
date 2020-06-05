package com.example.lib_java_test;

import java.util.HashSet;
import java.util.Set;

public class AlgorithmTest {

    public static void main(String[] args) {
        System.out.println(cutRope(4));
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
        int result=2;
        if(target==1)
            return 1;
        if(target==2)
            return 2;
        return 2*JumpFloorII(target-1);
    }

    /**
     *
     * 青蛙问题不是斐波那契数列 注意！！！！！！
     * 通项公式
     * @param target
     * @return
     */
    public int JumpFloorII2(int target){
        if (target == 0){
            return 0;
        }
        if (target == 1){
            return 1;
        }
        return (int) ((Math.pow((1 + Math.sqrt(5)) / 2, target) - Math.pow((1 - Math.sqrt(5)) / 2, target)) / Math.sqrt(5));
    }

}
