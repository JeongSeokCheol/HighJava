package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer01 {

	public static void main(String[] args) throws IOException {
		//TCP소켓 총신을 하기 위해 ServerSocket객체를 생성한다.
		ServerSocket server = new ServerSocket(7777); 
		
		System.out.println("접속을 기다립니다..");
		
		//accept() 메서드
		//    ==> 클라이언트에서 연결요청이 올 때까지 계속 기다린다.
		//    ==> 클라이언트의 연경 요청이 오면 새로운 Socket객체를 생성해서
		//        클라이언트의 Socket과 연결한다.
		Socket socket = server.accept();
		
		//accept()메서드 이후의 소스 내용은 연결이 완료되어야 실행된다.
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();
		
		System.out.println("접속한 상대방의 정보 ");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();

		System.out.println("접속된 본인의 정보");
		System.out.println("IP 주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		//상대방에게 메세지 보내기 ==> OutputStream객체를 구성해서 전송한다.
		// (Socket의 getOutputStream()메서드를 이용한다.)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		//상대방에세 메세지 보내기 (==메세지를 출력한다.)
		dos.writeUTF("환영합니다. 어서오세요...");
		
		System.out.println("메세지를 전송했습니다.");
		 
		//스트림과 소켓 닫기
		dos.close();
		socket.close();
		server.close();
		
		
	}

}
