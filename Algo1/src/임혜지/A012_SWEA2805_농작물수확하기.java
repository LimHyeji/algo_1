package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A012_SWEA2805_농작물수확하기 {
	static int n;
	static String[] value = new String[49];
	static char[][] farm = new char[49][];

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();// 테스트 케이스 수
		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();// 농장 크기 입력
			sc.nextLine();// 개행문자 제거
			for (int i = 0; i < n; i++) {
				value[i] = sc.nextLine();// 농작물가치 입력
				farm[i] = value[i].toCharArray();// 한 줄 입력 받아 char 배열로 변환
			}
			if(n==0) {
				System.out.println("#"+T+" "+farm[0][0]);
			}else {
			System.out.println("#"+T+" "+sol());
			}
		}
	}
	
	public static int sol() {
		int sum=0;
		for(int i=0;i<n/2+1;i++) {
			for(int j=0;j<2*i+1;j++) {
				System.out.println(i+" , "+(n/2-i+j));
				//sum+=farm[i][n/2-i+j]-'0';
			}
		}
		System.out.println();
		for(int i=0;i<n/2;i++) {//0 1
			for(int j=0;j<n-2*i-2;j++) {//0 1 2/0 
				System.out.println((i+n/2+1)+" , "+(n/2-(i+j)));//2 3 4/1 // 1 2 3/2
				//sum+=farm[i+n/2+1][n/2-i+j]-'0';				
			}
		}
		return sum;
	}
}
