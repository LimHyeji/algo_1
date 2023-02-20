package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A049_BJ1074_Z {
	static int n, r, c;// 입력받는 값(2의 n승이 이차원배열의 한 변, r행 c열의 순번 찾기)
	static int size;
	static int total = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력
		n = Integer.parseInt(st.nextToken());// 배열의 크기
		r = Integer.parseInt(st.nextToken());// r행
		c = Integer.parseInt(st.nextToken());// c열의 위치찾기

		size = (int) Math.pow(2, n);// 배열의 크기

		sol(0, size - 1, 0, size - 1, size);// 사각형 한칸이 (0,0)에서 (size-1,size-1)까지
	}

	static void sol(int rStart, int rEnd, int cStart, int cEnd, int size) {
		if (size == 1) {// 한칸일 경우
			total++;// 순회 숫자 1 증가
			if (rStart == r && cStart == c) {// 원하는 위치와 일치할 경우 출력
				System.out.println(total);
			}
			//System.out.println("total" + total + "번 : " + rStart + " " + rEnd + " " + cStart + " " + cEnd + " " + size);
			return;
		}

		sol(rStart, rEnd / 2, cStart, cEnd / 2, size / 2);
		sol(rStart, rEnd / 2, size / 2, cEnd, size / 2);
		sol(size / 2, rEnd, cStart, cEnd / 2, size / 2);
		sol(size / 2, rEnd, size / 2, cEnd, size / 2);
	}
}
