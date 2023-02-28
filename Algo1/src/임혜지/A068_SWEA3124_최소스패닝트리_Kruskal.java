package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A068_SWEA3124_최소스패닝트리_Kruskal {

	static int[] par;// 대표자 배열
	static int t, v, e;// 입력받을 정점 개수, 간선 개수
	static Edge[] edgeList;// 간선의 시작정점과 끝정점, 가중치 저장할 간선 리스트
	static StringBuilder str = new StringBuilder();// 출력할 문자열

	static class Edge implements Comparable<Edge> {
		int from, to, w;// 시작정점,끝정점,가중치

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {// 가중치로 오름차순 정렬할 비교 메소드
			return Integer.compare(this.w, o.w);
		}

	}

	private static void make() {// 집합 생성하고 초기화하는 메소드
		par = new int[v];// 정점의 개수만큼의 대표자 배열이 생성

		for (int i = 0; i < v; i++) {
			par[i] = i;// 모두 인덱스로 초기화
		}
	}

	private static int find(int a) {// a를 원소로 가지는 집합의 대표자 찾는 메소드
		if (a == par[a])// 찾고있는대표자가 자기자신이라면
			return a;// 그대로 리턴

		return par[a] = find(par[a]);// 못 찾았다면, 대표자 찾기 위해 거슬러 올라감
	}

	private static boolean union(int a, int b) {// a 집합과 b 집합을 합하는 메소드
		int aRoot = find(a);// a 집합의 대표자
		int bRoot = find(b);// b 집합의 대표자
		if (aRoot == bRoot)// 대표자가 이미 같다면
			return false;// union하지 않고 false 리턴

		par[bRoot] = aRoot;// 대표자 업데이트
		return true;// union 완료했으므로 true 리턴
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력받을 준비

		t = Integer.parseInt(br.readLine());// 테스트케이스 수 입력

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			v = Integer.parseInt(st.nextToken());// 정점 개수 입력
			e = Integer.parseInt(st.nextToken());// 간선 개수 입력

			edgeList = new Edge[e];// 간선의 개수만큼 리스트 생성

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken()) - 1;// 입력받은 시작정점에서 1 빼서(인덱스가 0부터 시작하므로) 저장
				int to = Integer.parseInt(st.nextToken()) - 1;// 입력받은 끝정점에서 1 빼서(인덱스가 0부터 시작하므로) 저장
				int w = Integer.parseInt(st.nextToken());// 가중치 저장
				edgeList[i] = new Edge(from, to, w);// 간선정보를 리스트에 저장
			}
			Arrays.sort(edgeList);// 가중치로 오름차순 정렬

			make();// 집합 생성

			int cnt = 0;// 탐색하는 횟수
			long result = 0;// 가중치 총합
			for (Edge edge : edgeList) {// 오름차순 정렬된 모든 간선에 대해
				if (union(edge.from, edge.to)) {// 시작정점과 끝정점 합하는 것이 가능하다면
					result += edge.w;// 그 간선의 가중치 더해주고
					if (++cnt == v - 1)// 탐색횟수가 정점개수-1만큼이라면
						break;// 탐색 종료
				}
			}
			str.append("#").append(tc).append(" ").append(result).append("\n");// 테스트케이스별 결과 저장
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}
}
