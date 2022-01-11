package kr.or.ddit.basic;

// 데몬 쓰레드 연습 ==> 자동 저장하는 쓰레드

public class ThreadTest08 {

	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		//데몬 쓰레드로 실행하기 ==> 반드시 start()메서드를 호출하기 전에 설정해야 한다.
		autoSave.setDaemon(true);
		
		autoSave.start();
		
		try {
			for(int i = 1; i <=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

//자동 저장하는 쓰레드(3초에 한번씩 자동으로 저장하는 쓰레드)
class AutoSaveThread extends Thread{
	//작업 내요을 저장하는 메서드
	public void save() {
		System.out.println("작업내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			save();
		}
	}
}