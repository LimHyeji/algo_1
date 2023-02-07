package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A012_SWEA2805_농작물수확하기 {
	static int n;//농장크기
	static String[] value = new String[49];//농작물 가치
	static char[][] farm = new char[49][];//농작물 가치에 대한 이차원배열

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
			if(n==0) {//1 by 1일 때는 입력된 값 그대로 출력
				System.out.println("#"+test_case+" "+farm[0][0]);
			}else {//그 외에는 마름모 찾아서 더해서 출력
			System.out.println("#"+test_case+" "+sol());
			}
		}
	}
	
	public static int sol() {
		int sum=0;//결과로 출력할 가치 합
		for(int i=0;i<n/2+1;i++) {
			for(int j=0;j<2*i+1;j++) {
				//System.out.println(i+" , "+(n/2-i+j));
				sum+=farm[i][n/2-i+j]-'0';//1~n/2 줄까지의 합 (별찍기) , char-'0'으로 int 계산
			}
		}
		//System.out.println();
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<n-2*i-2;j++) {
				//System.out.println((i+n/2+1)+" , "+(i+j+1));
				sum+=farm[i+n/2+1][i+j+1]-'0';		//이후 줄의 합		
			}
		}
		return sum;
	}
}
