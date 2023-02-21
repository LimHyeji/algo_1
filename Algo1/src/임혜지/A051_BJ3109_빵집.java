package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 왼쪽 끝(?,0)에서 오른쪽 끝(0,c-1) 이동
 * 1. 건물이 있으면 불가->visit 처리
 * 2. 파이프는 오른쪽/오른쪽위/오른쪽아래 만 가능
 * 3. 이미 파이프 놓였으면 불가, 같은 경로를 이용하는 두 파이프라인도 불가 -> visit 처리
 */

public class A051_BJ3109_빵집 {
	static int r, c;// 입력 받을 격자 행렬 사이즈
	static char[][] arr;// 격자판
	static boolean[][] visit;// 방문했는지 여부 적은 이차원배열
	static int total = 0;// 출력할 파이프라인 개수

	static int[] dirRow = { -1, 0, 1 };// 오른쪽 아래, 오른쪽 위, 오른쪽
	// static int[] dirCol = { 1, 1, 1 };// 오른쪽 아래, 오른쪽 위, 오른쪽

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 라인 입력

		r = Integer.parseInt(st.nextToken());// 격자의 행 크기
		c = Integer.parseInt(st.nextToken());// 격자의 열 크기

		arr = new char[r][c];// r by c 격자 생성
		visit = new boolean[r][c];// r by c 방문 여부 배열 생성

		for (int i = 0; i < r; i++) {// r라인에 걸쳐
			String temp = in.readLine();// 문자열 입력받고
			for (int j = 0; j < c; j++) {// c개의 열을
				arr[i][j] = temp.charAt(j);// char 배열에 추가
				if (arr[i][j] == 'x') {// 건물이 있다면
					visit[i][j] = true;// 방문한 것으로 처리
				}
			}
		}
		// 입력끝
		// --------------------------------------------------

		for (int i = 0; i < r; i++) {// 첫번째 열에서
			if (!visit[i][0]) {// 방문하지 않았다면(x가 아니라면)
				if (sol(i, 0))// 재귀함수가 true일때(마지막 열까지 도달했을 때)
					total++;// 방법의 가지수 한 개 추가
			}
		}

		System.out.println(total);// 결과(마지막 열 도달 횟수) 출력

	}

	static boolean sol(int row, int col) {// 재귀 함수 (현재 방문하고 있는 위치)
		visit[row][col] = true;// 이미 방문한 것으로 처리

		if (col == c - 1) {// 마지막 열에 도달했을 때
			// total++;
			// print();
			return true;// true 리턴
		}
		for (int i = 0; i < 3; i++) {// 세 가지 방향에 대해
			int nRow = row + dirRow[i];// 아래, 위, .
			int nCol = col + 1;// 오른쪽
			if (nRow >= 0 && nRow < r && nCol < c && visit[nRow][nCol] == false) {// 인덱스 벗어나지 않고, 방문한 곳이 아니라면
				if (sol(nRow, nCol)) {// 재귀 호출 했을 때 끝까지 도착했을 경우에
					return true;// true 리턴
				}
			}
		}
		return false;// 그 외의 경우에는 false 리턴
	}

	static void print() {// 디버깅용 프린트 함수
		for (boolean[] a : visit) {
			for (boolean b : a) {
				System.out.print(b == true ? "o" : "x");
			}
			System.out.println();
		}
		System.out.println();
	}
}
