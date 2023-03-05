package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A081_SWEA1952_수영장 {
	static int t;// 테스트케이스 수
	static int[] ticket = new int[4]; // 0:1일, 1:1달, 2:3달, 3:1년
	static int[] plan = new int[12];// 12개월 이용 계획을 담을 배열
	static int res;// 1년간 이용하는 수영장 최소 비용
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder str = new StringBuilder();

		t = Integer.parseInt(br.readLine());// 테스트 케이스 수 입력

		for (int tc = 1; tc <= t; tc++) {// 총 t번의 테스트 케이스
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {// 총 네 종류의
				ticket[i] = Integer.parseInt(st.nextToken());// 이용권 가격 입력
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {// 12개월의
				plan[i] = Integer.parseInt(st.nextToken());// 이용 계획 입력
			}

			// 입력끝
			// -----------------------------

			res = Integer.MAX_VALUE;// 1년 이용하기 위한 최소비용
			swimmingPool(0, 0); // 최소 비용을 찾기위한 재귀 호출

			str.append("#").append(tc).append(" ").append(res).append("\n");// 테스트 케이스별 결과 붙이기

		}
		System.out.println(str);// 결과 한꺼번에 출력
	}

	static void swimmingPool(int month, int paied) {// 누적되는 달과 누적되는 가격
		if (month >= 12) {// 12개월만큼의 재귀를 돌았을 때
			res = Math.min(res, paied);// 이용 비용 최소값 업데이트
			return;// 재귀 종료
		}

		swimmingPool(month + 1, paied + (ticket[0] * plan[month]));// 1일 이용권 쓸 때

		swimmingPool(month + 1, paied + ticket[1]);// 1달 이용권 쓸 때

		swimmingPool(month + 3, paied + ticket[2]);// 3달 이용권 쓸 때

		swimmingPool(month + 12, paied + ticket[3]);// 1년 이용권 쓸 때
	}

}
