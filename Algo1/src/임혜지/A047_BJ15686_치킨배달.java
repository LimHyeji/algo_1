package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int i, j;

	Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class A047_BJ15686_치킨배달 {
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		Queue<Node> house = new LinkedList<>();
		Queue<Node> chicken = new LinkedList<>();
		PriorityQueue<Integer> distance = new PriorityQueue<>();

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					// 집 위치 저장
					house.add(new Node(i, j));
				}
				if (temp == 2) {
					// 치킨집 위치 저장
					chicken.add(new Node(i, j));
				}
			}
		}
		int temp, min;
		for (Node h : house) {
			min = Integer.MAX_VALUE;
			for (Node c : chicken) {
				temp = Math.abs(h.i - c.i) + Math.abs(h.j - c.j);
				min = Math.min(temp, min);
			}
			distance.add(min);
		}
		int res = 0;
		System.out.println(distance);
		for (int i = 0; i < m; i++) {
			res += distance.remove();
		}
		System.out.println(res);
	}
}
