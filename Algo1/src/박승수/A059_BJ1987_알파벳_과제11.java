package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class A059_BJ1987_알파벳_과제11 {
	static HashSet<Character> check = new HashSet<Character>();
	static int Y;
	static int X;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static char[][] board;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		Y = Integer.parseInt(temp[0]);
		X = Integer.parseInt(temp[1]);
		
		board = new char[Y][X];
		
		for (int y = 0; y < Y; y++) {
			String t = br.readLine();
			for (int x = 0; x < X; x ++) {
				board[y][x] = t.charAt(x);
			}
		}
		
		answer = 1;
		check.add(board[0][0]);
		dfs(0,0,1);
		System.out.println(answer);
	}
	
	public static void dfs(int y, int x,int v) {
		
		answer = Math.max(answer, v);
		
		for (int idx = 0; idx < 4; idx ++) {
			int ty = y+dy[idx];
			int tx = x+dx[idx];
			
			if (0<= ty && ty < Y && 0<= tx && tx < X && !check.contains(board[ty][tx])) {
				check.add(board[ty][tx]);
				dfs(ty,tx,v+1);
				check.remove(board[ty][tx]);
			}
		}
	}

}