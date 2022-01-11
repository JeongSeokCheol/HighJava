package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class gametime {

	public static void main(String[] args) {
     GameTimer gt = new GameTimer();
	
     //난수를 이용해서 컴퓨터의 가위 바위 보 정하기
    String[] data = {"가위","바위","보"};  
    //index ==> 0~2사이의 값을 가지고 빼내올 수 있음
    int index = (int)(Math.random()*3);//0~2사이의 난수를 만듬
    String com = data[index];
    
    //사용자의 가위 바위 보 입력 받기
    gt.start();//카운트 다운 시작
    
    String man = null;
    do {
    	man = JOptionPane.showInputDialog("가위바위보를 입력하세요");
    }while(!("가위".equals(man) || "바위".equals(man) || "보".equals(man)));
    GameTimer.inputCheck = true;
    
    //결과 판정하기
    String result = ""; //결과가 저장될 변수 선언
    
    String temp = man + com;
    
    switch(temp) {
    	case "가위가위":
    	case "바위바위":
    	case "보보": result = "비겼습니다."; break;
    	case "가위보":
    	case "바위가위":
    	case "보바위": result = "당신이 이겼습니다.";break;
    	default : result = "당신이 졌습니다.";break;
    		
    		
    }
    
    /*
    if(com.equals(man)){
    	result = "비겼습니다.";
    }else if((man.equals("가위") && com.equals("보")) || 
    		(man.equals("바위") && com.equals("가위")) || 
    		(man.equals("보") && com.equals("바위"))){
    	//다 사용자가 이기는 경우
    	result = "당신이 이겼습니다.";
    }else{
    	result = "당신이 졌습니다.";
    }
	*/
    
    
    
    
    //결과 출력
    System.out.println("결과 출력");
    System.out.println("컴퓨터 : " + com);
    System.out.println("사용자 : "+ man);
    System.out.println("결과 : "+ result);
  
    System.exit(0);
    
	}

}

//카운트 다운 스레드
class GameTimer extends Thread{
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		System.out.println("카운트 시작");
		
		for(int i=5; i>0; i--){
			if(inputCheck == true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("결과");
		System.out.println("시간초과로 당신이 졌습니다.");
	}
	
}