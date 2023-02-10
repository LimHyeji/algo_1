package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 1. 왼 오 괄호 개수 일치
 * 2. 왼쪽 먼저
 * 3. 포함관계 일치(pair 일치)
 */

public class A023_SWEA1218_괄호짝짓기_배열활용 {
	static List<Character> check;//괄호 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));//buffer로 입력받기
		for(int test_case=1;test_case<=10;test_case++) {
			check=new ArrayList<>();//리스트 생성
			int res=1;//유효성 여부 (1-유효함, 0-유효하지않음)
			int len=Integer.parseInt(in.readLine());//테스트케이스의 길이 입력
			String str=in.readLine();//괄호 문자열 입력
			char [] strArr=str.toCharArray();//문자 검사를 위해 char 배열로 바꿈
			for(int i=0;i<len;i++) {//문자 배열 처음부터 끝까지 돌면서
				if(strArr[i]=='{'||strArr[i]=='['||strArr[i]=='('||strArr[i]=='<') {//등장한 괄호가 왼쪽 괄호이면
					check.add(strArr[i]);//스택에 저장
				}
				else {//등장한 괄호가 오른쪽 괄호이면
				if(check.get(check.size()-1)=='{'&&strArr[i]=='}'||check.get(check.size()-1)=='['&&strArr[i]==']'||check.get(check.size()-1)=='('&&strArr[i]==')'||check.get(check.size()-1)=='<'&&strArr[i]=='>') {//짝이 맞으면
						check.remove(check.size()-1);//저장했던 왼쪽 괄호 꺼냄
					}
				else if(check.isEmpty()) {//스택이 비어있으면 왼쪽 괄호 개수가 적은 것이므로
					res=0;//유효하지 않음
					break;//반복문 탈출
				}
				else {//일치하지 않으면
					res=0;//유효하지 않음
					break;//반복문 탈출
				}
				
				}
			}
			if(!check.isEmpty()) {//반복문 종료했는데 스택이 비어있지않으면 오른쪽 괄호 개수가 적은 것이므로
				res=0;//유효하지 않음
			}
			
			System.out.println("#"+test_case+" "+res);//결과 출력
		}
	}
}