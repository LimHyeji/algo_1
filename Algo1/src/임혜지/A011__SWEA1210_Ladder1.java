package 임혜지;

import java.util.ArrayList;
import java.util.Scanner;

public class A011__SWEA1210_Ladder1 {
	static int[][] ladder = new int[100][100];// 사다리 이차원 배열
	static ArrayList<Integer> start = new ArrayList<>(); // 출발점들
	static boolean[][] visit; //
	static int a, b;// 도착점 위치
	static int res;//결과값(도착점에 도달 가능한 출발점 x좌표)

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();// 테스트번호 입력

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				ladder[i][j] = sc.nextInt();
				if (ladder[i][j] == 1) {// 출발점일 경우
					start.add(j); // 가로 인덱스를 리스트에 추가
				}
				if (ladder[i][j] == 2) {
					a = i;
					b = j; // 도착점의 위치 저장
				}
			}
		}

		sol(start.get(0),start.get(0),0);//첫번째 출발점부터 시작(출발점 x좌표, 현재 x좌표, 현재 y좌표)

		System.out.println("#" + T + " " + res);
	}

	public static void sol(int idx,int x,int y) {
		
		start.remove(idx);	//이미 시도한 출발점에 대해서는 삭제
		
	}
}
