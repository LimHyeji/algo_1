package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class A026_BJ2164_카드2_과제4 {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		int n=Integer.parseInt(in.readLine());//카드 수
		int res;//마지막 남는 카드
		Deque<Integer> card=new ArrayDeque<>();//카드
		for(int i=1;i<=n;i++) {
			card.add(i);//1번부터 n번까지 카드 쌓기
		}
		while(true) {
			if(card.size()==1) {//한장만 남을 때
				res=card.remove();//꺼내서 저장하고
				break;//반복문 종료
			}
			card.removeFirst();//첫번째 장 버림
			card.add(card.removeFirst());//앞장 버리면서 뒤에 추가함
		}
		System.out.println(res);//결과출력
		
	}
}
