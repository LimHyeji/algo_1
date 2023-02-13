package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A032_BJ2493_탑 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st = null;//구분자 공백
		
		int n = Integer.parseInt(in.readLine());//탑의 개수
		int [] arr=new int[n+1];//입력받을 탑 높이
				
		st=new StringTokenizer(in.readLine()," ");//공백을 기준으로 문자열 입력
		for(int i=1;i<n+1;i++) {//n개의
			arr[i]=Integer.parseInt(st.nextToken());//탑 높이 입력
		}
		
		StringBuilder str = new StringBuilder("");//출력문 초기화
		int temp=arr[n]; 
		int tempIdx=n;
		for(int i=n-1;i>0;i--) {//거꾸로 가면서(n-1부터 1까지)
			if(arr[i]>=temp) {//최대값 등장 시에
				for(int j=i;j<tempIdx;j++) {//그간 기록되지 않은 만큼
				str.append(" ").append(i);// 최대값 출력
				}
				temp=arr[i];//최대값 업데이트
				tempIdx=i;//최대값의 인덱스 업데이트
			}
			else if(i==1) {//마지막 반복문일 때
				for(int j=i;j<tempIdx;j++) {//그간 기록되지 않은 만큼
				str.append(" ").append(0);//0 출력
				}
				break;
			}
		}
		str.append(" 0");//첫번째 타워는 무조건 0
		System.out.println(str.reverse().toString());//출력
	}
}
