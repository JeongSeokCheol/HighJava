package kr.or.ddit.basic;

public class ThreadTest18 {

	public static void main(String[] args) {
		DataBox dbox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dbox);
		ConsumerThread cth = new ConsumerThread(dbox);
		
		pth.start();
		cth.start();
	}

}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	// data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고,
	// data변수에 값이 있으면 해당 문자열을 반환한다.
	// 반환한 후에는 data변수를 null로 만든다.
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		String temp = data;
		System.out.println("쓰레드가 읽어간 데이터 : " + temp);
		data = null;
		
		notify();
		
		return temp;
		
	}
	
	// data변수에 값이 있으면 data변수의 값이 null이 될 때까지 기다린다.
	// data변수의 값이 null이 되면 새로운 문자열값을 data변수에 저장한다.
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch (Exception e) {

			}
		}
		
		this.data = data; // 데이터값 셋팅하기
		System.out.println("Thread에서 새로 저장한 데이터 : " + data);
		
		notify();
		
		
	}
	
}

// 데이터를 공급하는 쓰레드
class ProducerThread extends Thread{
	private DataBox dataBox;
	
	//생성자
	public ProducerThread(DataBox dataBox) {
		super();
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			dataBox.setData("data - " + i);
		}
		
	}
	
}

// 데이터를 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		super();
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			String temp = dataBox.getData();
		}
		
	}
}





