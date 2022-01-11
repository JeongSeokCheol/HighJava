package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
//이 쓰레드는 소켓을 통해서 메세지를 보내는 역활만 담당한다.
public class Sender extends Thread{

	private Socket socket;
	private DataOutputStream dos;
	private Scanner scan;
	
	private String name;
	
	//생성자
	public Sender(Socket socket) {
		super();
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.println("이름 입력 : ");
		name = scan.nextLine();
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		
		} catch (IOException e) {
			
		
		}
	}
	
	@Override
	public void run() {
		while(dos != null) {
			try {
				dos.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {

			} 	
			
		}
	
	}
		
		

}
