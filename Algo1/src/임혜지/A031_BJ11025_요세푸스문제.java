package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class A031_BJ11025_요세푸스문제 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");// 공백으로 구분하면서 한 라인 입력

		int n = Integer.parseInt(st.nextToken());// 인원수 입력
		int k = Integer.parseInt(st.nextToken());// 양의 정수 입력
		int res = -1;
		while (true) {
			if (n == 0) {// 마지막 사람일 때
				System.out.println(res);
				break;
			}
			
			n--;
		}

	}
}
