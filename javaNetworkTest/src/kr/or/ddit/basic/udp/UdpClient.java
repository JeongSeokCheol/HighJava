package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//송신용, 수신용 패킷 객체 변수 선언
		DatagramPacket inpacket ,outpacket;
		
		//수신 받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			//Socket 객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			//접속할 곳의 주소 정보 생성
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				System.out.println();
				//전송할 메세지 입력
				System.out.println("보낼 메세지 입력 : ");
				String msg = scan.nextLine();
				
				//전송할 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"), msg.getBytes("utf-8").length, address, 8888);
				
				//전송
				socket.send(outpacket);
				
				
				
				if("/end".equals(msg)) { // 메세지 전송 중지 여부 검사
					break;
				}
				
				//------------------------------------------------------
				
				//서버가 응답으로 보낸 메세지를 받아서 출력하기
				
				//수신용 패킷 객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				//수신
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 데이터 : " + new String(bMsg,0,inpacket.getLength(),"utf-8"));
				
				System.out.println();
				
			}
			
			System.out.println("클라이언트...통신 끝...");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
