package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("d:/d_other/test.dat");
			
			//자료형 단위로 출력할 보조스트림인 DataOutoutStream객체 생성
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(200);           //정수형으로 데이터 출력
			dos.writeFloat(123.45f);     //실수형(float)으로 데이터 출력
			dos.writeBoolean(false);     //논리형으로 데이터 출력
			dos.writeUTF("ABCDabcd");    // 문자열 형식으로 데이터 출력
			System.out.println("출력완료");
			
			dos.close();  //스트림 닫기
			//00000000 00000000 00000000 11001000
			//0 0000000 00000000 00000000 11001000
			//부호 지수부 가수부
			//123.456 => 1.23456 * 10^2
			//--------------------------------------------
			
			//출력했던 자료 읽어오기
			FileInputStream fis = new FileInputStream("d:/d_other/test.dat");
			DataInputStream dis =  new DataInputStream(fis);
			
			//DataInpoutStream 으로 데이터를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어야 한다.
			System.out.println("정수형 : " + dis.readInt());
			System.out.println("실수형 : " + dis.readFloat());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("문자열 : " + dis.readUTF());
			
			System.out.println();
			System.out.println("열기 작업 완료...");
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}

}
