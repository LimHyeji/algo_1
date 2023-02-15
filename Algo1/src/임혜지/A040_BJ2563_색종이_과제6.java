package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. 흰색 도화지(100 by 100), 검은색 색종이(10 by 10)
 * 2. 색종이의 시작 (x,y) 좌표 입력 (도화지 벗어날 수 없음)
 * 3. 색종이 넓이 출력
 * 	(전체 색종이 100*n에서 중복된 영역을 빼기)
 * 	(플래그 사용하기)
 * 	
 */

public class A040_BJ2563_색종이_과제6 {
	public static void main(String[] args) throws NumberFormatException, IOException {// main 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		int N = Integer.parseInt(br.readLine());// 색종이 개수 입력
		int board[][] = new int[101][101];// 흰색 도화지(100 by 100)
		int answer = 0;// 색종이 넓이 초기화
		for (int idx = 0; idx < N; idx++) {// 총 n번의 for문 시작
			String[] temp = br.readLine().split(" ");// 색종이 시작 (x,y)좌표 입력
			int x1, y1, y2, x2;//색종이의 왼쪽위와 오른쪽 아래 좌표
			// 왼쪽 위를 x1,y1
			// 오른쪽 아래를 x2,y2
			x1 = Integer.parseInt(temp[0]);//색종이의 왼쪽 x좌표
			x2 = x1 + 10;//색종이의 오른쪽 x좌표
			y2 = Integer.parseInt(temp[1]);//색종이의 아래 y좌표
			y1 = y2 + 10;//색종이의 위 y좌표

			//이차원배열 누적합 사용
			board[y1][x1] += 1;//왼쪽위 위치에 1 더하기
			board[y1][x2] -= 1;//오른쪽위 위치에 1 빼기
			board[y2][x1] -= 1;//왼쪽아래 위치에 1 빼기
			board[y2][x2] += 1;//오른쪽 아래 위치에 1 더하기
		}// 총 n번의 for문 끝

		// x축 누적합
		for (int y = 1; y < 101; y++) {
			for (int x = 1; x < 101; x++) {
				board[y][x] += board[y][x - 1];//합을 오른쪽으로 누적
			}
		}

		// y축 누적합
		for (int x = 1; x < 101; x++) {
			for (int y = 1; y < 101; y++) {
				board[y][x] += board[y - 1][x];//합을 아래쪽으로 누적
				if (board[y][x] != 0) {//그 위치에 색종이가 있다면
					answer++;//넓이 + 1
				}
			}
		}
		
		System.out.println(answer);//색종이의 넓이 출력
	}
}
