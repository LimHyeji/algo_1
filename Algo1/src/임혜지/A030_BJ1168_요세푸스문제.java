package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A030_BJ1168_요세푸스문제 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");// 공백으로 구분하면서 한 라인 입력

			int n = Integer.parseInt(st.nextToken());// 인원수 입력
			int k = Integer.parseInt(st.nextToken());// 양의 정수 입력

			Queue<Integer> p = new LinkedList<>();// 원형으로 앉을 사람 나열

			for (int i = 1; i <= n; i++) {
				p.add(i);// 1번부터 n번까지 추가
			}

			StringBuilder str = new StringBuilder("<");// 출력할 빌더 생성
			while (true) {
				if (p.size() == 1) {// 한명만 남을 때
					str.append(p.peek()).append(">");// 마지막 원소와 닫힌 괄호
					break;// 반복문 탈출
				}
				for (int i = 1; i < k; i++) {// k번째 제거하기 전까지 반복
					p.add(p.peek());// 1번부터 k-1번까지를 다시 추가하면서
					p.remove();// 삭제
				}
				str.append(p.peek()).append(", ");// k번째 출력문에 추가하면서
				p.remove();// 삭제
			}

			System.out.println(str.toString());
		}
	}
}
