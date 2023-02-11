package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 1. 왼 오 괄호 개수 일치
 * 2. 왼쪽 먼저
 * 3. 포함관계 일치(pair 일치)
 */

public class A023_SWEA1218_괄호짝짓기_배열활용 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// buffer로 입력받기
		for (int test_case = 1; test_case <= 10; test_case++) {
			int res = 1;// 유효성 여부 (1-유효함, 0-유효하지않음)
			int len = Integer.parseInt(in.readLine());// 테스트케이스의 길이 입력
			String str = in.readLine();// 괄호 문자열 입력
			int cnt = 0;// 짝이 안맞는 문자의 개수
			int bracket[] = { 0, 0, 0, 0 };// 괄호 종류별 카운트 변수

			for (int i = 0; i < len; i++) {
				int temp = str.charAt(i);// 한 문자씩 꺼내서

				switch (temp) {
				// 왼쪽 괄호일 경우
				case '{':// 괄호 종류에 따라
					bracket[0]++;
					break; // 하나씩 개수 세어놓기
				case '[':
					bracket[1]++;
					break;
				case '(':
					bracket[2]++;
					break;
				case '<':
					bracket[3]++;
					break;
				default:// 왼쪽 괄호가 아니고,
					int index = -1;
					switch (temp) {
					// 오른쪽 괄호일 경우
					case '}':// 괄호 종류에 따라
						index = 0;
						break;// 1을 뺄 위치 찾기
					case ']':
						index = 1;
						break;
					case ')':
						index = 2;
						break;
					case '>':
						index = 3;
						break;
					}
					if (bracket[index] < 1)
						cnt++;// 해당 위치의 짝이 안맞으면 1증가
					else
						bracket[index]--; // 짝이 있으므로 하나씩 감소
				}
			}

			for (int k : bracket)
				cnt += k;// 괄호가 남아있는지 확인

			System.out.printf("#%d %d\n", test_case, cnt == 0 ? 1 : 0);// 괄호가 모두 닫혀있으면 유효, 그렇지 않으면 무효
		}
	}
}