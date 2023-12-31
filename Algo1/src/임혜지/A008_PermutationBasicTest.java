package 임혜지;

import java.util.Arrays;

public class A008_PermutationBasicTest {
    
    // 1,2,3
    // 3자리수 순열
    // 3P3 = 3!
    
    static int N=3,R=3;
    static int[] numbers; 
    static boolean[] isSelected;
    static int totalCount;
    
    public static void main(String[] args) {
        numbers = new int[R];
        isSelected = new boolean[N+1]; // 1부터 N까지의 수의 선택여부 저장 
        permutation(0);
        System.out.println("총 경우의 수 : "+totalCount);
    }
    
    public static void permutation(int cnt) {
        if(cnt==R) {
            totalCount++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for(int i=1; i<=N; ++i) {
            if(isSelected[i]) continue;
            numbers[cnt] = i; 
            isSelected[i] = true;
            permutation(cnt+1);
            isSelected[i] = false;
        }
    }
}