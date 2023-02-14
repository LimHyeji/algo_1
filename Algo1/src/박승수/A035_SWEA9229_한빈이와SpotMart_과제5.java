package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A035_SWEA9229_한빈이와SpotMart_과제5 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			String[] temp = br.readLine().split(" ");
			
			int N = Integer.parseInt(temp[0]);
			int M = Integer.parseInt(temp[1]);
			
			int[] snack = new int [N];
			temp = br.readLine().split(" ");
			for (int idx = 0; idx < N; idx++) {
				snack[idx] = Integer.parseInt(temp[idx]);
			}
			
			int answer = -1;
			
			for (int i = 0 ; i < N; i ++) {
				for (int j = i+1; j < N; j++) {
					if (snack[i] + snack[j] > M) {
						continue;
					}
					
					answer = Math.max(answer, snack[i] + snack[j]);
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
