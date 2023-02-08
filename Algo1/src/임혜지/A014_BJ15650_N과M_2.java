package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A014_BJ15650_N과M_2 {
	static int n;// 범위
	static int m;// 수열 길이
	static int[] num;// 수열 숫자

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		num = new int[m];// m개의 숫자 (출력할 변수)

		com(0, 1);
	}

	public static void com(int cnt, int start) {// 순회할 횟수, 시작점
		if (cnt == m) {// 순회를 끝마치면
			for (int i = 0; i < m; i++) {// 출력 후
				System.out.print(num[i] + " ");
			}
			System.out.println();
			return;// 종료
		}

		for (int i = start; i <= n; i++) {

			num[cnt] = i;// 순회한 숫자 저장
			com(cnt + 1, i + 1);// 재귀 호출

		}
	}
}
