package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest02 {

	public static void main(String[] args) {
		//디렉토리(폴더) 만들기
		/*
		  - mkdir() ==> File객체의 경로중 마지막 위치의 디렉토리를 만든다.
		            ==> 반환값 : 만들기 성공 : true , 실패 : false
		            ==> 증간 부분의 경로가 모두 만들어져 있어야 한다.
		            
		  - mkdirs() ==> 중간부분의 경로가 없으면 중간 부분의 경로도 같이 만들어 준다.
		 */
		
		File file = new File("d:/D_Other/연습용");
		System.out.println(file.getName() + "의 존재 여부 : " + file.exists());
		
		if(file.mkdir()) {
			System.out.println(file.getName() + " 만들기 성공~~~~~");
		}else {
			System.out.println(file.getName() + " 만들기 실패!!!!");
		}
		
		File file2 = new File("d:/D_Other/test/java/src");
		if(file2.mkdirs()) {
			System.out.println(file2.getName() + " 만들기 성공~~~~~");
		}else {
			System.out.println(file2.getName() + " 만들기 실패!!!!");
		}
		
		
		// 파일 만들기
		File file3 = new File("d:/d_other/myfile.txt");
		System.out.println(file3.getName() + "의 존재 여부 : " + file3.exists());
		
		try {
		if(file3.createNewFile()) {
			System.out.println(file3.getName() + "파일 만들기 성공~~~");
		}else {
			System.out.println(file3.getName() + "파일 만들기 실패!!!");
		}
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
		
		
	}

}
