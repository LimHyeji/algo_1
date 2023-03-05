package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A082_SWEA1949_등산로 {
	static int N, K;
	static int result; // 최대 등산로 길이를 저장할 변수
	static int[][] map; // 등산로 지도 정보
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 }; // 4방 탐색을 위한 델타

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];
			int top = 0; // 가장 높은 봉우리 높이 저장 변수
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]); // 가장 높은 봉우리의 높이 찾아주기
				}
			}
			// ---------INPUT END-------------------
			// 가장 높은 봉우리를 찾아서 넣어주기
			List<int[]> start = new ArrayList<>();
			result = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == top) {
						start.add(new int[] { i, j });
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k <= K; k++) {
						map[i][j] -= k;
						for (int s = 0; s < start.size(); s++) {
							int[] point = start.get(s);
							int r = point[0];
							int c = point[1];
							dfs(r, c, map[r][c], 1);
						}
						map[i][j] += k;
					}
				}
			}

			System.out.println("#" + t + " " + result);
		}
	}

	static void dfs(int r, int c, int height, int count) {
		result = Math.max(result, count); // 최대 등산로 길이 갱신

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) { // 경계 체크
				if (height > map[nr][nc]) {// 다음 위치의 높이가 지금보다 낮으면 : 등산로 조성 가능
					visited[nr][nc] = true;
					dfs(nr, nc, map[nr][nc], count + 1);
					visited[nr][nc] = false;
				}

			}
		}
	}
}
