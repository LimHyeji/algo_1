package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class A074_BJ16236_아기상어 {
	static int[][] board;			//수족관 정보를 저장할 배열
	static int babySharkY;			//아기상어의 위치
	static int babySharkX;
	static int babySharkSize;		//아기상어의 크기
	static int N;					//수족관 N*N크기
	
	static int[] dy = {0,0,1,-1};	//4방향 탐색용 방향 미리 선언
	static int[] dx = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		babySharkSize = 2; 								//가장 처음에 아기 상어의 크기는 2
		for (int y = 0; y < N; y ++) {
			String[] temp = br.readLine().split(" ");	//수족관 입력을 받아주면서
			for (int x = 0; x < N; x ++) {
				board[y][x] = Integer.parseInt(temp[x]);
				if (board[y][x] == 9) {					//만약 아기상어 위치일경우
					babySharkY = y;						//아기상어 위치를 저장하고
					babySharkX = x;
					board[y][x] = 0;					//빈곳으로 바꿔놓는다
				}
			}
		}
		
		System.out.println(eatShark());					//상어가 몇초동안 먹을 수 있는지 확인한다
		
	}
	
	public static void printBoard() {
		for (int[] arr : board) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println();
	}
	
	public static int eatShark() {
		int answer = 0;
		int cnt = 2;		//상어의 크기가 커지는것을 확일할 먹은 개수 카운팅
		
		while (true) {
			Pos eatShark = canEatFishList();		//먹을수 있는 물고기중 우선도가 가장 높은 물고기를 가져와서
			if (eatShark == null) {					//물고기가 존재하지 않으면 끝
				break;
			}else {									//존재한다면
				cnt --;								//하나 먹엇다고 표시 해주고
				if (cnt == 0) {						// 카운팅이 끝났을경우
					babySharkSize += 1;				//물고기 크기를 늘려준다
					cnt = babySharkSize;			//카운팅도 바꿔준다
				}
				board[eatShark.y][eatShark.x] = 0;	//먹은 물고기는 없애준다
			}
			answer += eatShark.cnt;					//물고기를 먹는대까지 걸린 시간(거리)를 더해준다
			babySharkX = eatShark.x;				//아기상어의 위치를 먹은 상어의 위치로 바꿔준다
			babySharkY = eatShark.y;
			}
		return answer;								//몇초 걸렸는지 리턴
		
	}
	
	public static Pos canEatFishList() {			//BFS로 먹을 물고기 리턴하는 메서드
		boolean[][] isVisited = new boolean[N][N];	//방문했는지 확인할 배열을 하나 만든다
		
		Queue<Pos> queue= new ArrayDeque<Pos>();	//BFS 돌릴 큐 하나 만들고
		queue.add(new Pos(babySharkY,babySharkX,0));//아기상어의 위치를 넣어준다
		Pos eatShark = null;						//우리가 먹을 물고기를 초기화 해준다
		
		int cnt = Integer.MAX_VALUE;				//물고기를 먹을수 있을경우 이보다 큰 거리는 움직이지 않도록 할 거리 변수 최대로 초기화
		while (!queue.isEmpty()) {
			Pos nowPos = queue.poll();				//큐에서 뺴서
			if (nowPos.cnt > cnt) {					//먹은 최소거리보다 크면 더이상 진행하지 않는다
				break;
			}
			
			for (int idx = 0; idx < 4; idx ++) {	//4방향 탐색
				int ty = nowPos.y + dy[idx];
				int tx = nowPos.x + dx[idx];
				
				// 수족관 안애 있고,		아직방문 안했고, 					아기상어 크기의 이하라면
				if (isRange(ty, tx) && isVisited[ty][tx] == false && board[ty][tx] <= babySharkSize) {
					if (board[ty][tx] != 0 && board[ty][tx] <  babySharkSize) {	//먹는게 가능하다면
						cnt = nowPos.cnt;										//최소거리 업데이트
						if (eatShark == null) {									//이 물고기가 처음이라면
							eatShark = new Pos(ty,tx,cnt+1);					//이물고기를 타겟으로 넣어줌
						}{
							if (eatShark.y > ty) {								// 이미 타겟이 있을경우y좌표 작은거 우선, x좌표 작은거 우선으로 골라줌
								eatShark = new Pos(ty,tx,cnt+1);
							}else if (eatShark.y == ty) {
								if (eatShark.x > tx) {
									eatShark = new Pos(ty,tx,cnt+1);
								}
							}
						}
					}
					isVisited[ty][tx] = true;					//방문처리
					queue.add(new Pos(ty,tx,nowPos.cnt+1));		//큐에 삽입
				}
			}
		}
		return eatShark;										//우리가 먹을 물고기 리턴해줌
	}
	
	public static boolean isRange(int y, int x) {				//범위 체크하는 메서드
		if (0<= y && y < N && 0<= x && x < N) {
			return true;
		}
		
		return false;
	}
	
	public static class Pos{
		int y;
		int x;
		int cnt;
		
		public Pos(int y, int x,int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}		
	}

}
