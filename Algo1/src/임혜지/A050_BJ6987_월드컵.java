package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Team {// 팀의 승/무/패 데이터를 담은 클래스
	int win, draw, defeat;// 승리 수, 무승부 수, 패배 수

	Team(int win, int draw, int defeat) {// 클래스 생성자
		this.win = win;
		this.draw = draw;
		this.defeat = defeat;
	}
}

public class A050_BJ6987_월드컵 {
	static int[] teamA = new int[15];// 이쪽이 A팀일 경우
	static int[] teamB = new int[15];// 상대 B팀
	static List<Team> test;// 시뮬레이션할 경우
	static List<Team> round;// 하나의 라운드에 대한 6개 팀의 정보를 담을 리스트(입력값)
	static boolean flag;// 재귀를 중단시킬 수 있는 플래그

	public static void main(String[] args) throws IOException {
		// 승부를 겨룰 조합 (6C2 = 총 15가지)
		int idx = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				teamA[idx] = i;
				teamB[idx++] = j;
			}
		}

		// ---------------------------------------------------------------------------------

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력

		for (int i = 0; i < 4; i++) {// 총 네 개의 테스트 케이스
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");// 총 네 줄을 입력
			test = new ArrayList<>();
			round = new ArrayList<>();
			flag = false;
			for (int j = 0; j < 6; j++) {// 총 6개의 팀에 대해
				int win = Integer.parseInt(st.nextToken());// 승리 개수 입력
				int draw = Integer.parseInt(st.nextToken());// 무승부 개수 입력
				int defeat = Integer.parseInt(st.nextToken());// 패배 개수 입력

				round.add(new Team(win, draw, defeat));// 팀에 대한 정보 저장
				test.add(new Team(0, 0, 0));// 시뮬레이션할 팀도 추가 저장
			}
			// 입력 끝
			// -----------------------------------------------------------------------------

			sol(0);// 재귀 호출
			if (flag)// 재귀 중단하고 나온 플래그가 true이면
				System.out.print(1 + " ");// 가능한 결과
			else// false이면
				System.out.print(0 + " ");// 불가능한 결과임을 의미

		}

	}

	static boolean check() {// 재귀를 중단시킬 수 있는 조건을 메소드로 생성
		for (int i = 0; i < 6; i++) {// 6개 팀에 대해
			if (test.get(i).win > round.get(i).win || test.get(i).draw > round.get(i).draw// 시뮬레이션한 승/무승부/패 횟수가 지정 횟수를
																							// 초과할 경우
					|| test.get(i).defeat > round.get(i).defeat) {
				return false;// 재귀 돌지않도록 false 호출
			}
		}
		return true;// 그 외의 경우에는 재귀를 돎
	}

	static void sol(int cnt) {
		if (flag)// 재귀를 중단시키는 flag가 이미 true라면
			return;// 재귀 종료

		if (cnt == 15) {// 총 15번의 경기를 마쳤을때
			for (int i = 0; i < 6; i++) {// 6개 팀에 대해
				if (test.get(i).win != round.get(i).win || test.get(i).draw != round.get(i).draw// 시뮬레이션한 수와 입력받은 수가 다를
																								// 경우
						|| test.get(i).defeat != round.get(i).defeat) {
					return;// 재귀 종료하며 다른 재귀를 돌 수 있음
				}
			}
			flag = true;// 모두 수가 같은 경우, 가능한 결과이므로 flag를 true로 바꾸고
			return;// 재귀 종료
		}

		if (check()) {// 모든 check를 통과한 경우
			test.get(teamA[cnt]).win++;// A팀이 이기고
			test.get(teamB[cnt]).defeat++;// B팀이 지면서
			sol(cnt + 1);// 다음 경기에 대한 재귀 호출
			test.get(teamA[cnt]).win--;// A팀 이긴 횟수 원상복구
			test.get(teamB[cnt]).defeat--;// B팀 진 횟수 원상복구

			test.get(teamA[cnt]).draw++;// A팀과 B팀이 비긴 경우 횟수 증가
			test.get(teamB[cnt]).draw++;// A팀과 B팀이 비긴 경우 횟수 증가
			sol(cnt + 1);// 다음 경기에 대한 재귀 호출
			test.get(teamA[cnt]).draw--;// A팀 비긴 횟수 원상복구
			test.get(teamB[cnt]).draw--;// B팀 비긴 횟수 원상복구

			test.get(teamB[cnt]).win++;// B팀이 이기고
			test.get(teamA[cnt]).defeat++;// A팀이 지면서
			sol(cnt + 1);// 다음 경기에 대한 재귀 호출
			test.get(teamB[cnt]).win--;// B팀 이긴 횟수 원상복구
			test.get(teamA[cnt]).defeat--;// A팀 진 횟수 원상복구
		}
	}

}
