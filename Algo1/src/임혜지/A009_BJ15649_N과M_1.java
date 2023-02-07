package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A009_BJ15649_N과M_1 {
	static int n, m;
	static int[] num;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		num = new int[m];
		isSelected = new boolean[n + 1];
		per(0);

	}

	public static void per(int cnt) {
		if (cnt == m) {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(num[j] + " ");
				}
				System.out.println();
			}
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (isSelected[i])
				continue;

			num[cnt] = i;
			isSelected[i] = true;
			per(cnt + 1);

			isSelected[i] = false;
		}
	}
}
