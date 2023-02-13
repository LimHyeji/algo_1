package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class A028_SWEA1228_암호문1 {
	static int n;// 암호문 길이(10~20)
	static int cmd;// 명령어 개수(5~10)

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력받기
		StringTokenizer st = null;// 공백으로 구분

		for (int test_case = 1; test_case <= 10; test_case++) {
			n = Integer.parseInt(in.readLine());// 암호문 길이 입력
			List<Integer> pswd = new LinkedList<Integer>();// 연결리스트로 암호문 입력받을 준비
			st = new StringTokenizer(in.readLine(), " ");// 암호문 한 라인 입력 받아 공백으로 구분
			for (int i = 0; i < n; i++) {
				pswd.add(Integer.parseInt(st.nextToken()));// 상자에 하나씩 저장
			}
			cmd = Integer.parseInt(in.readLine());// 명령어 개수 입력
			st = new StringTokenizer(in.readLine(), " ");// 공백으로 한번에 입력받음
			for (int i = 0; i < cmd; i++) {// cmd번의 명령어를 입력받음
				st.nextToken();// I 명령어 입력
				int x = Integer.parseInt(st.nextToken());// x위치 입력받음
				int y = Integer.parseInt(st.nextToken());// 추가될 숫자 개수
				for (int j = 0; j < y; j++) {
					int temp = Integer.parseInt(st.nextToken());// 추가되는 숫자
					pswd.add(x++, temp);// x위치에 숫자 추가
				}
			}
			System.out.print("#" + test_case);
			for (int i = 0; i < 10; i++) {
				System.out.print(" " + pswd.get(i));
			}
			System.out.println();
		}
	}
}
