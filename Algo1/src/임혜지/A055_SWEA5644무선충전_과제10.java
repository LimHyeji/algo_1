package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 10 by 10 지도, 사용자 두 명 A(1,1) B(10,10) ---> 인덱스 1부터 10까지
 * 위치좌표, 충전범위 c, 성능 p
 * 
 * 1. bc와 거리가 c(충전범위) 이하이면 접속 가능
 * 2. 초(t)마다 사용자 이동(0초부터 시작)
 * 		-> 접속한 bc의 p(성능)만큼 충전 가능
 * 		-> 단, 두 명 이상이 접속 시 균등 분배
 * 3. 모든 사용자가 충전한 양의 합 최대 구하기
 */

public class A055_SWEA5644무선충전_과제10 {
	static class Node {// 위치좌표 나타내는 클래스
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static class User extends Node {// 사용자의 위치 반영할 클래스
		int[] dir;

		public User(int row, int col, int m) {
			super(row, col);
			dir = new int[m];
		}

		public void move(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static class Battery extends Node {
		int c, p, num;

		public Battery(int row, int col, int c, int p, int num) {
			super(row, col);
			this.c = c;
			this.p = p;
			this.num = num;
		}
	}

	static int t;
	static int m, a;// 총 이동 시간, 배터리 개수
	static int[] dirRow = { 0, -1, 0, 1, 0 };
	static int[] dirCol = { 0, 0, 1, 0, -1 };
	// static User posA, posB;
	static User[] pos;
	static Battery[] bc;
	static int total;
	static StringBuilder str = new StringBuilder("");
	// static List<Battery> userA, userB;
	static List<Battery> user[];// 초기화 문제

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		t = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());

			pos = new User[2];
			pos[0] = new User(1, 1, m);
			pos[1] = new User(10, 10, m);

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < m; j++) {
					pos[i].dir[j] = Integer.parseInt(st.nextToken());
				}
			}
			bc = new Battery[a];
			int cnt = 0;
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(in.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new Battery(row, col, c, p, cnt++);
			}

			// 입력끝
			// -------------------------------------------------

			total = 0;

			user = new ArrayList[2];//초기화 문제
			user[0] = new ArrayList<Battery>();
			user[1] = new ArrayList<Battery>();

			for (int i = 0; i < m; i++) {
				charge();
				pos[0].move(pos[0].row + dirRow[pos[0].dir[i]], pos[0].col + dirCol[pos[0].dir[i]]);
				pos[1].move(pos[1].row + dirRow[pos[1].dir[i]], pos[1].col + dirCol[pos[1].dir[i]]);
			}

			str.append("#").append(test_case).append(" ").append(total).append("\n");
		}
		System.out.println(str.toString());
	}

	static void charge() {
		if (user[0].size() == 0 && user[1].size() == 0)
			return;
		if (user[0].size() == 0 || user[1].size() == 0) {
			int idx;
			if (user[0].size() == 0 && user[1].size() != 0)
				idx = 0;
			else
				idx = 1;

			int temp = 0;
			for (int i = 0; i < user[idx].size(); i++) {
				temp = Math.max(temp, user[idx].get(i).p);
			}
			total += temp;
			return;
		}

		int tempMax = 0;
		for (int i = 0; i < user[0].size(); i++) {
			for (int j = 0; j < user[1].size(); j++) {
				int temp = 0;
				if (user[0].get(i).num == user[1].get(i).num)
					temp = user[0].get(i).p;
				else
					temp = user[0].get(i).p + user[1].get(i).p;

				tempMax = Math.max(tempMax, temp);
			}
		}
		total += tempMax;
	}

	static void check() {
		for (int i = 0; i < 2; i++) {
			user[i].clear();
			for (int j = 0; j < a; j++) {
				if (cal(pos[i], bc[j]) <= bc[j].c) {
					user[i].add(bc[i]);
				}
			}
		}
	}

	static int cal(Node a, Node b) {
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}
}
