package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A052_BJ1992_쿼드트리_과제9 {
	static int[][] board;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			String temp = br.readLine();
			for(int x = 0; x < N; x++) {
				board[y][x] = temp.charAt(x) - '0';
			}
		}
		
		sb.append("(");
		solution(N,0,0);
		sb.append(")");
		
		System.out.println(sb.toString());
	}
	
	public static void printBoard(int[][] board) {
		for (int[] a : board ) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
	
	public static void solution(int n,int y, int x) { // 좌표 기준은 왼쪽 위로 설정한다 
		System.out.println(n);
		if (n==2) {
			System.out.println("처리완료 ");
			if (board[y][x] == board[y+1][x] && board[y+1][x+1] == board[y][x+1] && board[y][x] ==board[y+1][x+1]) {
				sb.append(board[y][x]);
				return;
			}
			for (int ty = y; ty < y+2; ty++) {
				for(int tx = x; tx < x+2; tx++) {
					sb.append(board[ty][tx]);
				}
			}
			return;
		}
		
		int hn = n/2;
		int sameCheck = board[y][x];
		boolean isOk = true;
		System.out.println("왼쪽위");
		for (int ty = y; ty < y+hn;ty++) {		// 왼쪽위
			for (int tx = x; tx < x+hn; tx++) {
				if (board[ty][tx] != sameCheck) {
					System.out.println("다름");
					sb.append("(");
					solution(hn,y,x);
					sb.append(")");
					isOk = false;
					break;
				}
			}
			if (isOk == false) {
				break;
			}else {
				sb.append(sameCheck);
			}
		}
		
		
		sameCheck = board[y][x+hn];
		isOk = true;
		
		System.out.println("오른쪽위");
		for (int ty = y; ty < y+hn;ty++) {		// 오른쪽 위
			for (int tx = x+hn; tx < x+n; tx++) {
				if (board[ty][tx] != sameCheck) {
					System.out.println("다름");
					sb.append("(");
					solution(hn,y,x+hn);
					sb.append(")");
					isOk = false;
					break;
				}
			}
			if (isOk == false) {
				break;
			}else {
				sb.append(sameCheck);
			}
		}
		
		sameCheck = board[y+hn][x];
		isOk = true;
		System.out.println("왼쪽아래");
		for (int ty = y+hn; ty < y+n;ty++) {		// 왼쪽 아래
			for (int tx = x; tx < x+hn; tx++) {
				if (board[ty][tx] != sameCheck) {
					System.out.println("다름");
					sb.append("(");
					solution(hn,y+hn,x);
					sb.append(")");
					isOk = false;
					break;
				}
			}
			if (isOk == false) {
				break;
			}else {
				sb.append(sameCheck);
			}
		}
		
		sameCheck = board[y+hn][x+hn];
		isOk = true;
		System.out.println("오른쪽 아래");
		for (int ty = y+hn; ty < y+n;ty++) {		// 오른쪽 아래
			for (int tx = x+hn; tx < x+n; tx++) {
				if (board[ty][tx] != sameCheck) {
					System.out.println("다름");
					sb.append("(");
					solution(hn,y+hn,x+hn);
					sb.append(")");
					isOk = false;
					break;
				}
			}
			if (isOk == false) {
				break;
			}else {
				sb.append(sameCheck);
			}
		}
		
	}

}
