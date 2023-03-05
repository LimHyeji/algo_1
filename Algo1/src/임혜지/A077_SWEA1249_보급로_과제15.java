package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class A077_SWEA1249_보급로_과제15 {
	static class Vertex implements Comparable<Vertex> {// 각 가중치 저장할 정점 클래스
		int row, col;// 행과 열 인덱스
		int w;// 가중치(복구 시간)

		public Vertex(int row, int col, int w) {// 정점 클래스 생성자
			super();
			this.row = row;
			this.col = col;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {// 가중치로 오름차순 정렬할 메소드
			return Integer.compare(this.w, o.w);
		}
	}

	static int t;// 테스트케이스 수
	static int n;// 지도 크기
	static int arr[][];// 지도
	static boolean visit[][];// 탐색 시 사용할 방문 체크 배열
	static PriorityQueue<Vertex> pq;// 탐색 시 사용할 큐
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열
	static int res;// 최소 복구 시간

	static int dirR[] = { -1, 1, 0, 0 };// 상하좌우
	static int dirC[] = { 0, 0, -1, 1 };// 상하좌우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력

		for (int tc = 1; tc <= t; tc++) {// 총 t번의 테스트 케이스
			n = Integer.parseInt(in.readLine());// 지도의 크기 입력
			arr = new int[n][n];// 지도 생성

			for (int i = 0; i < n; i++) {// 총 n개의
				String temp = in.readLine();// 줄 입력
				for (int j = 0; j < n; j++) {
					arr[i][j] = temp.charAt(j) - 48;// char형을 int형으로 변환해 지도에 저장
				}
			}

			// 입력끝
			// ---------------------------------
			visit = new boolean[n][n];// 방문 여부 배열 생성
			pq = new PriorityQueue<>();// 탐색 시 사용할 큐 상성
			res = Integer.MAX_VALUE;// 복구시간 최소값 구하기 위해 최대값으로 초기화

			pq.offer(new Vertex(0, 0, 0));// 시작 정점과 그 가중치 큐에 추가
			visit[0][0] = true;// 방문체크

			while (!pq.isEmpty()) {// 탐색할 정점이 없을 때까지 반복
				Vertex cur = pq.poll();// 현재 큐에 저장된 값 중 최소값 꺼내서

				if (cur.row == n - 1 && cur.col == n - 1) {// 만약 도착정점에 도달했다면
					res = Math.min(res, cur.w);// 최소값 업데이트
				}

				for (int dir = 0; dir < 4; dir++) {// 네 방향에 대해
					int newR = cur.row + dirR[dir];
					int newC = cur.col + dirC[dir];

					if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visit[newR][newC]) {// 나아가고자 하는 방향이 인덱스를 벗어나지
																								// 않고, 탐색하지 않은 곳이라면
						pq.offer(new Vertex(newR, newC, cur.w + arr[newR][newC]));// 그 정점의 위치와 "이전까지 구한 가중치+나아간 정점의 가중치"
																					// pq에 새로 추가
						visit[newR][newC] = true;// 방문 체크
					}
				}
			}
			str.append("#").append(tc).append(" ").append(res).append("\n");// 테스트 케이스별결과 붙이기
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}
}
