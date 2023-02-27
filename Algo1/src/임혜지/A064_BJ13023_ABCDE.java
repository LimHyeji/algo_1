package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A064_BJ13023_ABCDE {
	static int n, m;// 사람 수와 친구 관계 수
	static int res = 0;// 출력할 결과(아직 유효한 값을 못 찾았으므로 0으로 저장)
	static ArrayList<Integer> g[];// 친구관계 연결할 그래프
	static boolean visit[];// 탐색할 때 사용할 플래그

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력

		String[] temp = in.readLine().split(" ");// 한 줄 입력
		n = Integer.parseInt(temp[0]);// 사람 수 저장
		m = Integer.parseInt(temp[1]);// 친구 관계 수 저장

		g = new ArrayList[n];// 연결리스트 배열 생성
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();// 각 연결리스트 생성
		}
		for (int i = 0; i < m; i++) {
			temp = in.readLine().split(" ");// 한 줄 입력
			int a = Integer.parseInt(temp[0]);// a친구와
			int b = Integer.parseInt(temp[1]);// b친구를
			g[a].add(b);// 연결
			g[b].add(a);// 연결(양방향)
		}
		visit = new boolean[n];// 탐색할 때 사용할 플래그 생성
		for (int i = 0; i < n; i++) {//모든 정점에 대해
			dfs(i, 1);//탐색할 시작 정점과 카운트 횟수
			if(res==1) break;//break문 추가하지 않으면 시간 초과
		}

		System.out.println(res);//유효한지 결과 출력
	}

	static void dfs(int cur, int cnt) {
		if (cnt == 5) {//시작정점에서 총 다섯 번의 탐색이 이루어진다면
			res = 1;//유효값 저장
			return;//재귀 종료
		}
		visit[cur] = true;//현재 정점 탐색 o
		for (int v : g[cur]) {//인접한 모든 정점에 대해
			if (!visit[v]) {//탐색하지 않은 값이라면
				dfs(v, cnt + 1);//다음 정점 탐색
			}
		}
		visit[cur] = false;//다음 재귀 호출을 위해 풀어주기
	}
}
