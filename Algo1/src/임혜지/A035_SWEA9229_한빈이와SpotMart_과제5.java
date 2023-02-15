package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A035_SWEA9229_한빈이와SpotMart_과제5 {
	static int tc;//테스트케이스 수
	static int n,m;//과자봉지의 개수(2~1000)와 무게합 제한
	static int [] snack;//과자봉지의 무게
	static int res;//출력할 최대 무게합
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st=null;//공백으로 입력받을 준비
		StringBuilder str=new StringBuilder("");//출력할 문자열
		tc=Integer.parseInt(in.readLine());//테스트 케이스 수 입력
		
		for(int test_case=1;test_case<=tc;test_case++) {//총 tc번의 테스트케이스
			st=new StringTokenizer(in.readLine()," ");//공백으로 구분해 입력
			n=Integer.parseInt(st.nextToken());//과자봉지 개수 입력
			snack=new int[n];//n개의 과자봉지 무게 담을 배열
			m=Integer.parseInt(st.nextToken());//제한된 무게합 입력
			st=new StringTokenizer(in.readLine()," ");//공백으로 구분해 입력
			for(int i=0;i<n;i++) {//n개의 과자봉지 무게 입력 받을 것
				snack[i]=Integer.parseInt(st.nextToken());//0번 인덱스부터 n-1번 인덱스까지 저장
			}
			res=-1;//유효하지 않은 값을 저장
			//무조건 2개 선택함
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) {
					int temp=snack[i]+snack[j];//조합된 경우의 두 과자 봉지 합
					if(temp<=m) {//제한된 무게를 넘지 않는다면
					res=Math.max(res,temp );//기존 저장값과 비교해 최대값 대입
					}
				}
			}
			str.append("#").append(test_case).append(" ").append(res).append("\n");//출력문에 결과 붙여넣음
		}
		System.out.println(str);//총 결과 출력
	}
}
