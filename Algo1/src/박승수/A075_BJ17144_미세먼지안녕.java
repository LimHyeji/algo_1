package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A075_BJ17144_미세먼지안녕 {
	static int[][] board;				//미세먼지 정보를 저장할 배열
	static int Y;						//방의 Y 크기
	static int X;						//방의 X 크기
	
	static int upperAPY = -1;			//공기청정기 윗부분위치
	static int upperAPX = -1;
	static int[] udy = {0,-1,0,1,0};	//공기청정기 윗부분에서 바람의 움직임
	static int[] udx = {-1,0,1,0,-1};
	
	static int lowerAPY;				//공기청정기 아랫부분위치
	static int lowerAPX;
	static int[] ldy = {0,1,0,-1,0};	//공기청정기 아랫부분에서 바람의 움직임
	static int[] ldx = {-1,0,1,0,-1};
	
	static int[] dy = {0,0,1,-1};		//4방향 탐색 미리 선언
	static int[] dx = {1,-1,0,0};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		Y = Integer.parseInt(temp[0]);			//방의 크기 Y
		X = Integer.parseInt(temp[1]);			//방의 크기 X
		int T = Integer.parseInt(temp[2]);		//공기청정기를 돌릴 시간 T
		
		board = new int[Y][X];					//방 초기화
		
		for (int y = 0; y < Y; y ++) {
			temp = br.readLine().split(" ");	//방 입력을 받으면서
			for (int x = 0; x < X; x ++) {
				board[y][x] = Integer.parseInt(temp[x]);
				if (board[y][x] == -1) {		//공기청정기일경우 위치에 맞게 넣어줌
					if (upperAPY == -1) {
						upperAPY = y;
						upperAPX = x;
					}else {
						lowerAPY = y;
						lowerAPX = x;
					}
				}
			}
		}
		for (int idx = 0; idx < T; idx ++) {	//주어진 시간만큼 진행하면서
			Spread();							//미세먼지 확산 후
			upperAirPurifier();					//공기청정기 윗부분 돌리기
			lowerAirPurifier();					//공기청정기 아랫부분 돌리기
		}
		
		System.out.println(sumDust());			//총 먼지량 출력
		
	}
	
	public static void Spread() {				//미세먼지 확산시키는 메서드
		int[][] tempBoard = new int[Y][X];		//확산 된 먼지들 저장할 배열
		
		for (int y = 0; y < Y; y ++) {
			for (int x= 0; x < X; x ++) {		//배열돌면서
				int cnt = 0;
				for (int idx = 0; idx < 4; idx ++) {	//4방향 탐색해서
					int ty = y + dy[idx];
					int tx = x + dx[idx];
					
					
					if (isRange(ty, tx)) {		//범위안에 들어오고 공청기가 아니면 먼지확산 시켜줄 배열에 넣어준다
						tempBoard[ty][tx] += board[y][x] / 5;	//넣는 값은 원래 값 /5 
						cnt ++;					//한칸 확산되었다고 표시
					}
					
					
				}
				board[y][x] -= board[y][x] / 5 * cnt;	//원래위치에 확산된만큼의 먼지를 제거해준다
			}
		}
		
		for (int y = 0; y < Y; y ++) {			//확산된 먼지들을 마지막에 다 더해준다
			for (int x = 0; x < X ; x ++) {		//이렇게 해야 한번에 모든 먼지들이 움직일 수 있음
				board[y][x] += tempBoard[y][x];
			}
		}
	}
	
	public static void upperAirPurifier() {		//공청기 윗부분 돌리는 함수
		int y = upperAPY;					//시작지점 잡아주고
		int x = upperAPX;
		board[y][x] = 0;
		for (int idx = 0; idx < 5; idx ++) {//5번 돌려준다, 중간에 있으면 다섯번 움직여야하기 떄문
			while (true) {
				int ty = y + udy[idx];		//탐색하면서
				int tx = x + udx[idx];
				if (idx == 4 && ty == upperAPY && tx == upperAPX) break;	//만약 마지막방향에서 원래위치까지 왔으면 완료
				if (isRange(ty,tx) && ty <= upperAPY) {		//범위안에 들어오고 넘어가지 않는다면
					Swap(ty,tx,y,x);		//두개의 위치를 변경시켜준다
					y = ty;					//다음위치로 이번것을 넣어줌
					x = tx;
				}else {
					break;
				}
			}
		}
		board[upperAPY][upperAPX] = -1;	//공청기 다시 설치
	}
	
	public static void lowerAirPurifier() {		//공청기 아랫부분 돌리는 함수
		int y = lowerAPY;					//시작지점 잡아주고
		int x = lowerAPX;
		board[y][x] = 0;
		for (int idx = 0; idx < 5; idx ++) {//5번 돌려준다, 중간에 있으면 다섯번 움직여야하기 떄문
			while (true) {
				int ty = y + ldy[idx];		//탐색하면서
				int tx = x + ldx[idx];
				if (idx == 4 && ty == lowerAPY && tx == lowerAPX) break;	//만약 마지막방향에서 원래위치까지 왔으면 완료
				if (isRange(ty,tx) && ty >= lowerAPY) {	//범위안에 들어오고 넘어가지 않는다면
					Swap(ty,tx,y,x);		//두개의 위치를 변경시켜준다
					y = ty;					//다음위치로 이번것을 넣어줌
					x = tx;
				}else {
					break;
				}
			}
		}
		board[lowerAPY][lowerAPX] = -1;	//공청기 다시 설치
	}
	
	public static void Swap(int ty, int tx, int y, int x) { //배열에서 두개의 위치를 바꿔주는 메서드
		int temp = board[ty][tx];
		board[ty][tx] = board[y][x];
		board[y][x] = temp;
	}
	
	public static boolean isRange(int y, int x) {			//범위확인하는 메서드 공청기도 제외함
		if (0 <= y && y < Y && 0 <= x && x < X && board[y][x] != -1) {
			return true;
		}
		return false;
	}
	
	public static int sumDust() {				//전체 배열을 돌면서 먼지의 합을 구하는 매서드
		int hap = 0;
		for (int y = 0; y < Y ; y ++) {
			for (int x = 0; x < X; x ++) {
				if (board[y][x] != -1) {		//공청기가 아니라면
					hap += board[y][x];			//먼지의 양을 다 더해준다
				}
			}
		}
		return hap;
	}
	
	public static void printBoard(int[][] board) {// 디버깅용 출력 메서드
		for(int[] arr: board) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println();
	}

}
