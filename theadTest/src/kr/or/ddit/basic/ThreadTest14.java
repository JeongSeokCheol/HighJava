package kr.or.ddit.basic;

/*
   쓰레드에서 객체를 공통으로 사용하는 예제
  
  - 원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
  - 원주율울 저장하는 객체가 필요하다.
  - 이 객체를 두 쓰레드가 공통으로 사용해서 처리한다.
  - 
  
 */
public class ThreadTest14 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체를 생성한다.
		ShareData sd = new ShareData();
		
		//쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPIThread calc = new CalcPIThread();
		calc.setSd(sd);
		
		PrintPIThread print = new PrintPIThread(sd);
	
		
		calc.start();
		print.start();
		
		
	}

}

//원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData{
	public double result; //계산된 원주율이 저장될 변수
	
	public boolean isOk = false; // 계산이 완료되었는지 여부를 나타내는 변수
	
	
	
}



//원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;
	
	public void setSd(ShareData sd) {
		
		this.sd = sd;
	}
	
	@Override
	public void run() {
		//원주율을 계산하는 일을 수행한다.
		
		
		/* 
		      원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 -.... ) *4
		    
		    1 - 3 + 5 - 7 + 9
		    
		    
		 */
		
		double sum = 0.0;
		for(int i = 1; i <= 1000000000; i+=2) {
			if((i / 2) % 2 == 0) {
				sum += 1.0/i;
			}else {
				sum -= 1.0/i;
			}
		}
		
		//계산이 완료되면 계산된 결과를 ShareData 객체에 저장한다.
		sd.result = sum *4;
		sd.isOk = true;
	}
	
}

// 계산이 완료되면 계산된 원주율을 출력하는 쓰레드 
class PrintPIThread extends Thread{
	private ShareData sd;
	
	//생성자
	public PrintPIThread(ShareData sd) {
		super();
		this.sd = sd;
	}
	@Override
	public void run() {
		// 계산이 완료될 때까지 기다리다가 계산이 완료되면 출력한다.
		while(!sd.isOk) {
			Thread.yield();//양보
			
		}
		
		// 이 부분은 반복문을 탈출한 후에 처리된다.
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println();
		System.out.println("PI : " + Math.PI);
	}
}




