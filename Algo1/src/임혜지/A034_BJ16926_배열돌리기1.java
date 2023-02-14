package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A034_BJ16926_배열돌리기1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st=new StringTokenizer(in.readLine()," ");//첫째줄 입력(n,m,r)
		int n=Integer.parseInt(st.nextToken());//배열의 크기(행 개수)
		int m=Integer.parseInt(st.nextToken());//배열의 크기(열 개수)
		int r=Integer.parseInt(st.nextToken());//회전해야 하는 횟수
		int [][] arr=new int[n][m];//n by m 배열 생성
		for(int i=0;i<n;i++) {//n줄에 걸쳐
			st=new StringTokenizer(in.readLine()," ");//한 라인 입력 받고
			for(int j=0;j<m;j++) {//열의 개수만큼
				arr[i][j]=Integer.parseInt(st.nextToken());//배열값 저장
			}
		}
		int [][] res=new int[n][m];//결과 저장할 배열
		/*
		 * 시작점은 [idx][idx] ... idx는 0부터 min(n,m)/2까지
		 * 
		 */
		for(int i=0;i<r;i++) {//총 r번 회전
				for(int j=0;j<Math.min(n,m)/2;j++) {//시작점은 [idx][idx] ... idx는 0부터 min(n,m)/2까지
					
				}
			
		}
	}
}
