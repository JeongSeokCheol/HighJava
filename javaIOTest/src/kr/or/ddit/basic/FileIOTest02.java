package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		//파일로 데이터 출력하기
		
		try {
			//파일 출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/out.txt");
			
			for(char ch ='A' ; ch <= 'Z'; ch++) {
				fout.write(ch);// ch변수의 값을 파일로 출력한다.
			}
			System.out.println("출력 작업 완료...");
			
			fout.close(); // 스트림 닫기
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
