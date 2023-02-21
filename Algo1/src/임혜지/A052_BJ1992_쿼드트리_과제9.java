package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A052_BJ1992_쿼드트리_과제9 {
	static int n;// 영상의 크기(2의 제곱수, 1~64)
	static int[][] arr;// 0과 1에 대한 영상
	static boolean zeroFlag;// 0인지 1인지 확인하는 플래그
	static StringBuilder str = new StringBuilder("");// 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		n = Integer.parseInt(in.readLine());// 영상의 크기 입력받음

		arr = new int[n][n];// 주어진 크기의 빈 영상 만들기

		for (int i = 0; i < n; i++) {// n줄에 걸쳐
			char[] temp = in.readLine().toCharArray();// 0과 1에 관한 문자열 입력받기
			for (int j = 0; j < n; j++) {// 입력받은 문자열을
				arr[i][j] = temp[j] - '0';// int형 이차원배열에 저장
			}
		}

		// 입력 끝
		// -------------------------------------------------------
		sol(0, 0, n, arr[0][0] == 0 ? true : false);// 시작하는 인덱스(row,col)와 사이즈, 시작점이 0인지 1인지 확인하는 플래그 전달
		System.out.println(str.toString());//결과 출력

	}

	static boolean check(int row, int col, int size, boolean zeroFlag) {//정사각형이 모두 같은 값인지 체크하는 메소드
		for (int i = row; i < row + size; i++) {//(row,col)부터 확인하면서
			for (int j = col; j < col + size; j++) {
				if (zeroFlag == true && arr[i][j] == 1) {//만약 (row,col)의 값이 0이고 배열값이 1이라면
					return false;//false 리턴
				}
				if (zeroFlag == false && arr[i][j] == 0) {//만약 (row,col)의 값이 1이고 배열값이 0이라면
					return false;//false 리턴
				}
			}
		}
		return true;//모두 일치한다면 true 리턴
	}
	
	static void sol(int row, int col, int size, boolean zeroFlag) {//시작 인덱스(row,col)과 2분의 1씩할 사이즈, (row,col)이 0인지 1인지 확인할 플래그 전달
		if(check(row, col, size, zeroFlag)) {//만약 그 정사각형의 값이 모두 일치한다면
			str.append(zeroFlag==true?0:1);//그 값으로 압축하고
			return;//재귀 종료
		}
		else{//정사각형의 값이 일치하지 않는다면 
			str.append("(");//괄호를 열면서 4개의 칸으로 분리
			sol(row, col, size / 2, arr[row][col] == 0 ? true : false);//왼쪽 위칸
			sol(row, col + size / 2, size / 2, arr[row][col + size / 2] == 0 ? true : false);//오른쪽 위칸
			sol(row + size / 2, col, size / 2, arr[row + size / 2][col] == 0 ? true : false);//왼쪽 아래칸
			sol(row + size / 2, col + size / 2, size / 2, arr[row + size / 2][col + size / 2] == 0 ? true : false);//오른쪽 아래칸
			str.append(")");//재귀 끝날 때 괄호 닫음
		}
	}
}
