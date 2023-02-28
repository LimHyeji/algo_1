package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class A069_SWEA1238_Contact_Kruskal_과제13 {

	static class Current implements Comparable<Current> {// 현재의 정점 번호와 트리 depth 저장할 클래스
		int cur, depth;

		Current(int cur, int depth) {
			this.cur = cur;
			this.depth = depth;
		}

		@Override
		public int compareTo(Current o) {// 깊이로 우선 내림차순 정렬 후, 정점번호로 내림차순 정렬할 비교 메소드
			if (this.depth != o.depth) {// 깊이값이 서로 다를 경우
				return Integer.compare(o.depth, this.depth);// 깊이가 더 큰 값이 우선순위
			} else {// 깊이값이 서로 같을 경우
				return Integer.compare(o.cur, this.cur);// 정점 번호가 더 큰 값이 우선순위
			}
		}
	}

	static ArrayList<Integer> g[];// 인접리스트 활용한 그래프
	static boolean visit[];// 탐색 여부
	static int n, start;// 입력값의 수, 탐색 시작 노드
	static PriorityQueue<Current> list;// 탐색 중인 노드 저장하고 정렬할 우선순위 큐
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력 받을 준비

		for (int tc = 1; tc <= 10; tc++) {// 총 10번의 테스트 케이스
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			n = Integer.parseInt(st.nextToken());// 입력 값의 개수
			start = Integer.parseInt(st.nextToken());// 탐색 시작할 노드번호

			g = new ArrayList[101];// 인접리스트 활용한 그래프 생성
			for (int i = 0; i <= 100; i++) {// 각 정점번호에 대한
				g[i] = new ArrayList<>();// 리스트 생성
			}

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			for (int i = 0; i < n / 2; i++) {// from,to로 구분하므로 총 n/2번 반복
				int from = Integer.parseInt(st.nextToken());// 출발정점
				int to = Integer.parseInt(st.nextToken());// 도착정점
				g[from].add(to);// 인접리스트에 저장
			}

			visit = new boolean[101];// 탐색 여부 배열 생성
			list = new PriorityQueue<>();// 탐색 노드 저장하고 정렬할 우선순위 큐 생성
			bfs(start, 0);// start 번호부터 탐색 시작

			str.append("#").append(tc).append(" ").append(list.poll().cur).append("\n");// 테스트 케이스별 결과 저장
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void bfs(int cur, int depth) {
		Queue<Current> q = new ArrayDeque<>();// bfs 탐색 위한 큐 생성

		q.offer(new Current(cur, depth));// 현재 탐색 중인 노드 큐에 추가
		list.offer(new Current(cur, depth));// 현재 탐색 중인 노드 우선순위 큐에 추가
		visit[cur] = true;// 현재 탐색 중인 노드 방문 체크

		Current current = null;// 탐색할 노드 받을 임시 변수
		while (!q.isEmpty()) {// 탐색할 노드가 없을 때까지
			current = q.poll();// 큐에서 한개의 노드 빼서
			int c = current.cur;// 각각 정점 번호와
			int d = current.depth;// 탐색중인 깊이 저장

			for (int e : g[c]) {// 인접한 정점들에 대해
				if (!visit[e]) {// 탐색하지 않은 정점은
					q.offer(new Current(e, d + 1));// 탐색하기 위해 큐에 저장
					list.offer(new Current(e, d + 1));// 탐색한 노드로 우선순위 큐에 저장
					visit[e] = true;// 방문 체크
				}
			}
		}
	}
}
