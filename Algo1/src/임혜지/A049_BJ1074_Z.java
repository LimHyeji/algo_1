package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A049_BJ1074_Z {
	static int n, r, c;// 입력받는 값(2의 n승이 이차원배열의 한 변, r행 c열의 순번 찾기)
	static int size;// 배열의 한 변
	static int total = 0;// 결과로 출력할 방문 순번

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력
		n = Integer.parseInt(st.nextToken());// 배열의 크기
		r = Integer.parseInt(st.nextToken());// r행
		c = Integer.parseInt(st.nextToken());// c열의 위치찾기

		size = (int) Math.pow(2, n);// 배열의 크기

		sol(0, 0, size);// 사각형 한칸이 (0,0)에서 시작
	}

	static void sol(int row, int col, int size) {
		int newSize = size / 2;// 2분의 1씩 줄여나감
		if (row == r && col == c) {// 원하는 위치와 일치할 경우
			System.out.println(total);// 결과 출력하고
			return;// 종료
		} else if (r >= row && r < row + size && c >= col && c < col + size) {// 시간초과로 인해 추가된 조건문(조건 내에 있을 경우에만 재귀)
			sol(row, col, newSize);// 왼쪽 위
			sol(row, col + newSize, newSize);// 오른쪽 위
			sol(row + newSize, col, newSize);// 왼쪽 아래
			sol(row + newSize, col + newSize, newSize);// 오른쪽 아래
		} else {
			total += size * size;// 재귀 돌리지 않고 사각형 한칸만큼 더함
		}

	}
}
