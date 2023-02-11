package 임혜지;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class A027_CompareTest {
	public static void main(String[] args) {
		/*
		 * ArrayList<E> 의 메소드 public void sort(Comparator<? super E> c)
		 */

		ArrayList<Integer> numList = new ArrayList<>();
		numList.add(new Integer(38));
		numList.add(new Integer(20));
		numList.add(new Integer(45));
		numList.sort((o1, o2) -> o1 - o2);//람다식 표현
										//o1-02 결과값이 양수이므로 오름차순
//		numList.sort(new Comparator<Integer>() {//외부심판
//			@Override//이름없는 클래스
//			public int compare(Integer o1,Integer o2) {
//				return o1-o2;
//			}
//		});
		System.out.println(numList);

		ArrayList<String> strList = new ArrayList<>();
		strList.add("전은수");
		strList.add("홍길동");
		strList.add("이영애");
		strList.sort((o1, o2) -> o1.compareTo(o2));
		System.out.println(strList);
		
		//-----------------------------------------------------------------------------
		ArrayList<Member> list = new ArrayList<>();
		list.add(new Member("전은수", 38));// -1 대입 시, 결과 오류 -> set 유효성 검사 -> 방법 변경(연산x)
		list.add(new Member("홍길동", 20));
		list.add(new Member("이영애", 45));

		System.out.println(list);
		list.sort((o1, o2) -> {
			// System.out.println(o1.age-o2.age);
			return o1.age - o2.age; // 오름차순 정렬
		});
		System.out.println(list);
		list.sort((o1, o2) -> {

			return o1.name.compareTo(o2.name);
		});
		System.out.println(list);

		Collections.sort(list);//
		System.out.println(list);
		
		//------------------------------------------------------------------------
		//그런데 나이에
		//다음과 같은
		//값을 주면
		//잘못된 결과가 나온다
		ArrayList<Member> list2 = new ArrayList<>();
		list2.add(new Member("전은수", -2147483647));// 먼저 -1을 줘서 확인해보자
		list2.add(new Member("홍길동", 1));
		list2.add(new Member("이영애", 45));

		/*
		 * System.out.println(list); list.sort(( o1, o2)->o1.age-o2.age);
		 * System.out.println(list); list.sort(( o1, o2)-> o1.name.compareTo(o2.name));
		 * System.out.println(list);
		 */

		Collections.sort(list2);
		System.out.println(list2);
	}

//	class A implements Comparator<String>{//외부심판
//		@Override
//		public int compare(String o1,String o2) {
//			return o1.compareTo(o2);//내부심판
//		}
//	}
}


class Member implements Comparable<Member> {
	String name;
	int age;

	public Member(String name, int age) {
		super();
		setName(name);
		setAge(age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + "]";
	}

//	@Override
//	public int compareTo(Member o) { //나이가 어린 직원부터 이름을 가나다 정렬하고자 할 때
//		if(this.age != o.getAge()) {
//			return this.age-o.getAge();
//		}
//		return this.name.compareTo(o.getName());
//	}

	// 이를 피하기 위한 방법 중 아래와 같이 < > == 와 같은 비교 연산자를 사용하는 것도 좋다.

	public int compareTo(Member o) {
		if (this.age > o.age)
			return 1;
		else if (this.age < o.age)
			return -1;
		else
			return 0;

	}

}