package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A016__BJ11660_구간합구하기5 {
	static int n;// 표의 크기
	static int m;// 합을 구해야하는 횟수
	static int[][] sum; // 누적합

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 수행시간 단축을 위해 사용
		StringTokenizer st = null;// 공백으로 입력받기 위해 미리 선언
		st = new StringTokenizer(in.readLine(), " "); // 공백으로 구분해 입력받음
		n = Integer.parseInt(st.nextToken()); // 표 크기 입력
		m = Integer.parseInt(st.nextToken()); // 횟수 입력
		StringBuilder str=new StringBuilder("");

		sum = new int[n + 1][n + 1];// 인덱스 1부터 시작, 라인별로 생성
		int idx = 1; // sum 연산 위한 인덱스 변수
		int temp;//표 데이터 입력받을 변수
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " "); // 공백으로 구분해 입력받음
			for (int j = 1; j <= n; j++) {
				temp= Integer.parseInt(st.nextToken());//표 입력
				sum[i][j] += sum[i][j - 1] + temp;//행별로 누적합 저장 (예) (1,1)~(1,n) 합 = sum[1][idx]
			}
		}
		int x1, y1, x2, y2; // 입력받을 위치 좌표
		int total;//출력할 총합
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine(), " "); // 공백으로 구분해 입력받음
			x1 = Integer.parseInt(st.nextToken());// 시작 위치
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());// 끝 위치
			y2 = Integer.parseInt(st.nextToken());
			
			total=0;
			for (int j = x1; j <= x2; j++) {
				total+=sum[j][y2]-sum[j][y1-1];//열별로 앞까지의 합 뺄셈
			}
			str.append(total+"\n");
		}
		System.out.println(str.toString());
	}
}