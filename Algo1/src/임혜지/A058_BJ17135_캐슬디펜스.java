package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 궁수 세 명 "동시" 공격
 * 2. d 이하인 적 중에서 가장 가까운 적
 * 			-> 여럿일 경우 가장 왼쪽
 * 3. 공격 이후 적 한칸 아래 이동(1이 없을 경우 게임 끝)
 */

public class A058_BJ17135_캐슬디펜스 {
	static int n, m, d;// 맵의 행,열 크기, 거리
	static char[][] map;// 맵
	static Archer archer[] = new Archer[3];// 세 명의 궁수
	static ArrayList<Enemy> enemy = new ArrayList<>();// 적의 리스트 생성

	static int maxKill = 0;

	static class Node {// 위치 좌표 나타낼 클래스
		int row, col;// 행과 열의 인덱스

		public Node(int row, int col) {// Node의 생성자
			this.row = row;
			this.col = col;
		}
	}

	static class Archer extends Node {// 위치 좌표와 함께 궁수 정보 저장할 클래스

		public Archer(int row, int col) {
			super(row, col);
		}
	}

	static class Enemy extends Node {// 위치 좌표와 함께 적 정보 저장할 클래스

		public Enemy(int row, int col) {
			super(row, col);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력받고

		n = Integer.parseInt(in.readLine());// 맵의 행 크기
		m = Integer.parseInt(in.readLine());// 맵의 열 크기
		d = Integer.parseInt(in.readLine());// 공격 가능 거리
		// map = new char[n][m];//n by m 크기의 맵 생성

		for (int i = 0; i < n; i++) {// n개의 행 중에서
			st = new StringTokenizer(in.readLine());// i번째 행 입력받고
			for (int j = 0; j < m; j++) {// 총 m개의 열 중에서
				map[i][j] = st.nextToken().charAt(0);// j번째 열 저장
				if (map[i][j] == '1') {// 만약 적이라면
					enemy.add(new Enemy(i, j));// 적의 리스트에 저장
				}
			}
		}
	}

	static int cal(Node a, Node b) {// 두 위치 좌표의 거리를 계산할 메소드
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}

	static void com(int cnt, int start) {// 세 명의 궁수의 위치를 정할 조합 메소드
		if (cnt == 3) {// 세 명의 위치가 완성되면
			game();// 게임 시작
			return;// 조합 종료
		}
		for (int i = start; i < m; i++) {
			archer[cnt] = new Archer(n, i);// 세 명의 좌표를 정함(조합)
			com(cnt + 1, i + 1);// 다음 궁수의 좌표 정하기 위해 재귀 호출
		}
	}

	static void game() {
		/*
		 * 1. 거리가 d 이하인지 체크 2. 가장 가까운지 체크 3. 여러명이면 가장 왼쪽에 있는지 체크 4.
		 * 
		 * 우선순위 큐 : 정렬(거리-> 열인덱스) , 위치 저장해서 0 만들기 타입?
		 * 
		 * 
		 * 1. 적은 한칸 아래 이동 2. 맵 벗어나면 (>=n이면) enemy-- 3.
		 * 
		 * -> 제거 가능 최대 수(전체 적 - 맵 벗어난 적) or (kill)
		 */
		while (enemy.size() > 0) {// 적이 있을 동안에만 턴

			for (int i = n - 1; i >= 0; i--) {//행을 거꾸로 순회하면서
				for (int j = 0; j < m; j++) {
					if (i == n - 1 && map[i][j] == 1) {//만약 마지막 행에 적이 있을 경우에는
						map[i][j] = 0;//적이 이동하므로, 0으로 바꾸고
						//enemy.remove()--;
					} else if (i != n - 1 && map[i][j] == 1) {
						map[i + 1][j] = 1;
						map[i][j] = 0;
					}
				}
			}
		}
	}
}
