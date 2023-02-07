package 임혜지;

import java.util.Scanner;

public class A010__SWEA1208_Flatten {
	static int [] h=new int[100];	//100개의 상자높이들
	static int n;	//입력되는 덤프횟수
	static int res;	//결과값
	
	
	
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n=sc.nextInt();
			for(int i=0;i<100;i++) {
				h[i]=sc.nextInt();
			}
			
			recur(n);
			
		}
	}
	
	public static void recur(int cnt) {
		if(cnt==0) {
			return;
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<)
		}
	}
}
