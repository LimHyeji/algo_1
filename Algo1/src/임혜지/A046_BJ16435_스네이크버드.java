package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A046_BJ16435_스네이크버드 {
	static int n, l;// 과일 개수와 스네이크버드 초기 길이
	static PriorityQueue<Integer> h = new PriorityQueue<Integer>();//높이를 오름차순 정리할 우선순위큐

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");//한 라인을 공백으로 입력받음

		n = Integer.parseInt(st.nextToken());//과일 개수
		l = Integer.parseInt(st.nextToken());//스네이크버드 초기 길이

		st = new StringTokenizer(in.readLine(), " ");//두 번째 줄 입력(과일의 높이들)
		while (n-- > 0) {//n개의
			h.add(Integer.parseInt(st.nextToken()));//과일높이를 입력받아 우선순위 큐에 저장
		}
		
		sol(0);//큐의 0번부터 출발
		System.out.println(l);//결과 도출된 스네이크버드 최종 길이 출력
	}

	static void sol(int idx) {
		if(h.isEmpty()) {//만약 큐가 비었다면(더이상 과일이 없다면)
			return;//재귀 종료
		}
		else if (h.peek() <= l) {//만약 먹을 수 있는 과일이라면(스네이크버드 길이보다 낮게 있다면)
			h.remove();//과일 제거하면서
			l += 1;//길이가 1 증가함
		}
		else {//더이상 먹을 수 없다면(모든 과일이 스네이크버드 길이보다 높다면)
			return;//재귀 종료
		}
		
		sol(idx + 1);//다음 과일 찾기
	}
}
