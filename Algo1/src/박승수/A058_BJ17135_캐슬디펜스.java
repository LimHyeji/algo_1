package 박승수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A058_BJ17135_캐슬디펜스 {
	static int Y;
	static int X;
	static int D;
	static int[] Archer = new int[3];
	static ArrayList<Monster> monsterList;
	static ArrayList<Monster> CopyMonsterList;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		Y = Integer.parseInt(temp[0]);
		X = Integer.parseInt(temp[1]);
		D = Integer.parseInt(temp[2]);
		
		monsterList = new ArrayList<Monster>();
		
		for (int y = 0; y < Y; y++) {
			temp = br.readLine().split(" ");
			for (int x = 0; x < X; x++) {
				if (Integer.parseInt(temp[x]) == 1) {
					monsterList.add(new Monster(y,x));
				}
			}
		}
		
		Combinations(0,0);
		System.out.println(answer);
	}
	
	public static int Attack() {
		int hap = 0;
		Monster[] temp = new Monster[3];
		for (int idx = 0; idx < 3; idx ++) {
			temp[idx]=findEnemy(Y,Archer[idx]);
		}
		//System.out.println(Arrays.toString(temp));
		for (int idx = 0; idx < 3; idx ++) {
			if (temp[idx].x != 999999) {
				for (int jdx = 0; jdx < CopyMonsterList.size();jdx++) {
					if (CopyMonsterList.get(jdx).x == temp[idx].x && CopyMonsterList.get(jdx).y == temp[idx].y) {
						hap += 1;
						CopyMonsterList.remove(jdx);
						jdx--;
						
					}
				}
			}
		}
		
		return hap;
		
	}
	
	public static Monster findEnemy(int y, int x) {
		Monster Target = new Monster(999999,999999);
		int minDis = Integer.MAX_VALUE;
		for (int idx = 0; idx < CopyMonsterList.size(); idx ++) { // 몬스터를 순회하면서
			int dis = canAttack(y,x,CopyMonsterList.get(idx).y,CopyMonsterList.get(idx).x);
			if (dis <= D) {
				if (dis == minDis) {
					if (Target.x < CopyMonsterList.get(idx).x) {
						continue;
					}else {
						Target = CopyMonsterList.get(idx);
					}
				}else if (dis < minDis) {
					Target = CopyMonsterList.get(idx);
					minDis = dis;
				}
			}
		}
		
		return Target;
		
	}
	
	public static int canAttack(int mx, int my, int ax, int ay) {
		return Math.abs(mx-ax) + Math.abs(my-ay);
	}
	
	public static void moveEnemy() {
		for (int idx = 0; idx < CopyMonsterList.size(); idx ++) {
			CopyMonsterList.get(idx).y += 1;
			if (CopyMonsterList.get(idx).y >= Y){
				CopyMonsterList.remove(idx);
				idx--;
			}
		}
	}
	
	public static int check() {
		int hap = 0;
		while (true) {
			if (CopyMonsterList.size() == 0) {
				break;
			}
			
			hap += Attack();
			moveEnemy();
		}
		return hap;
		
	}
	
	
	
	public static void Combinations(int v, int start) {
		if(v == 3) {
			CopyMonsterList = new ArrayList<Monster>();
			for (int idx = 0; idx < monsterList.size(); idx ++) {
				CopyMonsterList.add(new Monster(monsterList.get(idx).y,monsterList.get(idx).x));
			}
			//System.out.println(Arrays.toString(Archer));
			//System.out.println(CopyMonsterList.toString());
			//System.out.println(check());
			answer = Math.max(answer, check()); 
			return;
		}
		
		for (int i = start; i < X; i ++) {
			Archer[v] = i;
			Combinations(v+1,i+1);
		}
		
	}

}

class Monster{
	int y;
	int x;
	
	Monster(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Monster [y=" + y + ", x=" + x + "]";
	}
	
}
