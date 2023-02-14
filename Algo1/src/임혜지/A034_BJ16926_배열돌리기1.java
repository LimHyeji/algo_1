package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A034_BJ16926_배열돌리기1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");// 첫째줄 입력(n,m,r)
		int n = Integer.parseInt(st.nextToken());// 배열의 크기(행 개수)
		int m = Integer.parseInt(st.nextToken());// 배열의 크기(열 개수)
		int r = Integer.parseInt(st.nextToken());// 회전해야 하는 횟수
		int[][] arr = new int[n][m];// n by m 배열 생성
		for (int i = 0; i < n; i++) {// n줄에 걸쳐
			st = new StringTokenizer(in.readLine(), " ");// 한 라인 입력 받고
			for (int j = 0; j < m; j++) {// 열의 개수만큼
				arr[i][j] = Integer.parseInt(st.nextToken());// 배열값 저장
			}
		}
		/*
		 * 시작점은 오른쪽 모서리
		 * idx는 0부터 min(n,m)/2까지
		 * 겉 테두리 사각형, 두번째 테두리 사각형, ... 이 r번씩 이동(테두리별로 서로 값 바뀌지 않음)
		 * 테두리는 위/오른/아래/왼줄로 취급
		 */

		for (int i = 0; i < r; i++) {// 총 r번 회전
			for (int j = 0; j < Math.min(n, m) / 2; j++) {// 테두리 사각형의 개수이자 시작 인덱스
				int temp = arr[j][j];// 회전 시 하나의 데이터는 잃으므로 시작 값 저장해놓음
				for (int k = j + 1; k < m - j; k++) {//테두리 사각형의 윗줄
					arr[j][k - 1] = arr[j][k];//오른쪽 값을 왼쪽에 저장
				}
				for (int k = j + 1; k < n - j; k++) {//테두리 사각형의 오른줄
					arr[k - 1][m - j - 1] = arr[k][m - j - 1];//아래쪽 값을 위쪽에 저장
				}
				for (int k = m - j - 2; k >= j; k--) {//테두리 사각형의 아래줄
					arr[n - j - 1][k + 1] = arr[n - j - 1][k];//왼쪽 값을 오른쪽에 저장
				}
				for (int k = n - j - 2; k >= j; k--) {//테두리 사각형의 왼줄
					arr[k + 1][j] = arr[k][j];//위쪽 값을 아래쪽에 저장
				}
				arr[j + 1][j] = temp; //첫번째 값이 저장되었어야할 곳(첫째값의 아래쪽)에 저장
			}
		}

		StringBuilder str = new StringBuilder("");//출력할 문자열
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				str.append(arr[i][j]).append(" ");//결과 붙여넣고
			}
			str.append("\n");
		}
		System.out.println(str);//결과 한번에 출력
	}
}
