package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A062_BJ1759_암호만들기 {
	static int l, c;// 비밀번호의 길이와 주어진 문자의 개수
	static char vowel[] = { 'a', 'e', 'i', 'o', 'u' };// 최소 한개의 모음으로 구성해야 함
	static char base[];// 비밀번호를 구성할 문자들
	static StringBuilder str = new StringBuilder("");// 출력할 문자열
	static char pswd[];// 조합하는 비밀번호

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력받고

		l = Integer.parseInt(st.nextToken());// 비밀번호의 길이 입력
		c = Integer.parseInt(st.nextToken());// 입력받을 문자의 개수 입력
		base = new char[c];// c개의 문자를 저장할 base 배열 생성
		pswd = new char[l];// 총 l개의 문자로 비밀번호 구성

		st = new StringTokenizer(in.readLine());// 구성할 문자 입력받는 한 줄
		for (int i = 0; i < c; i++) {// 총 c개의
			base[i] = st.nextToken().charAt(0);// 입력받은 문자 저장
		}

		Arrays.sort(base);// 조합을 위해 오름차순 정리

		sol(0, 0, 0);// 카운트 0, 시작 인덱스 0, 모음 카운트 0
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void sol(int cnt, int start, int vCnt) {// vCnt는 모음개수로 0개여서는 안되고, l-2보다 커서도 안됨
		if (cnt == l) {// 비밀번호 길이 개수 만족 시
			if (vCnt > 0 && vCnt <= l - 2) {// 모음이 최소 1개, 자음이 최소 2개라면 유효한 값이므로
				for (int i = 0; i < l; i++) {// 그 문자들을
					str.append(pswd[i]);// 결과에 붙이기
				}
				str.append("\n");
			}
			return;// 재귀 종료
		}

		for (int i = start; i < c; i++) {// c개의 문자들을 순회하면서
			pswd[cnt] = base[i];// 비밀번호 문자를 만들고
			if (checkV(pswd[cnt])) {// 만든 비밀번호 문자가 모음이면
				sol(cnt + 1, i + 1, vCnt + 1);// 모음카운트를 1증가시키고 다음번호 탐색
			} else {// 만든 비밀번호 문자가 자음이면
				sol(cnt + 1, i + 1, vCnt);// 그대로 다음번호 탐색
			}

		}
	}

	static boolean checkV(char c) {// 해당 문자가 모음인지 확인하는 메소드
		for (int i = 0; i < vowel.length; i++) {
			if (vowel[i] == c)
				return true;
		}
		return false;
	}
}
