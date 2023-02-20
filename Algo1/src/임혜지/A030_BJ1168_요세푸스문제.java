package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
    private int[] node;
    //private int[] input;

    private int N;

    public SegmentTree(int N, int[] input) {
        int h=(int)Math.ceil(Math.log(N)/Math.log(2));//log(2)N : 자바에는 밑이 2인 로그 N의 값을 구하는 메소드가 없어서 logN/log2 한다
        int nodeSize1=(int)Math.pow(2, h+1);
        int nodeSize2=1 << (N + 1);
        System.out.println(nodeSize1+":"+nodeSize2);
        node = new int[nodeSize1];
        //this.input = input;
    }

    public int make(int cur, int left, int right) {
        if (left == right) {
            return node[cur] = 1; //input[left];
        }
        int mid = (left + right) / 2;
        return node[cur] = make(cur * 2, left, mid) + make(cur * 2 + 1, mid + 1, right);
    }

    public int remove(int cur, int left, int right, int rank) {
        if (left == right) {
            node[cur] = 0;
            return left;
        }
        int mid = (left + right) / 2;
        int ret = 0;
        if (rank <= node[cur * 2])
            ret = remove(cur * 2, left, mid, rank);
        else
            ret = remove(cur * 2 + 1, mid + 1, right, rank - node[cur * 2]);
        node[cur] = node[cur * 2] + node[cur * 2 + 1];
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
        int N = Integer.parseInt(st.nextToken());
        //int[] input = new int[N];

        // N크기의 배열을 생성한다.
        int K = Integer.parseInt(st.nextToken());
        int rank = K;
        // 1 ~ N까지 입력받는다.
//        for (int i = 0; i < N; i++) {
//            input[i] = 1;
//        }
        SegmentTree tree = new SegmentTree(N, null);// input);
        tree.make(1, 0, N - 1);
        sb.append("<");
        for (int i = 0; i < N; i++) {
            int removeRet = tree.remove(1, 0, N - 1, rank);
            sb.append(removeRet + 1);

            if (i < N - 1) {
                int size = (N - i - 1);
                rank = (rank + K - 2) % size + 1;
                sb.append(", ");
            }
        }
        sb.append(">");
        System.out.println(sb.toString());
    }
}