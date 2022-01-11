package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

//이 쓰레드는 소켓을 통해서 메세지를 받아서 화면에 출력하는 역활을 담당한다.
public class Receiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		super();
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
}
