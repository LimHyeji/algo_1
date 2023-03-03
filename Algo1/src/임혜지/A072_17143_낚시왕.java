package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 낚시왕 오른쪽 칸 이동(1초대에 0번 인덱스, ... , 맵 벗어나면 종료)
 * 
 * 2. 낚시왕 위치에서 가장 아래 상어 잡기(0으로 변경)
 * 
 * 3. 상어 이동(속도는 칸/초, 맵 벗어나면 "방향" 반대로 바꾸기. 즉, 방향 존재)
 * 
 * 상어가 같은 칸에 겹칠 경우에는 크기가 큰 상어가 이김
 * 
 * 4. 잡은 상어 크기 합 구하기
 */

/*
 * 상어 클래스
 * 위치(row, col), 속력, 크기, 식별자
 */
public class A072_17143_낚시왕 {
	static class Shark {
		int row, col;
		int s;
		int dir;
		int size;

		public Shark(int row, int col, int s, int dir, int size) {
			super();
			this.row = row;
			this.col = col;
			this.s = s;
			this.dir = dir;
			this.size = size;
		}
	}

	static int r, c, m;
	static int arr[][];
	static ArrayList<Shark> list;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[r][c];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;// 0: 위, 1: 아래, 2: 오른쪽, 3: 왼쪽
			int z = Integer.parseInt(st.nextToken());

			list.add(new Shark(r, c, s, d, z));
		}

		// 입력 끝
		// ---------------------------
		res = 0;
		for (int i = 0; i < c; i++) {
			catchShark(i);

		}

	}

	static void catchShark(int col) {
		// 잡은 상어 크기 합 더하기
		
	}

	static void moveShark() {
		/*
		 * 상하 이동 시, (현 위치+속도)%r
		 * 
		 * 좌우 이동 시, (현 위치+속도)%c
		 * 
		 * 인덱스 벗어나면 turnDir() 호출
		 * 
		 * 
		 */
	}

	static boolean checkDup() {

		return false;
	}
	
	static void turnDir(Shark s) {
		if(s.dir==1) s.dir=0;
		else if(s.dir==0) s.dir=1;
		else if(s.dir==2) s.dir=3;
		else if(s.dir==3) s.dir=2;
	}

}
