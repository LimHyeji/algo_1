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
	static int t;// 테스트케이스 수
	static int n;// 셀의 크기
	static int arr[][];// 셀
	static List<Node> list;// 코어 리스트
	static int dirR[] = { -1, 1, 0, 0 };// 상하좌우
	static int dirC[] = { 0, 0, -1, 1 };// 상하좌우
	static StringBuilder str = new StringBuilder();// 출력할 문자열
	static int maxC, minL;// 코어의 최대 개수, 전선의 최소길이 합

	static class Node {// 코어의 위치를 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int tc = 1; tc <= t; tc++) {// 총 t번의 테스트 케이스
			n = Integer.parseInt(in.readLine());// 셀의 크기 입력
			arr = new int[n][n];// 셀 생성
			list = new ArrayList<>();// 코어 위치 저장할 리스트 생성

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());// 셀 입력
					if (arr[i][j] == 1 && i > 0 && i < n - 1 && j > 0 && j < n - 1)// 가장자리인 코어들은 전선이 놓일 필요가 없으므로, 가장자리가
																					// 아닌 코어들에 대해
						list.add(new Node(i, j));// 코어 위치를 리스트에 저장
				}
			}

			// 입력끝
			// ------------------------
			/*
			 * 상하좌우 일직선 이동
			 * 
			 * 1을 만나거나 이미 전선이 깔려있을 때 break
			 * 
			 * 인덱스 벗어나면 전선 깔기 완료
			 * 
			 * 길에 1로 바꾸어주면서 전선 깔기
			 */
			maxC = 0;// 코어의 최대 개수 구하기 위한 초기화
			minL = Integer.MAX_VALUE;// 전선의 최소 길이 합 구하기 위한 초기화
			sol(0, 0, 0);// 탐색
			str.append("#").append(tc).append(" ").append(minL).append("\n");// 테스트 케이스별 결과 붙이기
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void sol(int cnt, int coreCnt, int wireCnt) {// cnt : 재귀 횟수, coreCnt : 코어의 개수, wireCnt : 전선의 길이
		if (cnt == list.size()) {// 리스트에 존재하는 코어에 대해 모두 재귀 돌았을 때
			if (coreCnt > maxC) {// 코어의 최대 개수 업데이트가 가능하면
				maxC = coreCnt;// 코어 최대 개수 업데이트
				minL = wireCnt;// 전선 최소 길이 업데이트
			} else if (coreCnt == maxC) {// 코어의 개수가 일치한다면
				minL = Math.min(minL, wireCnt);// 전선은 최소 길이인 것으로 선택
			}

			return;// 재귀 종료
		}

		int R = list.get(cnt).row;// 현재 탐색중인 코어의 행 위치
		int C = list.get(cnt).col;// 현재 탐색중인 코어의 열 위치

		for (int dir = 0; dir < 4; dir++) {// 총 네 방향에 대해

			int newR = R, newC = C;// 나아갈 행열 인덱스 변수 선언
			int count = 0;// 전선이 깔리는 개수 카운트할 변수 선언
			while (true) {// 조건을 벗어나지 않을 때에는 계속 반복
				newR += dirR[dir];// 나아가는 방향으로의 행 위치
				newC += dirC[dir];// 나아가는 방향으로의 열 위치

				if (newR >= 0 && newR < n && newC >= 0 && newC < n) {// 인덱스 범위 내에 있을 때
					if (arr[newR][newC] == 1) {// 전선 이미 존재한다면
						count = 0;// 그 전선은 깔 수 없으므로, 0으로 초기화하고
						break;// 반복문 탈출(그 방향으로는 더이상 못 나아감)
					}
					count++;// 인덱스 범위 내에 있으면서 전선이 깔려있지 않다면 카운트 1 증가
				} else {// 인덱스 벗어날 경우
					break;// 반복문 종료
				}
			}

			if (count == 0)// 전선이 이미 깔려 있던 경우에는
				sol(cnt + 1, coreCnt, wireCnt);// 코어와 전선 개수 업데이트하지 않고 다음 코어 탐색
			else {
				int tempR = R, tempC = C;
				for (int i = 0; i < count; i++) {// 전선을 깔 수 있는 경우에는
					tempR += dirR[dir];
					tempC += dirC[dir];
					arr[tempR][tempC] = 1;// 나아가는 방향에 대해 전선 깔 수 있는 횟수만큼 1로 바꿔주기
				}

				sol(cnt + 1, coreCnt + 1, wireCnt + count);// 다음 코어에 대해 재귀, 전선 까는 코어 개수 1 증가, count만큼의 전선 길이가 추가됨

				tempR = R;
				tempC = C;
				for (int i = 0; i < count; i++) {
					tempR += dirR[dir];
					tempC += dirC[dir];
					arr[tempR][tempC] = 0;// 재귀 돌고 난 이후에는 깔았던 전선 원상복구
				}
			}
		}
	}
}
