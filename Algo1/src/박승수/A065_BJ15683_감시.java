package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A065_BJ15683_감시 {
	static ArrayList<CCTV> cctvList;
	static int Y;
	static int X;
	static int[][] board;
	static int answer = Integer.MAX_VALUE;
	
	static int[] direct1Y = {0,0,1,-1};
	static int[] direct1X = {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		Y = Integer.parseInt(temp[0]);
		X = Integer.parseInt(temp[1]);
		
		board = new int[Y][X];
		cctvList = new ArrayList<CCTV>();
		int cnt = 0;
		for (int y = 0; y < Y; y++) {
			temp = br.readLine().split(" ");
			for(int x = 0; x < X; x ++) {
				board[y][x] = Integer.parseInt(temp[x]);
				if (board[y][x] != 0 && board[y][x] != 6) {					
					cctvList.add(new CCTV(y,x,board[y][x]));
				}
			}
		}
		
		DFS(0,board);
		System.out.println(answer);
		
		

	}
	
	public static void check(int[][] board) {
		int hap = 0;
		for (int y = 0; y < Y; y++) {
			for (int x = 0; x < X; x++) {
				if (board[y][x] == 0) {
					hap ++;
				}
			}
		}
		
		answer = Math.min(hap, answer);
	}
	
	public static int[][] cctvRange(int y, int x, int dy, int dx,int[][] board) {
		while (true) {
			y += dy;
			x += dx;
			
			if (0 <= y && y < Y && 0 <= x && x < X) {
				if(board[y][x] == 0 ||board[y][x] == 9) {
					board[y][x] = 9;
				}else if (board[y][x] == 1 || board[y][x] == 2 || board[y][x] == 3 || board[y][x] == 4 || board[y][x] == 5) {
					continue;
				}else if(board[y][x] == 6) {
					break;
				}
				
			}else {
				break;
			}
		}
		
		return board;
	}
	
	public static void DFS(int v,int[][] board) {
		if (v == cctvList.size()) {
			check(board);
			//printBoard(board);
			return;
		}
		
		int[][] temp;
		
		if(cctvList.get(v).type == 1) {
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
		}else if (cctvList.get(v).type == 2) {
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			DFS(v+1,temp);
			
		}else if (cctvList.get(v).type == 3) {
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			DFS(v+1,temp);
			
		}else if (cctvList.get(v).type == 4) {
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
			
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			DFS(v+1,temp);
			
		}else if (cctvList.get(v).type == 5) {
			temp = copyBoard(board);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,1,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,-1,0,temp);
			temp = cctvRange(cctvList.get(v).y, cctvList.get(v).x,0,-1,temp);
			DFS(v+1,temp);
		}
		
	}
	
	public static int[][] copyBoard(int[][] board){
		int[][] temp = new int[Y][X];
		
		for (int y = 0; y < Y; y++) {
			for (int x = 0; x < X; x++) {
				temp[y][x] = board[y][x];
			}
		}
		
		return temp;
	}
	
	public static void printBoard(int[][] board) {
		for (int[] b: board) {
			System.out.println(Arrays.toString(b));
		}
		System.out.println();
	}
	
	
	static class CCTV{
		int y;
		int x;
		int type;
		
		public CCTV(int y, int x, int type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}
		
	}

}


