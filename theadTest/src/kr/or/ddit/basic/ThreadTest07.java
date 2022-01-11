package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
   컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
   
   컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
   사용자의 가위 바위 보는 showInputDialog()메서드로 받는다.
   
   입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
  5초안에 입력이 없으면 진것으로 처리한 후 프로그램을 종료한다.
  
  5초안에 입력 있으면 승패를 구해서 출력한다.
  
   결과 예시)
  1) 5초안에 입력을 못했을 때
         --결  과--
           시간 초과로 당신이 졌습니다.
  
  2) 5초안에 입력 되었을 때
         --결  과--
         컴퓨터 : 가위
         사용자 : 바위
         결 과 : 당신이 이겼습니다. 
 */
public class ThreadTest07 {

	public static void main(String[] args) {
	
		Thread th1 = new Computer();
		Thread th2 = new User();
		Thread th3 = new Count();
		
		th1.start();
		th2.start();
		th3.start();
		
		
	}

}

class Computer extends Thread{
	public static int random = (int)(Math.random()*3);  
	public static String com;
	
	@Override
		public void run() {
		switch(random) {
		case 0: com = "가위";break;
		case 1: com = "바위";break;
		case 2: com = "보";break;
		}
//			if(random == 0) {
//				com = "가위";
//			}else if(random == 1){
//				com = "바위";
//			}else {
//				com = "보";
//			}
		
	}
}


class Count extends Thread{
	@Override
	public void run() {
		
		
		for(int i = 5; i >= 1; i--) {
			if(User.inputCheck == true) {
				
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}

class User extends Thread{
	public static boolean inputCheck = false;
	
	
	@Override
	public void run() {
		String user = JOptionPane.showInputDialog("가위 바위 보 중 입력하세요");
		String com = Computer.com;
		inputCheck = true;
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
		if(com.equals(user)){
			System.out.println("결과: 비겼습니다.");
		}else if(com.equals("가위") && user.equals("보")) {
			System.out.println("결과: 컴퓨터가 이겼습니다.");
		}else if(com.equals("가위") && user.equals("바위")) {
			System.out.println("결과: 당신이 이겼습니다.");
		}else if(com.equals("바위") && user.equals("보")) {
			System.out.println("결과: 당신이 이겼습니다.");
		}else if(com.equals("바위") && user.equals("가위")) {
			System.out.println("결과: 컴퓨터가 이겼습니다.");
		}else if(com.equals("보") && user.equals("바위")) {
			System.out.println("결과: 컴퓨터가 이겼습니다.");
		}else if(com.equals("보") && user.equals("가위")) {
			System.out.println("결과: 당신이 이겼습니다.");
		}
	}
}