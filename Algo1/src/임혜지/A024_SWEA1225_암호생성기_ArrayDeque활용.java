package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * idx 1부터 시작하면서, 앞에서 remove 후 idx만큼 감소해 맨 뒤에 add
 * 반복 시마다 idx++
 * -> 한 사이클
 * 
 * 0보다 작을 경우 0으로 저장, 종료
 * 
 * idx가 8이 되는 경우 1로 초기화
 */
public class A024_SWEA1225_암호생성기_ArrayDeque활용 {
	static Deque<Integer> base;// 입력받을 암호 base

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분하기 위한 선언

		for (int test_case = 1; test_case <= 10; test_case++) {// 테스트케이스 10개
			int t = Integer.parseInt(in.readLine());// 테스트케이스 번호 입력
			base = new ArrayDeque<>();// 입력받을 ArrayDeque 생성
			st = new StringTokenizer(in.readLine(), " ");// 공백으로 구분해 한 라인 입력
			for (int i = 0; i < 8; i++) {// 8개의 데이터 입력
				int temp = Integer.parseInt(st.nextToken());// 한 숫자 입력받고
				base.add(temp);// 저장(뒤에 저장됨)
			}
			int idx = 1;// 1에서 시작
			int temp;
			while (true) {// 값이 0보다 작아질 때 break
				if (idx > 5) {// idx가 5 초과 시
					idx = idx % 5 == 0 ? 5 : idx % 5;// 초기화
				}
				temp = base.removeFirst() - idx;// 앞에서 제거한 값을 idx만큼 감소하고
				idx++;// idx 하나씩 증가
				if (temp <= 0) {// 만약 0보다 작으면
					base.addLast(0);// 맨 뒤에 0 넣고
					break;// 반복문 종료
				}
				base.addLast(temp);// 0보다 작지 않으면 temp 맨 뒤에 add
			}
			StringBuilder str = new StringBuilder("#");// 출력할 문자열 초기화
			str.append(test_case);// 테스트 케이스 번호 추가
			for (int i = 0; i < 8; i++) {
				str.append(" ").append(base.removeFirst());// 공백과 앞 숫자 append
			}
			System.out.println(str.toString());
		}
	}
}
