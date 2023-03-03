package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A076_BJ7576_토마토 {
	static int Y;
	static int X;
	static int[][] board;
	static boolean[][] isBeforeRottend;
	static ArrayList<Tomato> rotten;
	
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		X = Integer.parseInt(temp[0]);		//토마토 저장한 판의 X 크기
		Y = Integer.parseInt(temp[1]);		//토마토 저장한 판의 Y 크기
		
		board = new int[Y][X];				//저장소 초기화
		isBeforeRottend = new boolean[Y][X];//중복되서 처리하지 않기위해 이전에 익었던건 무시하기위한 배열
		
		for (int y = 0; y < Y; y ++) {
			temp = br.readLine().split(" ");
			for (int x = 0; x < X; x ++) {
				board[y][x] = Integer.parseInt(temp[x]);		//토마토 판 저장 
			}
		}
		initTomato();				//초기 익은 토미토들의 위치를 rotten에 넣어준다
		
		int cnt = 0;
		while (rottenTomato()) { 	//만약 토마토가 하나라도 익었다면
			cnt ++;					//cnt ++
		}
		
		if (isAllRotten()) {		// 모든 토마토가 익었을경우
			System.out.println(cnt);// 걸린시간을 출력하고
		}else {
			System.out.println(-1);	//아닐경우 -1을 출력한다
		}
		
		

	}
	
	public static boolean rottenTomato() {
		ArrayList<Tomato> Newrotten = new ArrayList<Tomato>();	//새 토마토 배열을 만들고
		for (Tomato t : rotten) {					//이전에 익엇던 토마토들을 순회하면서
			for (int idx = 0; idx < 4; idx ++) {	//4방향을 확인해서
				int ty = t.y+dy[idx];
				int tx = t.x+dx[idx];
				
				if(isRange(ty, tx)) {				//범위를 벗어나지 않고
					if (board[ty][tx] == 0) {		//전에 안익었던거라면
						board[ty][tx] = 1;			//익은걸로 변경
						Newrotten.add(new Tomato(ty, tx));	//이번에 익은토마토 배열에 넣어줌
					}
					
				}
			}
		}
		
		rotten = Newrotten;			//익은 토마토배열에 이번에 익은거 넣어주고
		if (rotten.size() == 0) {	//만약 토마토가 하나도 안익엇다면 false 리턴
			return false;
		}							//익엇다면 true return
		return true;
	}
	
	public static void initTomato() {		//초기 익은 토마토 저자하는 메서드
		rotten = new ArrayList<Tomato>();	
		
		for (int y = 0; y < Y; y ++) {
			for (int x = 0; x < X; x++) {
				if (board[y][x] == 1) {		//익은 토마토들을 rotten 배열에 넣어준다
					rotten.add(new Tomato(y,x));
				}
			}
		}
	}
	
	public static boolean isRange(int y, int x) {	//범위 확인하는 메서드 
		if (0<= y && y < Y && 0<= x && x < X) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isAllRotten() {		//모든 토마토가 익었는지 확인하는 메서드 
		for (int y = 0 ; y < Y; y ++) {
			for (int x= 0; x < X ; x++) {		//전체를 순회해서
				if (board[y][x] == 0) {			//하나라도 안익엇다면 false 리턴
					return false;
				}
			}
		}
		return true;
	}
	
	public static class Tomato{
		int y;
		int x;
		
		public Tomato(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

}
