package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A066_SWEA3289서로소집합 {
	static int[] par;// 대표자 배열
	static int t, n, m;// 테스트 케이스 수, 원소 개수, 연산 횟수
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	static void make() {// 대표자 배열 생성하는 메소드
		for (int i = 1; i < par.length; i++) {// 1번부터 n번까지
			par[i] = i;// 각 대표자는 현재의 인덱스값을 가짐
		}
	}

	static int find(int a) {// 원소의 대표자를 찾는 메소드
		if (par[a] == a)// 대표자가 그 원소와 같다면
			return a;// 찾기 완료
		return par[a] = find(par[a]);// 같지 않다면 대표자를 찾아서 재귀 호출
	}

	static boolean union(int a, int b) {// 원소 a의 집합과 원소 b의 집합을 합
		int aRoot = find(a);// a 원소 가진 집합의 대표자
		int bRoot = find(b);// b 원소 가진 집합의 대표자

		if (aRoot == bRoot)// 대표자가 서로 같다면
			return false;// 서로소가 아니어서 union 불가이므로 false 리턴

		par[bRoot] = aRoot;// 대표자가 서로 다르다면, 서로소이므로 union 가능. 오른쪽 원소를 왼쪽에 합하기
		return true;// union 완료 이므로 true 리턴
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 입력받을 줄비
		t = Integer.parseInt(in.readLine());// 테스트케이스 수 입력

		for (int test_case = 1; test_case <= t; test_case++) {
			str.append("#").append(test_case).append(" ");// 테스트 케이스 append

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			n = Integer.parseInt(st.nextToken());// 원소 개수 입력
			m = Integer.parseInt(st.nextToken());// 연산 횟수 입력

			par = new int[n + 1];// 1번부터 n번까지이므로 n+1 크기의 배열 생성
			make();// 값 저장
			for (int i = 0; i < m; i++) {// 총 m번의
				st = new StringTokenizer(in.readLine());// 줄 입력받아
				int cmd = Integer.parseInt(st.nextToken());// 연산 번호(0: union, 1: find) 입력
				int a = Integer.parseInt(st.nextToken());// 원소 a 입력
				int b = Integer.parseInt(st.nextToken());// 원소 b 입력

				if (cmd == 1) {// 연산 번호가 1이면
					if (find(a) == find(b)) {// 두 집합의 대표자가 같다면(같은 집합이라면)
						str.append(1);// 1 출력
					} else {// 다른 집합이라면
						str.append(0);// 0 출력
					}
				} else {// 연산 번호가 0이면
					union(a, b);// a 원소 집합과 b 원소 집합을 합
				}
			}
			str.append("\n");

		}

		System.out.println(str.toString());// 결과 한번에 출력
	}
}
