package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class A057_BJ1697_숨바꼭질 {
	static int n, k;// 시작점과 도착점
	static int res;// 도착점에 도착하는 가장 빠른 시간
	static int time[];// 방문한 점에 도달 가능한 최소 시간 배열
	static boolean visit[];
	static ArrayList<Integer> graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 공백으로 구분해 한 라인 입력

		n = Integer.parseInt(st.nextToken());// 시작점 입력
		k = Integer.parseInt(st.nextToken());// 도착점 입력

		res = Math.abs(n - k);// 나올 수 있는 결과의 한계 설정
		time = new int[100001];// 방문한 점에 도달 가능한 최소 시간을 최소 시간을 저장하기 위해 생성
		Arrays.fill(time, res);// 한계값으로 채움
		visit=new boolean[100001];
		
		graph=new ArrayList[100001];
		for(int i=0;i<100001;i++) {
			graph[i]=new ArrayList<>();
		}
		
		sol();// 점 n에서 시작하고, 0번째임
		System.out.println(res);// 최소 시간 출력
	}

//	static void sol(int cur, int cnt) {
//		if (cnt >= res)// 이동 시간이 한계값 이상일 경우
//			return;// 종료
//		if (cur == k) {// 현재 점이 도착점일 경우
//			res = Math.min(res, cnt);// 최소 이동시간인지 판단후
//			return;// 종료
//		}
//		if (visit[cur] <= cnt)
//			return;// 현재점에 이동할 수 있는 시간의 최소값 이상일 경우 종료
//		visit[cur] = cnt;// 초과하지 않을 경우 업데이트
//
//		if (cur + 1 <= 100000)// +1한 값이 주어진 범위를 초과하지 않는다면
//			sol(cur + 1, cnt + 1);// 이동
//		if (cur - 1 >= 0)// -1한 값이 주어진 조건을 초과하지 않는다면
//			sol(cur - 1, cnt + 1);// 이동
//		if (cur * 2 <= 100000 && cur * 2 <= k + res)// *2한 값이 주어진 조건을 초과하지 않는 경우
//			sol(cur * 2, cnt + 1);// 이동
//	}

	static void sol() {
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(n);
		visit[n]=true;
		time[n] = 0;

		int cur = -1;
		while (!q.isEmpty()) {
			cur = q.poll();
			if(cur==k) {
				res=Math.min(res, time[cur]);
			}
			//i+1, i-1, i*2
			for(int i:graph[cur]) {
				if(!visit[i]) {
					q.offer(i);
					visit[i]=true;
				}
			}

			
		}
	}
}
