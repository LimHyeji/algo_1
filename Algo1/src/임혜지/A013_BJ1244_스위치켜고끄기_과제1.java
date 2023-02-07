package 임혜지;

import java.util.Arrays;
import java.util.Scanner;

public class A013_BJ1244_스위치켜고끄기_과제1 {
	static int n;// 스위치 개수
	static int[] arr = new int[101];// 스위치 상태
	static int stu;// 학생수
	static int gen; // 성별
	static int idx;// 바꿀 스위치 순번

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();// 스위치 개수 입력받고
		for (int i = 1; i <= n; i++) {// 스위치 상태 입력받음 (1부터 시작)
			arr[i] = sc.nextInt();
		}

		stu = sc.nextInt();
		for (int i = 0; i < stu; i++) {
			gen = sc.nextInt(); // 성별 입력
			idx = sc.nextInt(); // 스위치 순번 입력
			if (gen == 1)
				boy(idx); // 성별이 남자면, 배수 바꿈
			else
				girl(idx); // 성별이 여자면, 좌우대칭으로 바꿈
		}
		for(int i=1;i<=n;i++) {
			System.out.print(arr[i]+" ");//20개씩 출력
			if(i%20==0) System.out.println();
		}
		
	}

	public static void change(int idx) {// 스위치 뒤집는 메소드
		arr[idx]=arr[idx]==1?0:1;//1이면 0으로, 0이면 1로
	}

	public static void boy(int idx) {
		// 남학생 1
		// 배수인 곳 뒤집기
		for (int i = 1; i <= n; i++) {
			if (i % idx == 0) {// idx의 배수인 스위치는
				change(i);// 스위치 뒤집기
			}
		}
	}

	public static void girl(int idx) {
		// 여학생 2
		// 좌우대칭 뒤집기
		change(idx);//입력받은 인덱스는 뒤집기
		for (int i = 1; i <= n / 2; i++) {//인덱스에서 양쪽으로 뻗어나가면서
			if ((idx - i) <= 0 || (idx + i) > n) {//배열 사이즈 초과 시
				break;//종료
			} else if (arr[idx - i] == arr[idx + i]) {//인덱스 기준 좌우대칭일때
				change(idx - i);//왼쪽 뒤집기
				change(idx + i);//오른쪽 뒤집기
			}else {
				break;
			}
		}
	}
}
