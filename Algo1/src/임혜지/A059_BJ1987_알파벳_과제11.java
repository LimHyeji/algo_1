package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A059_BJ1987_알파벳_과제11 {
	static int r, c;// 보드의 세로, 가로
	static char arr[][];//보드
	static int check[] = new int[26];//알파벳 방문 여부
	static int res = 0;//결과로 출력할 최대 이동횟수
	static int dirR[] = { -1, 1, 0, 0 };//상하좌우
	static int dirC[] = { 0, 0, -1, 1 };//상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = in.readLine().split(" ");
		r = Integer.parseInt(temp[0]);//보드의 세로 입력
		c = Integer.parseInt(temp[1]);//보드의 가로 입력

		arr = new char[r][c];//보드 생성

		for (int i = 0; i < r; i++) {//r 줄에 걸쳐
			String input = in.readLine();//문자열 입력받고
			for (int j = 0; j < c; j++) {//c개의 문자를
				arr[i][j] = input.charAt(j);//보드에 저장
			}
		}
		// 입력끝
		// ------------------------
		check[arr[0][0] - 'A']++;//시작점의 알파벳 방문 횟수++
		sol(0, 0, 1);//(0,0)에서 시작하고, 시작점을 지났으므로 1부터 카운트

		System.out.println(res);//보드 이동 최대횟수 출력
	}

	static void sol(int row, int col, int cnt) {

		for (int dir = 0; dir < 4; dir++) {//총 네 방향에 대해
			int newR = row + dirR[dir];//상하좌우 인덱스 업데이트
			int newC = col + dirC[dir];//상하좌우 인덱스 업데이트

			if (newR >= 0 && newR < r && newC >= 0 && newC < c && check[arr[newR][newC] - 'A'] == 0) {
				//보드 범위를 초과하지 않으면서 이미 방문하지 않은 알파벳에 대해
				check[arr[newR][newC] - 'A']++;//방문횟수 1증가하고
				sol(newR, newC, cnt + 1);//다음칸으로 이동
				check[arr[newR][newC] - 'A']--;//또 다른 가짓수를 위해 방문횟수 1감소
			}
		}
		res = Math.max(res, cnt);//네 방향에 대해 모두 방문 불가일 때, 그때까지의 이동횟수와 비교해 최대 이동횟수 업데이트
		return;//재귀 종료

	}
}
