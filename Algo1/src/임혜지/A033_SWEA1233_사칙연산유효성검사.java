package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A033_SWEA1233_사칙연산유효성검사 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringBuilder str = new StringBuilder("");// 출력할 문자열
		for (int test_case = 1; test_case <= 10; test_case++) {// 테스트 케이스 총 10개
			int res = 1;// 유효성 변수(현재 유효한 상태이고, 유효하지 않은 상태를 만날 경우 0으로 바꾼 후 반복문 탈출)
			int n = Integer.parseInt(in.readLine());// 노드 개수 입력
			for (int i = 1; i <= n; i++) {// 총 n번의
				String[] temp = in.readLine().split(" ");// 공백으로 구분해 입력
				if(temp.length==4) {//자식 노드가 입력된 경우
					if((int)(temp[1].charAt(0))>=48&&(int)(temp[1].charAt(0))<=57) {//숫자가 입력되었을 때
						res=0;//유효하지 않음
					}
				}
				else {//자식 노드가 입력되지 않은 경우
					if((int)(temp[1].charAt(0))<48&&(int)(temp[1].charAt(0))>57) {//연산자가 입력되었을 때
						res=0;//유효하지 않음
					}
				}
			}
			str.append("#").append(test_case).append(" ").append(res).append("\n");//출력문 덧붙임
		}
		System.out.println(str);//결과 출력
	}
}
