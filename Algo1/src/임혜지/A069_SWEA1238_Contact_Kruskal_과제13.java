package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A069_SWEA1238_Contact_Kruskal_과제13 {
	static ArrayList<Integer> g[];
	static int n, start;
	static boolean visit[];
	static int res;
	static StringBuilder str = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			g = new ArrayList[101];
			for (int i = 0; i <= 100; i++) {
				g[i] = new ArrayList<>();
			}

			st = new StringTokenizer(in.readLine());

			for (int i = 0; i < n / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				g[from].add(to);
			}

			res = 0;
			visit = new boolean[101];
			bfs();

			str.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(str.toString());
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(start);
		visit[start] = true;

		int cur = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			//
			for (int i : g[cur]) {
				if (!visit[i]) {
					q.offer(i);
					visit[i] = true;
				}
			}
		}

	}
}
