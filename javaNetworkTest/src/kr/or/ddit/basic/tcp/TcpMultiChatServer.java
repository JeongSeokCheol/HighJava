package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {

	//접속한 정보를 저장할 Map
	//          ==> key값 : 접속한 사람의 이름, value값 : 접속한 클라이언트의 Socket객체
	private Map<String, Socket> clientMap;
	
	//생성자
	public TcpMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}

	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
		
	}

	// 시작하는 메서드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작 되었습니다.");
			
			while(true) {
				socket = server.accept(); //클라이언트의 접속을 기다린다.
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다.");
				
				//데이터를 받아서 처리하는 쓰레드를 실행 시킨다.
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(server != null) try {server.close();} catch(Exception e) {}
		}
	}
	
	//clientMap에 저장된 모든 사용자에게 메세지를 전송하는 메서드
	private void sendToAll(String msg) {
		//clientMap인 데이터 개수만큼 반복처리
		for(String name : clientMap.keySet()) {
		
			try {
				DataOutputStream dos = new DataOutputStream(
						clientMap.get(name).getOutputStream());
				
				dos.writeUTF(msg);
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		
	}
	
	//Inner Class형태로 서버에서 클라이언트로 메세지를 전송하는 Thread를 작성한다.
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//수신용
				dis = new DataInputStream(socket.getInputStream());
				
				//송신용
				dos = new DataOutputStream(socket.getOutputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) {
					//클라이언트는 접속이 완료되면 최초로 사용자의 이름을 전송한다.
					//이 때 이 이름이 중복되는지 여부를 feedback으로 클라이언트에게 보내준다.
					name = dis.readUTF();
					
					if(clientMap.containsKey(name)) {//이름이 중복될 때...
						dos.writeUTF("이름중복");
						
					}else { //이름이 중복되지 않을때
						dos.writeUTF("OK");
						break; //반복문 탈출
					}
				}
				
				//대화명을 이용해서 전체 클라이언트에게 대화방 참여 메세지를 보낸다.
				sendToAll("[" + name + "] 님이 입장하셨습니다...");
				
				clientMap.put(name, socket); //대화방 목록에 추가한다.
				
				System.out.println("현재 서버 접속자 수  : " + clientMap.size());
				
				//현재 클라이언트가 보낸 메세지를 전체 클라이언트에게 보내는 작업을 진행한다.
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				// 이 finally절이 실행된다는 것은 현재 클라이언트가 접속을 종료했다는 의미이다.
				sendToAll("[" + name + "]님이 대화방을 나갔습니다...");
				
				//대화방 목록에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println("현재 서버 접속자 수  : " + clientMap.size());
				
				
			}
			
		}
		
		
		
	}
	
	
}
