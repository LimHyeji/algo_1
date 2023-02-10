package 임혜지;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 1+2+3+...+n
 * 0+1+2+...+n
 * 0+0+1+...+n
 */
public class A025_마이쮸받기_ArrayDeque활용 {
	
	public static void main(String[] args) {
		Deque<Integer> p=new ArrayDeque<>();//사이즈 : 줄을 서는 사람들, 저장값 : 받아가는 마이쮸 개수
		int n=20;//마이쮸개수 20개
		int res=0;//마지막에 받은 사람
		n-=1;//1번이 한개를 받고 시작
		p.add(2);//1번이 2개를 받아야 함
		while(n>0) {//n이 0이 될 때(마이쮸 다 나눠줄 때) 반복문 종료
			for(int i=1;i<=p.size();i++) {
			int temp=p.removeFirst();//그 사람이 받는 마이쮸 개수 꺼내서
			n-=temp;//그만큼 나눠줌
			if(n<=0) {//마이쮸 소진 시
				res=i;//마지막 받은 사람 저장
				break;//종료
			}
			temp++;//1개 증가
			p.addLast(temp);//다음에는 1개 더 받도록 다시 줄을 섬
			}
			p.add(1);//새로운 사람 추가
		}
		System.out.println(res);//결과 출력
	}
}
