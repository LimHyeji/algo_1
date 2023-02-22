package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	private int[] node;
	// private int[] input;

	private int N;// 단말노드 개수

	public SegmentTree(int N, int[] input) {
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));// log(2)N : 자바에는 밑이 2인 로그 N의 값을 구하는 메소드가 없어서 logN/log2 한다
		int nodeSize1 = (int) Math.pow(2, h + 1);// 현재 16 (h = 3)
		int nodeSize2 = 1 << (N + 1);// 현재 256 (N = 7)
		System.out.println(nodeSize1 + ":" + nodeSize2);
		node = new int[nodeSize1];// 16개의 노드를 만듦
		// this.input = input;
	}

	public int make(int cur, int left, int right) {
		if (left == right) {// 단말 노드일 경우
			return node[cur] = 1; // 1로 채움 //input[left];
		}
		int mid = (left + right) / 2;//
		return node[cur] = make(cur * 2, left, mid) + make(cur * 2 + 1, mid + 1, right);// 왼쪽 자식 노드와 오른쪽 자식 노드 만듦
	}

	public int remove(int cur, int left, int right, int rank) {
		if (left == right) {// 단말 노드일 경우
			node[cur] = 0;// 1을 지우고
			return left;// 그 단말노드를 리턴
		}
		int mid = (left + right) / 2;//
		int ret = 0;//
		if (rank <= node[cur * 2])// 구하고자 하는 순번이 왼쪽 서브트리의 구간합보다 작을 경우
			ret = remove(cur * 2, left, mid, rank);// 왼쪽 서브트리로 이동
		else// 구하고자 하는 순번이 왼쪽 서브트리의 구간합보다 큰 경우
			ret = remove(cur * 2 + 1, mid + 1, right, rank - node[cur * 2]);// 오른쪽 서브트리로 이동
		node[cur] = node[cur * 2] + node[cur * 2 + 1];// 구간합
		return ret;
	}
}

//288ms
public class A030_BJ1168_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		// BufferedReader로 입력받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// ex) 단말노드가 7개
		// int[] input = new int[N];

		// N크기의 배열을 생성한다.
		int K = Integer.parseInt(st.nextToken());// ex) 세번째 있는 것을 찾기
		int rank = K;// ex) rank가 3으로 시작
		// 1 ~ N까지 입력받는다.
//        for (int i = 0; i < N; i++) {
//            input[i] = 1;
//        }
		SegmentTree tree = new SegmentTree(N, null);// 세그먼트 트리 생성 // input);
		// tree.make(1, 0, N - 1);//ex) 0번 인덱스부터 6번 인덱스까지를 1로 채움
		tree.make(1, 1, N);// ex) 1번 인덱스부터 7번 인덱스까지를 1로 채움
		sb.append("<");
		for (int i = 0; i < N; i++) {
			// int removeRet = tree.remove(1, 0, N - 1, rank);//ex) 0번부터 6번까지를
			// sb.append(removeRet + 1);//인덱스가 0부터 시작하므로 +1
			int removeRet = tree.remove(1, 1, N, rank);// ex) 1번부터 7번까지를
			sb.append(removeRet);

			if (i < N - 1) {
				int size = (N - i - 1);// 뽑은 수를 제외하고 남은 양
				rank = (rank + K - 2) % size + 1;// +1은 모듈러 연산 보정
				/*
				 * rank=(rank+K-2)%(N-i-1)+1 ex) (3+3-2)%(7-0-1)+1=5
				 */
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
	}
}