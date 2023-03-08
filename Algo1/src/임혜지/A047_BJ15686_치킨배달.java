package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A047_BJ15686_치킨배달 {
	static int n, m;// 도시의 크기, 최대 치킨집 개수
	static ArrayList<Node> house;// 집 위치 리스트
	static ArrayList<Node> chicken;// 치킨집 위치 리스트
	static int[] order;// 치킨집 m개 고르는 조합 배열
	static int res;// 결과로 출력할 도시 치킨거리 최소값

	static class Node {// 행열 인덱스를 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());// 도시 크기 입력
		m = Integer.parseInt(st.nextToken());// 치킨집 최대 개수 입력
		house = new ArrayList<>();// 집 위치 리스트 생성
		chicken = new ArrayList<>();// 치킨집 위치 리스트 생성

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());// 도시 데이터 입력
				if (temp == 1)// 만약 1이라면
					house.add(new Node(i, j));// 그 위치를 집 리스트에 추가
				if (temp == 2)// 2라면
					chicken.add(new Node(i, j));// 그 위치를 치킨집 리스트에 추가
			}
		}
		// 입력끝
		// -----------------------------
		order = new int[m];// m개의 조합을 입력받을 배열 생성
		res = Integer.MAX_VALUE;// 최소값 구하기 위한 초기화
		com(0, 0);// 조합 호출
		System.out.println(res);// 도시의 치킨거리 최소값 출력
	}

	static void com(int cnt, int start) {
		if (cnt == m) {// m개의 숫자를 구했을 때
			getMin(order);// 치킨거리 최소값 구할 메소드 호출
			return;// 재귀 종료
		}

		for (int i = start; i < chicken.size(); i++) {
			order[cnt] = i;
			com(cnt + 1, i + 1);
		}
	}

	static void getMin(int[] order) {// 도시의 치킨거리 최소값 구할 메소드
		int sum = 0;// 도시의 치킨거리(각 집의 치킨거리 합)

		for (Node h : house) {// 모든 집에 대해
			int distance = Integer.MAX_VALUE;// 각 집의 거리 최소값 구하기 위한 초기화
			for (int i = 0; i < order.length; i++) {// m개의 숫자에 대해
				if (getDistance(chicken.get(order[i]), h) < distance) {// 더 짧은 거리가 등장할 때
					distance = getDistance(chicken.get(order[i]), h);// 치킨거리 업데이트
				}
			}
			sum += distance;// 치킨거리 합
		}
		res = Math.min(res, sum);// 최소값 업데이트
	}

	static int getDistance(Node a, Node b) {// 두 노드 사이의 거리 구할 메소드
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}
}
