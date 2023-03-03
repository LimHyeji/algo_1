package 임혜지;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Prim과 Diijkstra 차이점
 * 
 * - Prim은 구한 정점을 포함한 그래프에서, 다른 정점으로의 최소 거리를 구함 ( 모든 정점 탐색)
 * 
 * - Diijkstra는 각 정점에서, 다른 정점으로의 최소 거리를 구함 (시작정점과 도착정점 설정)
 */

/*
 * 1. 슈팅
 * 
 * (1) 맨 윗칸(최대 12개 중에서 하나 고르기)
 * 
 * (2) 고른 후, 제거 시 1칸은 무시, 상하좌우로 n-1칸만큼 이동하면서 0으로 바꾸고 2 이상의 값 나올 때 저장
 * 
 * 저장된 값 꺼내서 상하좌우로 n-1칸만큼 이동하면서 모두 0으로 만들고 2 이상인 것 찾기 반복
 * 
 * -> 네 방향이 모두 끝에 도달할 경우 종료
 * 
 * (1), (2) 반복
 * 
 * 2. 벽돌 떨어지기
 *
 * 한 열에 남아 있는 값들 모두 열 아래에서부터 채우기
 * 
 * 3. 남은 벽돌의 개수 구하기
 */

public class A073_SWEA5656_벽돌깨기_과제14 {
	static class Node {// 큐에 저장할 행열 인덱스와 그 값
		int row, col;
		int data;

		Node(int row, int col, int data) {
			this.row = row;
			this.col = col;
			this.data = data;
		}
	}

	static int t;// 테스트 케이스 수
	static int n, w, h;// 슈팅 횟수, 맵의 가로세로 크기
	static int map[][];// 맵
	static int map2[][];// 맵 백업할 이차원 배열
	static int order[];// 완전탐색을 위해 슈팅의 순서 정할 배열
	static Queue<Node> q;// 연달아 터지는 블럭 위치 저장
	static int res;// 결과로 출력할 최소값
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력받을 준비
		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력

