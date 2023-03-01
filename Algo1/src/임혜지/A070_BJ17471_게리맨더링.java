package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 두 개의 그래프로 나누어야 함
 * 2. 각 그래프의 인구 수 차이가 최소값이 되어야 함
 * 3. 
 */

public class A070_BJ17471_게리맨더링 {
	static int n;// 구역의 개수
	static ArrayList<Integer> g[];// 각 구역을 연결할 인접리스트
	static int p[];// 각 구역의 인구 수
	static int res;// 결과로 출력할 두 선거구의 인구 차이 최소값

	static ArrayList<Integer> a;// a 선거구
	static ArrayList<Integer> b;// b 선거구
	static boolean isSel[];// 선거구를 두 개의 부분집합으로 구분하기 위한 방문 체크 배열

	static boolean visit[];// 선거구가 모두 연결되어 있는지 탐색할 때 사용할 방문 체크 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 공백으로 구분해 입력받을 준비

		n = Integer.parseInt(in.readLine());// 구역의 수 입력

		g = new ArrayList[n + 1];// 인접리스트 배열 생성(1번부터 n번까지의 구역 존재)
		for (int i = 0; i < n + 1; i++) {
			g[i] = new ArrayList<>();// 각 구역별로 인접리스트 생성
		}

		p = new int[n + 1];// 각 구역의 인구 수(1번~n번)
		st = new StringTokenizer(in.readLine());// 한 줄 입력
		for (int i = 1; i <= n; i++) {// 각 구역의 인구 수
			p[i] = Integer.parseInt(st.nextToken());// 입력 받아 저장
		}

		for (int from = 1; from <= n; from++) {// 총 n개의 구역에 대해
			st = new StringTokenizer(in.readLine());// 한 줄 입력 받아
			int temp = Integer.parseInt(st.nextToken());// 그 구역과 연결된 구역들 수
			for (int i = 1; i <= temp; i++) {
				int to = Integer.parseInt(st.nextToken());// 연결된 구역 번호 입력받아
				g[from].add(to);// 인접리스트에 저장(무향 그래프이나, 각 구역별로 입력받으로므로 한번만 저장)
			}
		}

		isSel = new boolean[n + 1];// 부분집합을 구하기 전 생성
		res = Integer.MAX_VALUE;// 최소값을 구하기 위해 max값 저장
		findSubSet(1);// 인덱스 1번부터 시작

		System.out.println(res == Integer.MAX_VALUE ? -1 : res);// res값 업데이트가 없을 시에는 -1 출력
	}

	static void findSubSet(int cnt) {// 두개의 구역으로 나누는 메소드
		if (cnt == n + 1) {// n번 재귀 돌았을 경우(집합 전체를 나누었을 경우)
			a = new ArrayList<>();// a 선거구 리스트 생성
			b = new ArrayList<>();// b 선거구 리스트 생성

			for (int i = 1; i <= n; i++) {
				if (isSel[i])// 선택한 원소는
					a.add(i);// a 선거구에
				else// 비선택한 원소는
					b.add(i);// b 선거구에 저장
			}

			if (a.size() == 0 || b.size() == 0)// 두 선거구 중 하나라도 0개이면
				return;// 무효값

			if (bfs(a) && bfs(b))// a와 b 선거구 모두 bfs 탐색으로 연결 상태 확인 후
				sol();// 모두 연결되어있다면 인구수의 차이 구하기

			return;// 부분집합 구하기 종료
		}

		isSel[cnt] = true;// 해당 원소 선택
		findSubSet(cnt + 1);// 다음 선택 위해 재귀 호출
		isSel[cnt] = false;// 해당 원소 비선택
		findSubSet(cnt + 1);// 다음 선택 위해 재귀 호출
	}

	static boolean bfs(ArrayList<Integer> list) {// 그래프가 모두 연결되어있는지 확인하는 메소드
		Queue<Integer> q = new ArrayDeque<>();// bfs 탐색 위한 큐 생성
		visit = new boolean[n + 1];// 방문체크 배열 생성

		visit[list.get(0)] = true;// 첫번째 원소 방문 체크
		q.offer(list.get(0));// 큐에 추가

		int cnt = 1;// 방문하는 지역의 수 카운트할 변수
		int cur = -1;// 큐에서 꺼낼 때 사용할 변수 선언
		while (!q.isEmpty()) {// 탐색 가능한 정점이 있을 때
			cur = q.poll();// 큐에서 꺼내서
			for (int v : g[cur]) {// 인접한 정점에 대해
				if (!visit[v] && list.contains(v)) {// 방문도 하지 않았으면서, 현재 돌고 있는 리스트에 포함되어있을 경우
					q.offer(v);// 큐에 추가
					visit[v] = true;// 방문 체크
					cnt++;// 방문하는 지역 수 1 증가
				}
			}
		}
		if (cnt == list.size())// 모든 탐색 종료 후 리스트의 원소들을 모두 탐색했다면
			return true;// 연결되어있는것이므로 true 리턴
		else// 그렇지 않다면
			return false;// 연결되지 않은 것이 있으므로 false 리턴
	}

	static void sol() {// 인구의 차이 최소값 구할 메소드
		int pA = 0, pB = 0;// a 선거구와 b 선거구의 인구 수
		for (int i = 1; i <= n; i++) {
			if (isSel[i])// 선택한 원소(a 리스트에 있는 경우)
				pA += p[i];// a 선거구 인구 수 추가
			else// 비선택한 원소일 경우
				pB += p[i];// b 선거구 인구 수 추가
		}
		res = Math.min(res, Math.abs(pA - pB));// 현재 구한 인구 수 차이와 기존 차이 비교해 최소값 업데이트
	}
}
