package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class A057_BJ1697_숨바꼭질 {
	static int n, k;// 수빈이가 있는 위치와 동생이 있는 위치
	static ArrayList<Integer> g[];// 인접리스트

	static class Node {// 각 위치와 이동횟수 저장할 클래스
		int x;
		int cost;

		public Node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		n = Integer.parseInt(temp[0]);// 수빈이의 위치 입력
		k = Integer.parseInt(temp[1]);// 동생의 위치 입력

		g = new ArrayList[100001];// 인접리스트 생성
		for (int i = 0; i < 100001; i++) {// 각 정점에 대해
			g[i] = new ArrayList<>();// 생성
		}

		System.out.println(sol());// 결과(최단경로) 출력
	}

	static int sol() {
		Queue<Node> q = new ArrayDeque<>();// bfs 순회를 위한 큐 생성
		boolean visited[] = new boolean[100001];// 방문 여부 확인할 플래그 생성

		q.offer(new Node(n, 0));// 첫번째 노드 큐에 저장
		visited[n] = true;// 첫번째 노드 방문 체크

		Node cur = null;// 큐에서 꺼낼 노드 변수
		while (!q.isEmpty()) {// 방문할 곳이 없을 때까지 반복
			cur = q.poll();// 큐에서 방문할 수 있는 노드 꺼내서

			if (cur.x == k) {// 만약 위치가 찾는 곳과 같다면
				return cur.cost;// 그 결과(최단경로) 리턴
			}

			if (cur.x - 1 >= 0 && !visited[cur.x - 1]) {// 인덱스 범위 넘어가지 않으면서 방문한 적 없는 노드일 때
				q.offer(new Node(cur.x - 1, cur.cost + 1));// 큐에 위치와 함께 이동횟수 1 추가한 값 저장하고
				visited[cur.x - 1] = true;// 방문 체크
			}

			if (cur.x + 1 < 100001 && !visited[cur.x + 1]) {// 인덱스 범위 넘어가지 않으면서 방문한 적 없는 노드일 때
				q.offer(new Node(cur.x + 1, cur.cost + 1));// 큐에 위치와 함께 이동횟수 1 추가한 값 저장하고
				visited[cur.x + 1] = true;// 방문 체크
			}

			if (cur.x * 2 < 100001 && !visited[cur.x * 2]) {// 인덱스 범위 넘어가지 않으면서 방문한 적 없는 노드일 때
				q.offer(new Node(cur.x * 2, cur.cost + 1));// 큐에 위치와 함께 이동횟수 1 추가한 값 저장하고
				visited[cur.x * 2] = true;// 방문 체크
			}

		}
		return -1;// 예외 경우
	}
}
