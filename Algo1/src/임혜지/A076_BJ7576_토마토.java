package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class A076_BJ7576_토마토 {
	static int m,n;//상자의 가로,세로 크기
	static int arr[][];//상자
	static int dirR[]= {-1,1,0,0};//상하좌우
	static int dirC[]= {0,0,-1,1};//상하좌우
	static boolean zeroF;//모두 익은 상태인지 확인하는 플래그
	static Queue<Node> q;//탐색 시 사용할 큐
	static int res;//결과로 출력할 토마토 익는 최소날짜
	
	static class Node{//토마토 위치 저장할 클래스
		int row,col;//행열 인덱스
		Node(int row,int col){//클래스 생성자
			this.row=row;
			this.col=col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));//버퍼로 입력
		StringTokenizer st=new StringTokenizer(in.readLine());//공백으로 구분해 한 줄 입력
		
		m=Integer.parseInt(st.nextToken());//상자 가로 크기 입력
		n=Integer.parseInt(st.nextToken());//상자 세로 크기 입력
		arr=new int[n][m];//상자 생성
		q=new ArrayDeque<>();//탐색을 위해 큐 생성
		zeroF=false;//0이 등장했을 때 true가 될 플래그 초기화

		for(int i=0;i<n;i++) {//총 n줄의
			st=new StringTokenizer(in.readLine());//라인 입력
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());//1 : 익은 토마토, 0 : 익지 않은 토마토, -1 : 토마토 없음
				if(arr[i][j]==0) {//안 익은 토마토가 등장 시
					zeroF=true;//플래그 true로 변경
				}
				if(arr[i][j]==1) {//익은 토마토는
					q.offer(new Node(i,j));//큐에 추가
				}
			}
		}
		
		if(zeroF==false) {//모두 익은 토마토일 때
			System.out.println(0);//0 출력하고
			System.exit(0);//프로그램 종료
		}
		
		Node cur=null;//현재 탐색중인 노드 저장할 변수
		while(!q.isEmpty()) {//탐색할 것이 없을 때까지
			cur=q.poll();//큐에 저장된 값 꺼내서
			
			for(int dir=0;dir<4;dir++) {//네 방향에 대해
				int newR=cur.row+dirR[dir];
				int newC=cur.col+dirC[dir];
				
				if(newR>=0&&newR<n&&newC>=0&&newC<m&&arr[newR][newC]==0) {//인덱스 벗어나지 않고, 안익은 토마토 등장 시
					q.offer(new Node(newR,newC));//큐에 저장(익은 토마토가 되었으므로)
					arr[newR][newC]=arr[cur.row][cur.col]+1;//저장된 날 수 업데이트
				}
			}	
		}
		
		res=0;//결과로 출력할 최소 날짜 수
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==0) {//여전히 안 익은 토마토가 있을 때
					System.out.println(-1);//-1 출력 후
					System.exit(0);//프로그램 종료
				}
				res=Math.max(res, arr[i][j]);//저장된 날짜 중의 최대값이 마지막 날짜
			}
		}
		System.out.println(res-1);//이미 1만큼 가지고 있었으므로 마지막날짜 수에서 1 빼서 출력
}
	
}
