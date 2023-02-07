package 임혜지;

import java.util.ArrayList;
import java.util.Scanner;

public class A011__SWEA1210_Ladder1 {
	static int[][] ladder = new int[100][100];// 사다리 이차원 배열
	static int a, b;// 도착점 위치
	static int res;//결과값(도착점에 도달 가능한 출발점 x좌표)

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;//테스트번호
		for(int test_case=1;test_case<=10;test_case++) {//테스트 10번 수행
			T = sc.nextInt();// 테스트번호 입력
			
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				ladder[i][j] = sc.nextInt();
				if (ladder[i][j] == 2) {
					a = i;
					b = j; // 도착점의 위치 저장
				}
			}
		}
		sol(a,b);
		System.out.println("#" + test_case + " " + res);
		}
	}
	
	public static void sol(int i,int j) {
		if(i<=0) {//인덱스 벗어나면 
			res=j;//출발점의 x좌표 저장
			return;//종료
		}
		ladder[i][j]=0;//1이었던 곳 지나갈때마다 0으로 바꾸고 지나가기
		
		if(j-1>=0&&ladder[i][j-1]==1) {
			//인덱스 벗어나지 않으면서 1인 경우 (왼쪽)
			sol(i,j-1);
		}else if(j+1<100&&ladder[i][j+1]==1) {
			//인덱스 벗어나지 않으면서 1인 경우 (오른쪽)
			sol(i,j+1);
		}else {
			sol(i-1,j);//왼쪽도 오른쪽도 없으면 무조건 위쪽이 있음
		}
		
	}
}
