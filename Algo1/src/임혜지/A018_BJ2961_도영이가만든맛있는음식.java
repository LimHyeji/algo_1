package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A018_BJ2961_도영이가만든맛있는음식 {
	static int n;//재료 개수
	static int [] sour;//신맛
	static int [] bitter;//쓴맛
	
	public static void main(String[] args) throws IOException {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));//속도 개선 위해 buffer 이용
		StringTokenizer st=null;//토크나이저 선언
		
		st=new StringTokenizer(in.readLine()," ");//새 라인 입력받고
		n=Integer.parseInt(st.nextToken());//int로 parse
		
		sour=new int [n];//입력받은 크기의 신맛 배열 생성
		bitter=new int[n];//입력받은 크기의 쓴맛 배열 생성
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(in.readLine()," ");//새 라인 입력받고
			sour[i]=Integer.parseInt(st.nextToken());//첫 문자는 신맛
			bitter[i]=Integer.parseInt(st.nextToken());//두번째 문자는 쓴맛
		}
		
		int temp1,temp2;
		int [] sub=new int[1<<n];//신맛과 쓴맛 차이 저장할 배열(부분집합 개수만큼 생성)	
		int res=1_000_000_000;//차이가 최소인 결과값
		
		for(int i=1;i<(1<<n);i++) {//부분집합 개수 : 1<<n(적어도 하나의 재료가 들어가야 하므로 1부터 시작)
			temp1=1;//곱셈을 위해 1 저장
			temp2=0;//덧셈을 위해 0 저장
			for(int j=0;j<n;j++) {//원소 개수만큼 비트 비교
				if((i&(1<<j))>0) {//i의 j번째 비트가 1일 때
					temp1*=sour[j];//신맛은 곱셈
					temp2+=bitter[j];//쓴맛은 덧셈					
				}
			}
			sub[i]=Math.abs(temp1-temp2);//신맛과 쓴맛의 차이 저장
			//System.out.println(sub[i]);
			res=Math.min(sub[i], res);//차이가 최소인 값 저장
			//System.out.println(res);
		}
		
		System.out.println(res);
	}
}
