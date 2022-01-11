package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;



public class TcpMultiChatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TcpMultiChatClient().clientStart();
	}
	
	public void clientStart() {
		Socket socket = null;
		
		try {
		socket = new Socket("192.168.32.72" , 7777);
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		//메세지 송신용 쓰레드 생성
		ClientSender sender = new ClientSender(socket);
		
		//메세지 수신용 쓰레드 생성
		ClientReceiver receiver = new ClientReceiver(socket);
		
		
		sender.start();
		receiver.start();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//-----------------------------------------------------------
	// 메세지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name;
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				//수신용
				dis = new DataInputStream(socket.getInputStream());
				
				//송신용
				dos = new DataOutputStream(socket.getOutputStream());
				
				if(dos != null) {
					//클라이언트는 프로그램이 처음 시작되면 자신의 대화명을 입력 받아 서버로 전송하고
					//대화명의 중복 여부를 feedback으로 받아서 확인한다.
					//중복되면 이 작업을 반복한다.
					
					System.out.println("대화명 : ");
					String name = scan.nextLine();
					
					while(true) {
						dos.writeUTF(name); //대화명 전송
						
						String feedback = dis.readUTF(); //대화명 중복 여부를 받는다.
						
						if("이름중복".equals(feedback)) { //대화명이 중복될 때
							System.out.println(name + "은 대화명이 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.println("대화명 : ");
							name = scan.nextLine();
							
						}else { // 대화명이 중복되지 않을 때
							this.name = name;
							System.out.println(name + "이라는 이름으로 대화방에 입장했습니다...");
							break;
							
						}
						
					}
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		} //생성자 끝..
		
		
		@Override
		public void run() {
			try {
				while(dis != null) {
					//키보드로 입력한 메세지를 서버로 전송한다.
					dos.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//---------------------------------------------------------
		//메세지 수신용 쓰레드
		
	}
	
		class ClientReceiver extends Thread{
			private Socket socket;
			private DataInputStream dis;
			
			//생성자
			public ClientReceiver(Socket socket) {
				this.socket = socket;
				try {
					dis = new DataInputStream(socket.getInputStream());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			@Override
			public void run() {
				try {
					while(dis != null) {
						//서버가 보내준 내용을 받아서 화면에 출력한다.
						System.out.println(dis.readUTF());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
		}
		
	}
	
	
	

