package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A048_BJ3040_백설공주와일곱난쟁이_과제8 {
	static int[] num = new int[9];// 입력받을 9개의 숫자
	static StringBuilder str = new StringBuilder("");// 출력할 문자열
	static int[] com = new int[7];// 조합하는 7개의 숫자

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력

		for (int i = 0; i < 9; i++) {// 9개의
			num[i] = Integer.parseInt(in.readLine());// 숫자입력
		}

		sol(0, 0);// cnt 0과 start 0 매개변수 전달해 재귀 호출
		System.out.println(str.toString());// 합이 100이 되는 7개의 숫자 출력
	}

	static void sol(int cnt, int start) {
		if (cnt == 7) {// 7개의 숫자를 모두 구했을 때
//			if (sum == 100) {
//				for (int i = 0; i < 7; i++) {
//					str.append(com[i]).append("\n");
//				}
//			}
			int sum = 0;// 합 변수 0으로 초기화 후
			for (int i : com) {// 7개의 숫자에 대해
				sum += i;// 합 계산 후
			}
			if (sum == 100) {// 그 합이 100일 때
				for (int i = 0; i < 7; i++) {// 7개의 숫자를
					str.append(com[i]).append("\n");// append
				}
			}
			return;// 재귀 종료
		}

		for (int i = start; i < 9; i++) {// 시작점부터 인덱스 8까지
			com[cnt] = num[i];// 7개의 숫자 조합
			// sum += num[i];
			// sol(cnt + 1, i + 1, sum);
			sol(cnt + 1, i + 1);// 다음 순번으로 넘어가서 숫자 구하기
		}
	}
}
