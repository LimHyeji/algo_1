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
 * 1번 : 한 방향
 * 2번, 3번 : 두 방향(반대방향, 직각방향)
 * 4번 : 세 방향
 * 5번 : 네 방향
 * 
 * 90도 회전 가능
 * 
 * -> 사각지대 최소 크기 구하기
 */

public class A065_BJ15683_감시 {
	static class Node {// cctv위치와 번호
		int row, col, num;

		public Node(int row, int col, int num) {
			this.row = row;
			this.col = col;
			this.num = num;
		}
	}

	static int n, m;// 세로가로 크기
	static int res;// 사각지대 최소 크기
	static int arr[][];// 사무실
	static ArrayList<Node> cctv;// cctv 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];// 사무실 생성
		cctv = new ArrayList<>();// cctv 리스트 생성

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {// 1번부터 5번이면 cctv
					cctv.add(new Node(i, j, arr[i][j]));// 위치와 입력받은 번호 저장
				}
			}
		}
		// 입력끝
		// --------------------------
		res = Integer.MAX_VALUE;// 최소값 구하기 위해 최대값으로 초기화
		sol(0);
		System.out.println(res);// 사각지대 최소값 출력
	}

	static void sol(int cnt) {
		if (cnt == cctv.size()) {// cctv 개수만큼 재귀 돌았으면
			// 최소값 구하기
			return;
		}

		int row = cctv.get(cnt).row;// 편의를 위해 탐색 중인 cctv의 위치와
		int col = cctv.get(cnt).col;
		int num = cctv.get(cnt).num;// 번호 저장

		if (num == 1) {

		} else if (num == 2) {

		} else if (num == 3) {

		} else if (num == 4) {

		} else if (num == 5) {

		}
	}

}
