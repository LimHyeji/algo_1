package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A045_BJ2839_설탕배달 {
	static int n;// 배달해야할 설탕 무게
	static int res = -1;// 결과값(무효할 경우 -1 리턴)
	static int temp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		n = Integer.parseInt(in.readLine());// 배달해야할 설탕 무게 입력

		// 3kg와 5kg 존재
		/*
		 * 5로 끝까지 나눔 -> 3으로 나눔 -> 나머지 0 (유효) 종료 5로 한개 덜 나눔 -> 3으로 나눔 -> 나머지 0 (유효) 종료
		 * ... 5로 나눌 수 없음 -> 3으로 나눔 -> 나머지 0 (유효) 종료
		 * 
		 * -> 나머지 0이 아님 (무효)
		 */
		temp = n / 5;//5로 나눴을 때 가장 큰 몫
		sol(temp);//그 몫을 매개변수로 재귀함수 호출
		System.out.println(res);//결과 출력
	}

	static void sol(int q) {
		if (q == 0) {//5로 나눈 몫이 0이면
			if (n % 3 == 0) {//3으로 나눠보고 나누어 떨어질 때
				res = n / 3;//그 몫을 저장
			}
			return;//재귀 종료(한번도 if문에 도달하지 않았을 경우에는 그대로 -1)
		}
		if((n-5*q)%3==0) {//5로 나눈 후의 값이 3으로 나누어 떨어질 경우
			res=q;//5로 나눈 몫 저장
			res+=(n-5*q)/3;//3으로 나눈 몫도 더해서
			return;//재귀 종료
		}
		sol(q-1);//몫을 1개 줄여서 재귀 호출
	}
}
