package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 입력과 출력을 담당할 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		//read()메서드를 이용한 입력과 write(int 값)메서드를 이용한 출력하기 예제
		
		int data; //읽어온 자료가 저장될 변수
		
		//read()메서드는 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1) { // 자료 읽기
			output.write(data); // 스트림으로 자료 출력
			
		}
		
		//출력된 스트림 값을 배열로 변환해서 저장하기
		outSrc = output.toByteArray();
		
		// 입출력 작업이 완료되면 사용했던 스트림을 닫아준다.
		
		try {
			input.close();
			output.close();
		} catch (IOException e) {
		}
		
		System.out.println(" inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		
	}
	
}
