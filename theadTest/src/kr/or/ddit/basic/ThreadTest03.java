package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//쓰레드가 수행되는 시간을 체크해 보자
		Thread th = new Thread(new TestRunner());
		
		//1970년 1월 1일 0시0분0초(표준시간)로부터 경과한 시간을 밀리세컨드 단위(1/1000초)로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
		th.join(); // 현재 실행중인 쓰레드에서 대상이 되는 쓰레드(현재변수가 th인 쓰레드)가
		           // 종료될때까지 기다린다.
			
		} catch (InterruptedException e) {

		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime)/1000.0);
		
	}

}


class TestRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0;
		for(int i =1; i <=1_000_000_000L; i++) {
			sum += i;
			
		}
		System.out.println("합계 : " + sum);
	}
	
}