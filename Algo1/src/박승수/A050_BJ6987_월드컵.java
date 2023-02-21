package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A050_BJ6987_월드컵 {
	static int[] verseA ;
	static int[] verseB ;
	static ArrayList<Team> Answer;
	static ArrayList<Team> Now;
	static boolean answer;
	
	public static void main(String[] args) throws IOException {
		verseA = new int[15];
		verseB = new int[15];
		
		
		int cnt = 0;
		for (int i = 0; i < 6; i ++) {
			for (int j = i+1; j<6; j++) {
				verseA[cnt] = i;
				verseB[cnt++] = j;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 0; test_case < 4; test_case++) {
			String[] temp = br.readLine().split(" ");
			Answer = new ArrayList<>();
			Now = new ArrayList<>();
			answer = false;
			for(int idx = 0; idx < 18; idx+=3) {
				Answer.add(new Team(Integer.parseInt(temp[idx]),Integer.parseInt(temp[idx+1]),Integer.parseInt(temp[idx+2])));
				Now.add(new Team(0,0,0));
			}
			
			solution(0);
			if (answer) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
			
			

		}
	}
	public static boolean isOk() {
		for(int teamNum = 0; teamNum <6 ;teamNum ++) {
			if (Now.get(teamNum).win > Answer.get(teamNum).win) { //만약 목표보다 더 이겨버리면 안됨
				return false;
			}
			if (Now.get(teamNum).lose > Answer.get(teamNum).lose) { //만약 목표보다 더 져버리변 안됨
				return false;
			}
			if (Now.get(teamNum).draw > Answer.get(teamNum).draw) { //만약 목표보다 더 비겨버리면 안됨
				return false;
			}

		}
		
		
		return true;
	}
	
	
	public static void solution(int v) {
		if (answer) {
			return;
		}
		
		
		if (v == 15) {
				for(int idx = 0; idx <6; idx ++) {
					//System.out.println(Now.get(idx).toString()+Answer.get(idx).toString());
					if (Now.get(idx).draw != Answer.get(idx).draw || Now.get(idx).lose != Answer.get(idx).lose ||  Now.get(idx).win != Answer.get(idx).win) {
						//System.out.println("Break");
						return;
					}
			}
				answer = true;
			return;
		}
		
		if (isOk()) {
			// A 승
			Now.get(verseA[v]).win++;
			Now.get(verseB[v]).lose++;
			solution(v+1);
			Now.get(verseA[v]).win--;
			Now.get(verseB[v]).lose--;
			
			// B 승
			Now.get(verseA[v]).lose++;
			Now.get(verseB[v]).win++;
			solution(v+1);
			Now.get(verseA[v]).lose--;
			Now.get(verseB[v]).win--;
			
			// 무승부
			Now.get(verseA[v]).draw++;
			Now.get(verseB[v]).draw++;
			solution(v+1);
			Now.get(verseA[v]).draw--;
			Now.get(verseB[v]).draw--;
		}

	}
		

}

class Team{
	int win;
	int lose;
	int draw;
	
	@Override
	public String toString() {
		return "Team [win=" + win + ", draw=" + draw + ", lose=" + lose + "]";
	}

	Team(int win, int draw, int lose ){
		this.win = win;
		this.lose = lose;
		this.draw = draw;
	}
}
