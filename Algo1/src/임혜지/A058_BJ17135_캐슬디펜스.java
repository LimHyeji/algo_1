package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 궁수 세 명 "동시" 공격
 * 2. d 이하인 적 중에서 가장 가까운 적
 * 			-> 여럿일 경우 가장 왼쪽
 * 3. 공격 이후 적 한칸 아래 이동(1이 없을 경우 게임 끝)
 */

public class A058_BJ17135_캐슬디펜스 {
	static int n,m,d;
	static char [][] arr;
	static int enemy=0;
	static int pos[]=new int[3];
	
	static class Node{
		int row,col;
		int minD;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine());
		
		n=Integer.parseInt(in.readLine());
		m=Integer.parseInt(in.readLine());
		d=Integer.parseInt(in.readLine());
		arr=new char[n][m];

		for(int i=0;i<n;i++) {
			st=new StringTokenizer(in.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j]=st.nextToken().charAt(0);
				if(arr[i][j]=='1') {
					enemy++;//적의 수
				}
			}
		}
	}
	
	static int cal(int row,int col,int eRow,int eCol) {
		return Math.abs(row-eRow)+Math.abs(col-eCol);
	}
	
	static void com(int cnt, int start) {
		if(cnt==3) {
			//궁수 자리 배치
			game(enemy);
			return;
		}
		for(int i=start;i<m;i++) {
			pos[cnt]=i;
			com(cnt+1,i+1);
		}
	}
	static int maxKill=0;
	static void game(int enemy) {
		/*
		 * 1. 거리가 d 이하인지 체크
		 * 2. 가장 가까운지 체크
		 * 3. 여러명이면 가장 왼쪽에 있는지 체크
		 * 4. 
		 * 
		 * 
		 * 1. 적은 한칸 아래 이동
		 * 2. 맵 벗어나면 (>=n이면) enemy--
		 * 3. 
		 * 
		 * -> 제거 가능 최대 수(전체 적 - 맵 벗어난 적) or (kill)
		 */
		while(enemy>0) {//적이 있을 동안에만 턴
			
			
			
			for(int i=n-1;i>=0;i--) {
				for(int j=0;j<m;j++) {
					if(i==n-1&&arr[i][j]==1) {
						arr[i][j]=0; enemy--;
					}
					else if(i!=n-1&&arr[i][j]==1) {
						arr[i+1][j]=1; arr[i][j]=0;
					}
				}
			}
		}
	}
}
