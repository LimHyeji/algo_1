package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 물고기 m마리와 아기상어 1마리(크기2)
 * 
 * 1. 아기상어 1초에 상하좌우 한칸씩 이동 (자기보다 작거나 같은 물고기 있는 칸만 가능)
 * 
 * 2. 자신보다 작은 물고기 먹을 수 있음
 * 	
 * 거리 가까운 순서로 이동 (거리 같으면 행 인덱스, 열 인덱스가 가장 작은 것이 우선 ( 행이 먼저 ) )
 * 
 * 3. 아기상어 크기 커지는 조건 : 자신의 크기와 같은 수의 물고기 먹을 때 +1
 *  
 * 4. 먹을 수 없으면 종료
 */

public class A074_BJ16236_아기상어 {
	static int n;
	static int arr[][];
	static PriorityQueue<Fish> fishList;
	static Fish shark;
	static int dirR[]= {-1,1,0,0};
	static int dirC[]= {0,0,-1,1};
	static int res;

	static class Fish implements Comparable<Fish> {
		int row, col;
		int size;
		int cnt;

		Fish(int row, int col, int size) {
			this.row = row;
			this.col = col;
			this.size = size;
		}

		Fish(int row, int col, int size, int cnt) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.cnt = cnt;
		}

		void move(int row, int col) {
			this.row = row;
			this.col = col;
		}

		int getDistance() {
			return Math.abs(shark.row - this.row) + Math.abs(shark.col - this.col);
		}

		@Override
		public int compareTo(Fish o) {
			if (this.getDistance() < o.getDistance())
				return -1;
			else if (this.getDistance() == o.getDistance()) {
				if (this.row < o.row)
					return -1;
				else if (this.row == o.row) {
					if (this.col < o.col)
						return -1;
					else if (this.col == o.col)
						return 0;
					else
						return 1;
				} else
					return 1;
			} else
				return 1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(in.readLine());

		arr = new int[n][n];
		fishList = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				if (arr[i][j] == 9) {
					shark = new Fish(i, j, 2, 0);
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if (arr[i][j] >= 1 && arr[i][j] <= 6) {
					fishList.add(new Fish(i, j, arr[i][j]));
				}
			}
		}
		// 입력 끝
		// ----------------------------
		res = 0;
		PriorityQueue<Fish> tempList = new PriorityQueue<>();

		while (!fishList.isEmpty()) {
			Fish cur = null;
			while (!fishList.isEmpty()) {

				cur = fishList.poll();

				if (cur.size >= shark.size) {
					tempList.add(cur);
				} else {
					break;
				}
			}

			res+=cur.getDistance();
			arr[cur.row][cur.col] = 9;
			arr[shark.row][shark.col] = 0;
			shark.move(cur.row, cur.col);
			shark.cnt++;


			if (shark.size == shark.cnt) {
				shark.size++;
			}
			
			fishList.addAll(tempList);
			tempList.clear();
		}
		
		System.out.println(res);
	}
}
