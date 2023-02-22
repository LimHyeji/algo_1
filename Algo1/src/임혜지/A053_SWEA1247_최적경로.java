package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A053_SWEA1247_최적경로 {
	static class Node {// 위치 값을 가질 클래스
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int t;// 전체 테스트케이스 수
	static int n;// 고객의 수
	static Node company, house;// 회사와 집의 위치
	static Node[] customer;// 고객들의 위치

	static int[] num;// 완전탐색을 위한 순열 만들 숫자배열
	static boolean[] visit;// 순열 생성 시 선택 여부 결정

	static int min;// 최단 이동경로
	static StringBuilder str = new StringBuilder("");// 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력받을 준비
		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력

		for (int test_case = 1; test_case <= t; test_case++) {// 총 t번의 테스트 케이스
			n = Integer.parseInt(in.readLine());// 고객의 수 입력
			int row, col;// 입력받은 위치 임시저장할 변수
			st = new StringTokenizer(in.readLine());// 한 라인 입력

			row = Integer.parseInt(st.nextToken());// 회사의 행 위치
			col = Integer.parseInt(st.nextToken());// 회사의 열 위치
			company = new Node(row, col);// 회사 위치 저장

			row = Integer.parseInt(st.nextToken());// 집의 행 위치
			col = Integer.parseInt(st.nextToken());// 집의 열 위치
			house = new Node(row, col);// 집 위치 저장

			customer = new Node[n];// n명의 고객 위치 정보 담을 배열
			num = new int[n];// 순열 만들 숫자
			visit = new boolean[n];// 순열 만들 플래그

			for (int i = 0; i < n; i++) {
				row = Integer.parseInt(st.nextToken());// i번째 고객의 행 위치
				col = Integer.parseInt(st.nextToken());// i번째 고객의 열 위치
				customer[i] = new Node(row, col);// 고객 위치 저장
			}

			// 입력끝
			// ---------------------------------------------------------------
			min = Integer.MAX_VALUE;// 최소값 저장할 변수 초기화
			per(0);// 순열 메소드에서 순열 만들 때마다 솔루션 함수 호출
			str.append("#").append(test_case).append(" ").append(min).append("\n");// 출력할 결과 문자열에 저장
		}
		System.out.println(str.toString());// 결과 한번에 출력
	}

	static int cal(Node a, Node b) {// 두 위치 입력받고 거리 계산해 리턴
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}

	static void per(int cnt) {// 순열 메소드
		if (cnt == n) {// 총 n번 돌면
			sol(num);// 경로 계산하러 이동
			return;
		}

		for (int i = 0; i < n; i++) {
			if(visit[i]) continue;
			num[cnt] = i;
			visit[i] = true;
			per(cnt + 1);
			visit[i] = false;
		}
	}

	static void sol(int[] num) {// 경로 계산하는 메소드
		int temp = cal(company, customer[num[0]]);// 회사에서 첫번째 고객까지의 거리 계산해 저장
		for (int i = 1; i < n; i++) {// 총 n-1번
			temp += cal(customer[num[i - 1]], customer[num[i]]);// 고객들끼리의 거리 계산해 저장
		}
		temp += cal(customer[num[n - 1]], house);// 마지막 고객과 집까지의 거리 계산해 저장
		min = Math.min(temp, min);// 최소값일 경우 최소값 업데이트
		return;
	}
}
