package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A015__BJ11659_구간합구하기4 {
	static int n;//숫자 개수
	static int m;//합 구해야 하는 횟수
	static int [] num;//n개의 숫자
	static int [] sum;//누적합
	
	public static void main(String[] args) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//수행시간 단축을 위해 사용
		StringTokenizer st=null;//공백으로 입력받기 위해 미리 선언
		st=new StringTokenizer(in.readLine()," ");		//공백으로 구분해 입력받음
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		num= new int [n+1];//1번부터 n번까지의 숫자
		sum=new int [n+1];//n번까지의 누적합
		int i,j;	//입력받을 구간의 인덱스
		
		st=new StringTokenizer(in.readLine()," ");
		for(int x=1;x<=n;x++) {//n개의
			num[x]=Integer.parseInt(st.nextToken());//숫자 입력
			sum[x]+=sum[x-1]+num[x];//1,1~2,1~3,...,1~n까지의 누적합
		}
		
		for(int x=0;x<m;x++) {//m개의
			st=new StringTokenizer(in.readLine()," ");//구간 입력
			i=Integer.parseInt(st.nextToken());
			j=Integer.parseInt(st.nextToken());
			System.out.println(sum[j]-sum[i-1]); //i~j합 출력
		}
	}
}
