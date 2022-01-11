package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램
		
		//쓰레드를 사용하는 방법
		
		//방법1) => Thread클래스를 상속한 class를 작성한 후 class의 인스턴스를
		//         생성한 후, 이 인스턴스의 start()메서드를 호출해서 실행한다.
		
		MyThread1 th1 = new MyThread1();
		th1.start();
//		th1.run();
		
		//방법2) => Runnable인터페이스를 구현한 class를 작성한 후에 class의 인스턴스를
		//         생성한다.
		//         Thread객체를 생성할 때 생성자의 인수값으로 Runnable 인스턴스를 넣어준다.
		//         이 때 생성은 Thread객체의 Start()메서드를 호출해서 실행한다.

		MyRunner r = new MyRunner();
		Thread th2 = new Thread(r);
		th2.start();
		
		//방법3) => 익명 구현체를 이용하는 방법
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		
		Thread th3 = new Thread(r2);
		th3.start();
		
		
		System.out.println("main메서드 끝...");
		
	}

}

interface test{
	public void mytest(int x);
}


//방법1
class MyThread1 extends Thread{
	
	//run메서드를 재정의 한다.
	@Override
	public void run() {
		//run()메서드 안에서는 쓰레드가 처리해야 할 내용을 기술한다.
		for(int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간); ==> 주어진 시간동안 작업을 잠시 멈춘다.
				//                   ==> 시간은 밀리세컨드 단위를 사용한다. (즉, 1000ms == 1초)
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
}


//방법2
class MyRunner implements Runnable{
	//run()메서드 재정의
	@Override
	public void run() {
		for(int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}



