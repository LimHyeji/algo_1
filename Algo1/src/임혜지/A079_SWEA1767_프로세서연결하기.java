package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 가로 n 세로 n
 * 1개 셀 : 1개 코어/전선	-> 전선은 직선, 교차 불가
 * 
 * 						-> 가장자리 코어는 전원 연결 간주
 * 
 * -> 최대한 많은 코어 연결 시, 전선 길이 최소 합 구하기
 * 
 */
public class A079_SWEA1767_프로세서연결하기 {
	static int t;
	static int n;
	static int arr[][];
	static List<Node> list;
	static int dirR[] = { -1, 1, 0, 0 };
	static int dirC[] = { 0, 0, -1, 1 };
	static StringBuilder str = new StringBuilder();
	static int maxC, minL;

	static class Node {
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		t = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(in.readLine());
			arr = new int[n][n];
			list = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && i > 0 && i < n - 1 && j > 0 && j < n - 1)
						list.add(new Node(i, j));
				}
			}

			// 입력끝
			// ------------------------
			/*
			 * 상하좌우 일직선 이동
			 * 
			 * 만약 1을 만나면 break
			 * 
			 * 이미 전선 깔려있으면 break
			 * 
			 * 인덱스 벗어나면 전선 깔기 완료
			 * 
			 * 전선 개수 1개 추가하고, 그때까지 깔린 길들을 -1로 바꿔주기
			 */
			maxC = 0;
			minL = Integer.MAX_VALUE;
			sol(0, 0, 0);
			str.append("#").append(tc).append(" ").append(minL).append("\n");
		}
		System.out.println(str.toString());
	}

	static void sol(int cnt, int coreCnt, int wireCnt) {
		if (cnt == list.size()) {
			if (coreCnt > maxC) {
				maxC = coreCnt;
				minL = wireCnt;
			} else if (coreCnt == maxC) {
				minL = Math.min(minL, wireCnt);
			}

			return;
		}

		int R = list.get(cnt).row;
		int C = list.get(cnt).col;

		for (int dir = 0; dir < 4; dir++) {

			int newR = R, newC = C;
			int count = 0;
			while (true) {
				newR += dirR[dir];
				newC += dirC[dir];

				if (newR >= 0 && newR < n && newC >= 0 && newC < n) {
					if (arr[newR][newC] == 1) {// 전선 이미 존재
						count = 0;
						break;
					}
					count++;
				}
				else {
					break;
				}
			}

			if (count == 0)
				sol(cnt + 1, coreCnt, wireCnt);
			else {
				int tempR = R,tempC = C;
				for (int i = 0; i < count; i++) {
					tempR+=dirR[dir];
					tempC+=dirC[dir];
					arr[tempR][tempC] = 1;
				}

				sol(cnt + 1, coreCnt + 1, wireCnt + count);

				tempR=R;tempC=C;
				for (int i = 0; i < count; i++) {
					tempR+=dirR[dir];
					tempC+=dirC[dir];
					arr[tempR][tempC] = 0;
				}
			}
		}
	}
	
	static void copyArr(int[][] a, int[][] b) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				b[i][j] = a[i][j];
			}
		}
	}
}
