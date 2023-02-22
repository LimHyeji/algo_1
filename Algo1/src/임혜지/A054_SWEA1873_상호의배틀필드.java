package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
 */

/*
 * 전차 한대의 위치 찾기
 * 1. 전차 이동 불가	-> 게임 맵 밖
 * 					-> *, #, -일 경우(.가 아닌 경우)
 * 2. 포탄 발사 	-> # 앞까지나 게임 맵 밖까지 이동
 * 				-> *일 경우, * -> . 변경
 *  
 */
public class A054_SWEA1873_상호의배틀필드 {
	static class Car {// 전차에 대한 클래스
		int row, col;// 전차의 위치
		int dir;// 방향배열의 인덱스

		public Car(int row, int col, int dir) {// 전차에 대한 생성자
			this.row = row;// 전차의 행 위치
			this.col = col;// 전차의 열 위치
			this.dir = dir;// 전차의 방향
		}

		public void changeDir(int dir) {// 전차의 방향을 바꿈
			this.dir = dir;// 방향 배열의 인덱스
			map[row][col] = direction[dir];
		}

		public void move(int row, int col) {// 전차의 위치를 바꿈 (인덱스를 벗어나지 않고, 이동 가능할 경우 체크)
			map[this.row][this.col] = '.';
			this.row = row;
			this.col = col;
			map[this.row][this.col] = direction[dir];
		}
	}

	static int t, h, w, n;// 전체 테스트케이스 수, 게임 맵의 크기, 명령어 개수
	static char[][] map;// 게임 맵
	static char[] cmd;// 명령어

	static Car car;// 전차
	static int[] dirRow = { -1, 1, 0, 0 };// 전차의 방향 : 위, 아래, 왼쪽, 오른쪽
	static int[] dirCol = { 0, 0, -1, 1 };// 전차의 방향 : 위, 아래, 왼쪽, 오른쪽
	static char[] direction = { '^', 'v', '<', '>' };

	static StringBuilder str = new StringBuilder("");// 출력할 결과

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {// 총 t번의 테스트 케이스
			String[] temp = in.readLine().split(" ");// 한 라인 입력 받아서
			h = Integer.parseInt(temp[0]);// 게임 맵의 행 크기
			w = Integer.parseInt(temp[1]);// 게임 맵의 열 크기 저장

			map = new char[h][w];// 게임 맵 생성
			for (int i = 0; i < h; i++) {// h줄에 걸쳐
				String input = in.readLine();// 한 문자열 입력받아
				for (int j = 0; j < w; j++) {// w개의 문자들을
					map[i][j] = input.charAt(j);// 맵에 저장
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {// 전차일 경우
						int row = i;// 전차의 행 위치
						int col = j;// 전차의 열 위치
						int dir = -1;// 전차 방향
						if (map[i][j] == '^')// 위쪽 방향이면
							dir = 0;// 방향 배열 인덱스 0
						else if (map[i][j] == 'v')// 아래쪽 방향이면
							dir = 1;// 방향 배열 인덱스 1
						else if (map[i][j] == '<')// 왼쪽 방향이면
							dir = 2;// 방향 배열 인덱스 2
						else// 오른쪽 방향이면
							dir = 3;// 방향 배열 인덱스 3

						car = new Car(row, col, dir);// 전차 생성
					}
				}
			}

			n = Integer.parseInt(in.readLine());// 명령어의 개수 입력
			cmd = new char[n];// 명령어 배열 생성
			String input = in.readLine();// 한 문자열 입력 받아
			for (int i = 0; i < n; i++) {// n개의 문자들을
				cmd[i] = input.charAt(i);// 명령어 배열에 저장
			}
			// 입력끝
			// --------------------------------------------------------------
			for (int i = 0; i < n; i++) {
				if (cmd[i] == 'U') {// 방향 위쪽으로 바꾸고, 위칸이 '.'이면 이동
					car.changeDir(0);
				} else if (cmd[i] == 'D') {// 방향 아래쪽으로 바꾸고, 아래칸이 '.'이면 이동
					car.changeDir(1);
				} else if (cmd[i] == 'L') {// 방향 왼쪽으로 바꾸고, 왼칸이 '.'이면 이동
					car.changeDir(2);
				} else if (cmd[i] == 'R') {// 방향 오른쪽으로 바꾸고, 오른칸이 '.'이면 이동
					car.changeDir(3);
				}

				if (cmd[i] != 'S' && check(car.row + dirRow[car.dir], car.col + dirCol[car.dir])) {
					car.move(car.row + dirRow[car.dir], car.col + dirCol[car.dir]);
					continue;
				}

				if (cmd[i] == 'S') {// 현재 방향으로 포탄 발사
					shoot(car.row, car.col);// 체크하는 행과 열 위치는 계속 변화할 것이므로 매개변수로 전달
				}
			}

			str.append("#").append(test_case).append(" ");
			//결과 붙이기
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					str.append(map[i][j]);
				}
				str.append("\n");
			}
		}
		System.out.println(str.toString());
	}

	static boolean check(int row, int col) {
		if (row >= 0 && row < h && col >= 0 && col < w && map[row][col] == '.') {// 맵을 벗어나지 않으면서 평지인 경우
			return true;// 이동 가능
		}
		return false;// 그 외의 경우에는 이동 불가
	}

	static void shoot(int row, int col) {
		if (row < 0 || row >= h || col < 0 || col >= w)
			return;// 인덱스 벗어나면 종료

		if (map[row][col] == '*') {// 벽돌 벽과 만나면
			map[row][col] = '.';// 평지로 바꾸면서
			return;// 종료
		}

		if (map[row][col] == '#')
			return;// 강철 벽과 만나면 종료

		shoot(row + dirRow[car.dir], col + dirCol[car.dir]);
	}
}
