package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A021_SWEA2001_파리퇴치_과제3 {
	static int t;// 테스트케이스 수
	static int n, m;// 테이블 한 변 길이, 파리 잡는 사각형 한 변 길이
	static int[][] sum;// 라인당 누적합
	static int total;// 출력할 결과값(파리의 최대 개수)
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		t = Integer.parseInt(in.readLine());// 테스트케이스 수 입력

		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(in.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sum = new int[n + 1][n + 1];// 한 행당 하나씩의 누적합 배열 생성
			int temp;// 입력받는 파리 개수

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(in.readLine(), " ");// 총 n줄 입력
				for (int j = 1; j <= n; j++) {
					temp = Integer.parseInt(st.nextToken());// 공백 기준으로 테이블 한 칸의 데이터 입력
					sum[i][j] += sum[i][j-1] + temp;// 누적합 저장
				}
			}

			// 각 라인당 n-m+1번
			// 5 by 5이고, 2칸이라면 ... 1~2,2~3,3~4,4~5
			// 1~m, ... , n-m+1~n
			total=0;//결과 출력에 쓸 합 초기화
			for (int i = 1; i <= n - m + 1; i++) {//sum인덱스가 1부터 시작
				for (int j = 1; j <= n - m + 1; j++) {//sum인덱스가 1부터 시작
					int tempSum=0;
					for (int k = 0; k < m; k++) {
						tempSum += sum[i + k][j + m - 1] - sum[i + k][j - 1];//m칸의 누적합 더함
					}
					total = Math.max(total, tempSum);//기존 값과 비교해 더 큰 값을 저장
				}
			}
			System.out.println("#" + test_case + " " + total);//결과 출력
		}
	}
}
