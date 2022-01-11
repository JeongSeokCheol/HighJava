package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException{
		//InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		//www.naver.com의 IP정보 가져오기
		InetAddress naver = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name : " + naver.getHostName());
		System.out.println("Host Address : " + naver.getHostAddress());
		System.out.println("toString : " + naver.toString());
		System.out.println();
		
		//자신의 컴퓨터의 IP정보 가져오기
		InetAddress local = InetAddress.getLocalHost();
		System.out.println("내컴의 Host Name : " + local.getHostName());
		System.out.println("내컴의 Host Address : " + local.getHostAddress());
		System.out.println();
		
		//IP주소가 여러개인 호스트의 IP정보 가져오기
		InetAddress[] netArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress netip : netArr) {
			System.out.println(netip.toString());
		}
		System.out.println();
		
	}

}
