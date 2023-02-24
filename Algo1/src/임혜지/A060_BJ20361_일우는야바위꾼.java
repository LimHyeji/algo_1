package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A060_BJ20361_일우는야바위꾼 {
	static int n,x,k;//컵의 수, 공의 위치, swap 횟수
	static int arr[];//컵 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		
		StringTokenizer st=new StringTokenizer(in.readLine());//한 줄 입력		
		n=Integer.parseInt(st.nextToken());//컵의 수 입력
		x=Integer.parseInt(st.nextToken());//공의 위치 입력
		k=Integer.parseInt(st.nextToken());//swap 횟수 입력

		arr=new int[n+1];//1번부터 n번까지 접근 가능한 컵 배열 생성
		arr[x]=1;//x 위치에 1 저장(공이 존재)
		int a,b;//교환할 인덱스
		for(int i=0;i<k;i++) {//총 k번의 swap이 진행됨
			st=new StringTokenizer(in.readLine());//한 줄 입력받고
			a=Integer.parseInt(st.nextToken());//교환할 인덱스
			b=Integer.parseInt(st.nextToken());//교환할 인덱스
			swap(a,b);//서로 교환
		}
		for(int i=1;i<=n;i++) {//1번부터 n번까지 순회하면서
			if(arr[i]==1) {//공이 존재하면
				System.out.println(i);//그 위치 출력하고
				break;//반복문 중단
			}
		}
	}
	static void swap(int a, int b) {//a 컵과 b 컵 교환하는 메소드
		int temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
}