		for (int tc = 1; tc <= t; tc++) {// t번의 테스트 케이스

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			n = Integer.parseInt(st.nextToken());// 슈팅횟수 입력
			w = Integer.parseInt(st.nextToken());// 맵의 가로 크기 입력
			h = Integer.parseInt(st.nextToken());// 맵의 세로 크기 입력

			map = new int[h][w];// 입력받을 맵
			map2 = new int[h][w];// 복사해놓고 사용할 맵
			boolean flag = false;// 맵의 값이 통일되었는지 확인하는 플래그(현재 통일 상태)
			int temp = -1;// 플래그를 위한 변수 선언
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(in.readLine());// 한 줄 입력
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());// 맵 입력
					if (i == 0 && j == 0)// 첫번째 값
						temp = map[i][j];// 저장
					else {// 첫번째 값이 아니면서
						if (temp != map[i][j])// 값이 바뀌었다면
							flag = true;// 변동 플래그 true
					}
				}
			}
			// 입력끝
			// -----------------------------------------
			copyArr(map, map2);// 맵 복사해놓기
			order = new int[n];// 중복순열로 슈팅순서 구하기
			res = Integer.MAX_VALUE;// 최소값 구한 변수 최대로 초기화
			if (flag == false) {// 변동 플래그가 false이면 모든 값이 통일되었으므로
				for (int i = 0; i < n; i++) {
					order[i] = i;// 순서 임의로 정해서
				}
				shoot(0);// 슈팅
			} else// 모든 값이 통일되지 않았다면
				getOrder(0);// 중복 순열부터 구해서 슈팅

			str.append("#").append(tc).append(" ").append(res).append("\n");// 테스트 케이스별 결과 덧붙이기
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void copyArr(int[][] a, int[][] b) {// 배열 백업할 메소드(a를 b에 저장)
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				b[i][j] = a[i][j];
			}
		}
	}

	static void getOrder(int cnt) {// 중복 순열로 슈팅 순서 구할 메소드
		if (cnt == n) {// 모두 구했다면
			copyArr(map2, map);// 백업한 맵 복사받고
			shoot(0);// 슈팅 시작
			return;// 종료
		}
		for (int i = 0; i < w; i++) {
			order[cnt] = i;
			getOrder(cnt + 1);
		}
	}

	static void shoot(int cnt) {// 슈팅하는 메소드
		if (cnt == n || getBlockCnt() == 0) {// 슈팅이 완료되었거나 블럭이 존재하지 않는다면
//			if(res>getBlockCnt()) {
//				printMap();
//			}
			res = Math.min(res, getBlockCnt());// 블럭 개수 최소값 업데이트
			return;// 종료
		}
		q = new ArrayDeque<>();// 큐 생성
		for (int i = 0; i < h; i++) {// 열을 순회하면서
			if (map[i][order[cnt]] != 0 && q.size() == 0) {// 큐가 비었으면(처음 등장하는 블럭이면)
				q.offer(new Node(i, order[cnt], map[i][order[cnt]]));// 큐에 저장하고
				break;// 순회 종료
			}
		}

		if (q.size() == 0) {// 만약 그 열에 블럭이 등장하지 않았다면
			return;// 종료
		}

		Node cur = null;// 현재 꺼내는 노드 변수 선언
		while (!q.isEmpty()) {// 큐가 비어있지 않다면
			cur = q.poll();// 하나 꺼내서
			map[cur.row][cur.col] = 0;// 방문 체크로(해당 블럭을 비우고)

			for (int right = 1; right < cur.data; right++) {// 쓰여있는 값만큼 반복하면서
				if (check(cur.row, cur.col + right))// 2 이상의 블럭이라면
					q.add(new Node(cur.row, cur.col + right, map[cur.row][cur.col + right]));// 큐에 저장
			}
			for (int left = 1; left < cur.data; left++) {// 쓰여있는 값만큼 반복하면서
				if (check(cur.row, cur.col - left))// 2 이상의 블럭이라면
					q.add(new Node(cur.row, cur.col - left, map[cur.row][cur.col - left]));// 큐에 저장
			}
			for (int up = 1; up < cur.data; up++) {// 쓰여있는 값만큼 반복하면서
				if (check(cur.row - up, cur.col))// 2 이상의 블럭이라면
					q.add(new Node(cur.row - up, cur.col, map[cur.row - up][cur.col]));// 큐에 저장
			}
			for (int down = 1; down < cur.data; down++) {// 쓰여있는 값만큼 반복하면서
				if (check(cur.row + down, cur.col))// 2 이상의 블럭이라면
					q.add(new Node(cur.row + down, cur.col, map[cur.row + down][cur.col]));// 큐에 저장
			}

		}

		drop();// 모두 떨어뜨림

		shoot(cnt + 1);// 다음 슈팅으로 넘어감
	}

	static boolean check(int row, int col) {
		if (row < 0 || row >= h || col < 0 || col >= w)// 맵을 벗어나면
			return false;// false 리턴
		if (map[row][col] > 1) {// 1보다 큰 블럭이면
			return true;// 큐에 저장하기 위해 true 리턴
		}
		map[row][col] = 0;// 1이라면 해당 블럭 방문했으므로 0으로 바꿈
		return false;// false 리턴
	}

	static void drop() {// 블럭 떨어뜨리기
		ArrayList<Integer> list;// 리스트 선언
		for (int j = 0; j < w; j++) {// 각 열에 대해
			list = new ArrayList<>();// 리스트 생성하고
			for (int i = h - 1; i >= 0; i--) {// 거꾸로 순회하면서
				if (map[i][j] > 0) {// 블럭이 있다면
					list.add(map[i][j]);// 리스트에 추가하고
					map[i][j] = 0;// 0으로 바꾸기
				}
			}

			if (list.size() == 0)// 리스트가 비었다면(해당 열에 블럭이 존재하지 않는다면)
				continue;
			int idx = h - 1;// 아래에서부터
			for (int i : list) {// 리스트 값
				map[idx--][j] = i;// 모두 저장
			}
		}
	}

	static int getBlockCnt() {// 배열 순회하면서 블럭 개수 카운트
		int blockCnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] > 0)
					blockCnt++;
			}
		}
		return blockCnt;
	}

	static void printMap() {// 디버깅용 맵 프린트 메소드
		for (int a[] : map) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
		System.out.println();
	}
}