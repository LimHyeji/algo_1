package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class A077_SWEA1249_보급로_과제15 {
	static int[][] board;				//보급로의 정보를 저장할 배열
	static int[][] check;				//현재까지의 최솟값을 저장할 배열
	static int[] dy = {0,0,1,-1};		//이동할 상하좌우 미리 선언
	static int[] dx = {1,-1,0,0};
	static int N;						//보급로의 크기 N*N

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());;
		
		for (int test_case = 1; test_case < T+1; test_case++) {		//테스트 케이스 수 입력
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N];									//보급로 초기화
			check = new int[N][N];									//최솟값 초기화
			
			for (int idx = 0; idx < N ; idx ++) {
				Arrays.fill(check[idx], Integer.MAX_VALUE);			//최솟값을 저장할 것이기 때문에 최대를 저장
			}
			
			
			for (int y = 0; y < N ; y ++) {
				String temp = br.readLine();
				for (int x = 0; x < N; x ++) {
					board[y][x] = temp.charAt(x)-'0';				//보급로 입력 받아주기
				}
			}
			check[0][0] = 0;										//시작지점의 최솟값은 0으로 바꿔준다
			BFS();
			
			System.out.printf("#%d %d\n",test_case,check[N-1][N-1]);//정답 출력
		}
		
		
		
	}
	
	public static void printBoard(int[][] board) {			//확인용 출력 메서드
		for (int[] arr : board) {
			System.out.println(Arrays.toString(arr) );
		}
		System.out.println();
	}
	
	public static void BFS() {
		Queue<Pos> queue = new ArrayDeque<Pos>();			//BFS를 진행할 큐를 선언해주고
		queue.add(new Pos(0,0));							//큐에 시작지점 0,0을 넣어준다
		
		while (!queue.isEmpty()) {							//큐가 빌때까지
			Pos nowPos = queue.poll();						//큐에서 좌표를 빼서
			int y = nowPos.y;
			int x = nowPos.x;
						
			for (int idx = 0; idx < 4; idx ++) {			//4방향 탐색을 진행하고
				int ty = y +dy[idx];
				int tx = x +dx[idx];
							
				if (isRange(ty, tx) && check[ty][tx] > check[y][x] + board[ty][tx]) { // 범위를 벗어나지 않고, 지금위치 가중치 + 다음위치 가중치가 원래보다 작다면 진행
					check[ty][tx] = check[y][x] + board[ty][tx];		//가중치 업데이트
					queue.add(new Pos(ty,tx));							//큐에 넣어줌
				}
			}
		}		
	}
	
	public static boolean isRange(int y,int x) {			//보급로 밖을 벗어나는지 확인하는 메서드
		if (0 <= y && y < N && 0 <= x && x < N) {
			return true;
		}
		return false;
	}
	
	public static class Pos{
		int y;
		int x;
		
		Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	

}
