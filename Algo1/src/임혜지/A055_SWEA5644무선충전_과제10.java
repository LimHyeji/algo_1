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
		int row, col;// 행과 열

		public Node(int row, int col) {// 위치 좌표 클래스 생성자
			this.row = row;
			this.col = col;
		}
	}

	static class User extends Node {// 사용자의 위치 반영할 클래스
		int[] dir;// 총 m번 이동하는 방향값

		public User(int row, int col, int m) {// 사용자의 위치좌표와 이동횟수 담는 생성자
			super(row, col);
			dir = new int[m];
		}

		public void move(int row, int col) {// 새로운 위치로 이동(맵 벗어날 일 없음)
			this.row = row;
			this.col = col;
		}
	}

	static class Battery extends Node {// 배터리 위치와 정보 반영할 클래스
		int c, p, num;// 배터리의 범위, 성능, 순번

		public Battery(int row, int col, int c, int p, int num) {// 배터리의 위치좌표와 범위,성능,순번 담는 생성자
			super(row, col);
			this.c = c;
			this.p = p;
			this.num = num;
		}
	}

	static int t;// 총 테스트케이스 수
	static int m, a;// 총 이동 시간, 배터리 개수
	static int[] dirRow = { 0, -1, 0, 1, 0 };// 제자리,위쪽,오른쪽,아래쪽,왼쪽
	static int[] dirCol = { 0, 0, 1, 0, -1 };// 제자리,위쪽,오른쪽,아래쪽,왼쪽
	// static User posA, posB;
	static User[] pos;// 두 명의 사용자 이동 방향 담을 배열
	static Battery[] bc;// a개의 배터리 정보 담을 배열
	static int total;// 결과로 출력할, 충천되는 최대 합
	static StringBuilder str = new StringBuilder("");// 결과로 출력할 문자열
	// static List<Battery> userA, userB;
	static List<Battery> user[];// 두 명의 사용자에 대해 그때그때 충전 가능한 배터리 리스트 담을 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 입력 받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {// 총 t개의 테스트 케이스
			st = new StringTokenizer(in.readLine());// 공백으로 구분해 입력받음
			m = Integer.parseInt(st.nextToken());// 총 이동횟수(시간) 입력
			a = Integer.parseInt(st.nextToken());// 배터리 개수 입력

			pos = new User[2];// 두 명의 사용자 배열 생성
			pos[0] = new User(1, 1, m);// a 사용자는 (1,1)에서 시작하고 m번의 이동을 함
			pos[1] = new User(10, 10, m);// b 사용자는 (10,10)에서 시작하고 m번의 이동을 함

			for (int i = 0; i < 2; i++) {// 두명의 사용자에 대해
				st = new StringTokenizer(in.readLine());// 공백으로 구분해 입력받음
				for (int j = 0; j < m; j++) {// 각각 m번의
					pos[i].dir[j] = Integer.parseInt(st.nextToken());// 이동방향 입력받음
				}
			}
			bc = new Battery[a];// a개의 배터리 배열 생성
			int cnt = 0;// 배터리 순번 나타낼 변수
			for (int i = 0; i < a; i++) {// 총 a개의 배터리를
				st = new StringTokenizer(in.readLine());// 공백으로 구분해 입력받음
				int col = Integer.parseInt(st.nextToken());// 열부터 입력받음
				int row = Integer.parseInt(st.nextToken());// 행 입력
				int c = Integer.parseInt(st.nextToken());// 범위 입력
				int p = Integer.parseInt(st.nextToken());// 성능 입력
				bc[i] = new Battery(row, col, c, p, cnt++);// 순번과 함께 생성
			}

			// 입력끝
			// -------------------------------------------------
			total = 0;// 출력할 최대합 0으로 초기화

			user = new ArrayList[2];// 두 명의 사용자에 대한 배열 생성
			user[0] = new ArrayList<Battery>();// a 사용자가 충전 가능한 배터리 저장할 리스트
			user[1] = new ArrayList<Battery>();// b 사용자가 충전 가능한 배터리 저장할 리스트

			check();// t=0일 때부터 시작함
			charge();// t=0일 때의 충전
			for (int i = 0; i < m; i++) {// 총 m번 이동
				pos[0].move(pos[0].row + dirRow[pos[0].dir[i]], pos[0].col + dirCol[pos[0].dir[i]]);// a 사용자 이동
				pos[1].move(pos[1].row + dirRow[pos[1].dir[i]], pos[1].col + dirCol[pos[1].dir[i]]);// b 사용자 이동
				check();// 각 사용자에 대해 충전 가능한 배터리 리스트 입력받고
				charge();// 충전 가능 최대 합 구하기
			}

			str.append("#").append(test_case).append(" ").append(total).append("\n");// 결과 붙이고
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void charge() {// 충전하는 메소드
		if (user[0].size() == 0 && user[1].size() == 0)// 두 사용자 모두 충전 가능한 배터리 없을 때
			return;// 종료
		if (user[0].size() == 0 || user[1].size() == 0) {// 둘 중 한명의 사용자가 충전 가능한 배터리 없을 때
			int idx;// 충전 가능한 사용자 파악 위해 인덱스 생성하고
			if (user[0].size() == 0 && user[1].size() > 0)// 1번 사용자가 충전 가능하면
				idx = 1;// 인덱스 1 저장
			else// 0번 사용자가 충전 가능하면
				idx = 0;// 인덱스 0 저장

			int temp = 0;// 최대 출력 위해 임시 저장할 변수
			for (int i = 0; i < user[idx].size(); i++) {// 충전 가능한 사용자에 대해 리스트 순회하며
				temp = Math.max(temp, user[idx].get(i).p);// 최대 성능을 가진 배터리 찾아서
			}
			total += temp;// 결과에 더함
			return;// 종료
		}

		int tempMax = 0;// 모든 경우에 대해 최대 출력 위해 임시 저장할 변수
		for (int i = 0; i < user[0].size(); i++) {// 0번 사용자가 충전 가능한 배터리와
			for (int j = 0; j < user[1].size(); j++) {// 1번 사용자가 충전 가능한 배터리에 대해 모든 경우 조합
				int temp = 0;// 이 경우에 대해 임시 저장할 변수
				if (user[0].get(i).num == user[1].get(j).num)// 만약 같은 배터리를 공유한다면
					temp = user[0].get(i).p;// 그 배터리를 두 명이서 나눠 가지므로 그대로 저장
				else// 공유하지 않는다면
					temp = user[0].get(i).p + user[1].get(j).p;// 각각의 충전 양 저장

				tempMax = Math.max(tempMax, temp);// 최대값 비교해 업데이트
			}
		}
		total += tempMax;// 최종 최대값을 결과에 더함
	}

	static void check() {// 사용 가능한 배터리가 있는지 확인하는 메소드
		for (int i = 0; i < 2; i++) {// 두 명의 사용자에 대해
			user[i].clear();// 그 전에 사용한 리스트 초기화
			for (int j = 0; j < a; j++) {// a개의 배터리에 대해
				if (cal(pos[i], bc[j]) <= bc[j].c) {// 거리조건을 충족한다면
					user[i].add(bc[j]);// 사용자가 충전가능한 배터리 리스트에 저장
				}
			}
		}
	}

	static int cal(Node a, Node b) {// 거리 계산하는 메소드
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}
}
