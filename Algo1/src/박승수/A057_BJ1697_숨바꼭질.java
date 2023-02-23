package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class A057_BJ1697_숨바꼭질 {
	static int K;
	static int N;
	static int[] DP = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		Arrays.fill(DP,100001);
		
		if (N == K) {
			System.out.println(0);
		}else {
			BFS(N);
			System.out.println(DP[K]);
		}
		
		
		
	}
	
	public static void DFS(int v, int Loc) {
		if (Loc == K) {
			System.out.println(v);
			return;
		}
		DP[Loc] = v;
		
		if (Loc*2 <= 100000 && DP[Loc*2] > v+1) {
			DFS(v+1,Loc*2);
		}
		
		if (Loc+1 <= 10000 && DP[Loc+1] > v+1){
			DFS(v+1,Loc+1);
		}
		
		if (Loc-1 >= 0 && DP[Loc-1] > v+1) {
			DFS(v+1,Loc-1);
		}
			
	}
	
	public static int BFS(int N) {
		Deque<DIS> queue = new ArrayDeque<DIS>();
		queue.add(new DIS(N,0));
		while (!queue.isEmpty()) {
			DIS nowDIS = queue.removeFirst();
			if (nowDIS.loc*2 <= 100000 && DP[nowDIS.loc*2] > nowDIS.times+1) {
				DP[nowDIS.loc*2] = nowDIS.times+1;
				queue.add(new DIS(nowDIS.loc*2,nowDIS.times+1));
			}
			
			if (nowDIS.loc+1 <= 100000 && DP[nowDIS.loc+1] > nowDIS.times+1) {
				DP[nowDIS.loc+1] = nowDIS.times+1;
				queue.add(new DIS(nowDIS.loc+1,nowDIS.times+1));
			}
			
			if (nowDIS.loc-1 >= 0 &&DP[nowDIS.loc-1] > nowDIS.times+1) {
				DP[nowDIS.loc-1] = nowDIS.times+1;
				queue.add(new DIS(nowDIS.loc-1,nowDIS.times+1));
			}
			
		}
		
		return DP[K];
	}

}

class DIS{
	int loc;
	int times;
	
	DIS(int loc, int times){
		this.loc = loc;
		this.times = times;
	}
}
