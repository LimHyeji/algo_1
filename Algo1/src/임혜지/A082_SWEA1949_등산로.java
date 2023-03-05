package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A082_SWEA1949_등산로 {
	static int t, n, k;// 테스트케이스 수,지도 크기, 최대공사가능깊이
	static int res; // 최대 등산로 길이를 저장할 변수
	static int[][] map; // 등산로 지도
	static boolean[][] visited;// 탐색 위한 방문 배열
	static List<Node> start;// 가장 높은 봉우리 찾아서 넣을 리스트
	static int[] dr = { -1, 1, 0, 0 };// 상하좌우
	static int[] dc = { 0, 0, -1, 1 }; // 상하좌우
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	static class Node {// 행열 위치 담을 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());// 테스트케이스 수 입력
		for (int tc = 1; tc <= t; tc++) {// 총 t번의 테스트케이스
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());// 지도 크기 입력
			k = Integer.parseInt(st.nextToken());// 공사 가능 깊이 입력

			map = new int[n][n];// 지도 생성
			visited = new boolean[n][n];// 방문 배열 생성
			int top = 0; // 가장 높은 봉우리 높이 저장할 변수
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());// 지도 입력
					top = Math.max(top, map[i][j]); // 가장 높은 봉우리의 높이 찾아주기
				}
			}
			// 입력끝
			// ----------------------------

			start = new ArrayList<>();// 가장 높은 봉우리 찾아서 넣을 리스트 생성
			res = Integer.MIN_VALUE;// 최대값 찾기 위한 초기화
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == top) {// 가장 높은 봉우리이면
						start.add(new Node(i, j));// 리스트에 추가
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int l = 0; l <= k; l++) {
						map[i][j] -= l;// 공사가능 깊이만큼 빼고나서
						for (int s = 0; s < start.size(); s++) {
							Node point = start.get(s);// 현재 보고 있는 봉우리
							int r = point.row;
							int c = point.col;
							dfs(r, c, map[r][c], 1);// 그 봉우리에 대해 탐색 시작
						}
						map[i][j] += l;// 원상복구 시키기
					}
				}
			}
			str.append("#").append(tc).append(" ").append(res).append("\n");// 테스트케이스별 결과 붙이기
		}
		System.out.println(str);// 결과 한꺼번에 출력
	}

	static void dfs(int r, int c, int height, int count) {// 현재 탐색중인 행열 위치와 그 높이, 재귀 횟수
		res = Math.max(res, count); // 최대 등산로 길이 업데이트

		for (int d = 0; d < 4; d++) {// 네 방향에 대해
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visited[newR][newC]) { // 인덱스를 벗어나지 않고, 방문하지 않았다면
				if (height > map[newR][newC]) {// 다음 위치의 높이가 지금보다 낮으면 등산로 조성 가능하므로
					visited[newR][newC] = true;// 방문체크하고
					dfs(newR, newC, map[newR][newC], count + 1);// 해당위치에 대해 탐색
					visited[newR][newC] = false;// 재귀 돌고 난 이후 방문 풀어주기
				}

			}
		}
	}
}
