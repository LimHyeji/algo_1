package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 0 빈칸
 * 1~5 cctv(최대 8개)
 * 6 벽
 */

/*
 * 1번 : 한 방향				(경우의 수 4)
 * 2번 : 두 방향(반대 방향)		(경우의 수 2) 
 * 3번 : 두 방향(직각 방향)		(경우의 수 4)
 * 4번 : 세 방향				(경우의 수 4)
 * 5번 : 네 방향				(경우의 수 1)
 * 
 * -> 모든 경우 조합하고자 4번의 반복문 돌림
 * 
 * 90도 회전 가능(dir 인덱스 1씩 증가)
 * 
 * -> 사각지대 최소 크기 구하기
 */

public class A065_BJ15683_감시 {
	static class Node {// cctv위치와 번호 저장할 클래스
		int row, col, num;// 행위치,열위치,cctv 종류(1~5)

		public Node(int row, int col, int num) {// cctv위치와 번호 저장 위한 생성자
			this.row = row;
			this.col = col;
			this.num = num;
		}
	}

	static int n, m;// 세로가로 크기
	static int res;// 사각지대 최소 크기
	static int arr[][];// 사무실
	static int arr2[][];// 재귀 시 사용할 사무실 환경
	static ArrayList<Node> cctv;// cctv 리스트
	static int dirR[] = { -1, 0, 1, 0 };// 상우하좌
	static int dirC[] = { 0, 1, 0, -1 };// 상우하좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 공백으로 구분해 한 줄 입력

		n = Integer.parseInt(st.nextToken());// 사무실의 세로 크기
		m = Integer.parseInt(st.nextToken());// 사무실의 가로 크기

		arr = new int[n][m];// 사무실 생성
		arr2 = new int[n][m];// 사무실 환경 생성
		cctv = new ArrayList<>();// cctv 리스트 생성

		for (int i = 0; i < n; i++) {// n줄의
			st = new StringTokenizer(in.readLine());// 문자열 입력받고
			for (int j = 0; j < m; j++) {// m개의
				arr[i][j] = Integer.parseInt(st.nextToken());// 문자들 저장
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {// 1번과 5번 사이이면 cctv이므로
					cctv.add(new Node(i, j, arr[i][j]));// 위치와 입력받은 번호 저장
				}
			}
		}
		// 입력끝
		// --------------------------
		res = Integer.MAX_VALUE;// 최소값 구하기 위해 최대값으로 초기화
		sol(0);// 사각지대 최소값 구할 메소드
		System.out.println(res);// 사각지대 최소값 출력
	}

	static void sol(int cnt) {
		if (cnt == cctv.size()) {// cctv 개수만큼 재귀 돌았으면
			int sum = 0;//그 사무실 환경의 사각지대 칸 계산
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr2[i][j] == 0)//-1인 경우에는 cctv 시야 내부이고, -1로 바뀌지 않은 빈칸의 경우에는
						sum++;//사각지대 1 추가
				}
			}
			res = Math.min(res, sum); // 최소값 업데이트
			return;// 재귀 종료
		}

		int row = cctv.get(cnt).row;// 편의를 위해 탐색 중인 cctv의 행위치와
		int col = cctv.get(cnt).col;// cctv의 열위치,
		int num = cctv.get(cnt).num;// cctv 종류 번호 저장

		for (int dir = 0; dir < 4; dir++) {// 상, 우, 하, 좌 순서
			copyArr();// 재귀 위해 배열 복사 -> cnt==0?
			// 배열 카피 위치

			if (num == 1) {// 한 방향
				check(row, col, dir);

			} else if (num == 2) {// 두 방향(반대 방향)
				check(row, col, dir);
				check(row, col, dir + 2);

			} else if (num == 3) {// 두 방향(직각 방향)
				check(row, col, dir);
				check(row, col, dir + 1);

			} else if (num == 4) {// 세 방향
				check(row, col, dir);
				check(row, col, dir + 1);
				check(row, col, dir + 2);

			} else if (num == 5) {// 네 방향
				check(row, col, dir);
				check(row, col, dir + 1);
				check(row, col, dir + 2);
				check(row, col, dir + 3);
			}
		}
	}

	static void check(int row, int col, int dir) {// 현재 cctv 방향으로 사각지대 체크할 메소드
		dir %= 4;// 4 이상인 경우 존재하므로 나머지값 이용

		int newR = row + dirR[dir];// 나아가는 방향으로의 새로운 행 인덱스
		int newC = col + dirC[dir];// 나아가는 방향으로의 새로운 열 인덱스

		if (newR < 0 || newR >= n || newC < 0 || newC >= m) {// 사무실 벗어날 때
			return;// 재귀 종료
		} else if (arr2[newR][newC] == 6) {// 벽일 때
			return;// 재귀 종료
		} else if (arr2[newR][newC] == 0) {// 빈칸일 때에는
			arr2[newR][newC] = -1;// '#' 사각지대가 아님을 표시
		}
		check(newR, newC, dir);// 나아가는 방향으로 한 칸 이동
	}

	static void copyArr() {// 여러번의 방법을 수행하므로
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr2[i][j] = arr[i][j];// arr2에 복사해놓고 재귀 시 사본 사용
			}
		}
	}
}
