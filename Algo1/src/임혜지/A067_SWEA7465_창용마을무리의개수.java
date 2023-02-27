package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class A067_SWEA7465_창용마을무리의개수 {
	static int[] par;// 대표자 배열
	static int t, n, m;// 테스트 케이스 수, 원소 개수, 연산 횟수
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열
	static HashSet<Integer> s;// 중복 제거할 set

	static void make() {// 집합 만드는 메소드
		for (int i = 1; i < par.length; i++) {// 1번부터 n번까지의
			par[i] = i;// 각 값은 그 배열의 인덱스
		}
	}

	static int find(int a) {// a 원소를 가진 집합의 대표자 찾는 메소드
		if (par[a] == a)// a 원소의 대표자와 그 원소가 같다면
			return a;// a가 대표자이므로 그대로 리턴(재귀 종료)
		return par[a] = find(par[a]);// 같지 않다면, a원소의 대표자를 찾을 때까지 재귀 호출
	}

	static boolean union(int a, int b) {// a 원소를 가진 집합과 b 원소를 가진 집합을 합
		int aRoot = find(a);// a 원소를 가진 집합의 대표자
		int bRoot = find(b);// b 원소를 가진 집합의 대표자

		if (aRoot == bRoot)// 대표자가 서로 같다면(서로소가 아니므로)
			return false;// union 불가

		par[bRoot] = aRoot;// 대표자가 서로 다르다면 오른쪽 원소를 왼쪽에 합하기
		for (int i = 0; i < par.length; i++) {// 자기 자신을 대표자로 갖는 원소들의 parent 모두 업데이트 하기 위해 순회하면서
			if (find(i) == bRoot) {// 자기 자신을 대표자로 갖는다면
				par[i] = aRoot;// 동일하게 새로운 대표자로 업데이트
			}
		}
		return true;// union 완료이므로 true 리턴
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력

		for (int tc = 1; tc <= t; tc++) {
			str.append("#").append(tc).append(" ");// 테스트 케이스 append

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			n = Integer.parseInt(st.nextToken());// 원소 개수 입력
			m = Integer.parseInt(st.nextToken());// 연산 횟수 입력

			par = new int[n + 1];// 대표자 배열 생성(1번부터 n번)
			make();// 대표자 배열 초기화

			for (int i = 0; i < m; i++) {// m번의
				st = new StringTokenizer(in.readLine());// 줄 입력
				int a = Integer.parseInt(st.nextToken());// a 원소와
				int b = Integer.parseInt(st.nextToken());// b 원소를

				union(a, b);// 합
			}

			s = new HashSet<>();// 중복 제거를 위해 set 생성
			for (int i = 1; i < n + 1; i++) {// 모든 par에 대해
				s.add(par[i]);// set에 추가한 후
			}

			str.append(s.size()).append("\n");// 그 사이즈가 곧 집합의 개수이므로 append
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}
}
