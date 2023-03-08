package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A058_BJ17135_캐슬디펜스 {
	static class Node {// 행열 위치를 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

		void update(int row, int col) {// 객체의 위치 업데이트할 메소드
			this.row = row;
			this.col = col;
		}
	}

	static int n, m, d;// 격자판 크기, 제한 거리
	static int[][] arr;// 격자판
	static int[] order;// 궁수 배치할 열 인덱스 순서
	static int res;// 결과로 출력할 제거 가능 적의 최대수

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());// 격자판 행 크기 입력
		m = Integer.parseInt(st.nextToken());// 격자판 열 크기 입력
		d = Integer.parseInt(st.nextToken());// 제한 거리 입력

		arr = new int[n][m];// 격자판 생성
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());// 격자판 입력
			}
		}

		// 입력 끝
		// ----------------------------------
		res = 0;// 제거 가능 적의 최대수를 구하기 위해 0으로 초기화
		order = new int[3];// 궁수 배치 경우의 수 구할 배열 생성
		order(0, 0);// 궁수 위치 구하기
		System.out.println(res);// 결과 출력
	}

	static void order(int cnt, int start) {// 총 3개의 값을 구하기 위해 cnt로 체크, 조합 구하기 위한 start 변수
		if (cnt == 3) {// 3번 재귀를 돌았을 경우
			simul(order, copyArr());// 가지고 있는 궁수 배치 경우의 수로 시뮬레이션
			return;// 재귀 종료
		}

		for (int i = start; i < m; i++) {
			order[cnt] = i;
			order(cnt + 1, i + 1);
		}
	}

	static boolean check(int[][] arr) {// 현재 격자판에 적이 존재하는지 확인하는 메소드
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1)// 적이 존재한다면
					return false;// false 리턴
			}
		}
		return true;// 격자판에서 적이 모두 제외되었을 때 true 리턴
	}

	static int getDistance(Node a, Node b) {// 궁수와 적 사이의 거리를 구할 메소드
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}

	static Node getTarget(int order, int[][] arr) {// 제거 가능한 적 구할 메소드
		Node archer = new Node(n, order);// 현재 궁수 위치
		Node target = new Node(0, 0);// 적 위치 초기화
		int distance = Integer.MAX_VALUE;// 거리의 최소값 구하기 위해 max 값 초기화

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int curD = getDistance(new Node(i, j), archer);// 현재 궁수에서의 거리
				if (arr[i][j] == 1 && curD < distance && curD <= d) {// 그 위치에 적이 존재하면서, 기존 최소 거리보다 가까우면서, 제한 거리 d이하이면
					target.update(i, j);// 타겟 위치 업데이트
					distance = curD;// 최소 거리 업데이트
				} else if (arr[i][j] == 1 && curD == distance) {// 적이 존재하면서, 기존 최소 거리와 같은 거리에 있으면
					if (j < target.col)// 현재 보고있는 적이 더 왼쪽에 있을 때
						target.update(i, j);// 타겟 위치 업데이트
				}
			}
		}

		if (distance == Integer.MAX_VALUE) {// 타겟이 업데이트 되지 않았을 경우
			target = null;// 타겟을 null로 초기화
		}
		return target;// 타겟 리턴
	}

	static void simul(int[] order, int[][] arr) {// 적이 모두 제외될 때까지 제거 가능 적의 최대수 업데이트할 메소드, 궁수 배치 가능한 경우의 수(조합)만큼 호출됨
		int cnt = 0;// 현재 상황에서의 제거 가능 적의 수 카운트할 변수

		while (!check(arr)) {// 적이 격자판에 존재할 때
			Node[] targetList = new Node[3];// 세 명의 궁수에 대한 타겟리스트 생성하고
			for (int i = 0; i < 3; i++) {
				targetList[i] = getTarget(order[i], arr);// 타겟 구하기
			}
			for (int i = 0; i < 3; i++) {
				if (targetList[i] != null) {// 제거 가능한 타겟이 있을 때
					if (arr[targetList[i].row][targetList[i].col] != 0) {// 적이 이미 제거되지 않았다면
						arr[targetList[i].row][targetList[i].col] = 0;// 제거 후
						cnt++;// 제거 가능 적의 수 1 증가
					}
				}
			}

			move(arr);// 적이 한칸 아래씩 이동
		}
		res = Math.max(res, cnt);// 현재 상황에서의 제거 가능 적의 수가 더 작으면 업데이트
	}

	static void move(int[][] arr) {// 적이 한칸 아래로 이동할 메소드
		for (int i = n - 1; i > 0; i--) {// 맨 아래줄부터 위에서 두번째 줄까지 총 n-1번 반복
			for (int j = 0; j < m; j++) {
				arr[i][j] = arr[i - 1][j];// 자신의 윗칸에 있는 값으로 업데이트하고
				arr[i - 1][j] = 0;// 자신의 윗칸은 0으로 초기화
			}
		}
	}

	static int[][] copyArr() {// 시뮬레이션을 여러번 돌리기 위해서 배열을 복사하는 메소드
		int[][] arr2 = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr2[i][j] = arr[i][j];
			}
		}

		return arr2;
	}
}
