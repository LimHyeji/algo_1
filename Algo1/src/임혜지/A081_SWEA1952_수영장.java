package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A081_SWEA1952_수영장 {
	// 테스트 케이스마다 초기화 고려해야함.
	static int[] ticket = new int[4]; // 0:1일, 1:1달, 2:3달, 3:1년
	static int[] plan = new int[12];// 12개월 이용 계획을 담을 배열\
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {// 이용권 가격 입력
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {// 12개월 이용 계획 입력
				plan[i] = Integer.parseInt(st.nextToken());
			}

			// -------------------INPUT END

			// 1년 이용하기 위한 최소비용
			result = Integer.MAX_VALUE;
			// 최소 비용을 찾기위한 재귀 호출
			swimmingPool(0, 0);

			sb.append("#").append(t).append(" ").append(result).append("\n");

		}
		System.out.println(sb);
	}

	// 누적 비용 전달, 누적 이용 개월수
	static void swimmingPool(int month, int paied) {
		if (month >= 12) {
			if (result > paied) {
				result = paied;
			}
			return;
		}
		// 1일 이용권 쓸 때
		swimmingPool(month + 1, paied + (ticket[0] * plan[month]));
		// 1달 이용권 쓸 때
		swimmingPool(month + 1, paied + ticket[1]);
		// 3달 이용권 쓸 때
		swimmingPool(month + 3, paied + ticket[2]);
		// 1년 이용권 쓸 때
		swimmingPool(month + 12, paied + ticket[3]);
	}

}
