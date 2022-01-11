package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		//FileInputStream 을 이용한 파일 내용 읽기
		try {
			// 읽어올 파일 정보를 인수값을 받는 FilrInputStream 객체 생성
			
			
			// 방법 1
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// 방법2
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c; // 읽어온 데이터를 저장할 변수
			
			while((c = fin.read())!=-1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)c);
			}
			//작업 완료 후 스트림 닫기
			fin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
