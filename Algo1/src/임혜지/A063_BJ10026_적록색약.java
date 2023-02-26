package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A063_BJ10026_적록색약 {
	static int n;// 배열 크기
	static int dirR[] = { -1, 1, 0, 0 };// 상하좌우
	static int dirC[] = { 0, 0, -1, 1 };// 상하좌우
	static char arr[][];// 각 문자 담을 이차원 배열
	static boolean visit[][];// 탐색 여부 판단할 이차원 배열
	static int resA, resB;// 첫번째 결과(일반인)와 두번째 결과(적록색맹)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력

		n = Integer.parseInt(in.readLine());// 배열의 크기 입력

		arr = new char[n][n];// 입력받은 배열 생성

		for (int i = 0; i < n; i++) {// n줄의
			String temp = in.readLine();// 문자열 입력받아
			for (int j = 0; j < n; j++) {// n개의 문자를
				arr[i][j] = temp.charAt(j);// 배열에 저장
			}
		}

		// 입력끝
		// ---------------------
		resA = 0;// 첫번째 변수 1씩 더하기 위해 0으로 초기화
		resB = 0;// 두번째 변수 1씩 더하기 위해 0으로 초기화

		visit = new boolean[n][n];// 첫번째 결과를 위해 방문 여부 배열 생성
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {//탐색하지 않은 값은
					visit[i][j] = true;//탐색한 것으로 하고
					sol1(i, j, arr[i][j]);//같은 문자가 반복될 때까지 탐색
					resA++;//해당 문자에 대한 탐색 종료 후 +1
				}
			}
		}

		visit = new boolean[n][n];// 두번째 결과를 위해 방문 여부 배열 생성
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {//탐색하지 않은 값은
					visit[i][j] = true;//탐색한 것으로 하고
					sol2(i, j, arr[i][j]);//같은 문자가 반복될 때까지 탐색(단, 빨간색과 초록색 구분x)
					resB++;//문자에 대한 탐색 종료 후 +1
				}
			}
		}

		System.out.println(resA + " " + resB);// 결과 출력
	}

	static void sol1(int row, int col, char c) {// 첫번째 결과를 위해 같은 문자까지는 모두 visit할 메소드
		for (int dir = 0; dir < 4; dir++) {//네 방향에 대해
			int newR = row + dirR[dir];
			int newC = col + dirC[dir];

			if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visit[newR][newC]) {//인덱스 벗어나지 않고 탐색하지 않은 값은
				if (arr[newR][newC] == c) {//탐색 중인 문자와 같은 것들에 대해
					visit[newR][newC]=true;//탐색한 것으로 하고
					sol1(newR, newC, arr[newR][newC]);//계속 탐색 진행
				}
			}
		}
	}

	static void sol2(int row, int col, char c) {// 두번째 결과를 위해 같은 문자까지는 모두 visit할 메소드
		for (int dir = 0; dir < 4; dir++) {//네 방향에 대해
			int newR = row + dirR[dir];
			int newC = col + dirC[dir];

			if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visit[newR][newC]) {//인덱스 벗어나지 않고 탐색하지 않은 값은
				if (((c == 'R' || c == 'G') && (arr[newR][newC] == 'R' || arr[newR][newC] == 'G'))//R과 G를 같은 문자 취급하고
						|| arr[newR][newC] == c) {//B인 경우는 구분하면서
					visit[newR][newC] = true;//탐색한 것으로 하고
					sol2(newR, newC, arr[newR][newC]);//계속 탐색 진행
				}
			}
		}
	}
}
