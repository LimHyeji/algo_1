package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class A059_BJ1987_알파벳_과제11 {
	static int r, c;
	static char[][] arr;
	static int dirRow[] = { -1, 1, 0, 0 };
	static int dirCol[] = { 0, 0, -1, 1 };
	static int res = 0;
	static List<Character> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		list = new ArrayList<>();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			String temp = in.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		list.add(arr[0][0]);
		sol(0, 0, 1);
		System.out.println(res);
	}

//	static void sol(int row,int col,int cnt) {
//		
//		for(int i=0;i<4;i++) {
//			int nRow=row+dirRow[i];
//			int nCol=col+dirCol[i];
//			
//			if(nRow>=0&&nRow<r&&nCol>=0&&nCol<c) {
//				if(check(arr[nRow][nCol])) {
//					list.add(arr[nRow][nCol]);
//					int idx=list.indexOf(arr[nRow][nCol]);
//					sol(nRow,nCol,cnt+1);
//					list.remove(idx);
//				}
//			}
//			res=Math.max(res, cnt);
//		}
//		return;
//	}

	static class Node {
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static void sol() {
		Queue<Node> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[r][c];

		q.offer(new Node(0, 0));
		visit[0][0] = true;
		Node cur = null;

		while (!q.isEmpty()) {
			cur = q.poll();

			if (!visit[cur.row][cur.col]) {

			}
		}
	}

	static boolean check(char c) {
		for (char a : list) {
			if (a == c)
				return false;
		}
		return true;
	}
}
