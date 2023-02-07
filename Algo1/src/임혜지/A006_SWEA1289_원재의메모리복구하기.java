package 임혜지;

import java.util.Scanner;

public class A006_SWEA1289_원재의메모리복구하기 {
	static boolean index;

	public static int recur(String s, int idx) {
		if (idx == s.length()) {
			return 0;
		}

		if (s.charAt(idx) != s.charAt(idx - 1)) {
			return recur(s, idx + 1) + 1;
		} else {
			return recur(s, idx + 1);
		}
	}

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		String s = "";
		for (int test_case = 1; test_case <= T; test_case++) {
			s = sc.next();
			int n = 1;
			index = false;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '1') {
					n = i;
					if (n == 0)
						index = true;
					break;
				}
			}
			if (index == false)
				System.out.println("#" + test_case + " " + recur(s, n));
			else
				System.out.println("#" + test_case + " " + (int)(recur(s, n + 1) + 1));
		}
	}
}
