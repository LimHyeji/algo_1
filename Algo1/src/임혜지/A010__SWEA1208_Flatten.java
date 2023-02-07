package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A010__SWEA1208_Flatten {
	static int [] h=new int[100];	//100개의 상자높이들
	static int n;	//입력되는 덤프횟수
	static int res;	//결과값
	static int max,min;	//최대최소값 담아놓을 변수
	
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n=sc.nextInt();	//덤프횟수 입력
			for(int i=0;i<5;i++) {
				h[i]=sc.nextInt(); //100개의 상자높이들 입력
			}
			Arrays.sort(h);	//상자높이 오름차순 정렬
			min=h[0]; max=h[99];//sort를 계속 할 수 없으므로, 최대최소 담아놓음
			sol(n);	//해답 찾기
			
			System.out.println("#"+test_case+" "+res);
		}
	}
	
	public static void sol(int cnt) {
		if(cnt==0) {
			res=max-min; //최대 높이-최소 높이
			return;	//덤프횟수만큼 돌고 종료
		}
		
		h[0]++; h[99]--;	//최대 높이를 1 빼고 최소 높이를 1 더함

		sol(cnt--);
	}
	

}
