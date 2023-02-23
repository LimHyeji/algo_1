package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {//각 위치에 대한 클래스
	int i, j;//행과 열 인덱스

	Node(int i, int j) {//행과 열 인덱스를 받는 클래스 생성자
		this.i = i;
		this.j = j;
	}
}

public class A047_BJ15686_치킨배달 {
	static int n, m;//도시의 크기, 남는 치킨집 최대 개수
	static Queue<Node> house = new LinkedList<>();//집 위치들을 저장할 큐
	static Queue<Node> chicken = new LinkedList<>();//치킨집 위치들을 저장할 큐
	static PriorityQueue<Integer> distance = new PriorityQueue<>();//거리를 오름차순으로 정리할 우선순위 큐


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");//한 라인 입력

		n = Integer.parseInt(st.nextToken());//도시의 크기
		m = Integer.parseInt(st.nextToken());//남는 치킨집 최대 개수 

		
		for (int i = 1; i <= n; i++) {//n by n 크기의 도시 입력을 받기 위해 n줄의
			st = new StringTokenizer(in.readLine(), " ");//한 라인 입력받고
			for (int j = 1; j <= n; j++) {//n열에 걸쳐
				int temp = Integer.parseInt(st.nextToken());//한 칸의 기록 입력
				if (temp == 1) {//집이라면
					house.add(new Node(i, j));// 집 위치 저장
				}
				if (temp == 2) {//치킨집이라면
					chicken.add(new Node(i, j));// 치킨집 위치 저장
				}
			}
		}
		int res = 0;//도시에 남은 m개의 치킨거리 저장할
		//System.out.println(distance);
		for (int i = 0; i < m; i++) {
			res += distance.remove();
		}
		System.out.println(res);
	}
	
	static int findMinD(Node h) {
		int min=Integer.MAX_VALUE;
		for(Node c:chicken) {
			min=Math.min(min, Math.abs(h.i - c.i) + Math.abs(h.j - c.j));
		}
		return min;
	}
}
