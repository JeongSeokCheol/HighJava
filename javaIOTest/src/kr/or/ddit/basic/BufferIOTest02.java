package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferIOTest02 {

	public static void main(String[] args) {
		//문자 기반의 Buffered스트림 사용예제
		
		try {
			//이클립스에서의 자바 프로그램이 실행되는 현재 위치는
			//해당 프로잭트 폴더가 현재 위치가 된다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			
			//문자 기반의 Buffered스트림은 데이터를 줄 간위로 읽어 올 수 있다.
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			
		
			for(int i =1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n" , i , temp);
				
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
