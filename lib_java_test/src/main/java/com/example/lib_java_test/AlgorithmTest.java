package com.example.lib_java_test;

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
     * @param A
     * @return
     */
    public int[] multiply2(int[] A) {
        int length=A.length;
        int[] B=new int[length];
        if (length!=0) {
            B[0]=1;
            //计算下三角连乘
            for (int i = 1; i < B.length; i++) {
                B[i]=B[i-1]*A[i-1];
            }
            //B[n]就会在第一个循环的时候，计算好，而把B[0]则在下一个循环的时候计算好。
            int temp=1;
            //计算上三角
            for (int j = length-2; j >= 0; j--) {
                temp*=A[j+1];
                B[j]*=temp;
            }
        }
        return B;
    }

}
