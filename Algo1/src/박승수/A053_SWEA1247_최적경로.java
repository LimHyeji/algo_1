package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A053_SWEA1247_최적경로 {
	static int N;
	static int[] per;
	static boolean[] choose;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Loc Company;
		Loc Home;
		
		for (int test_case = 0; test_case < T+1; test_case ++) {
			N = Integer.parseInt(br.readLine());
			per = new int[N];
			choose = new boolean[N];

			Loc[] Customer = new Loc[N]; 
			String[] temp = br.readLine().split(" ");
			Company = new Loc(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
			Home = new Loc(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
			
			int cnt = 0;
			for (int idx = 4; idx < N*2+4; idx +=2) {
				Customer[cnt++] = new Loc(Integer.parseInt(temp[idx]),Integer.parseInt(temp[idx+1]));
			}
			
			Permutations(0);
		}

	}
	
	public static void Permutations(int v) {
		if (v == N) {
			System.out.println(Arrays.toString(per));
			return;
		}
		
		for (int idx = 0; idx < N; idx ++) {
			if (choose[idx] == false) {
				choose[idx] = true;
				per[v] = idx;
				Permutations(v+1);
				choose[idx] = false;
			}
		}
		
	}
	
	public static int calAllDistance() {
		int hap = 0;
		
		
		
		return hap;
	}
	
	public static int calDistacne(Loc l1, Loc l2) {
		return Math.abs(l1.y - l2.y) + Math.abs(l1.x - l2.x);
	}

}

class Loc{
	int y;
	int x;
	
	Loc(int y, int x){
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Loc [y=" + y + ", x=" + x + "]";
	}
	
}