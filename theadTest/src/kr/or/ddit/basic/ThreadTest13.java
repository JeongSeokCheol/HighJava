package kr.or.ddit.basic;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
 * 10마리의 말들이 경주하는 경마 프로그램을 작성하시오.
 * 
 * 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
 * 이클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 * 그리고, 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다. (Comparable인터페이스 구현)
 * 
 * 경기 구간은 1~50 구간으로 되어있다.
 * 
 * 경기 중 중간 중간에 각 말들으리 위치를 나타내시오
 * 
 * 예)
 * 01번말 --->---------------------------
 * 02번말 ------>------------------------
 * 03번말 ---->--------------------------
 * ...
 * 10번말 ------------>------------------
 * 
 * 경기가 끝나면 등수 순으로 출력한다.
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("01번말"),	
			new Horse("02번말"),	
			new Horse("03번말"),	
			new Horse("04번말"),	
			new Horse("05번말"),	
			new Horse("06번말"),	
			new Horse("07번말"),	
			new Horse("08번말"),	
			new Horse("09번말"),	
			new Horse("10번말")	
		};
		
		GameState gs = new GameState(horses);
		
		for(Horse h : horses) {
			h.start();
		}
		gs.start();
		
		//----------------------------
		for(Horse h : horses) {
			try {
				h.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		try {
			gs.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//------------------------------
		System.out.println();
		System.out.println("경기 끝..");
		
		//등수의 오름차순으로 정렬하기
		Arrays.sort(horses);
		
		for(Horse h : horses) {
			System.out.println(h);
		}
		
		
	}
	
	
	
}




class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0;
	
	private String horseName;  // 말이름
	private int rank;          //등수
	private int position;      //현재위치
	
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다.";
	}      
	
	//등수의 오름차순
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++){
			this.position = i;
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}
		
		//한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다.
		currentRank ++;
		this.rank = currentRank;
	}
	
}
 

class GameState extends Thread{
	private Horse[] horse;

	public GameState(Horse[] horse) {
		super();
		this.horse = horse;
	}
	
	@Override
	public void run() {
		while(true) {
			//모든 말들의 경기가 종료되었는지 여부를 검사한다.
			if(Horse.currentRank == horse.length) {
				break;
			}
			for(int i = 1; i <= 10; i ++) {
				System.out.println();
			}
			for(int i = 0; i < horse.length; i++) {
				System.out.print(horse[i].getHorseName() + " : ");
				
				for(int j = 1; j <= 50; j++) {
					if(horse[i].getPosition() == j) {
						System.out.print(">");
					}else {
					System.out.print("-");
					}
					
				}
				System.out.println(); // 줄바꿈
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
	
	
	
}







