package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <조건>
 * 1. 부분문자열 길이 만족 => 부분집합의 원소 개수
 * 2. 각각의 최소 개수 만족
 * 3. 순서 있음
 */
public class A019_BJ12891_DNA비밀번호 {
	static int s;// 문자열 길이
	static int p;// 부분문자열 길이
	static char [] str;// 문자열
	static int[] cnt;// 각각의 최소 개수(4칸)
	static int[] tempCnt;//문자열의 A,C,G,T 개수
	//static final char[] c = { 'A', 'C', 'G', 'T' };// 비교할 문자 4개
	static int total;// 결과로 출력할, 가능한 비밀번호 종류의 수
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 속도 개선 위해 사용
		StringTokenizer st = null;// 공백 구분 위해 선언

		st = new StringTokenizer(in.readLine(), " ");// 한 라인 입력받음
		s = Integer.parseInt(st.nextToken());// 문자열 길이 입력받음
		p = Integer.parseInt(st.nextToken());// 부분문자열 길이 입력받음

		str= in.readLine().toCharArray();// 한 라인 문자열 입력받음

		cnt = new int[4];// 'A','C','G','T' 개수 입력받을 배열
		tempCnt=new int[4];//입력받은 문자열의 'A','C','G','T' 개수
		total=0;//출력할 경우의 수
		
		st = new StringTokenizer(in.readLine(), " ");// 한 라인 입력받음
		for (int i = 0; i < 4; i++) {
			cnt[i] = Integer.parseInt(st.nextToken());// 각각의 최소 개수 입력받음
		}

		for(int i=0;i<p;i++) {
		check(str[i]);//현재 문자열의 A C G T 개수 세어놓기
		}
		
		if(tempCnt[0]>=cnt[0]&&tempCnt[1]>=cnt[1]&&tempCnt[2]>=cnt[2]&&tempCnt[3]>=cnt[3]) {//개수 조건 만족 시에
			total++;//경우의 수 증가
		}

		for(int i=p;i<s;i++) {//s-p번 반복
			int start=i-p;//0번부터 증가
			unCheck(str[start]);//고를 때마다 저장한 문자 개수--
			check(str[i]);//
			if(tempCnt[0]>=cnt[0]&&tempCnt[1]>=cnt[1]&&tempCnt[2]>=cnt[2]&&tempCnt[3]>=cnt[3]) {//개수 조건 만족 시에
				total++;//경우의 수 증가
			}
		}
		System.out.println(total);//결과 출력
	}
	
	public static void check(char c) {//재사용으로 메소드화, 문자열의 각 문자 개수 세기
			if(c=='A') {
				tempCnt[0]++;
			}
			else if(c=='C') {
				tempCnt[1]++;
			}
			else if(c=='G') {
				tempCnt[2]++;
			}
			else if(c=='T') {
				tempCnt[3]++;
			}	
	}
	
	public static void unCheck(char c) {//재사용으로 메소드화, 문자열 고를 때마다 기존 개수에서 1씩 빼기
		if(c=='A') {
			tempCnt[0]--;
		}
		else if(c=='C') {
			tempCnt[1]--;
		}
		else if(c=='G') {
			tempCnt[2]--;
		}
		else if(c=='T') {
			tempCnt[3]--;
		}	
}
}
