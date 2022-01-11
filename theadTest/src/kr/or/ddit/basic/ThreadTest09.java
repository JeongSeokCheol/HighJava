package kr.or.ddit.basic;


//쓰레드의 상태를 출력하는 프로그램

public class ThreadTest09 {

	public static void main(String[] args) {
		TargetThread ta = new TargetThread();
		
		StatePrintThread th = new StatePrintThread(ta);
		
		th.start();
	}

}

//쓰레드의 상태를 검사할 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i = 1L; i <=20_000_000_000L; i++) { }//시간지연용
		try {
			Thread.sleep(1500);
		} catch (Exception e) {

		}
		for(long i = 1L; i <=20_000_000_000L; i++) { }//시간지연용
		
	}
	
}

//TargetThread의 상태를 검출해서 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;
	
	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	
	}
	
	@Override
	public void run() {
		while(true) {
			//쓰레드의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			// TargetThread의 상태가 NEW상태이면
			if(state == Thread.State.NEW) {
				target.start();
			}
			
			// Target Thread가 종료 상태이면
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	
	}
	
	
	
	
}