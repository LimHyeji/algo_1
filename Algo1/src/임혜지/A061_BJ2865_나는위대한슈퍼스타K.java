package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. n명의 사람 중에서 k명의 사람 뽑기
 * 2. 단, k명의 사람의 능력 점수 합이 모든 경우에 대해 최대값이어야 합
 * 3. k명의 사람이 같은 장르를 부를 수 있다 = 중복 허용
 */

public class A061_BJ2865_나는위대한슈퍼스타K {
	static int n, m, k;// 참가자 수, 장르 수, 본선진출자 수

	static PriorityQueue<Double> person[];// 배열 인덱스는 참가자번호이고, 저장하는 값은 능력점수. n명의 능력점수를 저장하고, 처음 꺼내는 것이 해당 사람의 최고점
	static double res;//출력할 능력의 최대합
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());//한 줄 입력

		n = Integer.parseInt(st.nextToken());//참가자 수 입력
		m = Integer.parseInt(st.nextToken());//장르 수 입력
		k = Integer.parseInt(st.nextToken());//본선진출자 수 입력

		person = new PriorityQueue[n + 1];//참가자에 대한 배열 생성
		for (int i = 0; i <= n; i++) {//n번까지의 참가자에 대해
			person[i] = new PriorityQueue<>(Collections.reverseOrder());//참가자의 점수를 저장할 리스트 생성
		}

		for (int i = 0; i < m; i++) {//장르 수 m개에 대해 
			// 참가자의 번호(정수)와 참가자의 능력(실수)
			st = new StringTokenizer(in.readLine());//한 줄 입력
			for (int j = 0; j < n; j++) {//참가자 수 n에 대해
				int p = Integer.parseInt(st.nextToken());//참가자 번호 입력
				double score = Double.parseDouble(st.nextToken());//참가자의 능력 점수 입력
				person[p].add(score);//해당하는 번호의 참가자에게 능력 점수 부여
			}
		}
		// 입력끝
		// --------------------------------------
		res=0.0;//결과로 출력할 최대 능력점수 합
		PriorityQueue<Double> list=new PriorityQueue<>(Collections.reverseOrder());//n명의 최고점들을 저장할 내림차순 큐

		for(int i=1;i<=n;i++) {//n명에 대해
			list.add(person[i].poll());//그 사람의 최고점 꺼내서 저장
		}
		
		for(int i=0;i<k;i++) {//최고점들 중에서도 k번째 순서까지를
			res+=list.poll();//꺼내서 더함
		}
		
		System.out.println(Math.round((res*10))/10.0);//소수점 아래 첫번째 자리까지 반올림해서 출력
	}
}
