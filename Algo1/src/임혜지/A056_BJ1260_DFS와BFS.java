package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class A056_BJ1260_DFS와BFS {
	static int v, e, start;// 정점 개수, 간선 개수, 탐색 시작 노드
	static StringBuilder str = new StringBuilder("");// 결과로 출력할 문자열
	static ArrayList<Integer> graph[];// 인접리스트 이용
	static boolean visited[];// 방문했는지 여부 판단

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 공백으로 구분해 한 라인 입력
		v = Integer.parseInt(st.nextToken());// 정점 개수
		e = Integer.parseInt(st.nextToken());// 간선 개수
		start = Integer.parseInt(st.nextToken());// 탐색 시작 노드

		graph = new ArrayList[v + 1];// v개의 정점에 대한 인접리스트 배열 생성

		for (int i = 0; i <= v; i++) {// 각 정점에 대한
			graph[i] = new ArrayList<>();// 리스트 생성
		}

		for (int i = 0; i < e; i++) {// 간선의 개수만큼
			st = new StringTokenizer(in.readLine());// 한 라인 입력
			int from = Integer.parseInt(st.nextToken());// 출발 정점
			int to = Integer.parseInt(st.nextToken());// 도착 정점
			graph[from].add(to);//두 정점에 대한 연결
			graph[to].add(from);//두 정점에 대한 연결
		}
		for (int i = 0; i <= v; i++) {//각 인접리스트를 오름차순으로 탐색하기 위해
			Collections.sort(graph[i]);//정렬
		}
		visited = new boolean[v + 1];//dfs에 대한 방문 여부 판단 배열 생성
		dfs(start);//정점 start부터의 dfs 순회
		str.append("\n");//dfs 재귀 마친 후 bfs 순회를 위해 개행문자 append

		visited = new boolean[v + 1];//bfs에 대한 방문 여부 판단 배열 생성
		bfs();//정점 start부터의 bfs 순회
		System.out.println(str.toString());//결과 한꺼번에 출력
	}

	static void dfs(int cur) {//현재 정점에 대해 dfs
		visited[cur] = true;//현재 정점 방문
		str.append(cur).append(" ");//현재 정점 탐색
		for (int i : graph[cur]) {//현재 정점과 인접한 정점들에 대해 
			if (!visited[i]) {//아직 탐색하지 않았다면
				dfs(i);//dfs 순회
			}
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(start);
		visited[start] = true;

		int cur = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			str.append(cur).append(" ");
			for (int i : graph[cur]) {
				if (!visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		str.append("\n");
	}
}
