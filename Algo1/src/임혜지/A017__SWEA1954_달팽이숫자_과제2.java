package 임혜지;

import java.util.Scanner;

public class A017__SWEA1954_달팽이숫자_과제2 {
	static int n;//달팽이 크기
	static int [][] arr;//달팽이
	static boolean [][] visit;//지나간 곳인지 체크
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n=sc.nextInt();//달팽이 크기 입력 받아
			arr=new int[n][n];//달팽이 생성
			visit=new boolean[n][n];//달팽이 크기만큼의 배열 생성
			
			System.out.println("#"+test_case);
			sol(0,0,0,1);//입력할 cnt값, (0,0)에서 시작, mode1(오른쪽 이동) ...//오른쪽아래쪽왼쪽위쪽 각각 1234
		}
	}
	
	public static void sol(int cnt,int i,int j, int mode) {
		if(cnt==n*n) {//달팽이 모든 칸을 돌았으면
			for(int x=0;x<n;x++) {
				for(int y=0;y<n;y++) {
					System.out.print(arr[x][y]+ " ");//달팽이 출력 후
				}
				System.out.println();
			}
			return;//종료
		}
		arr[i][j]=cnt+1;//숫자 저장
		visit[i][j]=true;//지나감
		
		if(mode==1&&j+1<n&&visit[i][j+1]==false) {//오른쪽 조건 만족시
			sol(cnt+1,i,j+1,1);//오른쪽으로 이동
		}
		else if(mode==1) {//오른쪽 모드이지만 조건 만족 안할 때
			sol(cnt+1,i+1,j,2);//아래쪽으로 이동
		}
		
		if(mode==2&&i+1<n&&visit[i+1][j]==false) {//아래쪽 조건 만족 시
			sol(cnt+1,i+1,j,2);//아래쪽으로 이동
		}
		else if(mode==2) {//아래쪽 모드이지만 조건 만족 안할 때
			sol(cnt+1,i,j-1,3);//왼쪽으로 이동
		}
		
		if(mode==3&&j-1>-1&&visit[i][j-1]==false) {//왼쪽 조건 만족 시
			sol(cnt+1,i,j-1,3);//왼쪽으로 이동
		}
		else if(mode==3) {//왼쪽 모드이지만 조건 만족 안할 때
			sol(cnt+1,i-1,j,4);//위쪽으로 이동
		}
		
		if(mode==4&&i-1>-1&&visit[i-1][j]==false) {//위쪽 조건 만족 시
			sol(cnt+1,i-1,j,4);//위쪽으로 이동
		}
		else if(mode==4) {//위쪽 모드이지만 조건 만족 안할 때
			sol(cnt+1,i,j+1,1);//다시 오른쪽으로 이동
		}
	}
}
