package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A038_SWEA1861_정사각형방 {
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int[][] arrCnt;
	static int[][] arr;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());					//Test Case 수 입력
		for (int test_case = 1; test_case < T+1; test_case ++) {
		
		N = Integer.parseInt(br.readLine());					//배열 크기 입력
		
		arr = new int [N][N];
		arrCnt = new int [N][N];
		
		int Max = -1;
		int Answer = N*N;
		
		for(int y = 0; y < N; y ++) { 								// 배열 입력
			String temp[] = br.readLine().split(" ");
			for (int x = 0; x < N; x++) {
				arr[y][x] = Integer.parseInt(temp[x]);
			}
		}
		
		for(int y = 0; y < N; y ++) {
			for (int x = 0; x < N; x++) {
				if (arrCnt[y][x] == 0) { //방문을 했다면 더이상 방문하지 않음.
					int temp = DFS(y,x);
					if ( temp > Max) {
						Max = temp;
						Answer = arr[y][x];
					}else if (temp == Max && arr[y][x] < Answer) {
						Answer = arr[y][x];
					}
				}		
				//printBoard(arrCnt);
			}
		}
		System.out.println("#"+test_case+" "+Answer+" "+Max+"\n");
		
		}
	}
	
	public static int DFS(int y, int x) {
		if (arrCnt[y][x] != 0) {
			return arrCnt[y][x];
		}
		
		for(int idx = 0; idx <4; idx ++) {
			int ty = y+dy[idx];
			int tx = x+dx[idx];
			
			if (0<=ty && ty<N && 0<= tx && tx<N && arr[y][x]+1 == arr[ty][tx]) { // 범위를 넘어가지 않는다면
				return arrCnt[y][x] = DFS(ty,tx) +1;
			}
		}
		arrCnt[y][x] += 1;
		return arrCnt[y][x];

		
	}
	
	public static void printBoard(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
		

}
