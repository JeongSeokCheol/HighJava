package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//현재 자기 자신 컴퓨터를 나타내는 방법
		//1) 원래의 IP주소 : 예) 192.168.32.3
		//2) 지정된 IP주소 : 127.0.0.1
		//3) 원래의 컴퓨터 이름 : 예) DESKTOP-HSQ8HE3
		//4) 지정된 컴퓨터의 이름 : localhost
		
		String serverIp = "localhost";
		System.out.println(serverIp + " 서버에 연결중입니다.");
		
		//서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		//Socket객체는 생성이 완료되면 해당 서버에 요청 신호를 자동으로 보낸다.
		Socket socket = new Socket(serverIp, 7777);
		
		//Socket객체 생성 이후의 내용은 서버와 연경이 완료된 이후에 실행되는 영역이다.
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		//서버에서 보내온 메세지를 받아서 출력하기
		
		//InputStream객체를 구한다. ==> Socket의  getInputStream()메서드 이용
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//메세지를 받기 (메세지를 읽는다.)
		System.out.println("서버에서 보내온 메세지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다.");
		
		//소켓과 스트림닫기
		socket.close();
		dis.close();
		
		
		
		
		
	}

}
