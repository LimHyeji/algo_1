package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A046_BJ16435_스네이크버드 {
	static int n, l;// 과일 개수와 스네이크버드 초기 길이
	static PriorityQueue<Integer> h = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine(), " ");
		while (n-- > 0) {
			h.add(Integer.parseInt(st.nextToken()));
		}
		
		sol(0);
		System.out.println(l);
	}

	static void sol(int idx) {
		if(h.isEmpty()) {
			return;
		}
		else if (h.peek() <= l) {
			h.remove();
			l += 1;
		}
		else {
			return;
		}
		
		sol(idx + 1);
	}
}
