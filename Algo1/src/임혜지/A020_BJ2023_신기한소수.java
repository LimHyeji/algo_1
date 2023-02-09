package 임혜지;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 첫째 수 무조건 소수(2,3,5,7)
 * 둘째수부터는 5 제외 홀수(1,3,7,9)
 */
public class A020_BJ2023_신기한소수 {
	static int n;//출력할 소수의 자리수
	static final int[] num= {1,3,7,9};//둘째수부터는 이 배열에서 하나를 고름
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();//구하는 소수의 자리 숫자 입력
		
		sol(1,2);//2로 시작하는 숫자
		sol(1,3);//3로 시작하는 숫자
		sol(1,5);//5로 시작하는 숫자
		sol(1,7);//7로 시작하는 숫자
	}
	
	public static boolean prime(int num) {//소수 판별 메소드
		for(int i=2;i<=num/2;i++) {//절반만 나눔
			if(num%i==0) {//나눠지는 숫자 있으면
				return false;//false 리턴
			}
		}
		return true;//모두 나눠보고, 나눠지는 숫자 없으면 true 리턴
	}
	
	public static void sol(int cnt,int p) {//재귀 횟수(소수자리수), 만들어진 소수
		if(cnt==n) {//소수자리수 완성 시
			System.out.println(p);//결과 출력
			return;//종료
		}
		
		for(int i=0;i<4;i++) {
			if(prime(p*10+num[i])) {//소수이면
				sol(cnt+1,p*10+num[i]);//다음 숫자 탐색
			}
		}
	}
}
