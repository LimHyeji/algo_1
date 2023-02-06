package 임혜지;

import java.util.Scanner;

public class A004_FibonacciTest {
    // 피보나치 n항을 계산하여 리턴 
    public static long fibo(int n) { 
        if (n <= 1) return n;
        return fibo(n - 1) + fibo(n - 2); // 어떤 문제가 있는지 주석을 달아보세요
    }
    /*
     * fibo(n-2)까지의 계산이 각각 두갈래로 진행되므로 성능 저하의 우려가 있음
     */
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(fibo(N));
    }

}