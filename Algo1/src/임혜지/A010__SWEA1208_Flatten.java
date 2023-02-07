package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A010__SWEA1208_Flatten {
	static int [] h=new int[100];	//100개의 상자높이들
	static int n;	//입력되는 덤프횟수
	static int res;	//결과값
	
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n=sc.nextInt();	//덤프횟수 입력
			for(int i=0;i<100;i++) {
				h[i]=sc.nextInt(); //100개의 상자높이들 입력
			}
			Arrays.sort(h);	//상자높이 오름차순 정렬
			for(int i=0;i<n;i++) {//덤프횟수만큼 돌면서
				h[99]--;//가장 높은 층에서 하나 빼서
				h[0]++;//가장 낮은 층에 하나 쌓기
				Arrays.sort(h);//다시 오름차순 정렬
			}
			System.out.println("#"+test_case+" "+(h[99]-h[0]));//최대층과 최소층 차이 출력
		}
	}
}
