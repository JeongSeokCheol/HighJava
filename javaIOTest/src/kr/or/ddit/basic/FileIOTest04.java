package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		try {
//			System.out.println("아무거나 입력하세요 : ");
//			int c = System.in.read();
//			
//			System.out.println("입력값 : " + (char)c);
			
			//InputStreamReader => 바이트 기반의 스트림을 문자 기반의 스트림으로 변환해주는 보조스트림
			
			InputStreamReader isr = new InputStreamReader(System.in);
			
			//파일로 출력하기 위한 문자 기반의 스트림 생성
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");
			
			int c;
			
			System.out.println("아무내용이나 입력하세요(입력의 끝은 Ctrl + Z 입니다)");
			while((c=isr.read()) != -1) { //키보드로 입력한 값을 읽어 들인다.
				fw.write(c);  //콘솔로 입력 받은 데이터를 파일에 출력한다.
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
