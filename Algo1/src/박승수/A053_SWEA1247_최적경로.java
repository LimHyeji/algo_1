package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A053_SWEA1247_최적경로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Loc Company;
		Loc Home;
		
		for (int test_case = 0; test_case < T+1; test_case ++) {
			int N = Integer.parseInt(br.readLine());
			Loc[] Customer = new Loc[N]; 
			String[] temp = br.readLine().split(" ");
			Company = new Loc(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
			Home = new Loc(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
			
			for (int idx = 4; idx < N*2+4; idx +=2) {
				
			}
		}

	}

}

class Loc{
	int y;
	int x;
	
	Loc(int y, int x){
		this.y = y;
		this.x = x;
	}
}