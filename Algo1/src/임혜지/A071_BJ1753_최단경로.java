package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A071_BJ1753_최단경로 {
	static class Vertex implements Comparable<Vertex> {// 도착정점과 가중치를 저장하는 클래스
		int end;
		int weight;

		Vertex(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {// 가중치로 오름차순 정렬할 비교 메소드
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int v, e;// 정점 개수, 간선 개수
	static int start;// 시작 정점
	static int minEdge[];// 시작 정점에서 다른 정점까지의 최단 거리
	static ArrayList<Vertex> g[];// 인접리스트

	static PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();// 최단 경로 뽑아낼 우선순위 큐

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력

		v = Integer.parseInt(st.nextToken());// 정점 개수 저장
		e = Integer.parseInt(st.nextToken());// 간선 개수 저장

		g = new ArrayList[v];// 인접리스트 배열 생성
		for (int i = 0; i < v; i++) {
			g[i] = new ArrayList<>();// 각 정점별 인접리스트 생성
		}

		start = Integer.parseInt(in.readLine()) - 1;// 시작정점 입력(문제에서는 1번부터 시작하므로 -1)

		for (int i = 0; i < e; i++) {// 간선의 개수만큼
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			int u = Integer.parseInt(st.nextToken()) - 1;// 출발정점 저장
			int v = Integer.parseInt(st.nextToken()) - 1;// 도착정점 저장
			int w = Integer.parseInt(st.nextToken());// 가중치 저장

			g[u].add(new Vertex(v, w));// 인접리스트에 도착정점과 가중치 저장
		}

		// 입력 끝
		// -------------------------
		minEdge = new int[v];// 최단경로 구할 배열 생성

		Arrays.fill(minEdge, Integer.MAX_VALUE);// 최단경로 구할 배열 최대값으로 초기화
		minEdge[start] = 0;// 시작점에서 시작점까지의 최단경로 0
		pq.offer(new Vertex(start, 0));// 도착정점까지의 경로 0인 출발정점을 큐에 저장

		while (!pq.isEmpty()) {// 큐가 비어있지 않을 동안
			Vertex minVertex = pq.poll(); // PQ 에서 간선비용이 최소인 정점 뽑고

			int from = minVertex.end;// 그 도착정점들 저장

			for (Vertex v : g[from]) { // 인접한 노드에 대해 간선비용 최소값으로 갱신
				if (v.weight != 0 && minEdge[v.end] > v.weight + minEdge[from]) {// 이동 가능하면서, 최소값 업데이트가 가능하면
					minEdge[v.end] = v.weight + minEdge[from];// 최소값 업데이트하고
					pq.offer(new Vertex(v.end, v.weight + minEdge[from]));// 큐에 저장
				}
			}
		}
		for (int i = 0; i < v; i++) {
			System.out.println(minEdge[i] == Integer.MAX_VALUE ? "INF" : minEdge[i]);// 최단경로 출력 (업데이트 안되었다면 INF 출력)
		}
	}
}
