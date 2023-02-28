package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A068_SWEA3124_최소스패닝트리_Kruskal {
	static class Edge {
		int from, to, w;

		Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}

	static int find(int a) {
		if (par[a] == a)
			return a;
		return par[a] = find(par[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		par[bRoot] = aRoot;// 경로 압축 필요
		return true;
	}

	static int t, v, e;
	static Edge edgeList[];
	static int par[];

	static StringBuilder str = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		t = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(in.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			edgeList = new Edge[e];

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(in.readLine());
				edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}

			par = new int[v+1];
			for (int i = 0; i < v+1; i++) {
				par[i] = i;
			}

			int res = 0;
			int cnt = 0;
			for (Edge e : edgeList) {
				if (union(find(e.from), find(e.to))) {
					res+=e.w;
					cnt++;
				}
				if(cnt==v-1) {
					break;
				}
			}

			str.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(str.toString());
	}
}
