package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A056_BJ1260_DFS와BFS {
	static HashMap<Integer,HashSet<Integer>> nodeInfo;
	static boolean[] isVisited; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int V = Integer.parseInt(temp[2]);
		
		
		nodeInfo = new HashMap<Integer,HashSet<Integer>>();
		
		
		for (int idx = 1; idx < N+1; idx++) {
			nodeInfo.put(idx, new HashSet<Integer>());
		}
		
		for (int idx = 0; idx < M; idx ++) {
			temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			
			nodeInfo.get(a).add(b);
			nodeInfo.get(b).add(a);
		}
		isVisited = new boolean[N+1];
		isVisited[V] = true;
		DFS(V);
		System.out.println();
		
		isVisited = new boolean[N+1];
		isVisited[V] = true;
		BFS(V);
		
		
	}
	
	public static void DFS(int node) {
		System.out.printf("%d ",node);
		
		ArrayList<Integer> temp = new ArrayList<Integer>(nodeInfo.get(node));
		Collections.sort(temp);
		
		for (int idx = 0; idx < temp.size(); idx ++) {
			int nextNode = temp.get(idx);
			
			if (isVisited[nextNode] == false) {
				isVisited[nextNode] = true;
				DFS(nextNode);
			}
		}
		
	}
	
	public static void BFS(int initNode) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		isVisited[initNode] = true;
		queue.addLast(initNode);
		
		while (queue.size() !=0) {
			int node = queue.removeFirst();
			System.out.printf("%d ",node);
			ArrayList<Integer> temp = new ArrayList<Integer>(nodeInfo.get(node));
			Collections.sort(temp);
			
			for (int idx = 0; idx < temp.size(); idx ++) {
				int nextNode = temp.get(idx);
				
				if (isVisited[nextNode] == false) {
					isVisited[nextNode] = true;
					queue.addLast(nextNode);
				}
			}
		}
	}
}



