package 임혜지;

import java.util.Scanner;

public class A005_HanoiTest {
	static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);
       int cnt = sc.nextInt();
       hanoi(cnt, 1, 2, 3);
       System.out.println(result.toString());
   }// end main    


    // cnt:원반갯수, from:시작기둥, temp:임시기둥, to:끝기둥
    private static void hanoi(int cnt, int from, int temp, int to) {
    	if(cnt==0)	return;
    	/*
    	 * 남은 원판이 없을 때 종료
    	 */
    	hanoi(cnt-1,from,to,temp);
    	/*
    	 * 맨 아래 원판을 제외하고 n-1개를 임시기둥에 옮겨놓기
    	 * 즉, 목적지가 임시기둥이 됨.
    	 */
    	System.out.println(cnt+" : "+from+" -> "+to);	//원판번호와 출발점 및 도착점 출력
    	hanoi(cnt-1,temp,from,to);
    	/*
    	 * 임시기둥에 옮겨놓은 원판에서 다시 시작.
    	 * 임시기둥을 시작기둥으로 취급하고, 시작기둥을 임시기둥으로 취급해서 다시 시작
    	 */
    }      

}
