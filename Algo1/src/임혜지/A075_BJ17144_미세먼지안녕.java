package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A075_BJ17144_미세먼지안녕 {
	static class Node {// 행열 인덱스를 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int r, c, t;// 방의 세로가로 크기, 시간
	static int[][] arr;// 방
	static int[][] arr2;// 방의 값을 저장해놓을 배열
	static Node first, second;// 위 공기청정기와 아래 공기청정기
	static int[] dirR = { -1, 0, 1, 0 };// 상우하좌(위 공기청정기 방향)
	static int[] dirR2 = { 1, 0, -1, 0 };// 하우상좌(아래 공기청정기 방향)
	static int[] dirC = { 0, 1, 0, -1 };

	static Queue<Node> q;// 미세먼지 값을 저장할 큐

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		r = Integer.parseInt(st.nextToken());// 방의 세로 크기 입력
		c = Integer.parseInt(st.nextToken());// 방의 가로 크기 입력
		t = Integer.parseInt(st.nextToken());// 시간 입력

		arr = new int[r][c];// 방 생성
		arr2 = new int[r][c];// 백업할 방 생성
		boolean flag = false;// 위 공기청정기 입력받기 위한 플래그
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());// 방의 값 입력
				if (arr[i][j] == -1 && flag == false) {// 공기청정기가 처음 등장했으면
					first = new Node(i, j);// 위 공기청정기 저장
					second = new Node(i + 1, j);// 바로 아래행이 아래 공기청정기
					flag = true;// 공기청정기 저장 완료
				}
			}
		}

		// 입력끝
		// -----------------------

		while (t-- > 0) {// 총 t번 반복
			copyArr(arr, arr2);// 배열 백업해놓고
			get();// 미세먼지 위치 저장하고
			spread();// 미세먼지 확산 후
			turnOn();// 공기청정기 가동
		}

		System.out.println(getRemain());// 남은 미세먼지 양 출력
	}

	static void spread() {// 미세먼지 확산하는 메소드
		Node cur = null;

		while (!q.isEmpty()) {// 큐가 빌 때까지
			cur = q.poll();// 미세먼지 하나 꺼내서

			int i = cur.row;
			int j = cur.col;

			int cnt = 0;// 네 방향 중 확산 가능한 방향 개수 셀 변수
			int temp = arr2[i][j] / 5;// 확산하는 양(배열의 값이 바뀌므로 백업된 기존 배열 활용)

			for (int dir = 0; dir < 4; dir++) {// 네 방향에 대해
				int newR = i + dirR[dir];
				int newC = j + dirC[dir];

				if (newR < 0 || newR >= r || newC < 0 || newC >= c || arr[newR][newC] == -1)// 확산한 곳에 대해 배열인덱스를 벗어나거나
																							// 공기청정기가 존재할 경우
					continue;// 다음 방향으로 넘어가기

				arr[newR][newC] += temp;// 확산한 곳에 확산되는 양 더해주기
				cnt++;// 확산한 방향 개수 1 추가
			}

			arr[i][j] -= temp * cnt;// 기존에 남아있는 양에서 확산한 양만큼 빼주기
		}
	}

	static void get() {// 미세먼지 위치들 저장하는 메소드
		q = new LinkedList<>();// 큐 생성
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] != 0 && arr[i][j] != -1) {// 미세먼지에 대해
					q.offer(new Node(i, j));// 그 위치 추가
				}
			}
		}
	}

	static void turnOn() {// 공기청정기 가동하는 메소드
		int fRow = first.row - 1;// 위 공기청정기의 윗칸
		int fCol = first.col;
		int sRow = second.row + 1;// 아래 공기청정기의 아랫칸
		int sCol = second.col;

		for (int dir = 0; dir < 4; dir++) {// 네 방향에 대해

			while (true) {// 위 공기청정기 가동
				int newR = fRow + dirR[dir];
				int newC = fCol + dirC[dir];
				if (newR < 0 || newR > first.row || newC < 0 || newC >= c || arr[newR][newC] == -1)// 위 공기청정기의 가동 영역을
																									// 벗어나거나 공기청정기가 등장하면
					break;// 실행x

				arr[fRow][fCol] = arr[newR][newC];// 이동 방향의 값을 기존 위치에 옮겨 적기
				fRow = newR;// 기존 위치 변경
				fCol = newC;
			}

			while (true) {// 아래 공기청정기 가동
				int newR = sRow + dirR2[dir];
				int newC = sCol + dirC[dir];
				if (newR < second.row || newR >= r || newC < 0 || newC >= c || arr[newR][newC] == -1)// 아래 공기청정기의 가동 영역을
																										// 벗어나거나 공기청정기가
																										// 등장하면
					break;// 실행x

				arr[sRow][sCol] = arr[newR][newC];// 이동 방향의 값을 기존 위치에 옮겨 적기
				sRow = newR;// 기존 위치 변경
				sCol = newC;
			}
		}
		arr[first.row][first.col + 1] = 0;// 공기청정기에서 나가는 방향은 0으로 바꾸기
		arr[second.row][second.col + 1] = 0;// 공기청정기에서 나가는 방향은 0으로 바꾸기
	}

	static int getRemain() {// 남은 미세먼지 개수 셀 메소드
		int cnt = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] == 0 || arr[i][j] == -1) {
					continue;
				}
				cnt += arr[i][j];
			}
		}
		return cnt;
	}

	static void copyArr(int[][] arr, int[][] arr2) {// 배열 백업할 메소드
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
	}
}
