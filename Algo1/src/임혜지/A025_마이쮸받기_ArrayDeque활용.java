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
		Deque<Integer> p = new ArrayDeque<>();//줄을 서는 사람들
		int n = 20;// 마이쮸개수 20개
		int [] cnt=new int[100];//한명당 받는 개수
		int idx=1;//새로 줄을 선 사람의 번호
		int res = -1;// 현재 받은 사람
		cnt[1]=1;//1번이 한개를 받아야 함 기록
		p.add(1);// 1번이 줄을 섬
		while (true) {// n이 0이 될 때(마이쮸 다 나눠줄 때) 반복문 종료
			res=p.removeFirst();//앞에 줄은 선 사람이 나와서 마이쮸를 받을 차례
			n-=cnt[res];//받아야하는 개수만큼 나눠줌
			if(n<=0) break;//마이쮸 소진 시 반복문 탈출
			cnt[res]++;//다음에 받아야할 마이쮸 1개 추가됨
			p.addLast(res);//받아야하는 개수를 저장하며 다시 줄을 섬
			p.addLast(++idx);//새로운 사람이 줄을 서는데, 
			cnt[idx]=1;//1개부터 시작
		}
		System.out.println(res);// 결과 출력
	}
}
