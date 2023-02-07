package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A009_BJ15649_N과M_1 {
	static int n, m;//총 개수와 선택하는 개수
	static int[] num;//순회한 숫자
	static boolean[] isSelected;//숫자별 순회 여부

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		num = new int[m];
		isSelected = new boolean[n + 1];
		per(0);//재귀함수 호출

	}

	public static void per(int cnt) {
		if (cnt == m) {//선택한 개수만큼 재귀 시 종료
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(num[j] + " ");//순회한 숫자 출력
				}
				System.out.println();
			}
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (isSelected[i])//이미 순회한 숫자일 경우 재귀 호출 x
				continue;

			num[cnt] = i;//순회한 숫자 기록
			isSelected[i] = true;//순회 여부 기록
			per(cnt + 1);//다음 숫자에 대해 재귀

			isSelected[i] = false;//또 다른 재귀를 위해 순회 여부 풀어줌
		}
	}
}
